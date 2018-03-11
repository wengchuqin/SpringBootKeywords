package top.chuqin.keywords.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.chuqin.keywords.JunitTestBase;
import top.chuqin.keywords.domain.Summary;
import top.chuqin.keywords.repository.SummaryRepository;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TfidfServiceTest extends JunitTestBase {
    @Autowired
    private TfidfService tfidfService;
    @Autowired
    private SummaryRepository summaryRepository;


    @Test
    public void tfidf() {
        summaryRepository.deleteAll();

        Summary summary = new Summary();
        summary.setSummary("我爱母牛，我爱母牛，我爱母牛，我爱母牛，我爱母牛，我爱母牛，毕业设计，毕业设计，毕业设计");
        summary.setKeywords("keywords");
        summary.setUrl("url");
        summary.setCatelogs("catelogs1");
        summaryRepository.saveAndFlush(summary);
        long id = summary.getId();

        Summary summary2 = new Summary();
        summary2.setSummary("我爱母牛");
        summary2.setKeywords("keywords");
        summary2.setUrl("url");
        summary2.setCatelogs("catelogs1");
        summaryRepository.saveAndFlush(summary2);

        for(int i = 0; i < 5; i++){
            Summary s = new Summary();
            s.setSummary("毕业设计");
            s.setKeywords("keywords");
            s.setUrl("url");
            s.setCatelogs("catelogs1");
            summaryRepository.saveAndFlush(s);
        }



        List<TfidfService.WordWithTfidf> list = tfidfService.tfidfList(id);
        System.out.println("tfidfList：" + list);

    }

    @Test
    public void test(){
        System.out.println(Math.log(22));
    }
}