package net.zhangyue.payment.controller;



import lombok.extern.slf4j.Slf4j;
import net.zhangyue.entities.CommonResult;
import net.zhangyue.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.*;
import net.zhangyue.payment.service.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private EurekaDiscoveryClient eurekaDiscoveryClient;

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


    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = eurekaDiscoveryClient.getServices();
        for (String service : services) {
            log.info("-----element:" + service);
        }
        List<ServiceInstance> serviceInstances = eurekaDiscoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance serviceInstance : serviceInstances) {
            log.info("cloud-payment-service:" + serviceInstance.getHost() + "/" + serviceInstance.getPort());
        }
        return null;
    }

    @GetMapping(value = "/payment/lb")
    public String paymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
