package com.superchakra.train.helper;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class StringHelper {

    private  final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private  final SecureRandom RANDOM = new SecureRandom();
    /**
     * TODO：用于生成64位随机的数字+字母的字符串
     */
    public  String generateRandomString(Integer length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    /**
     * TODO: bucketName: media-demo-bucket
     *        endpoint: https://oss-cn-shanghai.aliyuncs.com
     *            Url: https://media-demo-bucket.oss-cn-shanghai.aliyuncs.com/
     */

    public  String generateUrlString(String bucketName,String endpoint) {
       return new StringBuilder()
               .append("https://")
               .append(bucketName)
               .append(".")
               .append(endpoint.substring(8))
               .append("/")
               .toString();
    }


    public String generateVerificationCode() {
        // 定义生成验证码的位数
        int codeLength = 4;

        // 随机数生成器
        Random random = new Random();

        // 用于存储生成的验证码
        StringBuilder codeBuilder = new StringBuilder();

        // 生成指定位数的随机数字
        for (int i = 0; i < codeLength; i++) {
            // 生成随机数字，范围在0~9之间
            int digit = random.nextInt(10); // 生成0到9之间的随机数
            codeBuilder.append(digit); // 将随机数添加到验证码中
        }

        // 返回生成的验证码
        return codeBuilder.toString();
    }
}
