package com.lubanresearch.lubanmall.usercenter.application.controller;

import com.lubanresearch.lubanmall.usercenter.infrastructure.cache.Cache;
import com.lubanresearch.lubanmall.ssoclient.bean.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hilbert.cao
 */
@ApiIgnore
@Controller
@RequestMapping("/")
public class IndexController {

    @Value("${tgt.expiry}")
    private int tgtExpiry;
    private Map stTGTMapping = new HashMap();

    @RequestMapping(path = {"/go","/"})
    @ResponseBody
    public String go() {
        return "success";
    }


    @RequestMapping(path = {"/checkServiceTicket"})
    @ResponseBody
    public Authentication checkServiceTicket(@RequestParam("st")String st) {

        String tgt = (String) stTGTMapping.get(st);
        stTGTMapping.remove(st);
        return Cache.get(tgt);
    }

    @RequestMapping(path = {"/loginPage"})
    public String loginPage(HttpServletRequest request,
                            HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        if(cookies!=null){

            for (Cookie cookie:cookies){
                if("TGT".equals(cookie.getName())){
                    String tgt = cookie.getValue();
                    Authentication authentication = Cache.get(tgt);
                    if(authentication!=null){

                        String callback = request.getParameter("callback");
                        if(callback!=null){

                            String serviceTicket = System.currentTimeMillis()+"";
                            stTGTMapping.put(serviceTicket,tgt);
                            try {
                                String callbackAfterDecode = URLDecoder.decode(callback,"UTF-8");
                                if(callbackAfterDecode.contains("?")){
                                    callbackAfterDecode = callbackAfterDecode+"&st="+serviceTicket;
                                }else{
                                    callbackAfterDecode = callbackAfterDecode+"?st="+serviceTicket;
                                }
                                return "redirect:"+callbackAfterDecode;
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        }
        return "login";
    }


    @RequestMapping(path = {"/login"})
    public String login(
            @RequestParam("user")String user,
            @RequestParam("password")String password,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        Authentication authentication = new Authentication();
        authentication.setEmail("111@11.com");
        authentication.setUserId(1L);
        authentication.setUserName(user);
        authentication.setPhone("17700000000");
        String ticketGrantingTicket = System.currentTimeMillis()+"";
        String serviceTicket = System.currentTimeMillis()+"";
        Cache.put(ticketGrantingTicket,authentication);
        stTGTMapping.put(serviceTicket,ticketGrantingTicket);
        String callback = null;
        String referer = request.getHeader("Referer");
       if(referer!=null){
           if(referer.contains("?")){
               String paramPairs =  referer.substring(referer.indexOf("?")+1);
               String[] paramPairList = paramPairs.split("&");
               for(String paramPair:paramPairList){
                   if(paramPair.contains("callback=")){
                       callback = paramPair.substring("callback=".length());
                       break;
                   }
               }
           }


       }
        if(callback!=null){
            try {
                String callbackAfterDecode = URLDecoder.decode(callback,"UTF-8");
                if(callbackAfterDecode.contains("?")){
                    callbackAfterDecode = callbackAfterDecode+"&st="+serviceTicket;
                }else{
                    callbackAfterDecode = callbackAfterDecode+"?st="+serviceTicket;
                }
                Cookie cookie = new Cookie("TGT",ticketGrantingTicket);
                cookie.setMaxAge(tgtExpiry);
                response.addCookie(cookie);
                return "redirect:"+callbackAfterDecode;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "afterLogin";
    }

    @RequestMapping(path = {"/logout"})
    public String logout() {

        return "loginPage";
    }


}
