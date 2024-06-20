package com.superchakra.train.controller;

import com.superchakra.train.bo.UserBO;
import com.superchakra.train.entity.User;
import com.superchakra.train.helper.*;
import com.superchakra.train.reponse.Result;
import com.superchakra.train.reponse.ResultStatusEnum;
import com.superchakra.train.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.TimeUnit;

@RestController
public class UserController implements UserControllerApi {
    @Resource
    private UserService userService;
    @Resource
    private EncryptHelper encryptHelper;
    @Resource
    private JwtHelper jwtHelper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private ApiHelper apiHelper;
    @Resource
    private IpAddressHelper ipAddressHelper;
    /**
     * TODO:账号密码登陆
     */

    @Override
    public Result login1(UserBO userBO, HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, InvalidKeySpecException {

        User user = userService.queryUserByUsername(userBO.getUsername());

        if (user != null && user.getPassword().equals(encryptHelper.hashPassword(userBO.getPassword()))) {
            String token = jwtHelper.generateToken(user.getUsername());
            jwtHelper.generateCookie(response, token);
            return new Result().success(ResultStatusEnum.SUCCESS, null);
        }

        return new Result().error(ResultStatusEnum.USER_ACCOUNT_PASSWORD_ERROR, null);
    }

    @Override
    public Result login2(UserBO userBO, HttpServletRequest request, HttpServletResponse response) {

        if (stringRedisTemplate.hasKey(userBO.getPhone()) &&
                stringRedisTemplate.opsForValue().get(userBO.getPhone()).equals(userBO.getVerificationCode())) {

            String token = jwtHelper.generateToken(userBO.getUsername());
            jwtHelper.generateCookie(response, token);
            return new Result().success(ResultStatusEnum.SUCCESS, null);
        }
        return new Result().error(ResultStatusEnum.USER_PHONE_CODE_ERROR, null);
    }

    @Override
    public Result register1(UserBO userBO, HttpServletRequest request, HttpServletResponse response) {
        //查询该手机号是否注册过
        User user = userService.queryUserByPhone(userBO.getPhone());

        //判断手机号是否注册过
        if (user != null) {
            return new Result().error(ResultStatusEnum.USER_REGISTERED_ERROR, null);
        }

        //判断验证码是否正确
        if (stringRedisTemplate.opsForValue().get(userBO.getPhone()).equals(userBO.getVerificationCode())) {
            //进行用户注册逻辑
            User newUser = new User();
            newUser.setPhone(userBO.getPhone());
            newUser.setUsername(userBO.getUsername());
            return userService.addUser(newUser);
        } else {
            return new Result().error(ResultStatusEnum.USER_PHONE_CODE_ERROR, null);
        }
    }

    @Override
    public Result register2(UserBO userBO, HttpServletRequest request, HttpServletResponse response) {
        //执行注册逻辑
        Result result = register1(userBO, request, response);

        if ("200".equals(result.getCode())) {
            String token = jwtHelper.generateToken(userBO.getPhone());
            jwtHelper.generateCookie(response, token);
            return new Result().success(ResultStatusEnum.SUCCESS, null);
        } else {
            return new Result().error(ResultStatusEnum.ERROR, null);
        }

    }

    @Override
    public Result code(String phone, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String clientIp = ipAddressHelper.getClientIp(request);
        stringRedisTemplate.opsForValue().set(clientIp, phone, 60, TimeUnit.SECONDS);
        String verificationCode = apiHelper.sendSms(phone);
        stringRedisTemplate.opsForValue().set(phone, verificationCode, 5, TimeUnit.MINUTES);
        return new Result().success(ResultStatusEnum.SUCCESS, null);
    }

    @Override
    public Result hello(String name) {
        return new Result().success(ResultStatusEnum.SUCCESS, "hello " + name);
    }
}
