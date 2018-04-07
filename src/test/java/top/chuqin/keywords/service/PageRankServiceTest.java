package top.chuqin.keywords.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import top.chuqin.keywords.domain.ExtractKeywordResult;
import top.chuqin.keywords.domain.Summary;
import top.chuqin.keywords.repository.SummaryRepository;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PageRankServiceTest {
    private PageRankService pageRankService = new PageRankService();

    @Test
    public void extract() {
        SummaryRepository summaryRepository = mock(SummaryRepository.class);
        ReflectionTestUtils.setField(pageRankService, "summaryRepository", summaryRepository);
        Long summaryId = 1L;
        Summary summary = mock(Summary.class);
        when(summaryRepository.findOne(summaryId)).thenReturn(summary);
        String content = "程序员(英文Programmer)是从事程序开发、维护的专业人员。" +
                "一般将程序员分为程序设计人员和程序编码人员，" +
                "但两者的界限并不非常清楚，特别是在中国。" +
                "软件从业人员分为初级程序员、高级程序员、系统" +
                "分析员和项目经理四大类。";
        when(summary.getSummary()).thenReturn(content);
        when(summary.getKeywords()).thenReturn(Arrays.asList("程序员", "程序开发", "专业人员", "高级程序员"));


        ExtractKeywordResult result = pageRankService.extract(summaryId);


        Assert.assertEquals(result.getKeywords(), "程序员 人员 程序 分为");
        Assert.assertEquals(result.getP(), new Double(0.25));
        Assert.assertEquals(result.getR(), new Double(0.25));
        Assert.assertEquals(result.getF1(), new Double(0.25));
        System.out.println(result);
    }
}