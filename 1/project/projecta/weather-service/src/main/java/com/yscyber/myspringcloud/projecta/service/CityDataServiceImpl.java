package com.yscyber.myspringcloud.projecta.service;

import com.yscyber.myspringcloud.projecta.pojo.entity.City;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CityDataServiceImpl implements CityDataService {

    private static final String XML_FILE_PATH = "citylist.xml";
    private static final String XML_ELEM_NAME = "d";
    private static final String XML_CITY_NAME_ATTR = "d2";
    private static final String XML_CITY_KEY_ATTR = "d1";

    /**
     * 从 XML 读取出城市列表
     *
     * @return List<City>
     */
    @Override
    public List<City> listCities() {
        List<City> cityList = new ArrayList<>();
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(new ClassPathResource(XML_FILE_PATH).getFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (document != null) {
            Element rootElement = document.getRootElement();
            List<Element> cityXmlList = rootElement.elements(XML_ELEM_NAME);
            for (Element element : cityXmlList) {
                City city = new City();
                city.setCityKey(element.attributeValue(XML_CITY_KEY_ATTR));
                city.setCityName(element.attributeValue(XML_CITY_NAME_ATTR));
                cityList.add(city);
            }
        }
        return cityList;
    }

}