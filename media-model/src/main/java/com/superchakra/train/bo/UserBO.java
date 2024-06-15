package com.superchakra.train.bo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserBO {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "手机号不能为空")
    private String phone;
    @NotBlank(message = "验证码不能为空")
    private String verificationCode;
}
