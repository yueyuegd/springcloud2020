package net.zhangyue.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.zhangyue.service.PaymentFeignHystrixService;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentFeignHystrixFallBackServiceImpl implements PaymentFeignHystrixService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return "---------paymentFeignHystrixService:paymentInfo_ok fall back";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "---------paymentFeignHystrixService:paymentInfo_Timeout fall back";
    }
}
