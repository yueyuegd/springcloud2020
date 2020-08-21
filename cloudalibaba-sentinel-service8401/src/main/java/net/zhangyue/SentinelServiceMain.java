package net.zhangyue;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SentinelServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(SentinelServiceMain.class, args);
    }
}
