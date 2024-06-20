package com.superchakra.train.bo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileBO {

    private String userId;

    private MultipartFile multipartFile;


}
