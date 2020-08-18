package net.zhangyue.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class paymentService {

    public String paymentInfo_ok(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " payment_ok, id:" + id;
    }

    @HystrixCommand(fallbackMethod = "payment_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String payment_Timeout(Integer id) {
        //int result = 10 / 0;
        int second = 3;
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "payment_Timeout, id:" + id;
    }

    public String payment_TimeoutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "系统繁忙或者运行报错，请稍后再试, id:" + id;
    }
}
