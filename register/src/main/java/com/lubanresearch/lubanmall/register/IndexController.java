package com.lubanresearch.lubanmall.register;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hilbert.cao
 */
@Controller
@RequestMapping("/")
public class IndexController {


    @RequestMapping(path = {"/go"})
    public @ResponseBody String go() {
        return "success";
    }

}
