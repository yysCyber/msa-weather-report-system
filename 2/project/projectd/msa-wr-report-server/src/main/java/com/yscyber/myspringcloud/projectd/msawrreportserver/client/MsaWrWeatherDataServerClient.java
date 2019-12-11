package com.yscyber.myspringcloud.projectd.msawrreportserver.client;

import com.yscyber.myspringcloud.projectd.msawrreportserver.pojo.WeatherJsonObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("msa-wr-weather-data-server")
public interface MsaWrWeatherDataServerClient {

    @GetMapping("/weather/{key}")
    WeatherJsonObject jsonWeatherByCityKey(@PathVariable("key") String cityKey);

}