package top.chuqin.keywords.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chuqin.keywords.domain.Summary;
import top.chuqin.keywords.service.AnalyzationService;
import top.chuqin.keywords.vo.AlgorithmCompareVo;
import top.chuqin.keywords.vo.AnalyzationVo;
import top.chuqin.keywords.vo.SuccessVo;

import java.util.List;

@RestController
public class AnalyzationController {
    @Autowired
    private AnalyzationService analyzationService;

    @RequestMapping("/analyzation/{id}")
    public AnalyzationVo analyzation(@PathVariable(value="id") Long id){
        return analyzationService.analyzation(id);
    }

    @RequestMapping("/analyze/start")
    public SuccessVo startAnalyze() {
        analyzationService.reAnalyze();
        return new SuccessVo();
    }

    @RequestMapping("/analyze/result")
    public List<AlgorithmCompareVo> result() {
        return analyzationService.calcAlgorithmCompareVo();
    }


}
