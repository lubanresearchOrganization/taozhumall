package com.lubanmall.catagoryserviceapi.command;
/**
 * Created by zyf on 2017/12/21
 */
public class ChangeCategoryParentCategoryDTO {

    private Long id;

    private Long parentId;


    public ChangeCategoryParentCategoryDTO(){

    }

    public ChangeCategoryParentCategoryDTO(Long id, Long parentId) {
        this.id = id;
        this.parentId = parentId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public Long getParentId() {
        return parentId;
    }
}
