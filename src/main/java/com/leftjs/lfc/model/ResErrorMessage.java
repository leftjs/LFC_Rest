package com.leftjs.lfc.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jason on 2017/3/13.
 */
@XmlRootElement(name = "ERROR")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResErrorMessage {

    @XmlElement(name = "CODE")
    private int code;
    @XmlElement(name = "MESSAGE")
    private String message;

    public ResErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResErrorMessage() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResErrorMessage{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
