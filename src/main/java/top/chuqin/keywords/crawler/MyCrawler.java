package top.chuqin.keywords.crawler;

import top.chuqin.keywords.domain.Visit;
import top.chuqin.keywords.service.SummaryService;
import top.chuqin.keywords.service.VisitQueue;

import java.util.concurrent.BlockingDeque;

public class MyCrawler {
    private final VisitQueue visitQueue;
    private final SummaryService summaryService;

    public MyCrawler(VisitQueue visitQueue, SummaryService summaryService) {
        this.visitQueue = visitQueue;
        this.summaryService = summaryService;
    }

    public void crawler() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String url = null;
                while ((url = visitQueue.poll()) != null) {
                    try {
                        new LinksCrawler(url, visitQueue, summaryService).crawlLinks();
                        new DetailCrawler(url, visitQueue, summaryService).crawlSummary();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
}
