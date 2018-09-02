package com.lubanresearch.lubanmall.categoryservice.domain.command;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class AddCategoryCommand {

    @NotBlank
    private String name;

    @NotNull
    private Long parentId;

    public AddCategoryCommand(String name, Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
