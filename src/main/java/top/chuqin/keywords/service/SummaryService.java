package top.chuqin.keywords.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.chuqin.keywords.domain.Summary;
import top.chuqin.keywords.repository.SummaryRepository;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class SummaryService{
    private static Logger LOGGER = LoggerFactory.getLogger(SummaryService.class);

    @Autowired
    private SummaryRepository summaryRepository;


    public void add(Summary summary) {
        summaryRepository.save(summary);
    }

    public Page<Summary> findByPage(int pageNum, int size) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"));
        return summaryRepository.findAll(new PageRequest(pageNum, size, sort));
    }


    public long getRecordNum(){
        return summaryRepository.count();
    }

    public Summary findOne(Long id){
        Summary summary = summaryRepository.findOne(id);
        return summary;
    }

    public List<Summary> getSimilarSummaryList(Long id){
        Summary summary = findOne(id);
        return summaryRepository.findAll().stream().filter(new Predicate<Summary>() {
            @Override
            public boolean test(Summary test) {
                return test.getId() != summary.getId() && CollectionUtils.containsAny(summary.getCatelogs(), test.getCatelogs());
            }
        }).limit(100).collect(Collectors.toList());
    }
}
