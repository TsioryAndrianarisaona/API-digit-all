package com.digitall.api.model.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JsonModel {
    private int code;
    private String message;
    private Object data;

    @Override
    public String toString() {
        return "JsonModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
