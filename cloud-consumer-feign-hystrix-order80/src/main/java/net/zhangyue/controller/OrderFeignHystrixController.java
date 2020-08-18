package net.zhangyue.controller;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import net.zhangyue.service.PaymentFeignHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "global_handler")
public class OrderFeignHystrixController {

    @Autowired
    private PaymentFeignHystrixService paymentFeignHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id) {
        return paymentFeignHystrixService.paymentInfo_ok(id);
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    /*@HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })*/
    @HystrixCommand
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        return paymentFeignHystrixService.paymentInfo_Timeout(id);
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "80端口，发生异常";
    }

    public String global_handler() {
        return "异常";
    }


}
