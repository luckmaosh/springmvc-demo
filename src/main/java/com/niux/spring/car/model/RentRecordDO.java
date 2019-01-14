package com.niux.spring.car.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author maso
 */
public class RentRecordDO implements Serializable {
    private Long id;
    private Date gmtCreate;
    private Date gmtModified;

    private String owner;
    private String render;
    private String mobile;
    private String renterMobile;
    private long price;
    private int status;

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
        this.owner = owner;
    }

    public String getRender() {
        return render;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRenterMobile() {
        return renterMobile;
    }

    public void setRenterMobile(String renterMobile) {
        this.renterMobile = renterMobile;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
