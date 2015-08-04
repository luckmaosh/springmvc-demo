package com.niux.mywork.task;

import org.quartz.SchedulerException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.text.ParseException;

/**
 * Created with IntelliJ IDEA.
 * User: maso
 * Date: 13-6-29
 * Time: 下午4:16
 * To change this template use File | Settings | File Templates.
 */
public class ALipayJob implements InitializingBean, ApplicationContextAware {

    public static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        String time = "0 */3 * * * ?";                  //<!-- 秒，分，时，月的某天，月，星期的某天，年；-->

        try {
            QuartzManager.addJob("alipaypush", AlipayTask.class, time, null, 0); // new SitecodeJob()不行
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
