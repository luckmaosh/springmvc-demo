package com.niux.mywork.dao;

import com.niux.mywork.modle.Certificate;
import com.niux.mywork.modle.User;
import net.paoding.rose.jade.annotation.*;
import org.springframework.dao.DataAccessException;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 购买凭证（密钥）表
 *
 * @author daiwenzhe
 *
 */
@DAO(catalog = "nuomi_niux")
public interface CertificateDAO {

    public static String VIEW = " id,order_id,deal_id,password,username,mobile,status,buy_time,use_time,expire_time,title,user_id,operator,operator_name,deal_image,deal_option_id,site_code,hide_sms "
            + ",mer_id,site_code_id";

    @SQL("insert into certificate(order_id,deal_id,password,username,status,buy_time,title,user_id,deal_image,expire_time,deal_option_id,hide_sms) "
            + "values(:certificate.orderId,:certificate.dealId,:certificate.password,:certificate.userName,"
            + ":certificate.status,:certificate.buyTime,:certificate.title,:certificate.userId,:certificate.dealImage,:certificate.expireTime,:certificate.dealOptionId,:certificate.hideSms)")
    @ReturnGeneratedKeys
    public long insert(@SQLParam("certificate") Certificate certificate) throws DataAccessException;

    @UseMaster
    @SQL("select " + VIEW + " from certificate where user_id = :userId " + " and status = 1 "
            + "order by use_time desc limit :offset, :limit")
    public List<Certificate> findUsedCertificateByUserId(@SQLParam("userId") long userId,
                                                         @SQLParam("offset") int offset, @SQLParam("limit") int limit) throws DataAccessException;

    @UseMaster
    @SQL("select " + VIEW + " from certificate where user_id = :userId "
            + " and status=:status and (status<>0 or expire_time>now()) " + "order by id desc limit :offset, :limit")
    public List<Certificate> findCertificateByUserIdNotExpire(@SQLParam("userId") long userId,
                                                              @SQLParam("status") long status, @SQLParam("offset") int offset, @SQLParam("limit") int limit)
            throws DataAccessException;

    @UseMaster
    @SQL("select " + VIEW + " from certificate where user_id = :userId and status=0 and expire_time<now() "
            + "order by id desc limit :offset, :limit")
    // FIXME status 未使用
    public List<Certificate> findCertificateByUserIdExpire(@SQLParam("userId") long userId,
                                                           @SQLParam("status") long status, @SQLParam("offset") int offset, @SQLParam("limit") int limit)
            throws DataAccessException;

    @UseMaster
    @SQL("select "
            + VIEW
            + " from certificate where user_id = :userId and status=0 and expire_time<now() order by id desc limit :offset, :limit")
    public List<Certificate> findCertificateByUserIdExpire(@SQLParam("userId") long userId,
                                                           @SQLParam("offset") int offset, @SQLParam("limit") int limit) throws DataAccessException;

    @UseMaster
    @SQL("select "
            + VIEW
            + " from certificate where user_id = :userId and expire_time<now() "
            + " #if(:asc == false){ order by expire_time desc } #if(:asc == true){ order by expire_time asc } limit :offset, :limit")
    public List<Certificate> findCertificateByUserIdExpireOrderByExpireTime(@SQLParam("userId") long userId,
                                                                            @SQLParam("offset") int offset, @SQLParam("limit") int limit, @SQLParam("asc") boolean asc)
            throws DataAccessException;

    @UseMaster
    @SQL("select count(1) from certificate where user_id = :userId and status=0 and expire_time<now()")
    public int getCountByUserIdExpire(@SQLParam("userId") long userId) throws DataAccessException;

    @UseMaster
    @SQL("select "
            + VIEW
            + " from certificate where order_id = :orderId and user_id = :userId order by id desc limit :offset, :limit")
    public List<Certificate> findCertificateByOrderId(@SQLParam("orderId") long orderId,
                                                      @SQLParam("userId") long userId, @SQLParam("offset") int offset, @SQLParam("limit") int limit);

