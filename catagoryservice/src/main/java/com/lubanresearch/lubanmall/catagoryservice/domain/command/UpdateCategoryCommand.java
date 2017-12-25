package com.lubanresearch.lubanmall.catagoryservice.domain.command;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
/**
 * Created by zyf on 2017/12/22
 */
public class UpdateCategoryCommand {

    @NotNull
    private Long id;

    private Long parentId;

    private String name;

    public UpdateCategoryCommand(Long id, Long parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
