package com.niux.mywork.modle;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * 验证券（类似美团券）<br/>
 *
 * 条码：8位id+4位随机码
 *
 * @author liuwei
 * @createTime 2010-4-27 下午07:15:08
 */
public class Certificate {

    long id;

    long orderId;

    long dealId;

    long userId;

    String password;

    int status;

    String mobile;

    String userName;

    Date buyTime;

    Date useTime;

    private final Log logger = LogFactory.getLog(getClass());
    
    public int getMobileCount() {
        return mobileCount;
    }

    public void setMobileCount(int mobileCount) {
        this.mobileCount = mobileCount;
    }

    //短信发送次数
    int mobileCount;

    /**
     * @deprecated 准备作废，先上一版不在券上打印过期时间的，再删除各种代码引用，最后修改表结构
     */
    @Deprecated
    Date expireTime;

    String title;

    long operator;

    String operatorName;

    String dealImage;

    int dealOptionId;

    String siteCode;

    String siteCodeId;

    long merId;
    
    public static final long MERID_NUOMI = 0;
    
    private int hideSms;

    // 传递值使用
    private String dealOptionTitle;

    // 交易时传递值使用
    private long exchangeFromUserId;

    private boolean isRefundable;

    private boolean qrCode;

    // 退款中状态
    private boolean isRefunding;

    public boolean isRefunding() {
        return isRefunding;
    }

    public void setRefunding(boolean isRefunding) {
        this.isRefunding = isRefunding;
    }
    
    //对应的deal是否为站外码单
    private boolean isOtherSiteCode;

	public boolean isOtherSiteCode() {
		return isOtherSiteCode;
	}

	public void setOtherSiteCode(boolean isOtherSiteCode) {
		this.isOtherSiteCode = isOtherSiteCode;
	}

	public boolean isRefundable() {
        if (status != STATUS_UNUSE) {
            return false;
        }
        return isRefundable;
    }

    public void setRefundable(boolean isRefundable) {
        this.isRefundable = isRefundable;
    }

    public static final int STATUS_UNUSE = 0;

    public static final int STATUS_USED = 1;

    public static final int STATUS_EXPIRE = -1;

    public static final int STATUS_REFOUND = -2;

    public static final int STATUS_NEED_SEND = -3;

    public static final int STATUS_ALL = 2;

    /** 可交易 */
    public static final int STATUS_EXCHANGEABLE = 5;

    /** 交易过 */
    public static final int STATUS_EXCHANGED = 6;

    // 系统赠送的券，order_id统一为0
    public static final int SYSTEM_SEND_ORDER_ID = 0;

    // id位数
    public static final int ID_LENGTH = 8;

    // 随机数位数
    public static final int PASSWORD_LENGTH = 4;

    public static final int NOTIFY_DAY_BEFORE_EXPIRE = 7;

    public static final int REFUND_DAY = 7;

    public boolean isInRefundDay() {
        Date d = new Date();
        d = DateUtils.addDays(d, -REFUND_DAY);
        return (buyTime.after(d)) ? true : false;
    }

