package top.chuqin.keywords.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chuqin.keywords.service.SummaryService;
import top.chuqin.keywords.service.VisitQueue;

@Service
public class MyCrawler {

    @Autowired
    private VisitQueue visitQueue;
    @Autowired
    private SummaryService summaryService;

    private boolean run;

    public MyCrawler(VisitQueue visitQueue, SummaryService summaryService) {
        this.visitQueue = visitQueue;
        this.summaryService = summaryService;
    }


    public synchronized void start(){
        if(run == false){
            run = true;
            crawler();
        }
    }

    public synchronized void stop(){
        run = false;
    }

    private synchronized void crawler() {
        Thread thread = new Thread(() -> {
            String url = null;
            while (run && (url = visitQueue.poll()) != null) {
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
