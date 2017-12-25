package com.lubanmall.catagoryserviceapi.bean;

public class CategoryDTO {

    private Long id;

    private String name;

    private Long parentId;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
