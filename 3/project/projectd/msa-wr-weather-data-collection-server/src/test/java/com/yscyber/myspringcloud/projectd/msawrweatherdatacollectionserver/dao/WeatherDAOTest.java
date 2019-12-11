package com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.dao;

import com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.service.WeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WeatherDAOTest {

    @Autowired
    private WeatherDAO weatherDAO;

    @Autowired
    private WeatherService weatherService;

    @Test
    public void demo1() {
        System.out.println(weatherDAO.getWeatherJsonObjectByCityKeyFromInternet("101280101"));
    }

    @Test
    public void demo2() {
        weatherService.syncWeatherData("101280701");
    }

}
