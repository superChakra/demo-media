package com.superchakra.train.controller;

import com.aliyuncs.exceptions.ClientException;
import com.superchakra.train.helper.ApiHelper;
import com.superchakra.train.reponse.Result;
import com.superchakra.train.reponse.ResultStatusEnum;
import com.superchakra.train.service.FileService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController implements FileControllerApi {

    @Resource
    private FileService fileService;

    @Resource
    private ApiHelper apiHelper;

    @Override
    public Result hello(String name) {
        return new Result().success("hello " + name);
    }


    @Override
    public Result uploadAvatarFile(String userId, String fileType, MultipartFile file) {
        return null;
    }

    @Override
    public Result uploadImageFile(String userId, String fileType, MultipartFile file) throws ClientException {
        return null;
    }

    @Override
    public Result uploadCommonFile(String userId, String fileType, MultipartFile file) throws ClientException {

        if (!userId.isBlank() && !fileType.isBlank() && !file.isEmpty()) {
            return fileService.uploadFileOnOss(userId, fileType, file);
        } else {
            return new Result().error(ResultStatusEnum.FILE_UPLOAD_ERROR, null);
        }

    }
}
