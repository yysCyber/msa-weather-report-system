package com.yscyber.myspringcloud.projectd.msawrweatherdataserver.service;

import com.yscyber.myspringcloud.projectd.msawrweatherdataserver.dao.WeatherDAO;
import com.yscyber.myspringcloud.projectd.msawrweatherdataserver.pojo.json.WeatherJsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherDAO weatherDAO;

    @Override
    public WeatherJsonObject getWeatherByCityKey(String cityKey) {
        return weatherDAO.getWeatherByCityKey(cityKey);
    }

}