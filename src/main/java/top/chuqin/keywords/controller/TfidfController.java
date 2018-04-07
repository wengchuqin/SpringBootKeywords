package top.chuqin.keywords.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chuqin.keywords.domain.Tfidf;
import top.chuqin.keywords.service.AnalyzationService;
import top.chuqin.keywords.service.TfidfService;
import top.chuqin.keywords.utils.Progress;
import top.chuqin.keywords.vo.AnalyzationVo;

@RestController
public class TfidfController {
    @Autowired
    private TfidfService tfidfService;

    @RequestMapping("/tfidf/{summaryId}")
    public Tfidf tfidf(@PathVariable(value="summaryId") Long summaryId){
        return tfidfService.findBySummaryId(summaryId);
    }

    @RequestMapping("/tfidf/calcAllTfidf")
    public String calcAllTfidf(){
        tfidfService.calcAllTfidf();
        return "OK";
    }

    @RequestMapping("/tfidf/calcAllTfidfStatus")
    public Progress calcAllTfidfStatus(){
        return tfidfService.calcAllTfidfStatus();
    }
}
