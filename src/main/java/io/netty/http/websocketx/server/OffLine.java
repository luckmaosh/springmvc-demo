/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package io.netty.http.websocketx.server;

/**
 * User: maso [maoshaohui@baidu.com]
 * Date: 15-1-8
 * Time: 下午2:46
 */
public class OffLine {

    private int id;

    private int mdFlag;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMdFlag() {
        return mdFlag;
    }

    public void setMdFlag(int mdFlag) {
        this.mdFlag = mdFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
