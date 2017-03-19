package com.leftjs.lfc.model.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jason on 2017/3/11.
 */
@Entity
@XmlRootElement(name = "CLERK")
@XmlAccessorType(XmlAccessType.FIELD)
public class Clerk {

    @Id
    @GeneratedValue
    @XmlElement(name = "ID")
    private Long id; // 数据库id

    @XmlElement(name = "CLERK_ID")
    @Column(unique = true)
    @NotNull
    private String clerkId; // 工号

    @NotNull
    @XmlElement(name = "PASSWORD")
    private String password; // 密码

    public Clerk() {
    }


    public Clerk(String clerkId, String password) {
        this.clerkId = clerkId;
        this.password = password;
    }


    public Clerk setClerk(String clerkId) {
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClerkId() {
        return clerkId;
    }

    public void setClerkId(String clerkId) {
        this.clerkId = clerkId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Clerk{" +
                "id=" + id +
                ", clerkId='" + clerkId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}


