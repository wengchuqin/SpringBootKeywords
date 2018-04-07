package top.chuqin.keywords.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chuqin.keywords.crawler.MyCrawler;
import top.chuqin.keywords.service.AnalyzationService;
import top.chuqin.keywords.vo.AnalyzationVo;
import top.chuqin.keywords.vo.SuccessVo;

@RestController
public class SystemController {
    @Autowired
    private MyCrawler myCrawler;

    @RequestMapping("/crawler/start")
    public SuccessVo startCrawler(){
        myCrawler.start();
        return new SuccessVo();
    }

    @RequestMapping("/crawler/stop")
    public SuccessVo stopCrawler(){
        myCrawler.stop();
        return new SuccessVo();
    }

}
