package com.niux.mywork.dao;

import com.niux.mywork.modle.Bill;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import org.springframework.dao.DataAccessException;

import java.util.Date;
import java.util.List;

@DAO(catalog = "nuomi_pay")
public interface BillDAO {

    String VIEW = "id,user_id,time,update_time,out_serial_id,serial_id,subject,body,pay_type,bank_type,money,deal_url,return_url,status,"
            + "code,trace";

    @SQL("insert bill(user_id,time,update_time,out_serial_id,serial_id,subject,body,pay_type,bank_type,money,deal_url,return_url,status,"
            + "code,trace)values(:b.userId,now(),now(),:b.outSerialId,:b.serialId,:b.subject,:b.body,:b.payType,:b.bankType,:b.money,"
            + ":b.dealUrl,:b.returnUrl,:b.status,:b.code,:b.trace)")
    @ReturnGeneratedKeys
    public long insert(@SQLParam("b") Bill bill);

    @SQL("select " + VIEW + " from bill where id = :id")
    public Bill getById(@SQLParam("id") long id);

    @SQL("select " + VIEW + " from bill  order by id desc limit :limit")
    public List<Bill> getAllBillsByLimit(@SQLParam("limit") int limit ) throws DataAccessException;

    @SQL("select " + VIEW + " from bill where serial_id = :serialId")
    public Bill getBySerialId(@SQLParam("serialId") String serialId);

    @SQL("select " + VIEW + " from bill where out_serial_id = :outSerialId")
    public List<Bill> getByOutSerialId(@SQLParam("outSerialId") String outSerialId);

    @SQL("select " + VIEW + " from bill where user_id = :userId order by time desc limit :offset, :limit")
    public List<Bill> findByUserId(@SQLParam("userId") long userId, @SQLParam("offset") long offset,
                                   @SQLParam("limit") long limit);

    @SQL("select count(1) from bill where user_id = :userId ")
    public int getCountByUserId(@SQLParam("userId") long userId);

    @SQL("update bill set status = :status, update_time=now() where id = :id and status <> :status")
    public int updateStatus(@SQLParam("id") long id, @SQLParam("status") int status);

    @SQL("update bill set status = :status, update_time=null where id = :id and status <> :status")
    public int revertStatus(@SQLParam("id") long id, @SQLParam("status") int status);

    @SQL("select " + VIEW + " from bill where status = 1 and out_serial_id =:id limit 1")
    public Bill getSuccessBillByOutSerialId(@SQLParam("id") String outSerialId);

    @SQL("select p.url from pay_url p, bill b where bill_id = b.id and b.out_serial_id =:id limit 1")
    public String getPayUrlByOutSerialId(@SQLParam("id") String outSerialId);
    
    @SQL("select min(id) from niux_pay.bill where status=:status and pay_type=:payType and time between :startTime and :endTime")
    public Long getMinIdByTimeAndStatusAndType(@SQLParam("status") int status, @SQLParam("payType") String payType
            , @SQLParam("startTime") Date startTime, @SQLParam("endTime") Date endTime);
    
    @SQL("select " + VIEW + " from bill where status=:status and pay_type=:payType and id>=:startId order by id limit :limit")
    public List<Bill> getByStartIdAndStatusAndType(@SQLParam("status") int status, @SQLParam("payType") String payType
            , @SQLParam("startId") long startId, @SQLParam("limit") int limit);
    
    
    @SQL("select " + VIEW + " from bill where status=1 and pay_type='cmccpay' and update_time between '2013-06-18' and '2013-06-26' limit :limit")
    public List<Bill> specGet(@SQLParam("limit") int limit);

}
