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
    public Comment addComment(@RequestBody CommentDTO dto){

        return commandGateway.sendAndWait(new AddCommentCommand(dto.getProductId(),dto.getContent(),dto.getScore()));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public void removeComment(@PathVariable("id") Long id){

        commandGateway.sendAndWait(new RemoveCommentCommand(id));
    }
}
