package com.leftjs.lfc.model;

import com.leftjs.lfc.model.domain.Clerk;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by jason on 2017/3/11.
 */
@XmlRootElement(name= "CLERK_LIST")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClerkList extends PageBase{

//    @XmlElementWrapper(name = "CLERK_LIST")
    @XmlElement(name="CLERK")
    private List<Clerk> clerkList; // 业务员列表


    public ClerkList() {
    }

    public ClerkList(List<Clerk> clerkList) {
        this.clerkList = clerkList;
    }

    public ClerkList(Integer currentPage, Integer totalPages, Integer sizePerPage, Long totalSize) {
        super(currentPage, totalPages, sizePerPage, totalSize);
    }

    public ClerkList(Integer currentPage, Integer totalPages, Integer sizePerPage, Long totalSize, List<Clerk> clerkList) {
        super(currentPage, totalPages, sizePerPage, totalSize);
        this.clerkList = clerkList;
    }

    public List<Clerk> getClerkList() {
        return clerkList;
    }

    public void setClerkList(List<Clerk> clerkList) {
        this.clerkList = clerkList;
    }

    @Override
    public String toString() {
        return "ClerkList{" +
                "clerkList=" + clerkList +
                '}';
    }
}
