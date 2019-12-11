package com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.config;

import com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.quartzjob.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Quartz 配置
 *
 * @author Yuan
 */
@Configuration
public class QuartzConfig {

    /**
     * “自动同步数据”的时间间隔 1800s=30min
     */
    private static final int WEATHER_DATA_SYNC_TIME_INTERVAL = 1800;

    private static final String JOB_DETAIL_ID = "weather-data-sync";

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder
                .newJob(WeatherDataSyncJob.class)
                .withIdentity(JOB_DETAIL_ID)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger() {
        SimpleScheduleBuilder weatherDataSyncScheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(WEATHER_DATA_SYNC_TIME_INTERVAL)
                .repeatForever();
        return TriggerBuilder
                .newTrigger()
                .forJob(JOB_DETAIL_ID)
                .withSchedule(weatherDataSyncScheduleBuilder)
                .startNow()
                .build();
    }

}