package com.own.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.own.web.dao.dao.A1Dao;
import com.own.web.dao.dao.MUserInfoDao;
import com.own.web.dao.pojo.A1;
import com.own.web.dao.pojo.MUserInfo;
import com.own.web.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author liuChang
 * @date 2023/3/22 18:01
 * @describe
 */
@Service
public class UserServiceImpl extends ServiceImpl<MUserInfoDao, MUserInfo> implements UserService {

    @Resource
    private A1Dao a1Dao;


    @Transactional(propagation = Propagation.NESTED)
    public void b() {
        A1 req = new A1().setId(26).setName("33");
        int insert = a1Dao.insert(req);
        Thread thread = new Thread();
        thread.start();
        thread.interrupt();

        try {
            thread.wait();
        } catch (InterruptedException e) {
            thread.interrupt();
        }
    }
}
