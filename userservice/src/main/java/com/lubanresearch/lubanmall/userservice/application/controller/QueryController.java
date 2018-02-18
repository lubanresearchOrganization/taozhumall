package com.lubanresearch.lubanmall.userservice.application.controller;

import com.lubanresearch.lubanmall.common.exception.ServiceException;
import com.lubanresearch.lubanmall.userservice.domain.User;
import com.lubanresearch.lubanmall.userservice.infrastructure.persistence.db.mapper.UserMapper;
import com.lubanresearch.lubanmall.userservice.infrastructure.persistence.db.query.condition.UserQueryCondition;
import com.lubanresearch.lubanmall.userservice.infrastructure.util.MD5Util;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hilbertcao on 2017/12/16.
 */
@Controller
@RequestMapping("/v/0.1/users")
public class QueryController {

    @Autowired
    private UserMapper userMapper;


    @RequestMapping("/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") Long id){


        return userMapper.selectByPrimaryKey(id);
    }

    @RequestMapping("/")
    @ResponseBody
    public List<User> getUsers(@RequestParam(value = "ids",required = false) List<Long> ids){

        UserQueryCondition condition = new UserQueryCondition();
        condition.createCriteria().andIf(CollectionUtils.isNotEmpty(ids), new UserQueryCondition.Criteria.ICriteriaAdd() {
            @Override
            public UserQueryCondition.Criteria add(UserQueryCondition.Criteria add) {
                return add.andIdIn(ids);
            }
        });
        return userMapper.selectByExample(condition);
    }

    @RequestMapping("/authentication")
    @ResponseBody
    public User getAuthentication(@RequestParam("name") String name, @RequestParam("password")String password){


        UserQueryCondition userQueryCondition = new UserQueryCondition();
        userQueryCondition.createCriteria().andNameEqualTo(name);
        List<User> userList = userMapper.selectByExample(userQueryCondition);
        if(userList.isEmpty()){
            throw new ServiceException(500,"用户不存在");
        }
        if(userList.size()>1){
            throw new ServiceException(500,"存在同名用户");
        }
        User user = userList.get(0);
        if(!user.getPassword().equals(MD5Util.encode(password))){
            throw new ServiceException(500,"用户名或密码不正确");
        }
        return user;
    }

}
