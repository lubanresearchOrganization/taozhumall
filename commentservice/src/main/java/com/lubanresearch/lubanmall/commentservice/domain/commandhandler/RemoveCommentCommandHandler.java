package com.lubanresearch.lubanmall.commentservice.domain.commandhandler;

import com.lubanresearch.lubanmall.commentservice.domain.CommentRepository;
import com.lubanresearch.lubanmall.commentservice.domain.command.RemoveCommentCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hilbertcao on 2017/12/25.
 */
@Component
public class RemoveCommentCommandHandler {

    @Autowired
    private CommentRepository commentRepository;

    @CommandHandler
    public void handler(RemoveCommentCommand command){
        commentRepository.remove(command.getId());
    }
}
