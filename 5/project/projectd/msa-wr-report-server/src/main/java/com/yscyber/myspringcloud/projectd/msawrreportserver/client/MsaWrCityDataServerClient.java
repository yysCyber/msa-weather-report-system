package com.yscyber.myspringcloud.projectd.msawrreportserver.client;

import com.yscyber.myspringcloud.projectd.msawrreportserver.pojo.City;
import com.yscyber.myspringcloud.projectd.msawrreportserver.pojo.JsonDataObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @deprecated 直接依赖 MsaDataServerClient
 */
@FeignClient("msa-wr-city-data-server")
public interface MsaWrCityDataServerClient {

    @GetMapping("/weather/city")
    JsonDataObject<City> jsonCity();

}