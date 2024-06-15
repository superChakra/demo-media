package com.superchakra.train.reponse;

import lombok.Getter;


@Getter
public enum ResultStatusEnum {

    SUCCESS("200", true, "成功!"),
    ERROR("500", false, "系统错误!"),

    USER_REGISTER_SUCCESS("200",true,"用户注册成功,请登陆"),


    // 50x 用户相关错误
    USER_REQUEST_TOO_FREQUENT("551", false, "用户请求验证码过于频繁，请60秒后再试"),
    USER_NOT_REGISTERED("553", false, "用户未注册"),
    REGISTER_CODE_ERROR("554",false,"验证码错误"),
    USER_REGISTERED_ERROR("500",false,"手机号已注册"),
    USER_ACCOUNT_PASSWORD_ERROR("500",false,"账号或密码错误"),
    USER_PHONE_CODE_ERROR("500",false,"手机号或验证码错误");


    private final String code;
    private final boolean data;
    private final String msg;

    ResultStatusEnum(String code, boolean data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }


}
