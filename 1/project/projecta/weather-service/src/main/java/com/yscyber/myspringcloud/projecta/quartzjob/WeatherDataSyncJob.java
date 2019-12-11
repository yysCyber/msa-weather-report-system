package com.yscyber.myspringcloud.projecta.quartzjob;

import com.yscyber.myspringcloud.projecta.pojo.entity.City;
import com.yscyber.myspringcloud.projecta.service.CityDataService;
import com.yscyber.myspringcloud.projecta.service.WeatherDataService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import java.util.List;

/**
 * 天气数据定期更新
 */
public class WeatherDataSyncJob extends QuartzJobBean {

    @Autowired
    private CityDataService cityDataService;

    @Autowired
    private WeatherDataService weatherDataService;

    /**
     * 日志信息，便于调试
     */
    private static final Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    /**
     * 定期执行的逻辑写在该方法中
     *
     * @param jobExecutionContext jobExecutionContext
     * @throws JobExecutionException 异常
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<City> cityList = cityDataService.listCities();
        for (City city : cityList) {
            weatherDataService.syncWeatherDataByCityKey(city.getCityKey());
            logger.info(city.getCityKey());
        }
        logger.info("Sync finish");
    }

}