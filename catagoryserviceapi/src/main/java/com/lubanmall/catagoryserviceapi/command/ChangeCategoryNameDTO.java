package com.lubanmall.catagoryserviceapi.command;
/**
 * Created by zyf on 2017/12/21
 */
public class ChangeCategoryNameDTO {

    private Long id;

    private String name;


    public ChangeCategoryNameDTO(){

    }

    public ChangeCategoryNameDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
