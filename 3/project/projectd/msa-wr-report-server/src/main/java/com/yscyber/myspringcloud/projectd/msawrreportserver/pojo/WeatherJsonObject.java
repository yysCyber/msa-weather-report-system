package com.yscyber.myspringcloud.projectd.msawrreportserver.pojo;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * @author Yuan
 */
@Data
public class WeatherJsonObject implements Serializable {

    private String city;
    private String wendu;
    private YesterdayWeatherJsonObject yesterday;
    private List<FutureWeatherJsonObject> forecast;
    private String ganmao;

    @Override
    public String toString() {
        return "WeatherJsonObject{" +
                "city='" + city + '\'' +
                ", wendu='" + wendu + '\'' +
                ", yesterday=" + yesterday +
                ", forecast=" + forecast +
                ", ganmao='" + ganmao + '\'' +
                '}';
    }

}