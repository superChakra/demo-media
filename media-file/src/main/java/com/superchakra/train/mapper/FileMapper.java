package com.superchakra.train.mapper;

import com.superchakra.train.entity.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 查克拉
* @description 针对表【file(文件信息表)】的数据库操作Mapper
* @createDate 2024-06-18 11:52:54
* @Entity com.superchakra.train.entity.File
*/
@Mapper
public interface FileMapper extends BaseMapper<File> {

}




