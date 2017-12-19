package com.lubanresearch.lubanmall.userservice.domain.repository.impl;

import com.lubanresearch.lubanmall.userservice.domain.User;
import com.lubanresearch.lubanmall.userservice.domain.repository.UserRepository;
import com.lubanresearch.lubanmall.userservice.infrastructure.persistence.db.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by hilbertcao on 2017/12/16.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
