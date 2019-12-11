package com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.pojo.entity;

import lombok.Data;

@Data
public class City {

    private String provinceName;
    private String cityName;
    private String cityKey;
    private String cityPinyin;

    @Override
    public String toString() {
        return "City{" +
                "provinceName='" + provinceName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityKey='" + cityKey + '\'' +
                ", cityPinyin='" + cityPinyin + '\'' +
                '}';
    }

}