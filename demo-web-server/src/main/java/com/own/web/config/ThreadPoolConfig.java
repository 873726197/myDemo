package com.own.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author liuChang
 * @date 2022-11-28 14:02
 * @describe 线程池配置
 **/

@Component
public class ThreadPoolConfig {

    /**
     * 线程池维护线程的最少数量;核心线程数
     */
    private static final int CORE_POOL_SIZE = 10;
    /**
     * 线程池维护线程的最大数量
     */
    private static final int MAX_POOL_SIZE = 20;

    /**
     * 允许的空闲时间
     */
    private static final int KEEP_ALIVE_SECONDS = 30;

    /**
     * 队列长度
     */
    private static final int QUEUE_CAPACITY = 10;

    /**
     * 拒绝策略
     */
    private static final RejectedExecutionHandler REJECTED_EXECUTION_HANDLER =
            new ThreadPoolExecutor.AbortPolicy();


    @Bean("demoTask")
    public ThreadPoolTaskExecutor getDemoTask() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setRejectedExecutionHandler(REJECTED_EXECUTION_HANDLER);

        executor.initialize();
        return executor;
    }

}
