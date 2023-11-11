package com.blb.permission_ms.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * IOC容器获取工具
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    //容器
    private static ApplicationContext applicationContext;

    /**
     * 当项目创建ApplicationContext对象后自动调用
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 返回对象
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }
}
