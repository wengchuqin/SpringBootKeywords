package top.chuqin.keywords.service;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.chuqin.keywords.crawler.MyCrawler;
import top.chuqin.keywords.domain.ExtractKeywordResult;
import top.chuqin.keywords.domain.Summary;
import top.chuqin.keywords.repository.ExtractKeywordResultRepository;
import top.chuqin.keywords.repository.SummaryRepository;
import top.chuqin.keywords.utils.ApplicationContextUtil;
import top.chuqin.keywords.vo.AlgorithmCompareVo;
import top.chuqin.keywords.vo.AnalyzationVo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnalyzationService {
    private static Logger LOGGER = LoggerFactory.getLogger(AnalyzationService.class);
    private long total;
    private long done;
    private boolean run;

    @Autowired
    private SummaryRepository summaryRepository;
    @Autowired
    private ExtractKeywordResultRepository extractKeywordResultRepository;
    @Autowired
    private MyCrawler myCrawler;
    @Autowired
    private ApplicationContextUtil applicationContextUtil;


    public AnalyzationVo analyzation(Long id) {
        Summary summary = summaryRepository.findOne(id);

        AnalyzationVo analyzationVo = new AnalyzationVo();

        List<String> termList = new ArrayList<>();
        ToAnalysis.parse(summary.getSummary()).getTerms().forEach(term -> termList.add(term.getName()));
        analyzationVo.setTerms(termList);


        List<String> keywords = new ArrayList<>();
        KeyWordComputer kwc = new KeyWordComputer(summary.getKeywords().size());
        Collection<Keyword> result = kwc.computeArticleTfidf(null, summary.getSummary());
        result.stream().forEach(keyword -> keywords.add(keyword.getName()));
        analyzationVo.setAnsjKeywords(keywords);

        return analyzationVo;
    }

    /**
     * 重新计算关键词的性能.
     * 会关闭爬虫
     */
    public void reAnalyze(){
        if(run == true){
            LOGGER.debug("已经在计算关键词了");
            return;
        }
        new Thread(() -> {
            myCrawler.stop();
            extractKeywordResultRepository.deleteAll();
            run = true;

            Collection<IExtractKeyword> services = applicationContextUtil.getApplicationContext().getBeansOfType(IExtractKeyword.class).values();
            LOGGER.debug("IExtractKeyword实现类数量:[{}]", services.size());
            List<Summary> all = summaryRepository.findAll();
            total = services.size() * all.size();

            all.stream().forEach(summary -> {
                LOGGER.debug("提取关键词,{}", summary.getSummary());

                services.forEach(iExtractKeyword -> {
                    ExtractKeywordResult result = iExtractKeyword.extract(summary);
                    extractKeywordResultRepository.save(result);
                    total++;
                });
            });
            LOGGER.debug("关键词计算完毕");
            run = false;
        }).start();

    }

    public List<AlgorithmCompareVo> calcAlgorithmCompareVo(){
        List<AlgorithmCompareVo> ret = new ArrayList<>();

        for(ExtractKeywordResult.AlgorithmEnum e : ExtractKeywordResult.AlgorithmEnum.values()){
            List<ExtractKeywordResult> list = extractKeywordResultRepository.findAllByAlgorithm(e.name());
            MutableDouble p = new MutableDouble(0);
            MutableDouble r = new MutableDouble(0);
            MutableDouble f1 = new MutableDouble(0);

            list.stream().forEach(extractKeywordResult -> {
                p.add(extractKeywordResult.getP());
                r.add(extractKeywordResult.getR());
                f1.add(extractKeywordResult.getF1());
            });

            AlgorithmCompareVo vo = new AlgorithmCompareVo(
                    p.getValue() / list.size(),
                    r.getValue() / list.size(),
                    f1.getValue() / list.size(),
                    e.name()
            );
            ret.add(vo);
        }

        return ret;
    }



}
