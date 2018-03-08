package top.chuqin.keywords.crawler;

import top.chuqin.keywords.service.SummaryService;
import top.chuqin.keywords.service.VisitQueue;

public class MyCrawler {
    private final VisitQueue visitQueue;
    private final SummaryService summaryService;

    public MyCrawler(VisitQueue visitQueue, SummaryService summaryService) {
        this.visitQueue = visitQueue;
        this.summaryService = summaryService;
    }

    public void crawler() {
        Thread thread = new Thread(() -> {
            String url = null;
            while ((url = visitQueue.poll()) != null) {
                try {
                    new LinksCrawler(url, visitQueue, summaryService).crawlLinks();
                    new DetailCrawler(url, visitQueue, summaryService).crawlSummary();
                }catch (Throwable e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
