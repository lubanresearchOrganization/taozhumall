package com.lubanresearch.lubanmall.commentservice.domain.command;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by hilbertcao on 2017/12/25.
 */
public class AddCommentCommand {

    @NotNull
    private Long productId;

    @NotBlank
    private String content;

    @NotNull
    private Byte score;

    public AddCommentCommand(Long productId, String content, Byte score) {
        this.productId = productId;
        this.content = content;
        this.score = score;
    }

    public Long getProductId() {
        return productId;
    }

    public String getContent() {
        return content;
    }

    public Byte getScore() {
        return score;
    }
}
