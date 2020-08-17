package net.zhangyue.consumer.order;

import net.zhangyue.loadbalancerule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "cloud-payment-service", configuration = MySelfRule.class)
public class OrderConsumerMain {

    public static void main(String[] args) {
        SpringApplication.run(OrderConsumerMain.class, args);
    }
}
