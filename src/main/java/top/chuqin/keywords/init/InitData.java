package top.chuqin.keywords.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import top.chuqin.keywords.crawler.MyCrawler;
import top.chuqin.keywords.domain.Visit;
import top.chuqin.keywords.repository.VisitRepository;
import top.chuqin.keywords.service.SummaryService;
import top.chuqin.keywords.service.VisitQueue;

@Component
public class InitData implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger LOGGER = LoggerFactory.getLogger(InitData.class);

    @Value("${crawler.entrance.url}")
    private String entranceUrl;
    @Value("${crawler.enable}")
    private boolean crawlerEnable;


    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private VisitQueue visitQueue;
    @Autowired
    private SummaryService summaryService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            LOGGER.debug("init data");
            LOGGER.debug("入口url是:[{}]", entranceUrl);
            Visit visit = visitRepository.findFirstByUrl(entranceUrl);
            if (visit == null) {
                LOGGER.debug("入口url不在队列中, 入口队列为：[{}]", entranceUrl);
                visitQueue.offer(entranceUrl);
                LOGGER.debug("入口url入队列成功, 入口队列为：[{}]", entranceUrl);
            } else {
                LOGGER.debug("入口url已在队列中, 入口队列为：[{}]", entranceUrl);
            }

            if (crawlerEnable) {
                new MyCrawler(visitQueue, summaryService).crawler();
                LOGGER.debug("开启爬虫");
            } else {
                LOGGER.debug("没有开启爬虫");

            }
        }
    }
}
