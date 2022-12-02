package com.own.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.rholder.retry.*;
import com.own.web.dao.dao.UserInfoDao;
import com.own.web.dao.pojo.UserInfo;
import com.own.web.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author liuChang
 * @date 2022-11-29 16:13
 * @describe
 **/

@Service
@Slf4j
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements UserInfoService {

    @Override
    public String retryTest() {
        Retryer<Object> retry = RetryerBuilder
                .newBuilder()
                .retryIfException()
                .withWaitStrategy(WaitStrategies.incrementingWait(3, TimeUnit.SECONDS, 3, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(10))
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        log.info("第{}次重试", attempt.getAttemptNumber());
                        if (attempt.hasException()) {
                            log.error("第{}次重试异常,原因{}", attempt.getAttemptNumber(), attempt.getExceptionCause().toString());
                        }
                    }
                }).build();
        return null;
    }
}
