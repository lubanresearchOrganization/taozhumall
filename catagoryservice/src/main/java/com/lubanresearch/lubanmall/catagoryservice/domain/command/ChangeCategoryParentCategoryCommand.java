package com.lubanresearch.lubanmall.catagoryservice.domain.command;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
/**
 * Created by zyf on 2017/12/22
 */
public class ChangeCategoryParentCategoryCommand {

    @NotNull
    private Long id;
    @NotNull
    private Long parentId;

    public ChangeCategoryParentCategoryCommand(Long id, Long parentId) {
        this.id = id;
        this.parentId = parentId;
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
}
