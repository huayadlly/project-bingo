package cn.taike.spring.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by huayadlly on 2017/8/27.
 */

/**
 * 执行定时任务
 */
@Component
public class ScheduledDemo {

    @Scheduled(fixedDelay = 2000)
    public void doScheduledThing() {
        System.out.println("this is scheduled event 2000ms");
    }

    @Scheduled(cron = "0 46 16 ? * *")
    public void doScheduled() {
        System.out.println("定时指定时间完成任务");
    }
}
