package top.chuqin.keywords.service;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import top.chuqin.keywords.domain.Summary;
import top.chuqin.keywords.repository.SummaryRepository;
import top.chuqin.keywords.vo.AnalyzationVo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalyzationService {
    private static Logger LOGGER = LoggerFactory.getLogger(AnalyzationService.class);

    @Autowired
    private SummaryRepository summaryRepository;


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
}
