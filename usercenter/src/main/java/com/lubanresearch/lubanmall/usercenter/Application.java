package com.lubanresearch.lubanmall.usercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hilbert.cao
 */
/*@EnableDiscoveryClient
@EnableFeignClients*/
@SpringBootApplication
@ComponentScan(basePackages={"com.lubanresearch.lubanmall.usercenter"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}