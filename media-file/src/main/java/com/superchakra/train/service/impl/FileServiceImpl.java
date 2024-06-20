package com.superchakra.train.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superchakra.train.entity.File;
import com.superchakra.train.helper.ApiHelper;
import com.superchakra.train.reponse.Result;
import com.superchakra.train.reponse.ResultStatusEnum;
import com.superchakra.train.service.FileService;
import com.superchakra.train.mapper.FileMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
* @author 查克拉
* @description 针对表【file(文件信息表)】的数据库操作Service实现
* @createDate 2024-06-18 11:52:54
*/
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File>
    implements FileService{

    @Resource
    private FileMapper fileMapper;

    @Resource
    private ApiHelper apiHelper;


    @Override
    public Result uploadFileOnOss(String userId, String fileType, MultipartFile file) throws ClientException {

        String path = apiHelper.uploadNetworkStream(userId, fileType, file);

        //File newFile = new File();

       // fileMapper.insert(newFile);

        return new Result().success(ResultStatusEnum.FILE_UPLOAD_SUCCESS, path);
    }
}




