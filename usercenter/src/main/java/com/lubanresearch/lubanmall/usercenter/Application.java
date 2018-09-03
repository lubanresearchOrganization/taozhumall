package com.lubanresearch.lubanmall.usercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author hilbert.cao
 */

@EnableFeignClients
@EnableWebMvc
@SpringBootApplication
@ComponentScan(basePackages={"com.lubanresearch.lubanmall.usercenter"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}