    @UseMaster
    @SQL("select "
            + VIEW
            + " from certificate where order_id = :orderId and user_id = :userId and status = :status order by id desc limit :offset, :limit")
    public List<Certificate> findCertificateByOrderIdAndStatus(@SQLParam("orderId") long orderId,
                                                               @SQLParam("userId") long userId, @SQLParam("status") int status, @SQLParam("offset") int offset,
                                                               @SQLParam("limit") int limit);

    @UseMaster
    @SQL("select count(1) from certificate where order_id = :orderId and user_id = :userId and status = :status")
    public int getCountByOrderIdAndStatus(@SQLParam("orderId") long orderId,
                                          @SQLParam("userId") long userId, @SQLParam("status") int status);

    @UseMaster
    @SQL("select " + VIEW
            + " from certificate where deal_id = :dealId and user_id = :userId order by id desc limit :offset, :limit")
    public List<Certificate> findCertificateByDealId(@SQLParam("dealId") long dealId, @SQLParam("userId") long userId,
                                                     @SQLParam("offset") int offset, @SQLParam("limit") int limit);

    @UseMaster
    @SQL("select count(1) from certificate where user_id = :userId  and status #if (:status==2) {<}=:status")
    public int getCountByUserId(@SQLParam("userId") long userId, @SQLParam("status") int status)
            throws DataAccessException;

//    @UseMaster
//    @SQL("select count(1) from certificate where user_id = :userId and status=:status and (status<>0 or expire_time>now())")
//    public int getCountByUserIdNotExpire(@SQLParam("userId") long userId, @SQLParam("status") int status)
//            throws DataAccessException;

    @UseMaster
    @SQL("select count(1) from certificate where user_id = :userId and status=0 and expire_time<now() ")
    // FIXME status 未使用
    public int getCountByUserIdExpire(@SQLParam("userId") long userId, @SQLParam("status") int status)
            throws DataAccessException;

    @UseMaster
    @SQL("select count(1) from certificate where user_id = :userId  and order_id=:orderId")
    public int getCountByOrder(@SQLParam("orderId") long orderId, @SQLParam("userId") long userId)
            throws DataAccessException;

    @UseMaster
    @SQL("select count(1) from certificate where deal_id=:dealId and user_id=:userId")
    public int getCountByDeal(@SQLParam("dealId") long dealId, @SQLParam("userId") long userId)
            throws DataAccessException;

    @UseMaster
    @SQL("select count(1) from certificate where user_id = :userId")
    public int getCountByUser(@SQLParam("userId") long userId);

    @UseMaster
    @SQL("select " + VIEW + " from certificate where user_id = :userId order by id desc limit :offset, :limit")
    public List<Certificate> findByUser(@SQLParam("userId") long userId, @SQLParam("offset") int offset,
                                        @SQLParam("limit") int limit);

    @UseMaster
    @SQL("select count(*) from certificate where user_id = :userId and (status = :status1 or status = :status2 or status = :status3)")
    public int getCountByUserAndStatus(@SQLParam("userId") long userId, @SQLParam("status1") int status1,
                                       @SQLParam("status2") int status2, @SQLParam("status3") int status3);

    @UseMaster
    @SQL("select "
            + VIEW
            + " from certificate where user_id = :userId and (status = :status1 or status = :status2 or status = :status3) order by id desc limit :offset, :limit")
    public List<Certificate> findByUserAndStatus(@SQLParam("userId") long userId, @SQLParam("status1") int status1,
                                                 @SQLParam("status2") int status2, @SQLParam("status3") int status3, @SQLParam("offset") int offset,
                                                 @SQLParam("limit") int limit);

    @UseMaster
    @SQL("select  " + VIEW + " from certificate where id=:id and user_id = :userId ")
    public Certificate getCertificate(@SQLParam("id") long id, @SQLParam("userId") long userId)
            throws DataAccessException;

    @UseMaster
    @SQL("select " + VIEW + " from certificate where user_id = :userId and id in (:cerId)")
    public List<Certificate> getCertificateRange(@SQLParam("userId") long userId, @SQLParam("cerId") long[] cerId) throws DataAccessException;

