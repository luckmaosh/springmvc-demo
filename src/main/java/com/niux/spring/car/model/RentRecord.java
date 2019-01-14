package com.niux.spring.car.model;

import java.util.Date;

public class RentRecord {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String owner;

    private String renter;

    private Integer status;

    private String mobile;

    private String renterMobile;

    private Integer price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getRenter() {
        return renter;
    }

    public void setRenter(String renter) {
        this.renter = renter == null ? null : renter.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getRenterMobile() {
        return renterMobile;
    }

    public void setRenterMobile(String renterMobile) {
        this.renterMobile = renterMobile == null ? null : renterMobile.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}