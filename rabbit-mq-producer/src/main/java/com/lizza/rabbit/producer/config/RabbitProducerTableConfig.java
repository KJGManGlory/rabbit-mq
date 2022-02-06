package com.lizza.rabbit.producer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Desc: 创建表
 * @author: lizza.liu
 * @date: 2022-02-06
 */
@Configuration
public class RabbitProducerTableConfig {

    @Resource
    private DataSource rabbitProducerDataSource;

    /** 加载建表语句 **/
    @Value("${classpath:broker-message-table.sql}")
    private org.springframework.core.io.Resource schemaScript;

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        DataSourceInitializer initializer = new DataSourceInitializer();

        initializer.setDataSource(rabbitProducerDataSource);
        initializer.setDatabasePopulator(databasePopulator());

        return initializer;
    }

    /**
     * 执行建表语句
     * @param
     * @return org.springframework.jdbc.datasource.init.DatabasePopulator
     */
    private DatabasePopulator databasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(schemaScript);
        return populator;
    }
}
