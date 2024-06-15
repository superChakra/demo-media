package com.superchakra.train.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.superchakra.train.entity.User;
import com.superchakra.train.reponse.Result;

public interface UserService extends IService<User> {
    Result addUser(User newUser);

    User queryUserByUsername(String username);

    User queryUserByPhone(String phone);
}
