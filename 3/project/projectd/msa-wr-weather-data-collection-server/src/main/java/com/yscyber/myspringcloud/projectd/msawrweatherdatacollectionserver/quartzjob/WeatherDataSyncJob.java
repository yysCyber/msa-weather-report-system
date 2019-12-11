package com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.quartzjob;

import com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.service.WeatherService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class WeatherDataSyncJob extends QuartzJobBean {

    @Autowired
    private WeatherService weatherService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        weatherService.syncWeatherData();
    }

}