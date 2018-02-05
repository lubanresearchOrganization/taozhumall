package com.lubanresearch.lubanmall.customerui.application.controller;

import com.lubanmall.commentserviceapi.CommentDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.customerui.infrastructure.remote.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2018/2/5.
 */
@RequestMapping("/v/0.1/comments")
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public CommentDTO addComment(@RequestBody CommentDTO dto){

        return commentService.addComment(dto);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Pagination<CommentDTO> findComments(
            @RequestParam("orderId") Long orderId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {

        return commentService.findComments(orderId,page,size);
    }
}
