package com.lubanresearch.lubanmall.userservice.domain;

import com.lubanresearch.lubanmall.userservice.infrastructure.persistence.db.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by hilbertcao on 2017/12/16.
 */
@Repository
public class UserRepository {
    @Autowired
    private UserMapper userMapper;
    public User getById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public User addUser(User user) {

        user.setCreateTime(new Date());
        user.setId(System.currentTimeMillis());
        userMapper.insertSelective(user);
        return user;
    }

    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
}
