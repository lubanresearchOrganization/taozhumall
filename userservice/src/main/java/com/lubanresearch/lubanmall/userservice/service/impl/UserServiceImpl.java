package com.lubanresearch.lubanmall.userservice.service.impl;

import com.lubanresearch.lubanmall.userservice.domain.User;
import com.lubanresearch.lubanmall.userservice.domain.repository.UserRepository;
import com.lubanresearch.lubanmall.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hilbertcao on 2017/12/16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User register(User user) {
        return null;
    }
}
