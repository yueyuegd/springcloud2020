package net.zhangyue.payment.controller;


import lombok.extern.slf4j.Slf4j;
import net.zhangyue.entities.CommonResult;
import net.zhangyue.entities.Payment;
import net.zhangyue.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("---------------插入值:------------", payment);
        if (result > 0) {
            return new CommonResult(200, "插入数据成功,serverPort:" + serverPort, result);
        } else  {
            return new CommonResult(444, "插入数据失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        log.info("---------------查询值结果:------------", result);
        if (result != null) {
            return new CommonResult(200, "查询数据成功,serverPort:" + serverPort, result);
        } else  {
            return new CommonResult(444, "查询数据失败", null);
        }
    }
}
