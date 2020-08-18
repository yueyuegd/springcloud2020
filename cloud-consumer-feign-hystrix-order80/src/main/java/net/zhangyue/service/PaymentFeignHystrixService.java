package net.zhangyue.service;


import net.zhangyue.service.impl.PaymentFeignHystrixFallBackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cloud-payment-service", fallback = PaymentFeignHystrixFallBackServiceImpl.class)
@Component
public interface PaymentFeignHystrixService {

    @GetMapping(value = "/payment/hystrix/{id}")
    String paymentInfo_ok(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    String paymentInfo_Timeout(@PathVariable("id") Integer id);
}
