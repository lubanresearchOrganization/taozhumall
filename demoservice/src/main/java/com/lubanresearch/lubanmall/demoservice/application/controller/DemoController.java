package com.lubanresearch.lubanmall.demoservice.application.controller;

import com.lubanresearch.lubanmall.common.bean.Response;
import com.lubanresearch.lubanmall.demoservice.domain.Demo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hilbert.cao
 */
@Api(tags = "demo" , description = "【示例】")
@Controller
@RequestMapping("/demos")
public class DemoController{


    @ApiOperation(produces = "application/json",value = "获取Demo")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response<Demo> get(@PathVariable("id") Long id) {
        Demo demo = new Demo();
        demo.setId(id);
        return new Response(0,"成功",demo);
    }
}