    @UseMaster
    @SQL("select  " + VIEW + " from certificate where id=:id and operator=:operator and status=1")
    public Certificate getCertificateByIdOperator(@SQLParam("id") long id, @SQLParam("operator") long operator)
            throws DataAccessException;

    @SQL(" update certificate set status = :status where id = :id ")
    public void updateStatus(@SQLParam("id") long id, @SQLParam("status") int status) throws DataAccessException;

    @SQL(" update certificate set status = :status ,operator_name=:username , use_time=:time where id = :id ")
    public void updateStatus(@SQLParam("id") long id, @SQLParam("username") String username,
                             @SQLParam("time") String time, @SQLParam("status") int status) throws DataAccessException;

    @SQL(" update certificate set status = :status ,operator_name=:username ,operator=:operatorId, use_time=:time where id = :id ")
    public void update4Synch(@SQLParam("id") long id, @SQLParam("username") String username,
                             @SQLParam("operatorId") long operatorId, @SQLParam("time") String time, @SQLParam("status") int status)
            throws DataAccessException;

    @SQL(" update certificate set mobile = :mobile where id = :id ")
    public void updateMobile(@SQLParam("id") long id, @SQLParam("mobile") String mobile) throws DataAccessException;

    @SQL("update certificate set " + " expire_time = :dealExpireTime " + " where deal_id = :dealId")
    public int updateExpireTime(@SQLParam("dealId") long dealId, @SQLParam("dealExpireTime") Date dealExpireTime)
            throws DataAccessException;

    @SQL(" update certificate set password = :password where id = :id and status=0")
    public void updateStatusPassword(@SQLParam("id") long id, @SQLParam("password") String password)
            throws DataAccessException;

    @SQL(" update certificate set deal_option_id = :dealOptionId,title=:title where order_id=:orderId")
    public void updateDealOption(@SQLParam("dealOptionId") int dealOptionId, @SQLParam("title") String title,
                                 @SQLParam("orderId") long orderId) throws DataAccessException;

    @SQL(" update certificate set status = :status where order_id = :orderId")
    public void updateStatusByOrderId(@SQLParam("orderId") long orderId, @SQLParam("status") int status)
            throws DataAccessException;

    @UseMaster
    @SQL("select  " + VIEW
            + " from certificate where deal_id=:dealId and status=:status and operator in (:operators) and "
            + "use_time between :date and date_add(:date,interval 1 day) order by use_time desc limit :offset, :limit")
    public List<Certificate> findCertificateByStatusDealOperatorAndDate(@SQLParam("dealId") long dealId,
                                                                        @SQLParam("operators") List<Long> operators, @SQLParam("date") Date date, @SQLParam("status") int status,
                                                                        @SQLParam("offset") int offset, @SQLParam("limit") int limit) throws DataAccessException;

    @UseMaster
    @SQL("select  count(1) from certificate where deal_id=:dealId and status=:status and operator in (:operators) and "
            + "use_time between :date and date_add(:date,interval 1 day)")
    public int getCertificateCountByStatusDealOperatorAndDate(@SQLParam("dealId") long dealId,
                                                              @SQLParam("operators") List<Long> operators, @SQLParam("date") Date date, @SQLParam("status") int status);

    @SQL("update certificate set status = :status,operator=:user.id,operator_name=:user.name,use_time=now() where id = :id and status=0 and now() < expire_time")
    public int use(@SQLParam("id") long id, @SQLParam("status") int status, @SQLParam("user") User user)
            throws DataAccessException;

    @SQL("update certificate set status = :status,operator=:user.id,operator_name=:user.name,use_time=now() where id = :id and status=0 and now() < :date")
    public int use(@SQLParam("id") long id, @SQLParam("status") int status, @SQLParam("user") User user,
                   @SQLParam("date") String date) throws DataAccessException;

    @SQL("update certificate set status = :status,operator=:user.id,operator_name=:user.name,use_time=:useTime where id = :id and status=0 and :useTime < expire_time")
    public int use(@SQLParam("id") long id, @SQLParam("status") int status, @SQLParam("user") User user,
                   @SQLParam("useTime") Date useTime) throws DataAccessException;

