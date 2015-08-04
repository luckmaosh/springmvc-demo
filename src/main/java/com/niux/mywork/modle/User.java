package com.niux.mywork.modle;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chen.qi
 */
public class User implements Serializable {

    private static final long serialVersionUID = -8211332961163241530L;

    // -------------- state --------------
    public static final int UNACTIVE = 0;
    public static final int CLOSURE = 10;
    public static final int ACTIVE = 100;
    public static final int BIND_MOBILE = 200;

    // -------------- auth --------------
    public static final int AUTH_VENDOR = 5;// 外站合作账户，只有指定权限
    public static final int AUTH_SELLER = 10;
    public static final int AUTH_EDITER = 20;
    public static final int AUTH_SALESMAN = 30;
    public static final int AUTH_CUSTOM_SERVICE = 40;
    public static final int AUTH_VOUCHER_EDIT = 50;
    public static final int AUTH_CUSTOM_SERVICE1 = 60; // 一线客服
    public static final int AUTH_MERCHANT_MANAGE = 70;// 商户管理后台
    public static final int AUTH_ADMIN = 90;
    public static final int AUTH_SUPPER = 100;

    // -------------- email --------------
    public static final int EMAIL_VALID = 1;
    public static final int EMAIL_INVALID = 0;

    // -------------- 目前site字段仅仅表示人人链接 --------------
    public static final int SITE_RENREN = 1;

    // -------------- params --------------
    private long id;
    private String email;
    private String name;
    private String password; // 弃用
    private String passwordNew;
    private String mobile;
    private int auth;
    private int score;
    private int state;
    private String ticket;
    private Date regTime;
    private Date lastLoginTime;
    private String regIp;
    private String lastLoginIp;
    private int mergeType;
    private long areaId;
    private int version;
    private long siteConnected;
    private int emailValid;
    private long areaCode;
    private String areaName;
    private int site;
    private long siteId;
    private double moneySave;
    private boolean nativeLogin;
    private int passwordChange;
    private long headPortraitId;
    private String ref;
    private int growthLevel;

    // email正则
    public static final Pattern EMAIL_PATTERN = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    /**
     * 仅仅wap使用，返回登陆信息
     * @return
     */
    public String getLoginInfo() {
        if (ref == null) {
            return "userId=" + getId() + "&t=" + getTicket();
        }
        return "userId=" + getId() + "&t=" + getTicket() + "&ref=" + ref;
    }

    public String getTicket() {
        return this.ticket;
    }

    public void setRef(String ref) {
        if (StringUtils.length(ref) < 8) {
            this.ref = ref;
        }
    }

    public double getMoneySave() {
        return moneySave;
    }

    public int getEmailValid() {
        return emailValid;
    }

    public String getRef() {
        return ref;
    }

    public void setMoneySave(double moneySave) {
        this.moneySave = moneySave;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = StringUtils.lowerCase(StringUtils.trim(email));
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }

    @Deprecated
    public int getSite() {
        return site;
    }

    public void setSite(int site) {
        this.site = site;
    }

    @Deprecated
    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }

    public long getSiteConnected() {
        return siteConnected;
    }

    public void setSiteConnected(long siteConnected) {
        this.siteConnected = siteConnected;
    }

    public void setEmailValid(int emailValid) {
        this.emailValid = emailValid;
    }

    public boolean isActive() {
        return state >= ACTIVE;
    }

    public boolean isClosure() {
        return state == CLOSURE;
    }

    public boolean isBindMobile() {
        return state >= BIND_MOBILE;
    }

    public long getHeadPortraitId() {
        return headPortraitId;
    }

    public void setHeadPortraitId(long headPortraitId) {
        this.headPortraitId = headPortraitId;
    }

    public int getMergeType() {
        return mergeType;
    }

    public void setMergeType(int mergeType) {
        this.mergeType = mergeType;
    }

    public long getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(long areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public boolean isNativeLogin() {
        return nativeLogin;
    }

    public void setNativeLogin(boolean nativeLogin) {
        this.nativeLogin = nativeLogin;
    }

    public int getPasswordChange() {
        return passwordChange;
    }

    public void setPasswordChange(int passwordChange) {
        this.passwordChange = passwordChange;
    }

    public int getGrowthLevel() {
        return growthLevel;
    }

    public void setGrowthLevel(int growthLevel) {
        this.growthLevel = growthLevel;
    }

    public boolean isSteadyUser() {
        if (getEmail().endsWith("@nm.com")) {
            return false;
        }
        Matcher m = EMAIL_PATTERN.matcher(this.getEmail());
        return m.find();
    }


}
