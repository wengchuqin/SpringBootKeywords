package top.chuqin.keywords.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import top.chuqin.keywords.domain.Visit;
import top.chuqin.keywords.repository.VisitRepository;

@Service
@Scope("singleton")
public class VisitQueue {
    private static Logger LOGGER = LoggerFactory.getLogger(VisitQueue.class);

    @Autowired
    private VisitRepository visitRepository;


    //锁对象
    private Object offerLock = new Object();
    private Object pollLock = new Object();

    public void offer(String url) {
        synchronized (offerLock) {
            Assert.notNull(url, "url不能为null");

            //如果url已经存在，不做处理
            Visit v = visitRepository.findFirstByUrl(url);
            if (v != null) {
                return;
            }

            Visit visit = Visit.of(url);
            visitRepository.saveAndFlush(visit);

            LOGGER.debug("url({})入队", url);
        }
    }



    public String poll(){
        synchronized (pollLock) {
            Visit visit = visitRepository.findFirstByHasVisitedFalseOrderByIdAsc();

            if(visit == null){
                LOGGER.debug("url队列为空");
                return null;
            }
            visit.setHasVisited(true);
            visitRepository.saveAndFlush(visit);

            LOGGER.debug("url({})出队", visit.getUrl());
            return visit.getUrl();
        }
    }
}