    @SQL("update certificate set status = :status,operator=:user.id,operator_name=:user.name,use_time=now() where id = :id and status=0")
    public int expireUse(@SQLParam("id") long id, @SQLParam("status") int status, @SQLParam("user") User user)
            throws DataAccessException;

    // and now() < expire_time
    @SQL("update certificate set status = :status,password=:qqVerifyCode,operator=:user.id,operator_name=:user.name,use_time=now() where id = :id and status=0")
    public int qqTuanUse(@SQLParam("id") long id, @SQLParam("status") int status,
                         @SQLParam("qqVerifyCode") String qqVerifyCode, @SQLParam("user") User user) throws DataAccessException;

    @SQL("update certificate set site_code = :siteCode where id = :id")
    public void updateSiteCode(@SQLParam("siteCode") String siteCode, @SQLParam("id") long id)
            throws DataAccessException;
    
    @SQL("update certificate set site_code_id = :siteCodeId where id = :id")
    public long updateSiteCodeId(@SQLParam("siteCodeId") String siteCodeId, @SQLParam("id") long id)
            throws DataAccessException;

    @SQL("update certificate set site_code = :siteCode,mer_id=:merId #if(:siteCodeId!=null){,site_code_id=:siteCodeId} where id = :id")
    public void updateSiteCodeAndMerId(@SQLParam("merId") long merId, @SQLParam("siteCodeId") String siteCodeId,
                                       @SQLParam("siteCode") String siteCode, @SQLParam("id") long id) throws DataAccessException;

    @SQL("update certificate set status = :status,operator=:merId,use_time=now() where mer_id=:merId and site_code_id = :siteCodeId #if(:dealId!=0){ and deal_id=:dealId } #if(:checkExpire==true){ and now() < expire_time} and status in (0,-3)")
    public int siteCodeUse(@SQLParam("siteCodeId") String siteCodeId, @SQLParam("status") int status,
                           @SQLParam("merId") long merId, @SQLParam("dealId") long dealId, @SQLParam("checkExpire") boolean checkExpire)
            throws DataAccessException;
    @UseMaster
    @SQL("select  " + VIEW + " from certificate where id=:id")
    public Certificate getCertificate(@SQLParam("id") long id) throws DataAccessException;

    /**
     * 对帐使用，查找没有订单的券(系统赠出的)
     *
     * @param dealId
     * @return
     */
    @UseMaster
    @SQL("select " + VIEW + " from certificate where deal_id = :dealId and order_id = 0 limit :offset, :limit")
    public List<Certificate> findNoOrderCertificatesByDeal(@SQLParam("dealId") long dealId,
                                                           @SQLParam("offset") int offset, @SQLParam("limit") int limit);

    @UseMaster
    @SQL("select count(1) from certificate where deal_id=:dealId and operator=:id and status=1")
    public int getCountByOperator(@SQLParam("id") long id, @SQLParam("dealId") long dealId) throws DataAccessException;

    @UseMaster
    @SQL("select count(1) from certificate where deal_id=:dealId and operator=:id and date(now())=date(use_time) and status=1")
    public int getTodayCountByOperator(@SQLParam("id") long id, @SQLParam("dealId") long dealId)
            throws DataAccessException;

    @UseMaster
    @SQL("select deal_id,count(1) from certificate where  deal_id in(:ids) and status = :status and buy_time>= :start and buy_time < :end group by deal_id")
    public Map<Long, Integer> findCountByDealAndTimeStatus(@SQLParam("ids") List<Long> ids,
                                                           @SQLParam("status") int status, @SQLParam("start") String start, @SQLParam("end") String end);

    /**
     * 更新状态
     *
     * @param id
     * @param userId
     * @param oldStatus
     * @param newStatus
     * @return
     */
    @SQL("update certificate set status = :newStatus where id = :id and user_id = :userId and status = :oldStatus")
    public int updateStatus(@SQLParam("id") long id, @SQLParam("userId") long userId,
                            @SQLParam("oldStatus") int oldStatus, @SQLParam("newStatus") int newStatus);

