package com.own.web.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author liuChang
 * @date 2024/11/21 11:22
 */
@Data
@Accessors(chain = true)
@TableName("a1")
public class A1 {
    private Integer id;
    private String name;
}
