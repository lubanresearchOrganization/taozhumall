package com.lubanresearch.lubanmall.platformui.infrastructure.config;
import org.springframework.context.annotation.Configuration;
/**
 * Created by hilbertcao on 2017/12/2.
 */
@Configuration
public class WebConfig {
    public String ssoServerLoginUrl = "http://localhost:4002/loginPage";
    public String checkTicketUrl = "http://localhost:4002/checkServiceTicket";
}
