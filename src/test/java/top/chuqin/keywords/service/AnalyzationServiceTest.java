package top.chuqin.keywords.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import top.chuqin.keywords.domain.ExtractKeywordResult;
import top.chuqin.keywords.repository.ExtractKeywordResultRepository;
import top.chuqin.keywords.vo.AlgorithmCompareVo;
import top.chuqin.keywords.vo.Prf1;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnalyzationServiceTest {
    private AnalyzationService analyzationService = new AnalyzationService();

    @Test
    public void calcAlgorithmCompareVo() {
        ExtractKeywordResultRepository extractKeywordResultRepository = mock(ExtractKeywordResultRepository.class);
        ReflectionTestUtils.setField(analyzationService, "extractKeywordResultRepository", extractKeywordResultRepository);
        Prf1 prf11 = mock(Prf1.class);
        when(prf11.getP()).thenReturn(0.4);
        when(prf11.getR()).thenReturn(0.4);
        when(prf11.getF1()).thenReturn(0.4);

        Prf1 prf12 = mock(Prf1.class);
        when(prf12.getP()).thenReturn(0.5);
        when(prf12.getR()).thenReturn(0.5);
        when(prf12.getF1()).thenReturn(0.5);

        List<ExtractKeywordResult> list = Arrays.asList(
                new ExtractKeywordResult(null, ExtractKeywordResult.AlgorithmEnum.ANSJ_SEG, null, prf11),
                new ExtractKeywordResult(null, ExtractKeywordResult.AlgorithmEnum.TEXT_RANK, null, prf12)
        );

        for(ExtractKeywordResult.AlgorithmEnum e : ExtractKeywordResult.AlgorithmEnum.values()){
            when(extractKeywordResultRepository.findAllByAlgorithm(e.name())).thenReturn(list);
        }

        List<AlgorithmCompareVo> algorithmCompareVoList = analyzationService.calcAlgorithmCompareVo();
        algorithmCompareVoList.forEach(algorithmCompareVo -> {
            Assert.assertEquals(new Double(0.45), algorithmCompareVo.getP());
            Assert.assertEquals(new Double(0.45), algorithmCompareVo.getR());
            Assert.assertEquals(new Double(0.45), algorithmCompareVo.getF1());
            System.out.println(algorithmCompareVo);
        });


    }
}