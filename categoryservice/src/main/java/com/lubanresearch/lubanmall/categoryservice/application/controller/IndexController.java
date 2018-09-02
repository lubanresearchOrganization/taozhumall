package com.lubanresearch.lubanmall.categoryservice.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author hilbert.cao
 */
@ApiIgnore
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(path = {"info","/go","/"})
    @ResponseBody
    public String go() {
        return "success";
    }
}
