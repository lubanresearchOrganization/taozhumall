package com.lubanresearch.lubanmall.ssoclient.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lubanresearch.lubanmall.ssoclient.bean.Authentication;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class SSOFilter implements Filter {
    public static final String AUTHENTICATION_SESSION_KEY = "authentication";
    public static final String SERVICE_TICKET_PARAM_KEY = "st";
    public String ssoServerLoginUrl;
    public String checkTicketUrl;

    public SSOFilter(){}
    public SSOFilter(String ssoServerLoginUrl,String checkTicketUrl){
        this.ssoServerLoginUrl = ssoServerLoginUrl;
        this.checkTicketUrl = checkTicketUrl;
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse HttpServletResponse = (HttpServletResponse)servletResponse;
        HttpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        HttpServletResponse.setHeader("Access-Control-Expose-Headers", "*");
        HttpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
        HttpServletResponse.setHeader("Access-Control-Allow-Methods", "*");

        //对options方法不拦截
        boolean isOptionsMethod = isOptionsMethod(servletRequest);
        if(isOptionsMethod){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        Boolean isNonPage = isNonPageRequest(servletRequest);
        String redirectSSOUrl = generateSSOUrl(servletRequest,ssoServerLoginUrl,isNonPage);
        if(containSSOServiceTicket(servletRequest)){

            String serviceTicket = extractTicket(servletRequest);
            if(!sessionContainsAuthentication(servletRequest)){
                Authentication authentication = checkServiceTicket(serviceTicket);
                if(authentication!=null){

                    putIntoSession(authentication,servletRequest);

                }else{
                    redirectSSOserver(servletResponse,filterChain,redirectSSOUrl,isNonPage);
                    return;
                }
            }
        }
        if(!sessionContainsAuthentication(servletRequest)){

            redirectSSOserver(servletResponse,filterChain,redirectSSOUrl,isNonPage);
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

    private void redirectSSOserver(ServletResponse servletResponse, FilterChain filterChain, String redirectSSOUrl, Boolean isNonPage) {



        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;

        if(isNonPage){

            try {
                httpServletResponse.getWriter().println("{\"redirect\":\""+redirectSSOUrl+"\"}");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        try {
            httpServletResponse.setHeader("Cache-Control", "no-store");
            httpServletResponse.setDateHeader("Expires", 0);
            httpServletResponse.setHeader("Prama", "no-cache");
            httpServletResponse.sendRedirect(redirectSSOUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateSSOUrl(ServletRequest servletRequest, String ssoServerUrl,boolean isNonPage) {
        String callbackUrl = generateCallbackUrl(servletRequest,isNonPage);
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
        if(serviceTicket!=null&&!"".equals(serviceTicket)){

            return serviceTicket;
        }
        serviceTicket = ((HttpServletRequest)servletRequest).getHeader(SERVICE_TICKET_PARAM_KEY);
        return serviceTicket;
    }

    private boolean containSSOServiceTicket(ServletRequest servletRequest) {

        String requestServiceTicket = servletRequest.getParameter(SERVICE_TICKET_PARAM_KEY);
        String headServiceTicket = ((HttpServletRequest)servletRequest).getHeader(SERVICE_TICKET_PARAM_KEY);
        return requestServiceTicket!=null||headServiceTicket!=null;
    }


    /**
     * 判断是否为Ajax请求
     * @param request   HttpServletRequest
     * @return
     */
    public static boolean isNonPageRequest(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String requestType = httpServletRequest.getHeader("request-client");
        return requestType != null && requestType.equals("non-page");
    }

    private boolean isOptionsMethod(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        return "options".equalsIgnoreCase(httpServletRequest.getMethod());
    }
    private String generateCallbackUrl(ServletRequest servletRequest,boolean isNonPage) {
        HttpServletRequest httpServletRequest = ((HttpServletRequest)servletRequest);
        String callBackUrl;
        if(isNonPage){
            callBackUrl = "";
        }else{
            callBackUrl = httpServletRequest.getScheme()+
                    "://"+
                    httpServletRequest.getServerName()
                    +((httpServletRequest.getServerPort()==80)?"":":"
                    +httpServletRequest.getServerPort())+httpServletRequest.getRequestURI()+
                    ((httpServletRequest.getQueryString()==null)?"":httpServletRequest.getQueryString());
        }
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
