package com.yscyber.myspringcloud.projectd.msawrweatherdataserver.pojo.json;

import lombok.Data;
import java.io.Serializable;

/**
 * @author Yuan
 */
@Data
public class FutureWeatherJsonObject implements Serializable {

    private String date;
    private String type;
    private String high;
    private String low;
    private String fengxiang;
    private String fengli;

    @Override
    public String toString() {
        return "FutureWeatherJsonObject{" +
                "date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", fengxiang='" + fengxiang + '\'' +
                ", fengli='" + fengli + '\'' +
                '}';
    }

}