package com.yscyber.myspringcloud.projectd.msawrcitydataserver.service;

import com.yscyber.myspringcloud.projectd.msawrcitydataserver.dao.CityDAO;
import com.yscyber.myspringcloud.projectd.msawrcitydataserver.pojo.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Yuan
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDAO cityDAO;

    @Override
    public List<City> listCities() {
        return cityDAO.listCities();
    }

}