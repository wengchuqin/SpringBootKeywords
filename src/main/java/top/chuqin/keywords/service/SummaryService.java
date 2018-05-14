package top.chuqin.keywords.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.chuqin.keywords.domain.Summary;
import top.chuqin.keywords.repository.SummaryRepository;
import top.chuqin.keywords.vo.Category;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
public class SummaryService {
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


    public long getRecordNum() {
        return summaryRepository.count();
    }

    public Summary findOne(Long id) {
        Summary summary = summaryRepository.findOne(id);
        return summary;
    }

    public List<Summary> getSimilarSummaryList(Long id) {
        Summary summary = findOne(id);
        return summaryRepository.findAll().stream().filter(new Predicate<Summary>() {
            @Override
            public boolean test(Summary test) {
                return test.getId() != summary.getId() && CollectionUtils.containsAny(summary.getCatelogs(), test.getCatelogs());
            }
        }).limit(100).collect(Collectors.toList());
    }

    public List<Category> category() {
        List<Summary> summaryList = summaryRepository.findAll();
        List<Category> categoryList = new ArrayList<>();
        Map<String, Integer> codeCount = new HashMap<>();
        summaryList.stream().forEach(summary -> {
            summary.getCatelogs().forEach(s -> {
                if(s == null){
                    return;
                }

                String code = null;
                if(s.indexOf('.') != -1){
                    code = s.substring(0, s.indexOf('.'));
                }else {
                    code = s;
                }
                Integer count = codeCount.get(code);
                if (count == null) {
                    count = 1;
                } else {
                    count++;
                }
                codeCount.put(code, count);
            });
        });

        codeCount.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String code, Integer count) {
                categoryList.add(new Category(code, count));
            }
        });

        categoryList.sort(new Comparator<Category>() {
            @Override
            public int compare(Category o1, Category o2) {
                return Integer.compare(o2.getCount(), o1.getCount());
            }
        });

        return categoryList;
    }
}
