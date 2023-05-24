package com.own.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.rholder.retry.*;
import com.own.web.dao.dao.MUserInfoDao;
import com.own.web.dao.pojo.MUserInfo;
import com.own.web.service.MUserInfoService;
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
public class MUserInfoServiceImpl extends ServiceImpl<MUserInfoDao, MUserInfo> implements MUserInfoService {

    @Override
    public String retryTest() throws Exception {
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
        retry.call(()->{
            //执行业务逻辑
            return "200";
        });
        return null;
    }
}
