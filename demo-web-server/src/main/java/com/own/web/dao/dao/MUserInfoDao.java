package com.own.web.dao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.own.web.dao.pojo.MUserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liuChang
 * @date 2023/2/14 17:41
 * @describe
 */
@Mapper
public interface MUserInfoDao extends BaseMapper<MUserInfo> {

    void get();
}