    public boolean isUnUse() {
        return status == STATUS_UNUSE ? true : false;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public long getOperator() {
        return operator;
    }

    public String getDealImage() {
        return dealImage;
    }

    public void setDealImage(String dealImage) {
        this.dealImage = dealImage;
    }

    public int getDealOptionId() {
        return dealOptionId;
    }

    public void setDealOptionId(int dealOptionId) {
        this.dealOptionId = dealOptionId;
    }

    public void setOperator(long operator) {
        this.operator = operator;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    public String toString() {
        String s = String.format(
                "id=%d--orderId=%d--dealId=%d--password=%s--status=%s--mobile=%s--username=%s--buyTime=%s--useTime=%s",
                id, orderId, dealId, password, status, mobile, userName, buyTime, useTime);
        return s;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
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

    /**
     * 系统赠送的券，order_id统一为0
     *
     * @param orderId
     */
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getDealId() {
        return dealId;
    }

    public void setDealId(long dealId) {
        this.dealId = dealId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isExpire() {
        return (expireTime.before(new Date())) ? true : false;
    }

    public static final String CODE_PRE = "000000000";

    /**
     * 验证用码
     *
     * @return
     */
    public String getCode() {
        if (!StringUtils.isEmpty(siteCode)) {
            return siteCode;
        }
//        String code = String.valueOf(id) + String.valueOf(password);
//        int leaveLenght = ID_LENGTH + PASSWORD_LENGTH - code.length();
//        code = CODE_PRE.substring(0, leaveLenght) + code;
//        return code;
        
//        if(StringUtils.isNotBlank(siteCodeId)) {
//        	return siteCodeId;
//        } else {
//        	logger.error("取券码出错 id="+id+",password="+password+",site_code="+siteCode+",site_code_id="+siteCodeId);
//        	//双写阶段依然保留，过后删除
//        	String code = String.valueOf(id) + String.valueOf(password);
//            int leaveLenght = ID_LENGTH + PASSWORD_LENGTH - code.length();
//            code = CODE_PRE.substring(0, leaveLenght) + code;
//            return code;
//        }
        return siteCodeId;
    }

    /**
     * 验证用码
     *
     * @return
     */
    public String getCodeWithSpit() {
        if (!StringUtils.isEmpty(siteCode)) {
            return siteCode;
        }
        String code = getCode();
        // code = code.substring(0, 4)+"-"+code.substring(4, 8)+"-"+code.substring(8, 12)+"-"+code.substring(12, 16);
        // return code;
        // 支持16位以下的
        String result = "";
		if (code != null && code.length() > 0) {
			for (int i = 0; i < code.length(); i++) {
				result += code.charAt(i);
				if (i == 3 || i == 7 || i == 11 && i != code.length() - 1) {
					result += "-";
				}
			}
		}
        return result;
    }

    public String getCodeForView() {

        // 二维码显示提示语,客户端和wap临时使用
        if (qrCode) {
            return "请上糯米网获取消费码";
        }
        if(dealId==32570){
            //慈铭特殊处理
            return "";
        }
        if (StringUtils.isEmpty(siteCode)) {
            return getCodeWithSpit();
        }
        if (this.dealId == 347) {
            // 凡客V
            String[] pair = StringUtils.split(siteCode, ":");
            if (pair != null && pair.length == 2) {
                return "卡号: " + pair[0] + " 密码: " + pair[1];
            }
        }
        else if (this.dealId == 358) {
            // 银泰
            String[] pair = StringUtils.split(siteCode, ":");
            if (pair != null && pair.length == 2) {
                return "单品满400抵用200: " + pair[0] + " 累计一次购物满500抵用200: " + pair[1];
            }
        }
        return siteCode;
    }

    /**
     * 仅传递值给绘制券
     *
     * @return
     */
    public String getDealOptionTitle() {
        return dealOptionTitle;
    }

    public void setDealOptionTitle(String dealOptionTitle) {
        this.dealOptionTitle = dealOptionTitle;
    }

    public int getHideSms() {
        return hideSms;
    }

    public void setHideSms(int hideSms) {
        this.hideSms = hideSms;
    }

    /**
     * 仅在交易时是用
     *
     * @return
     */
    public long getExchangeFromUserId() {
        return exchangeFromUserId;
    }

    public void setExchangeFromUserId(long exchangeFromUserId) {
        this.exchangeFromUserId = exchangeFromUserId;
    }

    public boolean isQrCode() {
        return qrCode;
    }

    public void setQrCode(boolean qrCode) {
        this.qrCode = qrCode;
    }

    public String getSiteCodeId() {
        return siteCodeId;
    }

    public void setSiteCodeId(String siteCodeId) {
        this.siteCodeId = siteCodeId;
    }

    public long getMerId() {
        return merId;
    }

    public void setMerId(long merId) {
        this.merId = merId;
    }

}
