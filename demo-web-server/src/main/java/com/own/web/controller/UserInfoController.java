package com.own.web.controller;

import cn.hutool.json.JSONUtil;
import com.own.web.dao.pojo.UserInfo;
import com.own.web.service.impl.UserInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liuChang
 * @date 2022/8/29
 * @describe
 */

@RestController
@RequestMapping("/user")
@Slf4j
public class UserInfoController {

    @Resource
    private ThreadPoolTaskExecutor task;

    @Resource
    private UserInfoServiceImpl userInfoService;

    @RequestMapping("/getUserById")
    public String getUserById() {
        UserInfo userInfo = userInfoService.getById(1);
        return JSONUtil.toJsonStr(userInfo);
    }

    @RequestMapping("/getName")
    public String getName() {
        //落库
        log.info("开始落库-----{}", Thread.currentThread().getId());
//        task.execute(() -> {
        //上传
        AtomicLong id = new AtomicLong();
        CompletableFuture.runAsync(() -> {
            id.set(Thread.currentThread().getId());
            //上传文件
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //落数据库
            log.info("上传成功-----{},线程是否存在{}", id, Thread.currentThread().isAlive());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("线程{}是否销毁{}", id, !Thread.currentThread().isAlive());
        }).whenComplete((e, t) -> {
            if (Objects.nonNull(t)) {
                log.error("上传文件异常", t);
            }
        }).exceptionally(e -> {
            log.error("报异常了", e);
            return null;
        });
        //落库成功后返回成功
        return "success";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        log.info("file:{}", file);
        return "上传成功";
    }
}
