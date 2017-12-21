package com.lubanresearch.lubanmall.catagoryservice.domain.command;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class ChangeCategoryNameCommand {
    @NotNull
    private Long id;
    @NotBlank
    private String name;


    public ChangeCategoryNameCommand(Long id, String name) {
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
