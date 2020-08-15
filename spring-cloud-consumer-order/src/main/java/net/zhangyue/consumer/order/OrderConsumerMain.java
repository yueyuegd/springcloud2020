package net.zhangyue.consumer.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrderConsumerMain {

    public static void main(String[] args) {
        SpringApplication.run(OrderConsumerMain.class, args);
    }
}
