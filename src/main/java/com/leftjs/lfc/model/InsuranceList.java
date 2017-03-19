package com.leftjs.lfc.model;

import com.leftjs.lfc.model.domain.Insurance;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by jason on 2017/3/13.
 */
@XmlRootElement(name = "INSURANCES")
public class InsuranceList extends PageBase{

    @XmlElement(name = "INSURANCE")
    private List<Insurance> insuranceList;

    public InsuranceList() {
    }

    public InsuranceList(List<Insurance> insuranceList) {
        this.insuranceList = insuranceList;
    }

    public InsuranceList(Integer currentPage, Integer totalPages, Integer sizePerPage, Long totalSize) {
        super(currentPage, totalPages, sizePerPage, totalSize);
    }

    public InsuranceList(Integer currentPage, Integer totalPages, Integer sizePerPage, Long totalSize, List<Insurance> insuranceList) {
        super(currentPage, totalPages, sizePerPage, totalSize);
        this.insuranceList = insuranceList;
    }

    public List<Insurance> getInsuranceList() {
        return insuranceList;
    }

    public void setInsuranceList(List<Insurance> insuranceList) {
        this.insuranceList = insuranceList;
    }

    @Override
    public String toString() {
        return "InsuranceList{" +
                "insuranceList=" + insuranceList +
                '}';
    }
}
