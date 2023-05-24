package com.own.web.service.impl;

import com.own.web.dao.dao.LoginLogDao;
import com.own.web.dao.pojo.LoginLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
}
