package com.yscyber.myspringcloud.projectd.msawrreportserver.client;

import com.yscyber.myspringcloud.projectd.msawrreportserver.pojo.City;
import com.yscyber.myspringcloud.projectd.msawrreportserver.pojo.JsonDataObject;
import com.yscyber.myspringcloud.projectd.msawrreportserver.pojo.WeatherJsonObject;
import org.springframework.stereotype.Component;

/**
 * MsaDataServerClient Fallback
 *
 * Hystrix 实现在 Spring Cloud 中实现“断路器”的机制
 *
 * 1、当 msa-wr-city-data-server 微服务无法正常提供服务时，会自动调用这里的 jsonCity 方法而不再去调用 MsaDataServerClient 中的 jsonCity 方法
 * 2、同样，当 msa-wr-weather-data-server 微服务无法正常提供服务时，会自动调用这里的 jsonWeatherByCityKey 方法而不去调用 MsaDataServerClient 中的 jsonWeatherByCityKey 方法
 *
 * @author Yuan
 */
@Component
public class MsaDataServerClientFallback implements MsaDataServerClient {

    /**
     * 返回 null 即可
     *
     * @return null
     */
    @Override
    public JsonDataObject<City> jsonCity() {
        return null;
    }

    /**
     * 返回 null 即可
     *
     * @param cityKey 城市编号
     * @return null
     */
    @Override
    public WeatherJsonObject jsonWeatherByCityKey(String cityKey) {
        return null;
    }

}