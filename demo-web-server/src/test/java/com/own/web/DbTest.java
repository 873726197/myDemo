package com.own.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.own.web.dao.dao.RoleInfoDao;
import com.own.web.dao.dao.UserInfoDao;
import com.own.web.dao.pojo.RoleInfo;
import com.own.web.dao.pojo.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author liuChang
 * @date 2023/2/14 16:25
 * @describe
 */

@SpringBootTest
public class DbTest {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private RoleInfoDao roleInfoDao;

    @Test
    public void pageTest(){
        PageHelper.startPage(1,2);
        List<UserInfo> userInfos = userInfoDao.selectList(new QueryWrapper<>());
        PageInfo<UserInfo> info = new PageInfo<>(userInfos);
        System.out.println(userInfos);
        System.out.println("----------");
        System.out.println(info);
        System.out.println("----------");
        PageHelper.startPage(1,1);
        List<RoleInfo> roleInfos = roleInfoDao.selectList(new QueryWrapper<>());
        System.out.println(roleInfos);
    }
}
