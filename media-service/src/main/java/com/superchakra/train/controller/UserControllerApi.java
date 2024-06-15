package com.superchakra.train.controller;


import com.superchakra.train.bo.UserBO;
import com.superchakra.train.reponse.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Tag(name = "media-user",description = "user服务接口")
public interface UserControllerApi extends HelloControllerApi{

    @Operation(method = "GET",summary = "用户使用用户名和密码登陆")
    @GetMapping("/login1")
    Result login1(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, InvalidKeySpecException;

    @Operation(method = "GET",summary = "用户使用手机号与验证码登陆")
    @GetMapping("/login2")
    Result login2(@RequestBody UserBO userBO,HttpServletRequest request, HttpServletResponse response);

    @Operation(method = "GET",summary = "用户使用手机号注册")
    @GetMapping("/register1")
    Result register1(@RequestBody UserBO userBO,HttpServletRequest request, HttpServletResponse response);

    @Operation(method = "GET",summary = "用户使用手机号一键登陆注册")
    @GetMapping("/register2")
    Result register2(@RequestBody UserBO userBO,HttpServletRequest request, HttpServletResponse response);

    @Operation(method = "GET",summary = "获取验证码")
    @GetMapping("/code")
    Result code(@RequestParam("phone") String phone,HttpServletRequest request,HttpServletResponse response) throws Exception;

}
