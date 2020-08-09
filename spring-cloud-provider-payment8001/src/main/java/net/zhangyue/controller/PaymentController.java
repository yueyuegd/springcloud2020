package net.zhangyue.controller;


import lombok.extern.slf4j.Slf4j;
import net.zhangyue.entities.CommonResult;
import net.zhangyue.entities.Payment;
import net.zhangyue.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment) {
        int result = paymentService.create(payment);
        log.info("---------------插入值:------------", payment);
        if (result > 0) {
            return new CommonResult(200, "插入数据成功", result);
        } else  {
            return new CommonResult(444, "插入数据失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        log.info("---------------查询值结果:------------", result);
        if (result != null) {
            return new CommonResult(200, "查询数据成功", result);
        } else  {
            return new CommonResult(444, "查询数据失败", null);
        }
    }
}
