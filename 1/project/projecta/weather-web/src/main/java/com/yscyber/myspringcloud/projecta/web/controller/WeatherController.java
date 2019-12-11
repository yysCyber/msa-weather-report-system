package com.yscyber.myspringcloud.projecta.web.controller;

import com.yscyber.myspringcloud.projecta.pojo.entity.WeatherResponse;
import com.yscyber.myspringcloud.projecta.service.CityDataService;
import com.yscyber.myspringcloud.projecta.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/weather")
public class WeatherController {

    @Autowired
    private WeatherDataService weatherDataService;

    @Autowired
    private CityDataService cityDataService;

     // private static final String DEFAULT_CITY_KEY = "101280101";

     private static final int STATUS_OK = 1000;

    @GetMapping(value = {"/report/{key}"})
    public ModelAndView weatherReportPage(@PathVariable(name = "key") String cityKey) {
        ModelAndView modelAndView = new ModelAndView();
        WeatherResponse weatherResponse = weatherDataService.getWeatherDataByCityKey(cityKey);
        if (weatherResponse.getStatus() == STATUS_OK) {
            modelAndView.setViewName("report");
            modelAndView.addObject("hasSelectedCityKey", cityKey);
            modelAndView.addObject("cityList", cityDataService.listCities());
            modelAndView.addObject("weather", weatherResponse);
        } else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }


    @GetMapping(value = "/city/name/{name}")
    public WeatherResponse getWeatherOperationByCityName(@PathVariable(name = "name") String cityName) {
        return weatherDataService.getWeatherDataByCityName(cityName);
    }

    @GetMapping(value = "/city/key/{key}")
    public WeatherResponse getWeatherOperationByCityKey(@PathVariable(name = "key") String cityKey) {
        return weatherDataService.getWeatherDataByCityKey(cityKey);
    }

}