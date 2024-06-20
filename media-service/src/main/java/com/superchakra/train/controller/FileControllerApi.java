package com.superchakra.train.controller;

import com.aliyuncs.exceptions.ClientException;
import com.superchakra.train.reponse.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@Tag(name = "file模块", description = "file模块接口")
@CrossOrigin
public interface FileControllerApi extends HelloControllerApi {

    @PostMapping("/avatar")
    Result uploadAvatarFile(@RequestParam("userId") String userId,
                        @RequestParam("fileType") String fileType,
                        @RequestParam("file") MultipartFile file);


    @PostMapping("/image")
    Result uploadImageFile(@RequestParam("userId") String userId,
                       @RequestParam("fileType") String fileType,
                       @RequestParam("file") MultipartFile file) throws ClientException;

    @PostMapping("/common")
    Result uploadCommonFile(@RequestParam("userId") String userId,
                  @RequestParam("fileType") String fileType,
                  @RequestParam("file") MultipartFile file) throws ClientException;


}
