package com.niux.mywork.modle;

import com.niux.mywork.utils.PriceUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.json.JSONArray;

import java.text.DecimalFormat;
import java.util.*;

/**
 * 订单信息
 * 
 * @author liuwei
 * @createTime 2010-4-27 下午07:44:21
 */
public class Order {

    public static final int STATUS_UNPAID = OrderStatus.UNPAID.getCode(); // 未付款 新订单默认状态

    public static final int STATUS_SUCCESS = OrderStatus.SUCCESS.getCode(); // 成功

    public static final int STATUS_CANCEL = OrderStatus.CANCEL.getCode(); // 订单取消

    public static final int STATUS_REFOUND = OrderStatus.REFOUND.getCode(); // 已经退款

    public static final int STATUS_ALL = 2;

    public static final int[] STATUS_ALL_LIST = new int[] { STATUS_CANCEL, STATUS_UNPAID,
            STATUS_SUCCESS, STATUS_REFOUND };

    public static final int[] STATUS_PAID = new int[] { STATUS_SUCCESS, STATUS_REFOUND };

    public static final int[] STATUS_UNCANCEL = new int[] { STATUS_UNPAID, STATUS_SUCCESS,
            STATUS_REFOUND };

    public static final int PAYTYPE_BANK = 1;

    public static final int PAYTYPE_ALIPAY = 2;

    public static final int ACCOUNT_PAY = 3;

    public static final int PAYTYPE_CHINAMOBILE = 4;

    // 增加支付类型-拉卡拉支付
    public static final int PAYTYPE_LAKALA = 5;

    public static final int PAYTYPE_ALIPAY_WAP = 6; // 支付宝 wap

    public static final int PAYTYPE_ALIPAY_WAP_CREDIT = 61; // 支付宝 wap-信用卡

    public static final int PAYTYPE_ALIPAY_WAP_DEBIT = 62; // 支付宝 wap-储蓄卡

    public static final int PAYTYPE_ALIPAY_APP = 7; // 支付宝 app

    //财付通支付渠道
    public static final int PAYTYPE_TENPAY_APP = 14;
    public static final int PAYTYPE_TENPAY_WAP = 15;
    public static final int PAYTYPE_TENPAY_ANDROID = 16;
    public static final int PAYTYPE_TENPAY = 17;


    // PAYTYPE: 100 ~ 199 已被预留给各种快捷支付


    public static final int PAYTYPE_QQTUAN = 99; // qq团购

    public static final int PAYTYPE_CARNIVAL = 10; // 嘉年华活动

    public static final int PAYTYPE_ACTIVITY_TWOE = 20; // 双11活动

    public static final int PAYTYPE_CHINAMOBILE_WAP = 11; // 手机支付 wap

    public static final int PAYTYPE_UNIONPAY = 12; // 银联移动支付（只使用客户端）

    public static final int PAYTYPE_LAKALAAPP = 13; // 拉卡拉移动支付（只使用客户端）

    private final DecimalFormat df = new DecimalFormat("########.##");

    private long id;

    private long areaId;

    private long userId;

    private Date time;

    private long dealId;

    private int status;

    private String title;

    // 发货地址id，默认为0,无须发货
    private long addressId;

    // 商品单价
    private double price;

    // 支付宝站外应付款
    private double money;

    // 总金额
    private double totalMoney;

    private int count;

    private int payType;

    private String bankType;

    /** 订单业务流水号--此号重复标记作为订单转充值处理 */
    private String orderSerial;

    // 派送类型（1-5 6-7 不限）
    private int deliveryType;

    private String purchaseVouchersIds;

    private String dealImage;

    private Date expireTime;

    private String dealTinyurl;

    private Date payTime;

    private String dealOption = "";

    // 其它元数据，有些deal有，根据deal不一样
    private String meta;

    private String mobile;

    private String dealurl;

    private int isSend;

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    //虚拟字段，用于使用，未使用，过期糯米券的排序
    private Date outTime;

    private Date useTime;

    private Date buyTime;

    // 不入库
    private long giftCardId;

    // 不入库
    private List<GiftCard> giftCards;

    // 不入库
    private boolean showSendCost;

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public int getIsSend() {
        return isSend;
    }

    public void setIsSend(int isSend) {
        this.isSend = isSend;
    }


    public void setDealurl(String dealurl) {
        this.dealurl = dealurl;
    }

    private boolean payRecord;

    private long adjustMoney;

    private long deliveryCost;

    private long giftCardMoney;

    private boolean isWap;

    private boolean needInvoice;

//    private OrderLogisticsInfo logisticsInfo;

//    public OrderLogisticsInfo getLogisticsInfo() {
//        return logisticsInfo;
//    }
//
//    public void setLogisticsInfo(OrderLogisticsInfo logisticsInfo) {
//        this.logisticsInfo = logisticsInfo;
//    }

    public boolean isWap() {
        return isWap;
    }

    public void setWap(boolean isWap) {
        this.isWap = isWap;
    }

    @Override
    public String toString() {
        String s = "id " + id + " userId " + userId + " dealId " + dealId + " orderSerial: "
                + orderSerial + " dealImage " + dealImage + " money " + money + " status " + status;
        return s;
    }

    private Object formatDate(Date input) {
        if (input == null) {
            return input;
        }
        try {
            return DateFormatUtils.format(input, DATE_FORMAT);
        } catch (Exception e) {
            e.printStackTrace();
            return input;
        }
    }

