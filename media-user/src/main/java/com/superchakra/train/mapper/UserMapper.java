package com.superchakra.train.mapper;

import com.superchakra.train.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 查克拉
* @description 针对表【users】的数据库操作Mapper
* @createDate 2024-06-15 15:33:59
* @Entity com.superchakra.train.entity.Users
*/

@Mapper
public interface UserMapper extends BaseMapper<User> {

}




