package top.chuqin.keywords.crawler;

import org.slf4j.Logger;
import top.chuqin.keywords.service.SummaryService;
import top.chuqin.keywords.service.VisitQueue;
import top.chuqin.keywords.vo.LinksPage;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


public class LinksCrawler {
    private static Logger LOGGER = getLogger(DetailCrawler.class);
    private final String url;
    private final VisitQueue visitQueue;
    private final SummaryService summaryService;

    public LinksCrawler(String url, VisitQueue visitQueue, SummaryService summaryService) {
        this.url = url;
        this.visitQueue = visitQueue;
        this.summaryService = summaryService;
    }

    public void crawlLinks() {
        try {
            LinksPage linksPage = LinksPage.parse(url);

            List<String> list = linksPage.getRelatedUrlList();
            list.stream().forEach(url -> {
                visitQueue.offer(url);
                LOGGER.debug("url:[{}], 入队列", url);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
