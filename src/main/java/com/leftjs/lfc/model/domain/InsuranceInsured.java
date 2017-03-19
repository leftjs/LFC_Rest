package com.leftjs.lfc.model.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 保单的被保人
 * Created by jason on 2017/3/13.
 */

@Entity
@XmlRootElement(name = "INSURED")
@XmlAccessorType(XmlAccessType.FIELD)
public class InsuranceInsured {
    @Id
    @GeneratedValue
    @XmlElement(name = "ID")
    private Long id;


    @XmlElement(name = "REL")
    private String rel;

    @XmlElement(name = "INSURE_NAME")
    private String insureName;

    public InsuranceInsured(String rel, String insureName) {
        this.rel = rel;
        this.insureName = insureName;
    }

    public InsuranceInsured() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getInsureName() {
        return insureName;
    }

    public void setInsureName(String insureName) {
        this.insureName = insureName;
    }

    @Override
    public String toString() {
        return "InsuranceInsured{" +
                "id=" + id +
                ", rel='" + rel + '\'' +
                ", insureName='" + insureName + '\'' +
                '}';
    }
}
