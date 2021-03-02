package com.example.hotelSpring.configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource(){
        DataSourceBuilder dataSourceBuilder=DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/postgres?stringtype=unspecified");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("QWer1234");
        return dataSourceBuilder.build();
    }

}