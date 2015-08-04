package com.niux.mywork.dao;

import com.niux.mywork.modle.PayUrl;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import java.util.List;

@DAO(catalog = "nuomi_pay")
public interface PayUrlDAO {

    String VIEW = "id,url,bill_id,time,user_id,type,buyer_email";

    @SQL("select " + VIEW + " from pay_url where url like :url  limit :offset, :limit ")
    public List<PayUrl> findByUrl(@SQLParam("url") String url, @SQLParam("offset") long offset,
                                  @SQLParam("limit") long limit);

    @SQL("select count(1) from pay_url where url like :url")
    public int getCountByUrl(@SQLParam("url") String url);

    @SQL("insert pay_url(url,bill_id,time,user_id,type , buyer_email )values(:pu.url,:pu.billId,now(),:pu.userId,:pu.type, :pu.buyerEmail)")
    public void insert(@SQLParam("pu") PayUrl payUrl);
    
    
    @SQL("select " + VIEW + " from pay_url where bill_id=:billId ")
    public PayUrl findByBillId(@SQLParam("billId") long billId);


}
