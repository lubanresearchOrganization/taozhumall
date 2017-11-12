package com.lubanresearch.lubanmall.demoui.application.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lubanresearch.lubanmall.ssoclient.bean.Authentication;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*",filterName = "ssoFilter")
public class SSOFilter implements Filter {
    public static final String AUTHENTICATION_SESSION_KEY = "authentication";
    public static final String SERVICE_TICKET_PARAM_KEY = "st";
    public String ssoServerLoginUrl = "http://localhost:4002/loginPage";
    public String checkTicketUrl = "http://localhost:4002/checkServiceTicket";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String redirectSSOUrl = generateSSOUrl(servletRequest,ssoServerLoginUrl);
        if(containSSOServiceTicket(servletRequest)){

            String serviceTicket = extractTicket(servletRequest);
            if(!sessionContainsAuthentication(servletRequest)){
                Authentication authentication = checkServiceTicket(serviceTicket);
                if(authentication!=null){

                    putIntoSession(authentication,servletRequest);

                }else{
                    redirectSSOserver(servletResponse,filterChain,redirectSSOUrl);
                    return;
                }
            }
        }
        if(!sessionContainsAuthentication(servletRequest)){

            redirectSSOserver(servletResponse,filterChain,redirectSSOUrl);
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    private void putIntoSession(Authentication authentication, ServletRequest servletRequest) {
        HttpSession session  = ((HttpServletRequest)servletRequest).getSession();
        if(session==null){

        }
        session.setAttribute(AUTHENTICATION_SESSION_KEY,authentication);
    }

    private void redirectSSOserver(ServletResponse servletResponse, FilterChain filterChain, String redirectSSOUrl) {

        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
        try {
            httpServletResponse.setHeader("Cache-Control", "no-store");
            httpServletResponse.setDateHeader("Expires", 0);
            httpServletResponse.setHeader("Prama", "no-cache");
            httpServletResponse.sendRedirect(redirectSSOUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateSSOUrl(ServletRequest servletRequest, String ssoServerUrl) {
        String callbackUrl = generateCallbackUrl(servletRequest);
        StringBuilder result = new StringBuilder(ssoServerUrl);
        result.append("?callback=");
        try {
            result.append(URLEncoder.encode(callbackUrl,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    private Authentication checkServiceTicket(String serviceTicket) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(checkTicketUrl+"?st="+serviceTicket)
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(result,Authentication.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String extractTicket(ServletRequest servletRequest) {

        String serviceTicket = servletRequest.getParameter(SERVICE_TICKET_PARAM_KEY);
        return serviceTicket;
    }

    private boolean containSSOServiceTicket(ServletRequest servletRequest) {

        String serviceTicket = servletRequest.getParameter(SERVICE_TICKET_PARAM_KEY);
        return serviceTicket!=null;
    }


    private String generateCallbackUrl(ServletRequest servletRequest) {

        HttpServletRequest httpServletRequest = ((HttpServletRequest)servletRequest);
        String callBackUrl =

                httpServletRequest.getScheme()+
                        "://"+
                httpServletRequest.getServerName()
                        +((httpServletRequest.getServerPort()==80)?"":":"
                        +httpServletRequest.getServerPort())
                +httpServletRequest.getRequestURI()+
                ((httpServletRequest.getQueryString()==null)?"":httpServletRequest.getQueryString());
        return callBackUrl;
    }

    private boolean sessionContainsAuthentication(ServletRequest servletRequest) {

        HttpSession session  = ((HttpServletRequest)servletRequest).getSession();
        Authentication authentication = (Authentication) session.getAttribute(AUTHENTICATION_SESSION_KEY);
        return authentication!=null;
    }

    @Override
    public void destroy() {

    }
}
