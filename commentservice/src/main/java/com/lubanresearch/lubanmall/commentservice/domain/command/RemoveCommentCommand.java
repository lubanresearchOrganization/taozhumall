package com.lubanresearch.lubanmall.commentservice.domain.command;

import javax.validation.constraints.NotNull;

/**
 * Created by hilbertcao on 2017/12/25.
 */
public class RemoveCommentCommand {

    @NotNull
    private Long id;

    public RemoveCommentCommand(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
