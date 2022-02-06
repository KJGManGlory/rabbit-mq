package com.lizza.rabbit.producer.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Desc:
 * @author: lizza.liu
 * @date: 2022-02-06
 */
@Configuration
@AutoConfigureAfter(value = {DataSourceConfig.class})
public class MyBatisMapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        configurer.setBasePackage("com.lizza.rabbit.producer.mapper");
        return configurer;
    }
}
