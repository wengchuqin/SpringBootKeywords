package top.chuqin.keywords.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.chuqin.keywords.JunitTestBase;
import top.chuqin.keywords.domain.Visit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class VisitRepositoryTest extends JunitTestBase {

    @Autowired
    VisitRepository visitRepository;

    @Autowired
    Environment environment;

    @Before
    public void before(){
//        if(environment.acceptsProfiles("test")){
//            throw new RuntimeException("Junit 需要激活profile：test");
//        }
    }



    @Test
    public void testInsert(){
        for(int i = 0; i < 100; i++){
            Visit visit = new Visit(null, "url" + i, i %2 == 0);
            visitRepository.saveAndFlush(visit);
        }
    }

    @Test
    public void findFirstByHasVisitedTrueOrderByIdAscTest() {
        Visit v = visitRepository.findFirstByHasVisitedFalseOrderByIdAsc();
        System.out.println(v);
    }

    @Test
    public void findFirstByUrl() {
        Visit v = visitRepository.findFirstByUrl("url10");
        System.out.println(v);
    }

    @Test
    public void addNull(){
        Visit v = new Visit(null, null, null);
        visitRepository.save(v);
    }
}