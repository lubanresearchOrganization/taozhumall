package com.lubanresearch.lubanmall.shopui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hilbert.cao
 */

@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages={"com.lubanresearch.lubanmall.shopui"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}