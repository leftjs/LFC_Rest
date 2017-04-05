package com.leftjs.lfc.model.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Types;

/**
 * Created by jason on 2017/3/12.
 */
@Entity
@XmlRootElement(name = "ADMIN")
@XmlAccessorType(XmlAccessType.FIELD)
public class Admin {

    @Id
    @GeneratedValue
    @XmlElement(name = "ID")
    private Long id;
    @Column(unique = true)
    @NotNull
    @XmlElement(name = "USERNAME")
    private String username;
    @NotNull
    @XmlElement(name = "PASSWORD")
    private String password;

    public Admin() {
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
