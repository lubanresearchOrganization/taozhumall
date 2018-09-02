package com.lubanresearch.lubanmall.categoryservice.domain.commandhandler;


import com.lubanresearch.lubanmall.categoryservice.domain.Category;
import com.lubanresearch.lubanmall.categoryservice.domain.CategoryRepository;
import com.lubanresearch.lubanmall.categoryservice.domain.command.UpdateCategoryCommand;
import com.lubanresearch.lubanmall.common.exception.ServiceException;
import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zyf on 2017/12/25
 */
@Component
public class UpdateCategoryCommandHandler {


    @Autowired
    private CategoryRepository categoryRepository;

    @CommandHandler
    public Category handle(UpdateCategoryCommand command) throws ServiceException {

        Category existCategory = categoryRepository.getById(command.getId());
        if (existCategory == null) {
            throw new ServiceException(500, "类目不存在");
        }


        Long parentId = command.getParentId();
        String name = command.getName();

        if (parentId != null) {


            if (!parentId.equals(command.getId())) {

                existCategory.setParentId(parentId);

            } else {

                throw new ServiceException(500, "类目id不能等于父类目id");

            }

        }

        if (!StringUtils.isEmpty(name)) {


            if (!isCategoryNameExist(name)) {

                existCategory.setName(name);

            } else {

                throw new ServiceException(500, "类目名称已存在");

            }


        }


        categoryRepository.update(existCategory);

        return categoryRepository.getById(command.getId());

    }

    private boolean isCategoryNameExist(String name) {

        if (categoryRepository.getByName(name) != null) {

            return true;

        }

        return false;
    }


}
