package top.chuqin.keywords.vo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DetailPage {
    private static Logger LOGGER = LoggerFactory.getLogger(DetailPage.class);

    private final Document document;
    private final String pageUrl;
    private SummaryVo summaryVo;

    private DetailPage(String pageUrl) {
        this.pageUrl = pageUrl;

        Document doc = null;
        try {
            doc = Jsoup.connect(pageUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            document = doc;
        }

        parseHtml(doc);
    }


    public static DetailPage parse(String pageUrl){
        DetailPage detailPage = new DetailPage(pageUrl);
        return detailPage;
    }

    public SummaryVo getSummaryVo() {
        return summaryVo;
    }

    private String[] keyListToKeyArr(List<String> keyList){
        String[] keyArr = new String[keyList.size()];
        for(int i = 0; i < keyArr.length; i++){
            keyArr[i] = keyList.get(i).substring(0, keyArr.length - 1);
        }
        return keyArr;
    }

    private String[] parseCatelog(String catelogStr){
        return catelogStr.split(";");
    }

    private void parseHtml(Document document){

        try{
            summaryVo = new SummaryVo();

            //读取摘要
            Element summarySelect = document.selectFirst("#ChDivSummary");
            summaryVo.setSummary(summarySelect.text());
            System.out.println("摘要：" + summaryVo.getSummary());

            //读取摘要的关键词,例如 ‘复杂社会网络;结构测度;网络建模;模型评估;’
            Elements keysSelect = document.selectFirst("p:has(label#catalog_KEYWORD)").select("a");
            List<String> keyList = keysSelect.eachText();
            summaryVo.setKeywords(keyListToKeyArr(keyList));
            System.out.println("关键词：" + Arrays.toString(summaryVo.getKeywords()));


            //读取分类号,例如 TP393.09;TP311.13
            Element catalogSelect = document.selectFirst("p:has(label#catalog_ZTCLS)");
            String catelog = catalogSelect.ownText();
            summaryVo.setCatelogs(parseCatelog(catelog));
            System.out.println("分类号：" + catelog);
        }catch (Exception e){
            summaryVo = null;
            LOGGER.error("解析html页面内容失败。html页面如下:\n{}, 报错信息如下：\n{}", document.html(), e);
        }
    }
}
