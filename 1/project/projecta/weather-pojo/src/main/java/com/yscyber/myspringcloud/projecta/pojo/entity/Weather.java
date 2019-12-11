package com.yscyber.myspringcloud.projecta.pojo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 天气
 *
 * 对应 JSON 中的 data 部分
 */
public class Weather implements Serializable {

    private String city;
    private String wendu;
    private String ganmao;
    private YesterdayWeather yesterday;
    private List<FutureWeather> forecast;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public YesterdayWeather getYesterday() {
        return yesterday;
    }

    public void setYesterday(YesterdayWeather yesterday) {
        this.yesterday = yesterday;
    }

    public List<FutureWeather> getForecast() {
        return forecast;
    }

    public void setForecast(List<FutureWeather> forecast) {
        this.forecast = forecast;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", wendu='" + wendu + '\'' +
                ", ganmao='" + ganmao + '\'' +
                ", yesterday=" + yesterday +
                ", forecast=" + forecast +
                '}';
    }

}