package com.lubanresearch.lubanmall.merchantservice.domain.command;

import javax.validation.constraints.NotNull;

/**
 * Created by hilbertcao on 2017/12/25.
 */
public class RemoveShopCommand {
    @NotNull
    private Long id;

    public RemoveShopCommand(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
