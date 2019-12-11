package com.yscyber.myspringcloud.projecta.pojo.entity;

import java.io.Serializable;

/**
 * 昨日天气
 *
 * 为方便映射，和返回的 JSON 中的名称相同，但是命名并不符合规范
 */
public class YesterdayWeather implements Serializable {

    /**
     * 日期
     */
    private String date;

    /**
     * 最高温度
     */
    private String high;

    /**
     * 最低温度
     */
    private String low;

    /**
     * 风向
     */
    private String fx;

    /**
     * 风力
     */
    private String fl;

    /**
     * 天气类型
     */
    private String type;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "YesterdayWeather{" +
                "date='" + date + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", fx='" + fx + '\'' +
                ", fl='" + fl + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}