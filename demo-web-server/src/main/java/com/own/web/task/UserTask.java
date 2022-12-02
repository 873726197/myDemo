package com.own.web.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.own.web.dao.pojo.UserInfo;
import com.own.web.service.impl.UserInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuChang
 * @date 2022-11-28 16:42
 * @describe
 **/

@Slf4j
@Component
public class UserTask {

    @Resource
    private ThreadPoolTaskExecutor executor;

    @Resource
    private UserInfoServiceImpl userInfoService;

//    @Scheduled(cron = "0/10 * * * * ?")
    public void task() {

        log.info("---start");

        List<UserInfo> list = userInfoService.list(new QueryWrapper<UserInfo>()
                .and(l -> l.eq("is_success", true)
                        .or().isNull("is_success")));

        executor.execute(() -> {
            for (UserInfo userInfo : list) {
                log.info("-----{}", userInfo.toString());
//                userInfoService.updateById(new UserInfo().setId(userInfo.getId()).setIsSuccess(true));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
