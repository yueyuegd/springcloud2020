package net.zhangyue.storage.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "net.zhangyue.storage.dao")
public class MyBatisConfig {
}
