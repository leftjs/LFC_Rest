package com.leftjs.lfc.model.domain;

import com.leftjs.lfc.utils.JaxbClerkSerializer;
import com.leftjs.lfc.utils.JaxbDateSerializer;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * 保单的info
 * Created by jason on 2017/3/13.
 */
@Entity
@XmlRootElement(name = "INFO")
@XmlAccessorType(XmlAccessType.FIELD)
public class InsuranceInfo {
    public enum SEX {
        F,
        M
    };

    public enum POL_TYPE {
        意外险,
        健康险,
        补充医疗险,
        分红险
    };

    @Id
    @GeneratedValue
    @XmlElement(name = "ID")
    private Long id;

    @XmlElement(name = "HOLDER_NAME")
    private String holderName;

    @Enumerated(EnumType.STRING)
    @XmlElement(name = "SEX")
    private SEX sex;

    @Temporal(TemporalType.DATE)
    @XmlElement(name = "BIRTH_DATE")
    @XmlJavaTypeAdapter(JaxbDateSerializer.class)
    private Date birthDate;

    @XmlElement(name = "MOBILE")
    private String mobile;

    @XmlElement(name = "POL_NAME")
    private POL_TYPE polName; // 险种

    @XmlElement(name = "MONEY")
    private Integer money;

    @XmlElement(name = "CLERK")
    @XmlJavaTypeAdapter(JaxbClerkSerializer.class)
    @OneToOne(cascade = CascadeType.PERSIST)
    private Clerk clerk;

    @Temporal(TemporalType.DATE)
    @XmlElement(name = "INFORCE_TIME")
    @XmlJavaTypeAdapter(JaxbDateSerializer.class)
    private Date inforceTime;


    @XmlElement(name = "BAODAN_NO")
    private String baodanNo;

    public InsuranceInfo() {
    }

    public InsuranceInfo(String holderName, SEX sex, Date birthDate, String mobile, POL_TYPE polName, Integer money, Clerk clerk, Date inforceTime, String baodanNo) {
        this.holderName = holderName;
        this.sex = sex;
        this.birthDate = birthDate;
        this.mobile = mobile;
        this.polName = polName;
        this.money = money;
        this.clerk = clerk;
        this.inforceTime = inforceTime;
        this.baodanNo = baodanNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public SEX getSex() {
        return sex;
    }

    public void setSex(SEX sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public POL_TYPE getPolName() {
        return polName;
    }

    public void setPolName(POL_TYPE polName) {
        this.polName = polName;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Clerk getClerk() {
        return clerk;
    }

    public void setClerk(Clerk clerk) {
        this.clerk = clerk;
    }

    public Date getInforceTime() {
        return inforceTime;
    }

    public void setInforceTime(Date inforceTime) {
        this.inforceTime = inforceTime;
    }

    public String getBaodanNo() {
        return baodanNo;
    }

    public void setBaodanNo(String baodanNo) {
        this.baodanNo = baodanNo;
    }

    @Override
    public String toString() {
        return "InsuranceInfo{" +
                "id=" + id +
                ", holderName='" + holderName + '\'' +
                ", sex=" + sex +
                ", birthDate=" + birthDate +
                ", mobile='" + mobile + '\'' +
                ", polName='" + polName + '\'' +
                ", money=" + money +
                ", clerk=" + clerk +
                ", inforceTime=" + inforceTime +
                ", baodanNo='" + baodanNo + '\'' +
                '}';
    }
}


