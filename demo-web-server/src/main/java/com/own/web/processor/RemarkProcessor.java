package com.own.web.processor;

import com.own.web.annotation.RemarkAnnotation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author liuChang
 * @date 2023/2/3 11:30
 * @describe 项目启动后通过增强器扫描到备注注解获取到备注信息
 */

@Component
public class RemarkProcessor implements BeanPostProcessor {

    public static final HashSet<Object> set = new HashSet<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        for (Method method1 : methods) {
            RemarkAnnotation remarkAnnotation = AnnotationUtils.findAnnotation(method1, RemarkAnnotation.class);
            if (Objects.nonNull(remarkAnnotation)) {
                set.add(remarkAnnotation.remark());
            }
        }
        return bean;
    }
}
