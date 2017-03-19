package com.leftjs.lfc.model;

import com.leftjs.lfc.model.domain.Admin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by jason on 2017/3/11.
 */
@XmlRootElement(name= "ADMIN_LIST")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdminList extends PageBase{

//    @XmlElementWrapper(name = "CLERK_LIST")
    @XmlElement(name="ADMIN")
    private List<Admin> adminList; // 管理员列表


    public AdminList() {
    }

    public AdminList(List<Admin> adminList) {
        this.adminList = adminList;
    }

    public AdminList(Integer currentPage, Integer totalPages, Integer sizePerPage, Long totalSize, List<Admin> adminList) {
        super(currentPage, totalPages, sizePerPage, totalSize);
        this.adminList = adminList;
    }

    public AdminList(Integer currentPage, Integer totalPages, Integer sizePerPage, Long totalSize) {
        super(currentPage, totalPages, sizePerPage, totalSize);
    }

    public List<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(List<Admin> adminList) {
        this.adminList = adminList;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
