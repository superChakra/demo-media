package com.superchakra.train.service;

import com.aliyuncs.exceptions.ClientException;
import com.superchakra.train.bo.FileBO;
import com.superchakra.train.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;
import com.superchakra.train.reponse.Result;
import org.springframework.web.multipart.MultipartFile;

/**
* @author 查克拉
* @description 针对表【file(文件信息表)】的数据库操作Service
* @createDate 2024-06-18 11:52:54
*/
public interface FileService extends IService<File> {


    Result uploadFileOnOss(String userId, String fileType, MultipartFile file) throws ClientException;
}
