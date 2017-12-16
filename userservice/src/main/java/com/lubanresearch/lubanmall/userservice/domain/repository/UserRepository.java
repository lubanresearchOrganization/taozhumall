package com.lubanresearch.lubanmall.userservice.domain.repository;

import com.lubanresearch.lubanmall.userservice.domain.User;

/**
 * Created by hilbertcao on 2017/12/16.
 */
public interface UserRepository {
    User getById(Long id);
}
