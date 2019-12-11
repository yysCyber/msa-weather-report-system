package com.yscyber.myspringcloud.projecta.service;

import com.yscyber.myspringcloud.projecta.pojo.entity.City;
import java.util.List;

public interface CityDataService {

    /**
     * 从 XML 读取出城市列表
     *
     * @return  List<City>
     */
    List<City> listCities();

}