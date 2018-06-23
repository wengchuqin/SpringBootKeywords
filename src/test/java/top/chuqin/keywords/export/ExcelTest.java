package top.chuqin.keywords.export;

import org.assertj.core.util.Strings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.chuqin.keywords.domain.Summary;
import top.chuqin.keywords.repository.SummaryRepository;
import top.chuqin.utils.tools.ColumnsConfig;
import top.chuqin.utils.tools.SimpleXlsxExportor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ExcelTest {
    @Autowired
    private SummaryRepository summaryRepository;

    @Test
    public void test() {
        List<Summary> summaries = summaryRepository.findAll();
        System.out.println(summaries.size());
//        System.out.println(summaries);


    }

    @Test
    public void export() throws IOException {
        List<Summary> summaries = summaryRepository.findAll();

        List<SummaryVo> list = new ArrayList<>();
        summaries.forEach(new Consumer<Summary>() {
            @Override
            public void accept(Summary s) {
                list.add(new SummaryVo(s.getUrl(), s.getSummary(), String.join(",", s.getCatelogs()), String.join(",", s.getKeywords())));
            }
        });

        SimpleXlsxExportor simpleXlsxExportor = new SimpleXlsxExportor();


        ColumnsConfig columnsConfig = new ColumnsConfig();
        columnsConfig.add("url", "url");
        columnsConfig.add("摘要", "summary");
        columnsConfig.add("分类", "catelogs");
        columnsConfig.add("关键词", "keywords");

        File file = new File("/home/wengchuqin/Desktop/hello.xlsx");
        if (!file.exists()) {
            file.createNewFile();
        }
        //文件输出流
        FileOutputStream out = new FileOutputStream(file);

        simpleXlsxExportor.export(list, columnsConfig, out);
    }


}

class SummaryVo {
    String url;
    String summary;
    String catelogs;
    String keywords;

    public SummaryVo(String url, String summary, String catelogs, String keywords) {
        this.url = url;
        this.summary = summary;
        this.catelogs = catelogs;
        this.keywords = keywords;
    }
}