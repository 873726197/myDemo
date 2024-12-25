package com.own.web.controller;

import com.own.web.dao.vo.AreaNode;
import com.own.web.service.impl.AreaServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuChang
 * @date 2024/12/25 16:45
 */
@RestController("/area")
public class AreaController {

    @Resource
    private AreaServiceImpl areaService;

    @GetMapping("/getAreas")
    public List<AreaNode> getAreas() {
        return areaService.getAreas();
    }
}
