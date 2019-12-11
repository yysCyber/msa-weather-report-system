package com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.client;

import com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.pojo.entity.City;
import com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.pojo.json.JsonDataObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 调用“城市数据”微服务
 *
 * 注解 @FeignClient 将提供“微服务名”
 *
 * @author Yuan
 */
@FeignClient("msa-wr-city-data-server")
public interface MsaWrCityDataServerClient {

    /**
     * 从“城市数据”微服务中获取城市数据
     *
     * @return JsonDataObject
     */
    @GetMapping("/weather/city")
    JsonDataObject<City> jsonGetCityList();

}