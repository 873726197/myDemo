package com.own.web.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.own.web.dao.pojo.MUserInfo;
import com.own.web.service.impl.MUserInfoServiceImpl;
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
    private MUserInfoServiceImpl userInfoService;

//    @Scheduled(cron = "0/10 * * * * ?")
    public void task() {

        log.info("---start");

        List<MUserInfo> list = userInfoService.list(new QueryWrapper<MUserInfo>()
                .and(l -> l.eq("is_success", true)
                        .or().isNull("is_success")));

        executor.execute(() -> {
            for (MUserInfo MUserInfo : list) {
                log.info("-----{}", MUserInfo.toString());
//                MUserInfoService.updateById(new MMUserInfo().setId(MUserInfo.getId()).setIsSuccess(true));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
