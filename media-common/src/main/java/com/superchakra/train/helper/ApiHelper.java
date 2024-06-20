package com.superchakra.train.helper;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.tea.TeaException;
import com.superchakra.train.resource.ApiResource;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
public class ApiHelper {

    @Resource
    private ApiResource apiResource;
    @Resource
    private StringHelper stringHelper;


    /**
     * TODO:使用阿里云提供的oss服务,实现上传简单文件,以byte数组的形式
     *
     * @param userId
     * @param file
     * @return TODO:返回值是文件的实际地址,可以通过浏览器直接访问
     * @throws com.aliyuncs.exceptions.ClientException
     */
    public String uploadByteStream(String userId, String fileType, MultipartFile file) throws com.aliyuncs.exceptions.ClientException {
        // Endpoint
        String endpoint = apiResource.getEndpoint();
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        // 填写Bucket名称
        String bucketName = apiResource.getBucketName();
        // 填写Object完整路径，完整路径中不能包含Bucket名称
        String objectName =
                new StringBuilder().append(apiResource.getObjectName())
                        .append(userId)
                        .append("/")
                        .append(fileType)
                        .append("/")
                        .append(stringHelper.generateRandomString(32))
                        .append(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")))
                        .toString();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

        try {
            // 填写Byte数组。
            byte[] content = file.getBytes();
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(content));

            // 创建PutObject请求。
            PutObjectResult result = ossClient.putObject(putObjectRequest);

        } catch (OSSException oe) {
            System.out.println("捕获了一个OSSException异常，这意味着你的请求进入了OSS, "
                    + "但由于某种原因被拒绝了，并给出了错误的响应.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("捕获了ClientException，这意味着遇到了客户端 "
                    + "试图与OSS沟通时出现严重的内部问题,"
                    + "比如不能访问网络.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return new StringBuilder()
                .append(stringHelper.generateUrlString(bucketName, endpoint))
                .append(objectName)
                .toString();
    }

    /**
     * TODO: 使用阿里云提供的oss服务,实现上传简单文件,以网络流的形式
     *
     * @param userId
     * @param file
     * @return TODO:返回值是文件的实际地址,可以通过浏览器直接访问
     * @throws com.aliyuncs.exceptions.ClientException
     */
    public String uploadNetworkStream(String userId, String fileType, MultipartFile file) throws com.aliyuncs.exceptions.ClientException {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = apiResource.getEndpoint();
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        // 填写Bucket名称，
        String bucketName = apiResource.getBucketName();
        // 填写Object完整路径，完整路径中不能包含Bucket名称，
        String objectName =
                new StringBuilder().append(apiResource.getObjectName())
                        .append(userId)
                        .append("/")
                        .append(fileType)
                        .append("/")
                        .append(stringHelper.generateRandomString(32))
                        .append(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")))
                        .toString();

        // 填写网络流地址。
        //String url = "https://www.aliyun.com/";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

        try {
            /**
             * TODO:这里我注释掉原来方法，可以看出原来的方法是通过url地址获取一个inputStream流,
             *  由于一盘用户前端传过来的文件一般是不在服务器上的，所以很难获取文件的url地址
             *  然而我们传进来的file可以直接获取一个inputStream流
             *  所以这个网络流文件上传也是可以用的
             */
            // InputStream inputStream = new URL(url).openStream();

            InputStream inputStream = file.getInputStream();
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
            // 创建PutObject请求。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return new StringBuilder()
                .append(stringHelper.generateUrlString(bucketName, endpoint))
                .append(objectName)
                .toString();
    }

    /**
     * TODO:初始化短信服务
     *
     * @return
     * @throws Exception
     */
    public com.aliyun.dysmsapi20170525.Client createClient() throws Exception {

        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID。
                .setAccessKeyId(apiResource.getAccessKeyId())
                // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_SECRET。
                .setAccessKeySecret(apiResource.getAccessKeySecret());

        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    /**
     * TODO:配置短信服务的信息
     *      例： 接收者的手机号、发送者的阿里云服务配置（签名、模板、验证码样式等）
     *
     * @param phone
     * @param code
     * @throws Exception
     */
    public void SmsService(String phone, String code) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = createClient();
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName("查克拉")
                .setTemplateCode("SMS_465986333")
                .setTemplateParam("{\"code\":\"" + code + "\"}");
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            client.sendSmsWithOptions(sendSmsRequest, runtime);
        } catch (TeaException error) {
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            System.out.println(error.getMessage());
            // 诊断地址
            System.out.println(error.getData().get("Recommend"));
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            System.out.println(error.getMessage());
            // 诊断地址
            System.out.println(error.getData().get("Recommend"));
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
    }

    /**
     * TODO: 对阿里云的短信服务进行封装
     *
     * @param phone
     * @return
     * @throws Exception
     */
    public String sendSms(String phone) throws Exception {
        String code = stringHelper.generateVerificationCode();
        SmsService(phone, code);
        return code;
    }

}
