package com.yscyber.myspringcloud.projectd.msawrweatherdataserver.dao;

import com.alibaba.fastjson.JSON;
import com.yscyber.myspringcloud.projectd.msawrweatherdataserver.pojo.json.WeatherJsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * @author Yuan
 */
@Repository
public class WeatherDAOImpl implements WeatherDAO {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String WEATHER_API_URL_PREFIX = "http://wthrcdn.etouch.cn/weather_mini?citykey=";

    @Override
    public WeatherJsonObject getWeatherByCityKey(String cityKey) {
        ValueOperations<String, String> vops = stringRedisTemplate.opsForValue();
        if (stringRedisTemplate.hasKey(WEATHER_API_URL_PREFIX + cityKey)) {
            String result = vops.get(WEATHER_API_URL_PREFIX + cityKey);
            return JSON.parseObject(result, WeatherJsonObject.class);
        }
        return null;
    }
}
