package com.lubanresearch.lubanmall.commentservice.domain.command;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by hilbertcao on 2017/12/25.
 */
public class AddCommentCommand {

    @NotNull
    private Long orderId;

    @NotBlank
    private String content;

    @NotNull
    private Byte score;

    public AddCommentCommand(Long orderId, String content, Byte score) {
        this.orderId = orderId;
        this.content = content;
        this.score = score;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getContent() {
        return content;
    }

    public Byte getScore() {
        return score;
    }
}
