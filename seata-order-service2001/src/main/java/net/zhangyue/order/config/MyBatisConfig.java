package net.zhangyue.order.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "net.zhangyue.order.dao")
public class MyBatisConfig {
}
