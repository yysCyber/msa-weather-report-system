package com.yscyber.myspringcloud.projectd.msawrcitydataserver.controller;

import com.yscyber.myspringcloud.projectd.msawrcitydataserver.pojo.City;
import com.yscyber.myspringcloud.projectd.msawrcitydataserver.pojo.JsonDataObject;
import com.yscyber.myspringcloud.projectd.msawrcitydataserver.pojo.JsonDataObjectConstant;
import com.yscyber.myspringcloud.projectd.msawrcitydataserver.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author Yuan
 */
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/weather/city")
    public JsonDataObject<City> jsonGetCityList() {
        JsonDataObject<City> cityJsonDataObject = new JsonDataObject<>();
        cityJsonDataObject.setStatus(JsonDataObjectConstant.STATUS_SUCCESS);
        cityJsonDataObject.setMessage(JsonDataObjectConstant.MESSAGE_SUCCESS);
        List<City> cityList = cityService.listCities();
        if (cityList == null) {
            cityJsonDataObject.setDataCount(0);
        } else {
            cityJsonDataObject.setObjectList(cityList);
            cityJsonDataObject.setDataCount(cityList.size());
        }
        return cityJsonDataObject;
    }

}