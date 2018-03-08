package top.chuqin.keywords.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chuqin.keywords.domain.Summary;
import top.chuqin.keywords.service.AnalyzationService;
import top.chuqin.keywords.vo.AnalyzationVo;

@RestController
public class AnalyzationController {
    @Autowired
    private AnalyzationService analyzationService;

    @RequestMapping("/analyzation/{id}")
    public AnalyzationVo analyzationOne(@PathVariable(value="id") Long id){
        return analyzationService.analyzation(id);
    }
}
