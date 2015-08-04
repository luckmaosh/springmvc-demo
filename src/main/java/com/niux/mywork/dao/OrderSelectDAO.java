package com.niux.mywork.dao;

import com.niux.mywork.modle.Order;
import net.paoding.rose.jade.annotation.*;
import org.springframework.dao.DataAccessException;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 订单dao 更新最好使用事务或使用存储过程 order为mysql关键字，必须添加反引号``
 *
 * @author liuwei
 * @createTime 2010-4-28 上午10:42:34
 */
@DAO(catalog = "nuomi_order")
public interface OrderSelectDAO {

    public static String VIEW = " id, user_id,time,pay_time,deal_id,status,title,address_id,price,money,total_money,count,pay_type,order_serial,delivery_type,"
            + "bank_type,deal_image,expire_time,deal_tinyurl,deal_option, meta, mobile, pay_record,area_id,adjust_money,delivery_cost,gift_card_money ";


    /**
     * 查找用户的指定状态订单
     *
     * @param userId
     * @param status
     * @param offset
     * @param limit
     * @return id倒序，不包含充值的
     */
    @UseMaster
    @SQL("select "
            + VIEW
            + " from `order` where deal_id > 0 and user_id = :userId #if(:status!=null) { and status in ( :status ) } order by id desc limit :offset, :limit")
    public List<Order> findByUserAndStatus(@ShardBy @SQLParam("userId") long userId, @SQLParam("status") int[] status,
                                           @SQLParam("offset") long offset, @SQLParam("limit") long limit);


    /**
     * 用户指定状态的订单数
     *
     * @param userId
     * @param status
     * @return 不包含充值的
     */
    @UseMaster
    @SQL("select count(1) from `order` where deal_id > 0 and user_id = :userId #if(:status!=null) { and status in ( :status ) }")
    public int getCountByUserAndStatus(@ShardBy @SQLParam("userId") long userId, @SQLParam("status") int[] status);


    @UseMaster
    @SQL("select " + VIEW
            + " from `order` where deal_id = :dealId and user_id = :userId  #if (:status!=2) {and status =:status} "
            + "order by id desc limit :offset, :limit ")
    public List<Order> findByUserAndDealStatus(@SQLParam("dealId") long dealId, @ShardBy @SQLParam("userId") long userId,
                                               @SQLParam("status") int status, @SQLParam("offset") long offset, @SQLParam("limit") long limit);

    @UseMaster
    @SQL("select " + VIEW + "from `order` where id=:id and user_id=:userId")
    public Order getOrder(@SQLParam("id") long id, @ShardBy @SQLParam("userId") long userId) throws DataAccessException;

    @UseMaster
    @SQL("select " + VIEW + "from `order` where id=:id")
    public Order getOrder(@SQLParam("id") long id) throws DataAccessException;

    @UseMaster
    @SQL("select " + VIEW + "from `order` where order_serial = :orderSerial and user_id=:userId")
    public Order getOrder(@SQLParam("orderSerial") String orderSerial, @ShardBy @SQLParam("userId") long userId) throws DataAccessException;

    /**
     * 用户一段时间内的订单数
     */
    @UseMaster
    @SQL("select count(1) from `order` where deal_id > 0 and status = :status and user_id = :userId and pay_time > :startTime and pay_time < :endTime")
    public int getPeriodCountByUserAndStatus(@ShardBy @SQLParam("userId") long userId, @SQLParam("status") int status,
                                             @SQLParam("startTime") Date startTime, @SQLParam("endTime") Date endTime);

    @SQL("select count(1) from `order` where user_id = :userId and id in (:ids) and status in (1, -5)")
    public int countSuccessByUserAndIds(@ShardBy @SQLParam("userId") long userId, @SQLParam("ids") List<Long> ids);

    @SQL("select count(1) from `order` where user_id = :userId and deal_id = :dealId and status in (1, -5)")
    public int countSuccessByUserAndDeal(@ShardBy @SQLParam("userId") long userId, @SQLParam("dealId") long dealId);

    @SQL("select "
            + VIEW
            + " from `order` where deal_id > 0 and user_id = :userId and status = 1 and pay_time between :startTime and :endTime ")
    public List<Order> findSuccessOrdersByUser(@ShardBy @SQLParam("userId") long userId, @SQLParam("startTime") Date startTime, @SQLParam("endTime") Date endTime);

    @SQL("select "
            + VIEW
            + " from `order` where user_id = :userId and id in (:ids);")
    List<Order> findOrderByUserAndIds(@ShardBy @SQLParam("userId") long userId, @SQLParam("ids") Set<Long> ids);
}
