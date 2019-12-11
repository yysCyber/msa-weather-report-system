package com.yscyber.myspringcloud.projecta.config;

import com.yscyber.myspringcloud.projecta.quartzjob.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Quartz 配置类
 *
 * 配置 Quartz 需要配置两项
 * 1、JobDetail：配置 Job 的细节
 * 2、Trigger：配置 Job 的触发详情
 */
@Configuration
public class QuartzConfig {

    /**
     * 每半个小时（1800s）定期更新天气数据
     */
    private static final int WEATHER_DATA_SYNC_TIME = 1800;

    /**
     * 配置项目中的所有 Job 的细节
     *
     * 可配置多个 Job
     *
     * @return JobDetail
     */
    @Bean
    public JobDetail jobDetail() {
        /*
            1、使用 JobBuilder 来“创建” Job 的细节
            2、newJob 方法来配置所编写的 Job 类
            3、withIdentity 方法给所配置的 Job 起别名，便于后期使用
            4、storeDurably 方法确保即使没有 Trigger 关联，也确保 Job 也能继续在队列中，以使用手动触发
         */
        return JobBuilder
                .newJob(WeatherDataSyncJob.class)
                .withIdentity("weatherDataSyncJobDetail")
                .storeDurably()
                .build();
    }

    /**
     * 配置项目中的所有 Job 的触发
     *
     * @return Trigger
     */
    @Bean
    public Trigger trigger() {
        /*
            1、SimpleScheduleBuilder 用来声明这个“定期”的，withIntervalInSeconds 表示“几秒钟将进行触发 Job 执行”，repeatForever 表示程序运行过程中“一直”触发

            2、forJob 方法指定该触发对应的 JobDetail
            3、withIdentity 方法给触发取别名
            4、withSchedule 方法指定定义好的 Schedule
         */
        SimpleScheduleBuilder weatherDataSyncScheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(WEATHER_DATA_SYNC_TIME)
                .repeatForever();

        return TriggerBuilder
                .newTrigger()
                .forJob("weatherDataSyncJobDetail")
                .withIdentity("weatherDataSyncJobTrigger")
                .withSchedule(weatherDataSyncScheduleBuilder)
                .startNow()
                .build();
    }

}