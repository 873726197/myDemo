package com.own.web.dao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.own.web.dao.pojo.LoginLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liuChang
 * @date 2023/3/29 17:04
 * @describe
 */
@Mapper
public interface LoginLogDao extends BaseMapper<LoginLog> {
}
