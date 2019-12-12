package com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.pojo.json;

import lombok.Data;
import java.io.Serializable;

/**
 * 封装从网上获取的天气数据
 *
 * 根据“第三方 API 最终返回的 JSON”格式编写
 *
 * @author Yuan
 */
@Data
public class ResponseJsonObject implements Serializable {

    private WeatherJsonObject data;
    private Integer status;
    private String desc;

    @Override
    public String toString() {
        return "ResponseJsonObject{" +
                "data=" + data +
                ", status=" + status +
                ", desc='" + desc + '\'' +
                '}';
    }

}