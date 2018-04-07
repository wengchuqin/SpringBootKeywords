package top.chuqin.keywords.service;

import com.hankcs.hanlp.HanLP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.chuqin.keywords.constant.Constant;
import top.chuqin.keywords.domain.ExtractKeywordResult;
import top.chuqin.keywords.domain.Summary;
import top.chuqin.keywords.domain.Tfidf;
import top.chuqin.keywords.repository.SummaryRepository;
import top.chuqin.keywords.repository.TfidfRepository;
import top.chuqin.keywords.utils.Progress;
import top.chuqin.keywords.vo.Prf1;
import top.chuqin.keywords.vo.WordFreq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class PageRankService implements IExtractKeyword{
    private static Logger LOGGER = LoggerFactory.getLogger(PageRankService.class);

    @Autowired
    private SummaryRepository summaryRepository;

    public ExtractKeywordResult extract(Long summaryId) {
        Summary summary = summaryRepository.findOne(summaryId);
        return extract(summary);
    }

    @Override
    public ExtractKeywordResult extract(Summary summary) {
        List<String> keywordList = HanLP.extractKeyword(summary.getSummary(), summary.getKeywords().size());
        Prf1 prf1 = new Prf1(summary.getKeywords(), keywordList);

        ExtractKeywordResult result = new ExtractKeywordResult(summary.getId(), ExtractKeywordResult.AlgorithmEnum.PAGE_RANK,
                String.join(Constant.SEPARATOR, keywordList), prf1);

        LOGGER.debug("extract:" + result);
        return result;
    }
}
