package com.yscyber.myspringcloud.projectd.msawrreportserver.client;

import com.yscyber.myspringcloud.projectd.msawrreportserver.pojo.City;
import com.yscyber.myspringcloud.projectd.msawrreportserver.pojo.JsonDataObject;
import com.yscyber.myspringcloud.projectd.msawrreportserver.pojo.WeatherJsonObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 聚合 API
 *
 * Zuul 网关
 *
 * @author Yuan
 */
@FeignClient("msa-wr-gateway")
public interface MsaDataServerClient {

    @GetMapping("/json/city/weather/city")
    JsonDataObject<City> jsonCity();

    @GetMapping("/json/weather/weather/{key}")
    WeatherJsonObject jsonWeatherByCityKey(@PathVariable("key") String cityKey);

}