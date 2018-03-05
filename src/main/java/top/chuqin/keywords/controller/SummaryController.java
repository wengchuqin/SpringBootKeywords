package top.chuqin.keywords.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chuqin.keywords.domain.Summary;
import top.chuqin.keywords.service.SummaryService;

@RestController
public class SummaryController {

    @Autowired
    private SummaryService summaryService;

    /**
     *
     * @param number pageNum are zero indexed
     * @param size
     * @return
     */
    @RequestMapping("/summaries")
    public Page<Summary> findByPage(int number, int size){
        Page<Summary> page = summaryService.findByPage(number, size);
        return page;
    }


}
