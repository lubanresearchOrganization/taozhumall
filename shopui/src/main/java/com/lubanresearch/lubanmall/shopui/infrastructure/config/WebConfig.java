package com.lubanresearch.lubanmall.shopui.infrastructure.config;
import com.lubanresearch.lubanmall.ssoclient.filter.SSOFilter;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.cloud.netflix.feign.support.ResponseEntityDecoder;
import org.springframework.cloud.netflix.feign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by hilbertcao on 2017/12/2.
 */
//@Configuration
public class WebConfig {
    @Value("${sso.ssoServerLoginUrl}")
    private String ssoServerLoginUrl;
    @Value("${sso.checktiketUrl}")
    private String checkTicketUrl;

    @Bean
    public FilterRegistrationBean testFilterRegistration() {

        SSOFilter ssoFilter = new SSOFilter(ssoServerLoginUrl,checkTicketUrl);
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(ssoFilter);
        registration.addUrlPatterns("/*");
        registration.setName("ssoFilter");
        registration.setOrder(1);
        return registration;
    }

}
