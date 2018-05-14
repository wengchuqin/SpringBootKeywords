package top.chuqin.keywords.service;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.chuqin.keywords.constant.Constant;
import top.chuqin.keywords.domain.ExtractKeywordResult;
import top.chuqin.keywords.domain.Summary;
import top.chuqin.keywords.repository.SummaryRepository;
import top.chuqin.keywords.vo.Prf1;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AnsjSegService implements IExtractKeyword{
    private static Logger LOGGER = LoggerFactory.getLogger(AnsjSegService.class);

    @Autowired
    private SummaryRepository summaryRepository;

    public ExtractKeywordResult extract(Long summaryId) {
        Summary summary = summaryRepository.findOne(summaryId);
        return extract(summary);
    }

    @Override
    public ExtractKeywordResult extract(Summary summary) {
        KeyWordComputer kwc = new KeyWordComputer(summary.getKeywords().size());
        String title = "";
        List<String> list = new ArrayList<>();
        kwc.computeArticleTfidf(title, summary.getSummary()).stream().forEach(kw -> list.add(((Keyword) kw).getName()));

        Prf1 prf1 = new Prf1(summary.getKeywords(), list);
        ExtractKeywordResult result = new ExtractKeywordResult(summary.getId(), ExtractKeywordResult.AlgorithmEnum.TF_IDF,
                String.join(Constant.SEPARATOR, list), prf1);
        LOGGER.debug("extract:" + result);
        return result;
    }


}