    public String toStringData() {
        String seprator = "&&";
        String s = id + seprator + userId + seprator + formatDate(time) + seprator + dealId
                + seprator + status + seprator + title + seprator + addressId + seprator + price
                + seprator + money + seprator + count + seprator + payType + seprator + "0"
                + seprator + purchaseVouchersIds + seprator + deliveryType + seprator + orderSerial
                + seprator + bankType + seprator + dealImage + seprator + formatDate(expireTime)
                + seprator + dealTinyurl + seprator + totalMoney + seprator + formatDate(payTime)
                + seprator + dealOption + seprator + "0" + seprator + meta + seprator + mobile
                + seprator + payRecord + seprator + areaId + seprator + adjustMoney + seprator
                + deliveryCost + seprator + giftCardMoney;
        return s;
    }

    public int getPriceDecimal() {
        // 总是显示1位
        return 1;
        // return NiuxUtil.getPriceDecimal(price);
    }

    /**
     * 此时刻前支付
     * 
     * @return
     */
    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public int getDealOptionId() {
        if (StringUtils.isNotBlank(dealOption)) {
            return Integer.valueOf(dealOption);
        }
        return 0;
    }

    public String getDealOption() {
        return dealOption;
    }

    public boolean isPayRecord() {
        return payRecord;
    }

    public void setPayRecord(boolean payRecord) {
        this.payRecord = payRecord;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getDealOption4Num() {
        return NumberUtils.toInt(dealOption);
    }

    public void setDealOption(String dealOption) {
        this.dealOption = dealOption;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
//        this.totalMoney = NiuxUtil.doubleToMoney(totalMoney);
    }

    public String getDealTinyurl() {
        return dealTinyurl;
    }

    public void setDealTinyurl(String dealTinyurl) {
        this.dealTinyurl = dealTinyurl;
    }

    public String getDealImage() {
        return dealImage;
    }

    public void setDealImage(String dealImage) {
        this.dealImage = dealImage;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getPurchaseVouchersIds() {
        return purchaseVouchersIds;
    }

    public void setPurchaseVouchersIds(String purchaseVouchersIds) {
        this.purchaseVouchersIds = purchaseVouchersIds;
    }

    public int getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(int deliveryType) {
        this.deliveryType = deliveryType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public long getUserId() {
        return userId;
    }

    public long getDealId() {
        return dealId;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setDealId(long dealId) {
        this.dealId = dealId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMoney(double money) {
        this.money = PriceUtil.truncate(money);
    }

    /**
     * 获得需要去站外支付的钱
     * 
     * @return
     */
    public double getMoney() {
        return money;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getAdjustMoney() {
        return adjustMoney;
    }

    public void setAdjustMoney(long adjustMoney) {
        this.adjustMoney = adjustMoney;
    }

    public long getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(long deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public void setGiftCardMoney(long giftCardMoney) {
        this.giftCardMoney = giftCardMoney;
    }

    public long getGiftCardMoney() {
        return giftCardMoney;
    }

    public boolean isExpire() {
        return (expireTime.before(new Date()) && (status == STATUS_UNPAID || status == STATUS_CANCEL)) ? true
                : false;
    }

    public boolean isAfterExpireTime() {
        return (expireTime.before(new Date())) ? true : false;
    }

    public boolean isNeedInvoice() {
        return needInvoice;
    }

    public void setNeedInvoice(boolean needInvoice) {
        this.needInvoice = needInvoice;
    }


    public long getGiftCardId() {
        return giftCardId;
    }

    public void setGiftCardId(long giftCardId) {
        this.giftCardId = giftCardId;
    }

    public List<GiftCard> getGiftCards() {
        return giftCards;
    }

    public void setGiftCards(List<GiftCard> giftCards) {
        this.giftCards = giftCards;
    }

    public boolean isShowSendCost() {
        return showSendCost;
    }

    public void setShowSendCost(boolean showSendCost) {
        this.showSendCost = showSendCost;
    }

    /**
     * 支付回调使用，过期时间判断延长1小时
     * 
     * @return
     */
    public boolean isAfterPayExpireTime() {
        Date d = new Date();
        d = DateUtils.addHours(d, -1);
        return (expireTime.before(d)) ? true : false;
    }

    /**
     * 某些特殊deal才有的属性
     * 
     * @return
     */
    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public List<Option> getOrderMultOptions() {
        if (StringUtils.isNotBlank(meta)) {
            List<Option> ls = new ArrayList<Option>();
            try {
                JSONArray ja = new JSONArray(meta);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject obj = (JSONObject) ja.get(i);
                    Option op = new Option();
                    op.setId(obj.getInt("id"));
                    op.setCount(obj.getInt("count"));
                    op.setName(obj.getString("name"));
                    op.setPrice(obj.getDouble("price"));
                    ls.add(op);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ls;
        }
        return null;
    }

    public Map<Integer, Option> getMultOptionMap() {
        if (StringUtils.isNotBlank(meta)) {
            Map<Integer, Option> result = new HashMap<Integer, Option>();
            try {
                JSONArray ja = new JSONArray(meta);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject obj = (JSONObject) ja.get(i);
                    Option op = new Option();
                    op.setId(obj.getInt("id"));
                    op.setCount(obj.getInt("count"));
                    op.setName(obj.getString("name"));
                    op.setPrice(obj.getDouble("price"));
                    result.put(obj.getInt("id"), op);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
        return null;
    }

    public String getOrderString() {
        List<Option> ops = getOrderMultOptions();
        if (ops != null) {
            return StringUtils.join(ops, "+");
        } else {
            return price + "x" + count;
        }
    }

    public class Option {

        private int id;

        private String name;

        private int count;

        private double price;

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return df.format(price) + "x" + count;

        }
    }

}
