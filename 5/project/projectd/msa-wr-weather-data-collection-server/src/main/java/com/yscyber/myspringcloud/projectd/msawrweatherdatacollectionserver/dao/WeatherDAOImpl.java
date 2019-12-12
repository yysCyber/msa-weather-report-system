package com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.dao;

import com.alibaba.fastjson.JSON;
import com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.pojo.json.ResponseJsonObject;
import com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.pojo.json.WeatherJsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * @author Yuan
 */
@Repository
public class WeatherDAOImpl implements WeatherDAO {

    @Autowired
    private RestTemplate restTemplate;

    private static final String WEATHER_API_URL_PREFIX = "http://wthrcdn.etouch.cn/weather_mini?citykey=";

    private static final int API_SELF_STATUS_OK = 1000;

    @Override
    public WeatherJsonObject getWeatherJsonObjectByCityKeyFromInternet(String cityKey) {
        String url = WEATHER_API_URL_PREFIX + cityKey;

        WeatherJsonObject weatherJsonObject = null;

        // 使用 RestTemplate 从网络请求数据
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // responseString.getStatusCodeValue() == 200
        if (response.getStatusCodeValue() == HttpStatus.OK.value()) {
            String json = response.getBody();
            ResponseJsonObject responseJsonObject = JSON.parseObject(json, ResponseJsonObject.class);
            if (responseJsonObject != null && responseJsonObject.getStatus() == API_SELF_STATUS_OK) {
                weatherJsonObject = responseJsonObject.getData();
            }
        }

        return weatherJsonObject;
    }

}