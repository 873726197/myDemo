package com.own.web.dao.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @作者 liuChang
 * @创建时间 2022/8/29
 * @描述
 */

@Data
@Accessors(chain = true)
public class UserInfo {

    private Integer id;

    private Boolean isSuccess;
}
