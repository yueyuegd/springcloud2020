package net.zhangyue.account.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "net.zhangyue.account.dao")
public class MyBatisConfig {
}
