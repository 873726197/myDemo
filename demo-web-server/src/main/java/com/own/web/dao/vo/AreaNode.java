package com.own.web.dao.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 区域节点
 * @author liuChang
 * @date 2024/12/25 16:36
 */
@Data
public class AreaNode {
    private String areaCode;
    private String areaName;
    private String parentCode;
    private byte areaLevel;

    private List<AreaNode> children = new ArrayList<>();

}