    @UseMaster
    @SQL("select  "
            + VIEW
            + " from certificate where mer_id=:merId and trim(trailing '\r' from site_code_id)=:siteCodeId #if(:dealId!=0){ and deal_id=:dealId} limit 1")
    public Certificate getCertificateByMerIdAndSiteCodeId(@SQLParam("merId") long merId,
                                                          @SQLParam("siteCodeId") String siteCodeId, @SQLParam("dealId") long dealId) throws DataAccessException;

    @UseMaster
    @SQL("select count(1) from certificate where mer_id=:merId and trim(trailing '\r' from site_code_id)=:siteCodeId limit 1")
    public int getCountByMerIdAndSiteCodeId(@SQLParam("merId") long merId, @SQLParam("siteCodeId") String siteCodeId)
            throws DataAccessException;

    /**
     * eLong验证码
     */
    @UseMaster
    @SQL("select  " + VIEW + " from certificate where deal_id=:dealId and site_code=:siteCode limit 1")
    public Certificate getCertificateByDealAndSiteCode(@SQLParam("dealId") long dealId,
                                                       @SQLParam("siteCode") String siteCode) throws DataAccessException;

    @UseMaster
    @SQL("select "
            + VIEW
            + " from certificate where user_id = :userId and status = :status and expire_time > :today order by expire_time asc limit :offset, :limit")
    public List<Certificate> findByUserAndStatusByExpire(@SQLParam("userId") long userId,
                                                         @SQLParam("status") int status, @SQLParam("today") Date now, @SQLParam("offset") int offset,
                                                         @SQLParam("limit") int limit);

    @UseMaster
    @SQL("select "
            + VIEW
            + " from certificate where user_id = :userId "
            + " #if(:status != 2 && status != -1){ and status = :status } "
            + " #if(:status == -1){ and expire_time < now() } "
            + " #if(:asc == false){ order by expire_time desc } #if(:asc == true){ order by expire_time asc } limit :offset, :limit")
    public List<Certificate> findByUserAndStatusByExpire(@SQLParam("userId") long userId,
                                                         @SQLParam("status") int status, @SQLParam("today") Date now, @SQLParam("offset") int offset,
                                                         @SQLParam("limit") int limit, @SQLParam("asc") boolean asc);

    @UseMaster
    @SQL("select count(1) from certificate where user_id = :userId and status = :status and expire_time > :today")
    public int getCountByUserAndStatusByExpire(@SQLParam("userId") long userId, @SQLParam("status") int status,
                                               @SQLParam("today") Date now);

    @UseMaster
    @SQL("select count(1) from certificate where deal_id=:dealId and order_id=:orderId and status=:status")
    public int getCountByOrderAndStatus(@SQLParam("dealId") long dealId, @SQLParam("orderId") long orderId,
                                        @SQLParam("status") int status);

    @UseMaster
    @SQL("select " + VIEW + " from certificate where deal_id=:dealId and order_id=:orderId")
    public List<Certificate> findBydealAndOrder(@SQLParam("dealId") long dealId, @SQLParam("orderId") long orderId);

    @UseMaster
    @SQL("select " + VIEW + " from certificate where deal_id=:dealId and order_id=:orderId and status =:status")
    public List<Certificate> findBydealAndOrder(@SQLParam("dealId") long dealId, @SQLParam("orderId") long orderId,
                                                @SQLParam("status") int status);

    @UseMaster
    @SQL("select " + VIEW + " from certificate where deal_id=:dealId")
    public List<Certificate> findByDeal(@SQLParam("dealId") long dealId);

    @SQL("update certificate set buy_time = :time where id=:id")
    public int updateBuyTime(@SQLParam("id") long id, @SQLParam("time") Date time) throws DataAccessException;

    @UseMaster
    @SQL("select order_id from certificate  where user_id = :userId and deal_id = :dealId and status = :status")
    public List<Long> findCertificateOrderIdList(@SQLParam("userId") long userId, @SQLParam("dealId") long dealId,
                                                 @SQLParam("status") int status);

