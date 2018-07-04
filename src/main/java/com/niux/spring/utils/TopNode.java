package com.niux.spring.utils;

/**
 * @author 矛戈 on 2017/10/17.
 */
public class TopNode {

    private int  number;//编码
    private int count;//次数

    public TopNode(int number, int count) {
        this.number = number;
        this.count = count;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
