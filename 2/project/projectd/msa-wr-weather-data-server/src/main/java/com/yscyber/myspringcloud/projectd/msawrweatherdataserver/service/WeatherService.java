package com.yscyber.myspringcloud.projectd.msawrweatherdataserver.service;

import com.yscyber.myspringcloud.projectd.msawrweatherdataserver.pojo.json.WeatherJsonObject;

public interface WeatherService {

    WeatherJsonObject getWeatherByCityKey(String cityKey);

}