package com.lubanresearch.lubanmall.shopui.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Created by hilbertcao on 2017/12/2.
 */
@Configuration
public class WebConfig {
    @Value("${sso.ssoServerLoginUrl}")
    private String ssoServerLoginUrl;
    @Value("${sso.checktiketUrl}")
    private String checkTicketUrl;

//    @Bean
//    public FilterRegistrationBean testFilterRegistration() {
//
//        SSOFilter ssoFilter = new SSOFilter(ssoServerLoginUrl,checkTicketUrl);
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(ssoFilter);
//        registration.addUrlPatterns("/*");
//        registration.setName("ssoFilter");
//        registration.setOrder(1);
//        return registration;
//    }
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1
        corsConfiguration.addAllowedHeader("*"); // 2
        corsConfiguration.addAllowedMethod("*"); // 3
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }
}
