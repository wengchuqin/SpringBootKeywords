package top.chuqin.keywords;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@PropertySource("entrance.yml")
public class PropertiesTest extends JunitTestBase{

    @Value("${crawler}")
    List<String> list = new ArrayList<>();

    @Value("${crawler.entrance.url2")
    List<String> urlList = new ArrayList<>();

    @Test
    public void test(){
        System.out.println(list);
        System.out.println(urlList);
    }
}