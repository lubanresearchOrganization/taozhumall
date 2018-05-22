package com.lubanresearch.lubanmall.register;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hilbert.cao
 */
@EnableEurekaServer
@SpringBootApplication
@ComponentScan(basePackages={"com.lubanresearch.lubanmall.register"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}