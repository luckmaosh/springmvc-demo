package com.niux.mywork.service;

import com.niux.mywork.dao.BillDAO;
import com.niux.mywork.modle.Bill;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maso
 * Date: 13-6-29
 * Time: 下午1:20
 * To change this template use File | Settings | File Templates.
 */
public class BillServiceImpl implements BillService {

    @Autowired
    BillDAO billDAO;

    @Override
    public List<Bill> getAlipayBillList() {
        List<Bill> result = new ArrayList<Bill>();
        return result;
    }

    @Override
    public List<Bill> getAllBillsLimit(int limit) {
        return billDAO.getAllBillsByLimit(limit);
    }
}
