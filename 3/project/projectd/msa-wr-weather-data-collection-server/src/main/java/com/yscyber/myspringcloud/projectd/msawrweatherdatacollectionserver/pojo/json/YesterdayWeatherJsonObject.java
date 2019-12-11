package com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.pojo.json;

import lombok.Data;
import java.io.Serializable;

/**
 * @author Yuan
 */
@Data
public class YesterdayWeatherJsonObject implements Serializable {

    private String date;
    private String type;
    private String high;
    private String low;
    private String fx;
    private String fl;

    @Override
    public String toString() {
        return "YesterdayWeatherJsonObject{" +
                "date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", fx='" + fx + '\'' +
                ", fl='" + fl + '\'' +
                '}';
    }

}