package top.chuqin.keywords.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.chuqin.keywords.JunitTestBase;
import top.chuqin.keywords.domain.Visit;
import top.chuqin.keywords.repository.VisitRepository;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class VisitQueueTest extends JunitTestBase{

    @Autowired
    VisitQueue visitQueue;
    @Autowired
    VisitRepository visitRepository;

    @Autowired
    Environment environment;


    @Before
    public void before(){
        visitRepository.deleteAll();
        for(int i = 0; i < 10; i++){
            Visit visit = new Visit(null, "url" + i, i % 2 == 0);
            visitRepository.save(visit);
        }
        System.out.println("初始化完毕");
    }


    @Test
    public void deleteAll(){
        visitRepository.deleteAll();
        System.out.println("数据库记录数:" + visitRepository.findAll().size());
    }


    @Test
    public void offer() {
        visitRepository.deleteAll();
        Assert.assertEquals(visitRepository.findAll().size(), 0);
        Assert.assertNull(visitQueue.poll());

        visitQueue.offer("url");
        Assert.assertEquals(visitQueue.poll(), "url");
    }

    @Test
    public void poll() {
        List<Visit> all = visitRepository.findAll();
        for(int i = 0; i < all.size(); i++){
            System.out.println(visitQueue.poll());
        }
    }
}