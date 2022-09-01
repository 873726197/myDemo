package com.own.mydemo.controller;

import cn.hutool.json.JSONUtil;
import com.own.mydemo.dao.dao.UserInfoDao;
import com.own.mydemo.dao.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者 liuChang
 * @创建时间 2022/8/29
 * @描述
 */

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoDao userInfoDao;

    @RequestMapping("/getUserById")
    public String getUserById(){
        UserInfo userInfo = userInfoDao.selectById(1);
        return JSONUtil.toJsonStr(userInfo);
    }
}
