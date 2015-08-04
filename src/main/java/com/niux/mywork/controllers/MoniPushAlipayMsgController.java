package com.niux.mywork.controllers;

import com.niux.mywork.dao.CertificateDAO;
import com.niux.mywork.dao.OrderSelectDAO;
import com.niux.mywork.dao.PayUrlDAO;
import com.niux.mywork.modle.Bill;
import com.niux.mywork.modle.Certificate;
import com.niux.mywork.modle.Order;
import com.niux.mywork.modle.PayUrl;
import com.niux.mywork.service.BillService;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: maso
 * Date: 13-6-29
 * Time: 下午12:47
 * 模拟推送支付宝
 */

@Path("push")
public class MoniPushAlipayMsgController {

    @Autowired
    BillService billService;

    @Autowired
    PayUrlDAO payUrlDAO;

    @Autowired
    OrderSelectDAO orderDAO;

    @Autowired
    CertificateDAO certificateDAO;

    @Autowired
    @Qualifier("queue.certificate.notifywap.statuschange")
    private Destination pushAlipayCertificateDestination;

    @Autowired
    private JmsTemplate jmsTemplate;


    private final Pattern ALPAY_BUYER_ID_REGEX = Pattern.compile("buyer_id=([^&]*)&");


    @Get("")
    @Post("")
    public String index(@Param("limit") int limit) {
        List<Bill> billList = billService.getAllBillsLimit(limit);

        for (Bill bi : billList) {
            if (bi.getPayType().startsWith("alipay")) {

                //读取pay_url
                PayUrl payurl = payUrlDAO.findByBillId(bi.getId());

                if (payurl == null) {
                    continue;
                }

                // 获取订单号
                Order order = orderDAO.getOrder(bi.getOutSerialId(), bi.getUserId());


                if (order == null) {
                    continue;
                }
                //获取券
                List<Certificate> certificates = certificateDAO.findCertificateByOrderIdAndStatus(order.getId(),
                        order.getUserId(), Order.STATUS_SUCCESS, 0, 10);

                //推送

                String accountId = payurl.getBuyerEmail();
                if (accountId == null || "".equals(accountId)) {
                    Matcher matcher = ALPAY_BUYER_ID_REGEX.matcher(payurl.getUrl());
                    if (matcher.find()) {
                        accountId = matcher.group(1);
                    }
                }
                if(accountId != null){
                    jmsTemplate.convertAndSend(pushAlipayCertificateDestination, bi.getId() + "&" + accountId + "&" + Certificate.STATUS_UNUSE);
                }
//                for (Certificate c : certificates) {
//                    jmsTemplate.convertAndSend(pushAlipayCertificateDestination, c.getId() + "&" + accountId + "&" + Certificate.STATUS_UNUSE);
//                }


                //休眠
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                }


            }
        }

        return "@SUCCESS";
    }


}

