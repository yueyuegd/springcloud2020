package net.zhangyue;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class paymentMain9003 {

    public static void main(String[] args) {
        SpringApplication.run(paymentMain9003.class, args);
    }
}
