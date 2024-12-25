package com.own.web.service.impl;

import com.own.web.dao.dao.A1Dao;
import com.own.web.dao.dao.LoginLogDao;
import com.own.web.dao.pojo.A1;
import com.own.web.dao.pojo.LoginLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author liuChang
 * @date 2023/3/29 16:46
 * @describe
 */

@Service
public class TransactionServiceImpl {

    private final LoginLogDao loginLogDao;

    public TransactionServiceImpl(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    @Resource
    private A1Dao a1Dao;

    @Resource
    private UserServiceImpl userServiceImpl;

    @Transactional
    public void t1() {
        LoginLog log = new LoginLog();
        log.setLoginUserId(1);
        log.setCreateTime(LocalDateTime.now());

        loginLogDao.insert(log);
        t2();
        int i = 1 / 0;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void t2() {
        LoginLog log = new LoginLog();
        log.setLoginUserId(2);
        log.setCreateTime(LocalDateTime.now());

        loginLogDao.insert(log);
        int i = 1 / 0;
//        t1();

    }

    @Transactional
    public void transactionTest() {
        A1 req = new A1().setId(3).setName("33");
        int insert = a1Dao.insert(req);
//        b();
        try {
//            userServiceImpl.b();
            b();
        } catch (Exception e) {
        }

    }

//    @Transactional(rollbackFor = Exception.class)
    public void b() {
        A1 req = new A1().setId(4).setName("33");
        int insert = a1Dao.insert(req);
        throw new RuntimeException();
    }

    @Transactional
    public void insert() throws InterruptedException {
        a1Dao.updateById(new A1().setId(1).setName("6666"));
        Thread.sleep(30000);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public String select() {
        return a1Dao.selectById(1).getName();
    }

    public String select2() {
        return a1Dao.selectById(1).getName();
    }


}
