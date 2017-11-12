package com.lubanresearch.lubanmall.usercenter.application.controller;

import com.lubanresearch.lubanmall.usercenter.infrastructure.cache.Cache;
import com.lubanresearch.lubanmall.ssoclient.bean.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author hilbert.cao
 */
@ApiIgnore
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(path = {"/go","/"})
    @ResponseBody
    public String go() {
        return "success";
    }


    @RequestMapping(path = {"/checkServiceTicket"})
    @ResponseBody
    public Authentication checkServiceTicket(@RequestParam("st")String st) {

        return Cache.get(st);
    }

    @RequestMapping(path = {"/loginPage"})
    public String loginPage() {

        return "loginPage";
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
        String serviceTiket = System.currentTimeMillis()+"";
        Cache.put(serviceTiket,authentication);
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
                    callbackAfterDecode = callbackAfterDecode+"&st="+serviceTiket;
                }else{
                    callbackAfterDecode = callbackAfterDecode+"?st="+serviceTiket;
                }
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
