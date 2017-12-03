package com.lubanresearch.lubanmall.customerui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hilbert.cao
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages={"com.lubanresearch.lubanmall.demoui"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}