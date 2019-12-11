package com.yscyber.myspringcloud.projecta;

import com.yscyber.myspringcloud.projecta.pojo.entity.WeatherResponse;
import com.yscyber.myspringcloud.projecta.service.CityDataService;
import com.yscyber.myspringcloud.projecta.service.WeatherDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherDataServiceTest {


    @Autowired
    private WeatherDataService weatherDataService;

    @Autowired
    private CityDataService cityDataService;

    @Test
    public void demo1() {
        WeatherResponse response = weatherDataService.getWeatherDataByCityName("北京");
        System.out.println(response == null);
    }

    @Test
    public void demo2() {
        System.out.println(cityDataService.listCities());
    }

}