    @UseMaster
    @SQL("select " + VIEW + " from certificate  where deal_id in (:dealIds) and operator=:operatorId and use_time " +
            "between :date and date_add(:date,interval 1 day) and status=1 limit :offset , :limit")
    public List<Certificate> findUsedCertifycateByDealOperatorAndDate(@SQLParam("dealIds") List<Long> dealIds, @SQLParam("operatorId") long id,
                                                                      @SQLParam("date") Date date, @SQLParam("offset") int offset, @SQLParam("limit") int limit);

    @SQL("update certificate set status = 1,operator =:user.id,operator_name=:user.name,use_time =now() where deal_id =:dealId and user_id =:userId and status = 0 #if(:optionId > 0) { and deal_option_id =:optionId }")
    public int updateStatus(@SQLParam("dealId") long dealId, @SQLParam("optionId") long optionId,
                            @SQLParam("userId") long userId, @SQLParam("user") User user);

    @SQL("select id from certificate where deal_id =:dealId and user_id =:userId and status = 0 #if(:optionId > 0) { and deal_option_id =:optionId }")
    public List<Long> getBaoLiangCerId(@SQLParam("dealId") long dealId, @SQLParam("optionId") long optionId,
                                       @SQLParam("userId") long userId);


    @UseMaster
    @SQL("select deal_id from certificate where user_id=:userId and order_id>0 and status<>-2")
    public abstract List<Long> findAllNotRefund(@SQLParam("userId") long paramLong) throws DataAccessException;

    @UseMaster
    @SQL("select  " + VIEW
            + " from certificate where deal_id in(:dealIds) and status=:status and operator =:operatorId and "
            + "use_time between :startTime and :endTime order by use_time desc limit :offset, :limit")
    public List<Certificate> findByStatusDealOperatorDate(
            @SQLParam("dealIds") List<Long> dealIds, @SQLParam("operatorId") long operatorId,
            @SQLParam("startTime") Date startTime, @SQLParam("endTime") Date endTime, @SQLParam("status") int status,
            @SQLParam("offset") int offset, @SQLParam("limit") int limit) throws DataAccessException;

    @UseMaster
    @SQL("select count(1) "
            + " from certificate where deal_id in(:dealIds) and status=:status and operator =:operatorId and "
            + "use_time between :startTime and :endTime order by use_time desc limit :offset, :limit")
    public int findCountByStatusDealOperatorDate(@SQLParam("dealIds") List<Long> dealIds,
                                                 @SQLParam("operatorId") long operatorId, @SQLParam("startTime") Date startTime,
                                                 @SQLParam("endTime") Date endTime, @SQLParam("status") int status, @SQLParam("offset") int offset,
                                                 @SQLParam("limit") int limit) throws DataAccessException;

    @UseMaster
    @SQL("select  " + VIEW
            + " from certificate where deal_id in(:dealIds) and status=:status and operator =:operatorId and deal_option_id in(:dealOptionIds) and "
            + " use_time between :startTime and :endTime order by use_time desc limit :offset, :limit")
    public List<Certificate> findByStatusDealOptionOperatorDate(@SQLParam("dealIds") List<Long> dealIds,
                                                                @SQLParam("operatorId") long operatorId, @SQLParam("dealOptionIds") List<Integer> dealOptionIds,
                                                                @SQLParam("startTime") Date startTime, @SQLParam("endTime") Date endTime, @SQLParam("status") int status,
                                                                @SQLParam("offset") int offset, @SQLParam("limit") int limit) throws DataAccessException;

    @UseMaster
    @SQL("select  count(1) "
            + " from certificate where deal_id in(:dealIds) and status=:status and operator =:operatorId and deal_option_id in(:dealOptionIds) and "
            + " use_time between :startTime and :endTime order by use_time desc")
    public int findCountByStatusDealOptionOperatorDate(
            @SQLParam("dealIds") List<Long> dealIds, @SQLParam("operatorId") long operatorId,
            @SQLParam("dealOptionIds") List<Integer> dealOptionIds,
            @SQLParam("startTime") Date startTime, @SQLParam("endTime") Date endTime, @SQLParam("status") int status)
            throws DataAccessException;

