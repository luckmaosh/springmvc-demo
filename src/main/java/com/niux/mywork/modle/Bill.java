package com.niux.mywork.modle;

import com.niux.mywork.utils.PriceUtil;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 交易账单信息
 *
 * @author liuwei
 * @createTime 2010-8-2 下午07:00:10
 */
public class Bill implements Serializable {

    private static final long serialVersionUID = 4964850381139216831L;

    private static final double EPSILON = 0.0001d;

    // 付款成功
    public static final int STATUS_SUCCESS = 1;

    // 未付款
    public static final int STATUS_UNPAID = 0;

    //以下两个在pay_type为alipaywap时适用
    public static final String CODE_CREDIT = "credit";
    public static final String CODE_DEBIT = "debit";

    private long id;

    private String payType;

    private String bankType;

    private double money;

    private long userId;

    /** 支付来源信息 */
    private String code;

    /** 支付商品信息（一般为id） */
    private String trace;

    /** 商品标题 */
    private String subject;

    /** 商品描述 */
    private String body;

    /** 来源订单业务id */
    private String outSerialId;

    /** 对外订单号 */
    private String serialId;

    private Date time;

    private Date updateTime;

    /** 商品链接 */
    private String dealUrl;

    private String returnUrl;

    private int status;

    /** bill 无此字段 回调url+参数，记录于payurl */
    private String backUrl;

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDealUrl() {
        return dealUrl;
    }

    public void setDealUrl(String dealUrl) {
        this.dealUrl = dealUrl;
    }

    public long getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMoneyPrec() {
        return (long) (Math.round(money * 1000) + EPSILON);
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = PriceUtil.truncate(money);
    }



    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        subject = StringUtils.replace(subject, "&", "＆");
        subject = StringUtils.replace(subject, "=", "＝");
        subject = StringUtils.replace(subject, "＋", "＋");
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        body = StringUtils.replace(body, "&", "＆");
        body = StringUtils.replace(body, "=", "＝");
        body = StringUtils.replace(body, "＋", "＋");
        this.body = body;
    }

    public String getOutSerialId() {
        return outSerialId;
    }

    public void setOutSerialId(String orderSerial) {
        this.outSerialId = orderSerial;
    }

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }
}
