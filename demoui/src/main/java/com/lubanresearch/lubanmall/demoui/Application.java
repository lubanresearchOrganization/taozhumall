package com.lubanresearch.lubanmall.demoui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableRedisHttpSession
@ComponentScan(basePackages={"com.lubanresearch.lubanmall.demoui"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}