    @UseMaster
    @SQL("select  "
            + VIEW
            + " from certificate where deal_id in(:dealIds) and status=:status and operator =:operatorId and deal_option_id in(:dealOptionIds) "
            + "  order by use_time desc limit :offset, :limit")
    public List<Certificate> findByStatusDealOptionOperator(
            @SQLParam("dealIds") List<Long> dealIds, @SQLParam("operatorId") long operatorId,
            @SQLParam("dealOptionIds") List<Integer> dealOptionIds, @SQLParam("status") int status,
            @SQLParam("offset") int offset,
            @SQLParam("limit") int limit) throws DataAccessException;

    @UseMaster
    @SQL("select count(1) "
            + " from certificate where deal_id in(:dealIds) and status=:status and operator =:operatorId and deal_option_id in(:dealOptionIds) "
            + "  order by use_time desc ")
    public int findCountByStatusDealOptionOperator(@SQLParam("dealIds") List<Long> dealIds,
                                                   @SQLParam("operatorId") long operatorId, @SQLParam("dealOptionIds") List<Integer> dealOptionIds,
                                                   @SQLParam("status") int status)
            throws DataAccessException;

    // @UseMaster
    // @SQL("select  " + VIEW + " from certificate where deal_id in(:dealIds) and status=:status and "
    // + " use_time between :startTime and :endTime order by use_time desc limit :offset, :limit")
    // public List<Certificate> findByStatusDealMasterDate(
    // @SQLParam("dealIds") List<Long> dealIds, @SQLParam("startTime") Date startTime,
    // @SQLParam("endTime") Date endTime,
    // @SQLParam("status") int status,
    // @SQLParam("offset") int offset, @SQLParam("limit") int limit) throws DataAccessException;


    @UseMaster
    @SQL("select  "
            + VIEW
            + " from certificate where deal_id in(:dealIds) and status=:status "
            + " #if(null!=:startTime && null!=:endTime){and use_time between :startTime and :endTime } order by use_time desc #if(:limit!=0){limit :offset, :limit}")
    public List<Certificate> findByStatusDealMasterDate(@SQLParam("dealIds") List<Long> dealIds,
                                                        @SQLParam("startTime") Date startTime, @SQLParam("endTime") Date endTime, @SQLParam("status") int status,
                                                        @SQLParam("offset") int offset, @SQLParam("limit") int limit) throws DataAccessException;

    @UseMaster
    @SQL("select  count(1) from certificate where deal_id in(:dealIds) and status=:status  "
            + " #if(null!=:startTime && null!=:endTime){and use_time between :startTime and :endTime } order by use_time desc ")
    public int findCountByStatusDealMasterDate(
            @SQLParam("dealIds") List<Long> dealIds, @SQLParam("startTime") Date startTime,
            @SQLParam("endTime") Date endTime, @SQLParam("status") int status)
            throws DataAccessException;

    @UseMaster
    @SQL("select  " + VIEW
            + " from certificate where deal_id in(:dealIds) and status=:status and deal_option_id in(:dealOptionIds) and operator =:operatorId"
            + "  and use_time>:endTime order by use_time desc limit :offset")
    public List<Certificate> findUsedCertificateLastN4CheckNew(@SQLParam("dealIds") List<Long> dealIds,
                                                               @SQLParam("operatorId") long operatorId,
                                                               @SQLParam("dealOptionIds") List<Integer> dealOptionIds,
                                                               @SQLParam("status") int status, @SQLParam("offset") int offset, @SQLParam("endTime") Date endTime) throws DataAccessException;

    @UseMaster
    @SQL("select distinct(deal_id) from certificate where user_id = :userId and status=0 and expire_time > now() "
            + " #if(:orderType == 1){ order by buy_time desc } #if(:orderType == 2){ order by expire_time desc } limit :offset, :limit")
    public List<Long> findUnuseDealIdByUser(@SQLParam("userId") long userId, @SQLParam("orderType") int orderType,
                                            @SQLParam("offset") int offset, @SQLParam("limit") int limit);

    @UseMaster
    @SQL("select distinct(deal_id) from certificate where user_id = :userId and status=0 and expire_time < now() order by id desc")
    public List<Long> findExpiredDealIdByUser(@SQLParam("userId") long userId, @SQLParam("offset") int offset,
                                              @SQLParam("limit") int limit);

