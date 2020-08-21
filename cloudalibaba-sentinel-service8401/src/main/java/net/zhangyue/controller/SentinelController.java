package net.zhangyue.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SentinelController {

    @GetMapping("/test1")
    public String test1() {
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        log.info("---------test1-----------");
        return "test1";
    }

    @GetMapping("/test2")
    public String test2() {
        log.info("---------test2-----------");
        return "test2";
    }
}
