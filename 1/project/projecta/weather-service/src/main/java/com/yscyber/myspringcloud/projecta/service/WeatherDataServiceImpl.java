package com.yscyber.myspringcloud.projecta.service;

import com.alibaba.fastjson.JSON;
import com.yscyber.myspringcloud.projecta.pojo.entity.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 获取天气数据服务 实现
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    /**
     * RestTemplate 是 Spring 提供的 HTTP 客户端工具
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * RedisTemplate 是 Spring 将 Redis 客户端的 API （lettuce、Jedis等）做了封装，使用 Redis 更加方便
     * StringRedisTemplate 是 RedisTemplate 的子类，面向处理简单的字符串键值
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String WEATHER_DATA_API_URL_PREFIX = "http://wthrcdn.etouch.cn/weather_mini?";

    private static final int STATUS_OK = 1000;

    /**
     * 日志信息，便于确定数据是来自缓存还是新请求得到的
     */
    private static final Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);

    @Override
    public WeatherResponse getWeatherDataByCityName(String cityName) {
        String url = WEATHER_DATA_API_URL_PREFIX + "city=" + cityName;
        return doGetWeather(url);
    }

    @Override
    public WeatherResponse getWeatherDataByCityKey(String cityKey) {
        String url = WEATHER_DATA_API_URL_PREFIX + "citykey=" + cityKey;
        return doGetWeather(url);
    }

    @Override
    public void syncWeatherDataByCityKey(String cityKey) {
        String url = WEATHER_DATA_API_URL_PREFIX + "citykey=" + cityKey;
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        ResponseEntity<String> responseString =  restTemplate.getForEntity(url, String.class);
        if (responseString.getStatusCodeValue() == HttpStatus.OK.value()) {
            valueOperations.set(url, responseString.getBody());
        }
    }

    private WeatherResponse doGetWeather(String url) {
        WeatherResponse weatherResponse;
        String responseBody = null;
        // 获取缓存中所有键值对
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();

        // 判断缓存中是否存在想要的数据，如果存在直接从缓存中取，否则请求第三方 API
        if (stringRedisTemplate.hasKey(url)) {
            // 打印日志
            logger.info("Redis has data!");
            // 根据键，获取值
            responseBody = valueOperations.get(url);
        } else {
            // 打印日志
            logger.info("Redis doesn't have data!");
            ResponseEntity<String> responseString =  restTemplate.getForEntity(url, String.class);
            // 先判断是否响应成功 200
            if (responseString.getStatusCodeValue() == HttpStatus.OK.value()) {
                responseBody = responseString.getBody();
            }

        }
        weatherResponse = JSON.parseObject(responseBody, WeatherResponse.class);
        if (weatherResponse != null && weatherResponse.getStatus() == STATUS_OK) {
            valueOperations.set(url, responseBody);
        }
        return weatherResponse;
    }

}