    @UseMaster
    @SQL("select distinct(deal_id) from certificate where user_id = :userId and status=1 order by use_time desc")
    public List<Long> findUsedDealIdByUser(@SQLParam("userId") long userId, @SQLParam("offset") int offset,
                                           @SQLParam("limit") int limit);

    @UseMaster
    @SQL("select count(distinct(deal_id)) from certificate where user_id = :userId and status=0 and expire_time > now()")
    public int countUnuseDealIdByUser(@SQLParam("userId") long userId);

    @UseMaster
    @SQL("select count(distinct(deal_id)) from certificate where user_id = :userId and status=0 and expire_time < now()")
    public int countExpiredDealIdByUser(@SQLParam("userId") long userId);

    @UseMaster
    @SQL("select count(distinct(deal_id)) from certificate where user_id = :userId and status=1")
    public int countUsedDealIdByUser(@SQLParam("userId") long userId);

    @UseMaster
    @SQL("select "
            + VIEW
            + " from certificate where status = :status and deal_id = :dealId and user_id = :userId order by id desc limit :offset, :limit")
    public List<Certificate> findCertificateByUserAndDealAndStatus(@SQLParam("dealId") long dealId,
                                                                   @SQLParam("userId") long userId, @SQLParam("status") int status, @SQLParam("offset") int offset,
                                                                   @SQLParam("limit") int limit);

    @UseMaster
    @SQL("select  " + VIEW
            + " from certificate where order_id = :orderId and user_id = :userId and status = 1 order by use_time desc limit :offset, :limit")
    public List<Certificate> findByOrderAndStatusByUsedTime(@SQLParam("orderId") long orderId, @SQLParam("userId") long userId, @SQLParam("offset") int offset, @SQLParam("limit") int limit) throws DataAccessException;

    @SQL("select  " + VIEW
            + " from certificate where deal_id = :dealId and user_id = :userId and status = 1 order by use_time desc limit :offset, :limit")
    public List<Certificate> findByDealAndStatusByUsedTime(@SQLParam("dealId") long dealId, @SQLParam("userId") long userId, @SQLParam("offset") int offset, @SQLParam("limit") int limit) throws DataAccessException;
    
    /**
     * 填充数据用，其他请勿用
     * @return
     * @throws org.springframework.dao.DataAccessException
     */
    @SQL("select deal_id,id,password,site_code from certificate where id between :start and :end and site_code='' and status!=-3")
    public List<Certificate> findDataForFill(@SQLParam("start") long start, @SQLParam("end") long end) throws DataAccessException;

    /**
     * 填充数据用，其他请勿用
     * @param start
     * @param end
     * @return
     * @throws org.springframework.dao.DataAccessException
     */
    @SQL("update certificate set site_code_id=lpad(concat(cast(id as char),password),12,'0') where site_code='' and status!=-3 and length(password)=4 and id between :start and :end")
    public void batchUpdateSiteCodeId(@SQLParam("start") long start, @SQLParam("end") long end) throws DataAccessException;
    @SQL("select count(1) from certificate  where deal_id =:dealid and buy_time >= :datetime ")
    public int    getCountBydealId(@SQLParam("dealid") long dealid, @SQLParam("datetime") Date datetime);

    @SQL("select count(1) from certificate  where deal_id =:dealId and buy_time >= :startTime and buy_time <= :endTime ")
    public Long countByDealAndBuyTime(@SQLParam("dealId") long dealId, @SQLParam("startTime") Date startTime, @SQLParam("endTime") Date endTime);

    @SQL("select  " + VIEW
            + " from certificate where user_id = :userId and status =:status #if(:status == 0){ and expire_time > now() } order by id desc;")
    List<Certificate> findAllByUserIdAndStatus(@SQLParam("userId") long userId, @SQLParam("status") int status);

    @SQL("update certificate set hide_sms = :newHideSMS where deal_id = :dealId and hide_sms != :oldHideSMS")
    public int updateHideSMS(@SQLParam("dealId") long dealId, @SQLParam("newHideSMS") int hideSMS, @SQLParam("oldHideSMS") int oldHideSMS) throws DataAccessException;
}
