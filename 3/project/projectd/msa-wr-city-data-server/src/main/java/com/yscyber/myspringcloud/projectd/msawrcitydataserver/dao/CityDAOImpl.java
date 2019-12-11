package com.yscyber.myspringcloud.projectd.msawrcitydataserver.dao;

import com.yscyber.myspringcloud.projectd.msawrcitydataserver.pojo.City;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuan
 */
@Repository
public class CityDAOImpl implements CityDAO {

    private static final String XML_FILE_PATH = "citylist.xml";
    private static final String XML_ELEM_NAME = "d";
    private static final String XML_PROVINCE_NAME_ATTR = "d4";
    private static final String XML_CITY_PINYIN_ATTR = "d3";
    private static final String XML_CITY_NAME_ATTR = "d2";
    private static final String XML_CITY_KEY_ATTR = "d1";

    @Override
    public List<City> listCities() {
        List<City> cityList = null;
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new ClassPathResource(XML_FILE_PATH).getFile());
            if (document != null) {
                Element rootElement = document.getRootElement();
                List<Element> cityXmlList = rootElement.elements(XML_ELEM_NAME);
                for (Element element : cityXmlList) {
                    if (cityList == null) {
                        cityList = new ArrayList<>();
                    }
                    City city = new City();
                    city.setProvinceName(element.attributeValue(XML_PROVINCE_NAME_ATTR));
                    city.setCityName(element.attributeValue(XML_CITY_NAME_ATTR));
                    city.setCityKey(element.attributeValue(XML_CITY_KEY_ATTR));
                    city.setCityPinyin(element.attributeValue(XML_CITY_PINYIN_ATTR));
                    cityList.add(city);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityList;
    }

}