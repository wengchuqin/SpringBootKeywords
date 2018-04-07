package top.chuqin.keywords.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.chuqin.keywords.JunitTestBase;
import top.chuqin.keywords.domain.Summary;
import top.chuqin.keywords.repository.SummaryRepository;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SummaryServiceTest extends JunitTestBase {
    @Autowired
    private SummaryService summaryService;
    @Autowired
    private SummaryRepository summaryRepository;

    @Test
    public void add() {
        summaryRepository.deleteAll();

        Summary summary = new Summary();
        summary.setCatelogs(Arrays.asList("catelogs"));
        summary.setKeywords(Arrays.asList("keywords"));
        summary.setSummary("summary");
        summaryService.add(summary);
        System.out.printf("summary:%s\n", summary);

        Summary summaryInDb = summaryRepository.findAll().get(0);
        System.out.printf("summaryInDb:%s\n", summaryInDb);

        Assert.assertEquals(summary.getCatelogs(), summaryInDb.getCatelogs());
        Assert.assertEquals(summary.getKeywords(), summaryInDb.getKeywords());
        Assert.assertEquals(summary.getSummary(), summaryInDb.getSummary());
    }


    @Test
    public void getSimilarSummaryList() {
        summaryRepository.deleteAll();

        long id1= -1;
        for(int i = 0; i < 10; i++){
            Summary summary = new Summary();
            summary.setSummary("summary");
            summary.setKeywords(Arrays.asList("keywords"));
            summary.setUrl("url");
            summary.setCatelogs(Arrays.asList("TP111"));
            summaryRepository.saveAndFlush(summary);
            id1 = summary.getId();
        }

        long id2= -1;
        for(int i = 0; i < 5; i++){
            Summary summary = new Summary();
            summary.setSummary("summary");
            summary.setKeywords(Arrays.asList("keywords"));
            summary.setUrl("url");
            summary.setCatelogs(Arrays.asList("TP222"));
            summaryRepository.saveAndFlush(summary);
            id2 = summary.getId();
        }

        Assert.assertEquals(summaryService.getSimilarSummaryList(id1).size(), 9);
        Assert.assertEquals(summaryService.getSimilarSummaryList(id2).size(), 4);
    }
}