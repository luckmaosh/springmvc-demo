package com.niux.mywork.modle;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class GiftCard implements Serializable {

    // 
    private static final long serialVersionUID = -269113744819667899L;

    public static final int STATUS_UNUSE = 0;

    public static final int STATUS_USED = 1;

    public static final int STATUS_ALL = 2;

    public static final int STATUS_EXPIRE = -1;

    public static final int STATUS_FREEZED = -2;

    private long id;

    private String name;

    private long userId;

    private long collectionId;


    private Date time;

    private Date useTime;

    private long dealId;

    private long orderId;

    private Date startTime;

    private Date expireTime;

    private long money;

    private long threshold;

    private long usedMoney;

    private int status;

    private Date lockTime;

    private long codeId;


    private long operatorId;

    private long trackId;

    // 渠道
    private int channel;

    /*
     * 允许使用的dealId，null或空表示不限制使用的deal
     */
    @Deprecated
    private Set<Long> restrictDealIds = new HashSet<Long>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDealId() {
        return dealId;
    }

    public void setDealId(long dealId) {
        this.dealId = dealId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(long collectionId) {
        this.collectionId = collectionId;
    }


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public long getThreshold() {
        return threshold;
    }

    public void setThreshold(long threshold) {
        this.threshold = threshold;
    }

    public long getUsedMoney() {
        return usedMoney;
    }

    public void setUsedMoney(long usedMoney) {
        this.usedMoney = usedMoney;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public boolean isLocking() {
        Date lockTime = getLockTime();
        if (lockTime != null) {
            Date now = new Date();
            return now.before(lockTime);
        }
        return false;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public int getStatus() {
        Date now = new Date();
        if (status == GiftCard.STATUS_UNUSE) {
            if (now.before(startTime)) {
                return GiftCard.STATUS_FREEZED;
            }
            if (now.after(expireTime)) {
                return GiftCard.STATUS_EXPIRE;
            }
        }
        return status;
    }

    public int getDaysTillStartTime() {
        long time = startTime.getTime() - System.currentTimeMillis();
        int days = (int) TimeUnit.DAYS.convert(time, TimeUnit.MILLISECONDS) + 1;
        return (days > 0) ? days : 1;
    }

    public boolean isForeverFreezed() {
        return status == GiftCard.STATUS_FREEZED;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public long getCodeId() {
        return codeId;
    }

    public void setCodeId(long codeId) {
        this.codeId = codeId;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }


    @Deprecated
    public Set<Long> getRestrictDealIds() {
        return restrictDealIds;
    }

    @Deprecated
    public void setRestrictDealIds(Set<Long> restrictDealIds) {
        this.restrictDealIds = restrictDealIds;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }


    public boolean hasExpired() {
        return new Date().after(getExpireTime());
    }

    public boolean isAvailable() {
        return getStatus() == STATUS_UNUSE && !hasExpired();
    }

    public boolean isAvailable(long userId) {
        return getStatus() == STATUS_UNUSE && !hasExpired() && userId == getUserId();
    }

    public boolean hasThreshold() {
        return getThreshold() > 0;
    }

}
