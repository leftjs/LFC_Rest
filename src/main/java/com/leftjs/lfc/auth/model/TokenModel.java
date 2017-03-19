package com.leftjs.lfc.auth.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jason on 2017/3/12.
 */
@XmlRootElement(name = "TOKEN_ROOT")
@XmlAccessorType(XmlAccessType.FIELD)
public class TokenModel {

    @XmlElement(name = "TOKEN")
    private String token;

    @XmlElement(name = "EXPIRED_AT")
    private Long expiredAt;

    public TokenModel() {
    }

    public TokenModel(String token, Long expiredAt) {
        this.token = token;
        this.expiredAt = expiredAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Long expiredAt) {
        this.expiredAt = expiredAt;
    }

    @Override
    public String toString() {
        return "TokenModel{" +
                "token='" + token + '\'' +
                ", expiredAt=" + expiredAt +
                '}';
    }
}
