package com.yscyber.myspringcloud.projectd.msawrreportserver.controller;

import com.yscyber.myspringcloud.projectd.msawrreportserver.client.MsaWrCityDataServerClient;
import com.yscyber.myspringcloud.projectd.msawrreportserver.client.MsaWrWeatherDataServerClient;
import com.yscyber.myspringcloud.projectd.msawrreportserver.pojo.City;
import com.yscyber.myspringcloud.projectd.msawrreportserver.pojo.JsonDataObject;
import com.yscyber.myspringcloud.projectd.msawrreportserver.pojo.WeatherJsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Yuan
 */
@Controller
public class WeatherController {

    @Autowired
    private MsaWrCityDataServerClient cityDataServerClient;

    @Autowired
    private MsaWrWeatherDataServerClient weatherDataServerClient;

    @GetMapping("/weather/report/{key}")
    public ModelAndView pageWeatherReport(@PathVariable("key") String cityKey) {
        ModelAndView modelAndView = new ModelAndView();
        JsonDataObject<City> cityJsonDataObject = cityDataServerClient.jsonCity();
        WeatherJsonObject weatherJsonObject = weatherDataServerClient.jsonWeatherByCityKey(cityKey);
        if (weatherJsonObject != null) {
            modelAndView.setViewName("report");
            modelAndView.addObject("cityList", cityJsonDataObject.getObjectList());
            modelAndView.addObject("weather", weatherJsonObject);
            modelAndView.addObject("hasSelectedCityKey", cityKey);
        } else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

}
