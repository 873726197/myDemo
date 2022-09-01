package com.own.mydemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.own.mydemo.dao.dao.UserInfoDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyDemoApplicationTests {

    @Autowired
    private UserInfoDao userInfoDao;

    @Test
    void select(){
        System.out.println(userInfoDao.selectList(new QueryWrapper<>()));
    }

}
