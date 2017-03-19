package com.leftjs.lfc.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by jason on 2017/3/11.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PageBase {
    @XmlElement(name = "CURRENT_PAGE")
    private Integer currentPage; // 当前页
    @XmlElement(name = "TOTAL_PAGES")
    private Integer totalPages; // 总页数
    @XmlElement(name= "SIZE_PER_PAGE")
    private Integer sizePerPage; // 每页个数
    @XmlElement(name = "TOTAL_SIZE")
    private Long totalSize; // 总元素个数

    public PageBase() {
    }

    public PageBase(Integer currentPage, Integer totalPages, Integer sizePerPage, Long totalSize) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.sizePerPage = sizePerPage;
        this.totalSize = totalSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getSizePerPage() {
        return sizePerPage;
    }

    public void setSizePerPage(Integer sizePerPage) {
        this.sizePerPage = sizePerPage;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    @Override
    public String toString() {
        return "PageBase{" +
                "currentPage=" + currentPage +
                ", totalPages=" + totalPages +
                ", sizePerPage=" + sizePerPage +
                ", totalSize=" + totalSize +
                '}';
    }
}
