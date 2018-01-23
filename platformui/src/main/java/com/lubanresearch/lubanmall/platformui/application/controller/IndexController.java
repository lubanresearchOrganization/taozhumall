package com.lubanresearch.lubanmall.platformui.application.controller;

import com.lubanresearch.lubanmall.platformui.infrastructure.remote.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author hilbert.cao
 */
@ApiIgnore
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = {"info","/go","/"})
    public String go() {
        return "redirect:index.html";
    }

}
