package com.niux.mywork.task;

import com.niux.mywork.service.PushServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: maso
 * Date: 13-6-29
 * Time: 下午4:28
 * To change this template use File | Settings | File Templates.
 */
public class AlipayTask implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        PushServiceImpl pushService = (PushServiceImpl) ALipayJob.context.getBean("pushService");
        pushService.index();
    }
}
