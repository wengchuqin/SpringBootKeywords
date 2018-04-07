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
import top.chuqin.keywords.repository.SummaryRepository;
import top.chuqin.keywords.repository.VisitRepository;
import top.chuqin.keywords.service.SummaryService;
import top.chuqin.keywords.service.VisitQueue;

import java.util.function.Consumer;

@Component
public class InitData implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger LOGGER = LoggerFactory.getLogger(InitData.class);

    @Value("${crawler.enable:true}")
    private boolean crawlerEnable;
    @Value("${data.delete:false}")
    private boolean deleteData;
    @Value("${revisit:false}")
    private boolean revisit;

    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private SummaryRepository summaryRepository;
    @Autowired
    private VisitQueue visitQueue;
    @Autowired
    private SummaryService summaryService;
    @Autowired
    private MyCrawler myCrawler;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            LOGGER.debug("init data");


            //删除数据
            if (deleteData) {
                visitRepository.deleteAll();
                summaryRepository.deleteAll();
                LOGGER.debug("删除所有数据成功");
            } else {
                LOGGER.debug("不删除数据");
            }


            //把所有的url设置为未读取
            if (revisit) {
                visitRepository.findAll().stream().forEach((v) -> {
                    v.setHasVisited(false);
                    visitRepository.saveAndFlush(v);
                });
            }

            new EntranceUrlList().getData().forEach(new Consumer<String>() {
                @Override
                public void accept(String entranceUrl) {
                    LOGGER.debug("入口url是:[{}]", entranceUrl);
                    Visit visit = visitRepository.findFirstByUrl(entranceUrl);
                    if (visit == null) {
                        LOGGER.debug("入口url不在队列中, 该url为：[{}]", entranceUrl);
                        visitQueue.offer(entranceUrl);
                        LOGGER.debug("入口url入队列成功, 该url为：[{}]", entranceUrl);
                    } else {
                        LOGGER.debug("入口url已在队列中, 该url为：[{}]", entranceUrl);
                    }

                }
            });



            if (crawlerEnable) {
                myCrawler.start();
                LOGGER.debug("开启爬虫");
            } else {
                LOGGER.debug("没有开启爬虫");
            }


        }
    }
}
