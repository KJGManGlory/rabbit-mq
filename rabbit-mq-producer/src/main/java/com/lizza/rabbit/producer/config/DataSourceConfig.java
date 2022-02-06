package com.lizza.rabbit.producer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @Desc:
 * @author: lizza.liu
 * @date: 2022-02-06
 */
@Slf4j
@Configuration
@PropertySource({"classpath:broker-message-db.properties"})
public class DataSourceConfig {

    @Value("${rabbit.producer.druid.type}")
    private Class<? extends DataSource> dataSourceType;

    @Primary
    @Bean("rabbitProducerDataSource")
    @ConfigurationProperties(prefix = "rabbit.producer.druid.jdbc")
    public DataSource dataSource() {
        DataSource dataSource = DataSourceBuilder.create().type(dataSourceType).build();
        log.info("======================= rabbit producer datasource create finished! =======================");
        return dataSource;
    }
}
