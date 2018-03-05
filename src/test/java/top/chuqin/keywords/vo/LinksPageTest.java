package top.chuqin.keywords.vo;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinksPageTest {

    @Test
    public void parse() {
        String pageUrl = "http://kns.cnki.net/KCMS/detail/detail.aspx?dbcode=CDFD&dbname=CDFDLAST2017&filename=1016922147.nh&v=MDYxODc2SE5ESXFKRWJQSVI4ZVgxTHV4WVM3RGgxVDNxVHJXTTFGckNVUkxLZlplUnFGeS9oVmIzQlZGMjZHTHE=";
        LinksPage page = LinksPage.parse(pageUrl);
        System.out.println(page.getRelatedUrlList());
    }
}