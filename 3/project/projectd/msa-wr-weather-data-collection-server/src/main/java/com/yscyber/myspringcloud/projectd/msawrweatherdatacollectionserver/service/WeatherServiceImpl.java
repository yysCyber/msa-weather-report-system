package com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.service;

import com.alibaba.fastjson.JSON;
import com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.client.MsaWrCityDataServerClient;
import com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.dao.WeatherDAO;
import com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.pojo.entity.City;
import com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.pojo.json.JsonDataObject;
import com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.pojo.json.JsonDataObjectConstant;
import com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.pojo.json.WeatherJsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Yuan
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherDAO weatherDAO;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private MsaWrCityDataServerClient msaWrCityDataServerClient;

    private static final String WEATHER_API_URL_PREFIX = "http://wthrcdn.etouch.cn/weather_mini?citykey=";

    @Override
    public void syncWeatherData() {
        JsonDataObject<City> cityJsonDataObject = msaWrCityDataServerClient.jsonGetCityList();
        if (cityJsonDataObject.getStatus() == JsonDataObjectConstant.STATUS_SUCCESS) {
            if (cityJsonDataObject.getDataCount() != 0) {
                for (City city : cityJsonDataObject.getObjectList()) {
                    syncWeatherData(city.getCityKey());
                }
            }
        }
    }

    @Override
    public WeatherJsonObject getWeatherDataByCityKeyFromInternet(String cityKey) {
        return weatherDAO.getWeatherJsonObjectByCityKeyFromInternet(cityKey);
    }

    @Override
    public void syncWeatherData(String cityKey) {
        WeatherJsonObject weatherJsonObject = weatherDAO.getWeatherJsonObjectByCityKeyFromInternet(cityKey);
        if (weatherJsonObject != null) {
            String jsonString = JSON.toJSONString(weatherJsonObject);
            stringRedisTemplate.opsForValue().set(WEATHER_API_URL_PREFIX + cityKey, jsonString);
        }
    }

    @Override
    public void syncWeatherData(List<String> cityKeyList) {
        for (String cityKey : cityKeyList) {
            syncWeatherData(cityKey);
        }
    }

    @Override
    public void syncWeatherDataByCity(City city) {
        syncWeatherData(city.getCityKey());
    }

    @Override
    public void syncWeatherDataByCity(List<City> cityList) {
        for (City city : cityList) {
            syncWeatherData(city.getCityKey());
        }
    }

}