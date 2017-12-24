package com.lubanresearch.lubanmall.commentservice.application.controller;

import com.lubanmall.commentserviceapi.CommentDTO;
import com.lubanresearch.lubanmall.commentservice.domain.Comment;
import com.lubanresearch.lubanmall.commentservice.domain.command.AddCommentCommand;
import com.lubanresearch.lubanmall.commentservice.domain.command.RemoveCommentCommand;
import com.lubanresearch.lubanmall.common.bean.Response;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hilbertcao on 2017/12/25.
 */

@Controller
@RequestMapping("/v/0.1/comments")
public class CommandController {

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public Response<Comment> addShop(@RequestBody CommentDTO dto){

        Comment shop = commandGateway.sendAndWait(new AddCommentCommand(dto.getOrderId(),dto.getContent(),dto.getScore()));
        return new Response<>(0,"success",shop);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Response removeShop(@PathVariable("id") Long id){

        commandGateway.sendAndWait(new RemoveCommentCommand(id));
        return new Response<>(0,"success",null);
    }
}
