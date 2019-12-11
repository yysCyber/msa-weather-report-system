package com.yscyber.myspringcloud.projecta.pojo.entity;

public class City {

    private String cityName;
    private String cityKey;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityKey() {
        return cityKey;
    }

    public void setCityKey(String cityKey) {
        this.cityKey = cityKey;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", cityKey='" + cityKey + '\'' +
                '}';
    }

}