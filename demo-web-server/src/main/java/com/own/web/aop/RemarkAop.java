package com.own.web.aop;

import cn.hutool.core.io.file.FileAppender;
import com.own.web.annotation.RemarkAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Method;

/**
 * @author liuChang
 * @date 2023/2/2 18:10
 * @describe 备注增强器
 */
@Component
@Aspect
public class RemarkAop {


    @Pointcut("@annotation(com.own.web.annotation.RemarkAnnotation)")
    public void remark() {

    }

    @Before("remark()")
    public void test(JoinPoint joinPoint) {
        //文件追加
        FileAppender fileAppender = new FileAppender(new File("D:\\Desktop\\2.txt"), 1, true);
        //文件覆盖
//        FileWriter fileWriter = new FileWriter("D:\\Desktop\\1.txt");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RemarkAnnotation annotation = method.getAnnotation(RemarkAnnotation.class);
        String remark = annotation.remark();
//        fileWriter.write(remark);
        fileAppender.append(remark);
        fileAppender.flush();
    }
}
