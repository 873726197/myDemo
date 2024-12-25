package com.own.web.dao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.own.web.dao.vo.AreaNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author liuChang
 * @date 2024/12/25 16:21
 */
@Mapper
public interface AreaDao extends BaseMapper<AreaNode> {
    List<AreaNode> getAreas();
}
