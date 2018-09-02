package com.lubanresearch.lubanmall.searchservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hilbert.cao
 */

@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages={"com.lubanresearch.lubanmall.searchservice"})
@MapperScan("com.lubanresearch.lubanmall.searchservice.infrastructure.persistence.db")
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}