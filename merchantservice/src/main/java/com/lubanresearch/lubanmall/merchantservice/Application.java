package com.lubanresearch.lubanmall.merchantservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author hilbert.cao
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages={"com.lubanresearch.lubanmall.merchantservice"})
@MapperScan(basePackages="com.lubanresearch.lubanmall.merchantservice.infrastructure.persistence.db.mapper")
@EnableJpaRepositories(basePackages = "com.lubanresearch.lubanmall.merchantservice")
@EntityScan(basePackages = "com.lubanresearch.lubanmall.merchantservice")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}