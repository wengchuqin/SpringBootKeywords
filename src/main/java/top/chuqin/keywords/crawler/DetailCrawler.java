package top.chuqin.keywords.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.chuqin.keywords.domain.Summary;
import top.chuqin.keywords.service.SummaryService;
import top.chuqin.keywords.service.VisitQueue;
import top.chuqin.keywords.vo.DetailPage;

import java.io.IOException;

public class DetailCrawler{
    private static Logger LOGGER = LoggerFactory.getLogger(DetailCrawler.class);

    private final String url;
    private final VisitQueue visitQueue;
    private final SummaryService summaryService;

    public DetailCrawler(String url, VisitQueue visitQueue, SummaryService summaryService) {
        this.url = url;
        this.visitQueue = visitQueue;
        this.summaryService = summaryService;
    }



    public void crawlSummary() {
        try {
            Document document = Jsoup.connect(url).get();
            DetailPage detailPage = DetailPage.parse(url);

            //把摘要数据写入数据库
            Summary summary = Summary.fromVo(detailPage.getSummaryVo());
            summaryService.add(summary);

            LOGGER.debug("摘要：[{}]写入数据库成功", summary);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
