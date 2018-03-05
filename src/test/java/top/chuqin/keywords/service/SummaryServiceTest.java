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
        summary.setCatelogs("catelogs");
        summary.setKeywords("keywords");
        summary.setSummary("summary");
        summaryService.add(summary);
        System.out.printf("summary:%s\n", summary);

        Summary summaryInDb = summaryRepository.findAll().get(0);
        System.out.printf("summaryInDb:%s\n", summaryInDb);

        Assert.assertEquals(summary.getCatelogs(), summaryInDb.getCatelogs());
        Assert.assertEquals(summary.getKeywords(), summaryInDb.getKeywords());
        Assert.assertEquals(summary.getSummary(), summaryInDb.getSummary());
    }
}