package com.niux.mywork.modle;

/**
 * 订单状态
 * 
 * @author wensong
 * @since 2013-6-17 上午11:52:08 [wensong1987@gmail.com]
 */
public enum OrderStatus {

    UNPAID(0, "未付款"), //
    SUCCESS(1, "成功"), //
    CANCEL(-1, "订单取消"), //
    REFOUND(-5, "已退款");

    private int code;

    private String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private OrderStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

}
