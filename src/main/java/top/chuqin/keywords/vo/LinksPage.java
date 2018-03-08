package top.chuqin.keywords.vo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinksPage {
    private static Logger LOGGER = LoggerFactory.getLogger(LinksPage.class);

    private final Document document;
    private final String pageUrl;
    private final List<String> relatedUrlList;

    private LinksPage(String pageUrl) {
        this.pageUrl = pageUrl;

        Document doc = null;
        try {
            doc = Jsoup.connect(pageUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            document = doc;
        }

        relatedUrlList = getRelatedHtml(document, pageUrl);
    }

    public static LinksPage parse(String pageUrl) {
        LinksPage linksPage = new LinksPage(pageUrl);
        return linksPage;
    }

    public List<String> getRelatedUrlList() {
        return relatedUrlList;
    }

    private List<String> getRelatedHtml(Document doc, String pageUrl) {
        List<String> urlList = null;
        try {
            String str = doc.html();

            String catalogId = "lcatalog_func604";
            String catalogName = "%E7%9B%B8%E4%BC%BC%E6%96%87%E7%8C%AE%0A%20%20%20%20%20%20%20%20%20%20";

            String startStr = "case \"catalog_func604\":";
            int startIndex = str.indexOf(startStr) + startStr.length();
            int endIndex = startIndex + str.substring(startIndex).indexOf("break");
            String functionLine = str.substring(startIndex, endIndex).trim();
            LOGGER.debug("WriteToPage函数[{}]:", functionLine);

            Pattern p = Pattern.compile("'.*?'");
            Matcher m = p.matcher(functionLine); // 获取 matcher 对象

            m.find();
            String filename = functionLine.substring(m.start() + 1, m.end() - 1);

            m.find();
            String dbname = functionLine.substring(m.start() + 1, m.end() - 1);

            m.find();
            String dbcode = functionLine.substring(m.start() + 1, m.end() - 1);

            m.find();
            String curdbcode = functionLine.substring(m.start() + 1, m.end() - 1);

            m.find();
            String reftype = functionLine.substring(m.start() + 1, m.end() - 1);

            LOGGER.debug("filename:[{}]", filename);
            LOGGER.debug("dbname:[{}]", dbname);
            LOGGER.debug("dbcode:[{}]", dbcode);
            LOGGER.debug("curdbcode:[{}]", curdbcode);
            LOGGER.debug("reftype:[{}]", reftype);


            String newUrl = String.format("http://kns.cnki.net/kcms/detail/frame/asynlist.aspx?dbcode=%s&dbname=%s&filename=%s&curdbcode=%s&reftype=%s&catalogId=%s&catalogName=%s",
                    dbcode,
                    dbname,
                    filename,
                    curdbcode,
                    reftype,
                    catalogId,
                    catalogName);
            LOGGER.debug("页面js拼接出来的URL:[{}]", newUrl);

            java.net.URL  url = new  java.net.URL(pageUrl);
            System.out.println(url);
            String baseUrl = pageUrl.substring(0, pageUrl.indexOf(url.getPath()));
            LOGGER.debug("baseUrl:[{}]", baseUrl);
            urlList = getAllUrlFromHtml(Jsoup.connect(newUrl).get(), baseUrl);
            LOGGER.debug("页面js拼接出来的URL，所指向的页面抽取出来的URL:[{}]", newUrl);
        } catch (Exception e) {
            e.printStackTrace();
            urlList = new ArrayList<>();
        }

        return urlList;
    }


    private List<String> getAllUrlFromHtml(Document document, String baseUrl) {
        Elements aTag = document.getElementsByTag("a");
        List<String> hrefList = aTag.eachAttr("href");
        for (int i = 0; i < hrefList.size(); i++) {
            hrefList.set(i, baseUrl + hrefList.get(i));
        }

        LOGGER.debug("href:[{}]", hrefList);
        return hrefList;
    }

}
