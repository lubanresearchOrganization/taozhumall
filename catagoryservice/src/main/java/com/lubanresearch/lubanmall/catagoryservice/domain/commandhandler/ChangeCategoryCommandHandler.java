package com.lubanresearch.lubanmall.catagoryservice.domain.commandhandler;

import com.lubanresearch.lubanmall.catagoryservice.domain.Category;
import com.lubanresearch.lubanmall.catagoryservice.domain.CategoryRepository;
import com.lubanresearch.lubanmall.catagoryservice.domain.command.AddCategoryCommand;
import com.lubanresearch.lubanmall.catagoryservice.domain.command.ChangeCategoryNameCommand;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zyf on 2017/12/21
 */
@Component
public class ChangeCategoryCommandHandler {


    @Autowired
    private CategoryRepository categoryRepository;

    @CommandHandler
    public Category handle(ChangeCategoryNameCommand command)throws ServiceException {

        Category existCategory = categoryRepository.getById(command.getId());
        if(existCategory==null){
            throw new ServiceException(500,"类目不存在");
        }


        existCategory.setName(command.getName());

        categoryRepository.update(existCategory);

        return categoryRepository.getById(command.getId());
    }
}
