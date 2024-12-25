package com.own.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
@EnableAsync
@EnableScheduling
@EnableDiscoveryClient
@MapperScan("com.own.web.dao.dao")
@EnableConfigurationProperties
public class MyDemoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyDemoWebApplication.class, args);

    }

}
