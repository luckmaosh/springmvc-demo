package com.niux.mywork.service;

import com.niux.mywork.modle.Bill;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maso
 * Date: 13-6-29
 * Time: 下午1:20
 * To change this template use File | Settings | File Templates.
 */
public interface BillService {

    public List<Bill> getAlipayBillList();

    public List<Bill> getAllBillsLimit(int limit);

}
