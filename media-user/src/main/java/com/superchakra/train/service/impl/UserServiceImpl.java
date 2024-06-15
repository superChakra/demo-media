package com.superchakra.train.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superchakra.train.entity.User;
import com.superchakra.train.mapper.UserMapper;
import com.superchakra.train.reponse.Result;
import com.superchakra.train.reponse.ResultStatusEnum;
import com.superchakra.train.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Result addUser(User newUser) {
        //执行插入用户
        int insertResult = userMapper.insert(newUser);
        //判断用户是否插入成功
        if (insertResult > 0) {
            return new Result().success(ResultStatusEnum.SUCCESS,null);
        }else {
            return new Result().error(ResultStatusEnum.ERROR,null);
        }
    }

    @Override
    public User queryUserByUsername(String username) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    public User queryUserByPhone(String phone) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
    }
}
