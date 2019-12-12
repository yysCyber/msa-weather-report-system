package com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.pojo.json;

import lombok.Data;
import java.util.List;

/**
 * @author Yuan
 */
@Data
public class JsonDataObject<T> {

    private Integer status;
    private String message;
    private Integer dataCount;
    private List<T> objectList;

    @Override
    public String toString() {
        return "JsonDataObject{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", dataCount=" + dataCount +
                ", object=" + objectList +
                '}';
    }

}