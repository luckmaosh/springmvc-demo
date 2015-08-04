package com.niux.mywork.modle;

import java.io.Serializable;
import java.util.Date;

/**
 * 记录外部支付宝，易宝等回调返回的url,方便查询
 * 
 * @author wei_liu
 * @createTime 2010-8-17 下午05:43:10
 */
public class PayUrl implements Serializable {

    private static final long serialVersionUID = 4964850381139236831L;

    private long id;

    private long billId;

    private String url;

    private int type;

    private long userId;

    private Date time;

    private String buyerEmail;

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
