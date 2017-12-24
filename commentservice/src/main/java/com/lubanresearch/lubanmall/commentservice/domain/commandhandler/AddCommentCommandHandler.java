package com.lubanresearch.lubanmall.commentservice.domain.commandhandler;

import com.lubanresearch.lubanmall.commentservice.domain.Comment;
import com.lubanresearch.lubanmall.commentservice.domain.CommentRepository;
import com.lubanresearch.lubanmall.commentservice.domain.command.AddCommentCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by hilbertcao on 2017/12/25.
 */
@Component
public class AddCommentCommandHandler {

    @Autowired
    private CommentRepository commentRepository;

    @CommandHandler
    public Comment handler(AddCommentCommand command){
        Comment comment = new Comment();
        comment.setId(System.currentTimeMillis());
        comment.setCreateTime(new Date());
        comment.setContent(command.getContent());
        comment.setScore(command.getScore());
        comment.setOrderId(command.getOrderId());
        return commentRepository.get(comment.getId());
    }
}
