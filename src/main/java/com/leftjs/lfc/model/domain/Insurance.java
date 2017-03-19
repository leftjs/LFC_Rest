package com.leftjs.lfc.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by jason on 2017/3/13.
 */
@Entity
@XmlRootElement(name = "ROOT")
@XmlAccessorType(XmlAccessType.FIELD)
public class Insurance {
    @Id
    @GeneratedValue
    @XmlElement(name= "ID")
    @JsonIgnore
    private Long id;

    @XmlElement(name = "INFO")
    @OneToOne(cascade = CascadeType.ALL)
    private InsuranceInfo info;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @XmlElementWrapper(name = "INSUREDS")
    @XmlElement(name = "INSURED")
    private List<InsuranceInsured> insureds;

    public Insurance() {
    }

    public Insurance(InsuranceInfo info, List<InsuranceInsured> insureds) {
        this.info = info;
        this.insureds = insureds;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "id=" + id +
                ", info=" + info +
                ", insureds=" + insureds +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InsuranceInfo getInfo() {
        return info;
    }

    public void setInfo(InsuranceInfo info) {
        this.info = info;
    }

    public List<InsuranceInsured> getInsureds() {
        return insureds;
    }

    public void setInsureds(List<InsuranceInsured> insureds) {
        this.insureds = insureds;
    }
}
