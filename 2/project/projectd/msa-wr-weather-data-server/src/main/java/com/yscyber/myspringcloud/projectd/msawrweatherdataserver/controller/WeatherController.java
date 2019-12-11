package com.yscyber.myspringcloud.projectd.msawrweatherdataserver.controller;

import com.yscyber.myspringcloud.projectd.msawrweatherdataserver.pojo.json.WeatherJsonObject;
import com.yscyber.myspringcloud.projectd.msawrweatherdataserver.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather/{key}")
    public WeatherJsonObject jsonGetWeather(@PathVariable("key") String cityKey) {
        return weatherService.getWeatherByCityKey(cityKey);
    }

}