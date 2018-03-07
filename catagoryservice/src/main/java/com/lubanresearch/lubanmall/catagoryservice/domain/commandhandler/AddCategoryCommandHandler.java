package com.lubanresearch.lubanmall.catagoryservice.domain.commandhandler;

import com.lubanresearch.lubanmall.catagoryservice.domain.Category;
import com.lubanresearch.lubanmall.catagoryservice.domain.CategoryRepository;
import com.lubanresearch.lubanmall.catagoryservice.domain.command.AddCategoryCommand;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * Created by zyf on 2017/12/21
 */
@Component
public class AddCategoryCommandHandler {


    @Autowired
    private CategoryRepository categoryRepository;

    @CommandHandler
    public Category handle(AddCategoryCommand command)throws ServiceException {

        Category existCategory = categoryRepository.getByName(command.getName());
        if(existCategory!=null){
            throw new ServiceException(500,"类目已经存在");
        }

        Category category = new Category();

        category.setName(command.getName());
        category.setParentId(command.getParentId());

        return categoryRepository.addCatagory(category);
    }
}
