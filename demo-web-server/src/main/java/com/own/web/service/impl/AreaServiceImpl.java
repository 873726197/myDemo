package com.own.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.own.web.dao.dao.AreaDao;
import com.own.web.dao.vo.AreaNode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuChang
 * @date 2024/12/25 16:27
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaDao, AreaNode> {

    @Resource
    private AreaDao baseMapper;

    public List<AreaNode> getAreas() {

        List<AreaNode> allRegions = baseMapper.getAreas();

        return allRegions.stream()
                .peek(node -> node.setChildren(allRegions.stream()
                        .filter(child -> node.getAreaCode().equals(child.getParentCode()))
                                .collect(Collectors.toList()))
                )
                .filter(node -> node.getParentCode() == null)
                .collect(Collectors.toList());

    }
}
