package com.own.mydemo.dao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.own.mydemo.dao.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @作者 liuChang
 * @创建时间 2022/8/29
 * @描述
 */
@Mapper
public interface UserInfoDao extends BaseMapper<UserInfo> {

}
