package com.lubanresearch.lubanmall.userservice.service;

import com.lubanresearch.lubanmall.userservice.domain.User;

/**
 * Created by hilbertcao on 2017/12/16.
 */
public interface UserService {

    User getById(Long id);
    User register(User user);

}
