package com.lubanresearch.lubanmall.merchantservice.domain.command;

import javax.validation.constraints.NotNull;

/**
 * Created by hilbertcao on 2017/12/25.
 */
public class RemoveProductCommand {
    @NotNull
    private Long id;

    public RemoveProductCommand(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
