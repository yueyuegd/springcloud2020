package net.zhangyue;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class feignOrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(feignOrderMain80.class, args);
    }
}
