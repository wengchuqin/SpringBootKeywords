package top.chuqin.keywords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.chuqin.keywords.domain.ExtractKeywordResult;
import top.chuqin.keywords.repository.ExtractKeywordResultRepository;

import java.util.List;

@Transactional
@Service
public class ExtractKeywordResultService {
    @Autowired
    private ExtractKeywordResultRepository extractKeywordResultRepository;

    public List<ExtractKeywordResult> findAllBySummaryId(Long summaryId){
        return extractKeywordResultRepository.findAllBySummaryId(summaryId);
    }
}
