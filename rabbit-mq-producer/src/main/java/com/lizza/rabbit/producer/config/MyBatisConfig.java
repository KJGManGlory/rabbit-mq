package com.lizza.rabbit.producer.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Desc: 整合 MyBatis, 需要在 DataSource 创建完再加载
 * @author: lizza.liu
 * @date: 2022-02-06
 */
@Slf4j
@Configuration
@AutoConfigureAfter(value = {DataSourceConfig.class})
public class MyBatisConfig {

    @Resource
    private DataSource rabbitProducerDataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        // 创建 SqlSessionFactoryBean
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(rabbitProducerDataSource);

        // 创建解析器
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        try {
            factoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
            SqlSessionFactory factory = factoryBean.getObject();
            factory.getConfiguration().setCacheEnabled(true);

            return factory;
        } catch (Exception e) {
            log.error("create sql session factory exception", e);
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
