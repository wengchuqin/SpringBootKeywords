package top.chuqin.keywords.crawler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.chuqin.keywords.JunitTestBase;
import top.chuqin.keywords.service.SummaryService;
import top.chuqin.keywords.service.VisitQueue;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DetailCrawlerTest extends JunitTestBase {
    @Autowired
    private VisitQueue visitQueue;
    @Autowired
    private SummaryService summaryService;
    private final String url = "http://kns.cnki.net/KCMS/detail/detail.aspx?dbcode=CDFD&dbname=CDFD1214&filename=1013244694.nh&v=MDU4NTdXTTFGckNVUkxLZlpPWnBGQ25uVkx2S1ZGMjZIYkc4R3RmRnE1RWJQSVI4ZVgxTHV4WVM3RGgxVDNxVHI=";

    @Test
    public void test(){
        new DetailCrawler(url, visitQueue, summaryService).crawlSummary();
    }
}