package top.chuqin.keywords.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chuqin.keywords.domain.ExtractKeywordResult;
import top.chuqin.keywords.domain.Summary;
import top.chuqin.keywords.service.AnalyzationService;
import top.chuqin.keywords.service.ExtractKeywordResultService;
import top.chuqin.keywords.service.SummaryService;
import top.chuqin.keywords.vo.*;

import java.util.List;
import java.util.Map;

@RestController
public class AnalyzationController {
    @Autowired
    private AnalyzationService analyzationService;
    @Autowired
    private ExtractKeywordResultService extractKeywordResultService;
    @Autowired
    private SummaryService summaryService;

    @RequestMapping("/analyzation/{id}")
    public List<ExtractKeywordResult> analyzation(@PathVariable(value="id") Long id){
        return extractKeywordResultService.findAllBySummaryId(id);
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

    @RequestMapping("/analyze/status")
    public AnalyzeStatus status() {
        return analyzationService.getStatus();
    }

    @RequestMapping("/analyze/category")
    public List<Category> category(){
        return summaryService.category();
    }
}
