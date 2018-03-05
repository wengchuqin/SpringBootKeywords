package top.chuqin.keywords.vo;

import org.junit.Assert;
import org.junit.Test;
import top.chuqin.keywords.domain.Summary;

import static org.junit.Assert.*;

public class DetailPageTest {

    @Test
    public void parse() {
        String pageUrl = "http://kns.cnki.net/kcms/detail/detail.aspx?filename=WRFZ200802015&dbcode=CJFD&dbname=CJFD2008&v=";
        DetailPage page = DetailPage.parse(pageUrl);
        SummaryVo vo = page.getSummaryVo();
        Summary summary = Summary.fromVo(vo);

        System.out.println(vo);
        System.out.println(summary);
        Assert.assertEquals(summary.getKeywords(), "农业 污染 AG");
        Assert.assertEquals(summary.getCatelogs(), "X52");
    }
}