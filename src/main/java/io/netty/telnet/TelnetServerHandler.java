/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.telnet;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;
import io.netty.common.NettyConstants;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.po.MobileChannel;
import io.netty.po.UserPerson;

import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.djt.v2.dao.netty.OffLineDao;
import com.djt.v2.pojo.OffLine;
import com.djt.v2.service.netty.OffLineService;
import com.djt.v2.utils.ContextUtils;
import com.djt.v2.utils.SpringContextUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * Handles a server-side channel.
 */
@Sharable
public class TelnetServerHandler extends ChannelInboundMessageHandlerAdapter<String> {

	private static Log logger = LogFactory.getLog(TelnetServerHandler.class);
	
    private int count;
    private String userCode;
    
    /** 离线服务 */
	private OffLineService offLineService;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // Send greeting for a new connection.
    	//System.out.println("test:"+ InetAddress.getLocalHost().getHostName() );
        //ctx.write("{\"user\":[{\"pwd\":\"123\",\"userCode\":\"admin\",\"flag\":\"empty\"}]}\r\n");
       // ctx.write("It is " + new Date() + " now.\r\n");
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, String request) throws Exception {
        // Generate and write a response.
        String response="";;
        boolean close = false;
        List<OffLine> list = new ArrayList<OffLine>();
        logger.info("接受报文1："+request);
        try{
        	 if (request !=null && !"".equals(request)&&request.indexOf("respData")>-1){
            	 //request = new String(request.getBytes(),"utf-8");   
            	 logger.info("接受报文2："+request);
            	 JSONObject myObj = JSONObject.parseObject(request);
                 JSONArray myArray = myObj.getJSONArray("respData");
                 if(myArray !=null && myArray.size()==1){
                	 logger.info("接受报文3："+request);
                	 for(int i=0;i<myArray.size();i++)
                     {
                		 logger.info("接受报文4："+request);
    	                 JSONObject o = myArray.getJSONObject(i);
    	                 ////0：表示ping;1:表示连接；-1：表示关闭连接；2:表示用户登录；3：表示数据推送
    	                 String flag=o.get("flag")+"";
    	                 
    	                 String eachData="";
    	                 count=0;
    	                 if("0".equals(flag)){
    	                	 eachData=o.get("eachData")+"";
    	                	 System.out.println("针对心跳");
    	                	 logger.info("针对心跳");
    	                 }else if("1".equals(flag)){
    	                	 System.out.println("客户端一开始连接的时候，还没有登录，则走这里");
    	                	 logger.info("客户端一开始连接的时候");
    	                 }else if("2".equals(flag)){
    	                	 String mobileFlag=o.getString("mobileFlag");
    	                	 String userCode=o.get("userCode")+"";
    	                	 logger.info("注册用户信道："+userCode);
    	                	 MobileChannel mobileChannel=new MobileChannel();
    	                	 mobileChannel.setCtx(ctx);
    	                	 mobileChannel.setMmobilieFlag(mobileFlag);
    	                	 if(userCode !=null &&!"".equals(userCode)&&!NettyConstants.MAPCHANNEL.containsKey(userCode)){
    	                		 NettyConstants.MAPCHANNEL.put(o.get("userCode")+"", mobileChannel);	 
    	                		 //userCode=o.get("userCode")+"";
    	                	 }else  if(userCode !=null &&!"".equals(userCode)&&NettyConstants.MAPCHANNEL.containsKey(userCode)){
    	                		 NettyConstants.MAPCHANNEL.remove(userCode);
    	                		 NettyConstants.MAPCHANNEL.put(o.get("userCode")+"", mobileChannel);
    	                	 }
    	                	 ///用户登录后，首先查找该用户有没有离线数据，然后主动推送。
    	                	 /**
    	                	  * flag=3 表示数据推送
    	                	  * mdFlag =01  表示考勤
    						  * mdFlag =02  表示家园联系
    						  * mdFlag =03  表示成长手册
    						  * mdFlag =04  表示班级圈
    						  * mdFlag =05  上传照片
    						  * mdFlag =06   表示通知消息
    	                	  * 
    	                	  */
    	                		 offLineService=ContextUtils.getBean("offLineService");
    	                		 list=offLineService.queryOffLineListByUserCode(userCode);
    	                		 
    	                	 //}
    	                	 
    	                	 if(list !=null &&list.size()>0){
    	                		 logger.info("离线数据记录数："+list.size());
    	                		 for(int j=0;j<list.size();j++){
    	                			 String pushJsons ="{\"respData\":[{\"flag\":\"3\",\"mdFlag\":\""+list.get(j).getMdFlag()+"\",\"eachData\":\""+list.get(j).getMessage()+"\"}]}";
    	     		        		 pushJsons=URLEncoder.encode(pushJsons,NettyConstants.ENCODE);//
    	     		        		 ctx.write(pushJsons+"\r\n");
    	     		        		 ctx.flush();
    	     		        		offLineService.delOffLine(list.get(j).getId());//离线数据推送后，则删除。
    	                		 }
    	                	 }
    	                	 
    	                 }else if("-1".equals(flag)){
    	                	 if(userCode !=null &&!"".equals(userCode)&&NettyConstants.MAPCHANNEL.containsKey(userCode)){
    	                		 NettyConstants.MAPCHANNEL.remove(userCode);
    	                	 }
    	                	 ctx.disconnect();//关闭连接 
    	                 }else if("3".equals(flag)){
    	                	  ////0：表示ping;1:表示连接；-1：表示关闭连接；2:表示用户登录；3：表示数据推送
    	                	 eachData=o.get("eachData")+"";
    	                	 System.out.println("eachData:"+eachData);
    	                	 System.out.println("目前不需要实现，其他地方已经实现！！！");
    	                 }
    	             	 
                     } 
                 }
            }	
        }catch(Exception e){
        	ctx.disconnect();
        	System.out.println(e);
        	logger.error("解析报文异常",e);
        }
       

        // We do not need to write a ChannelBuffer here.
        // We know the encoder inserted at TelnetPipelineFactory will do the conversion.
        ChannelFuture future = ctx.write(response);

        // Close the connection after sending 'Have a good day!'
        // if the client has sent 'bye'.
        if (close) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("异常",cause);
        ctx.close();
    }
    
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {

    	try{
    		
    		/*心跳处理*/
            if (evt instanceof IdleStateEvent) {
                IdleStateEvent event = (IdleStateEvent) evt;
                if (event.state() == IdleState.READER_IDLE) {
                    /*读超时*/
                    
                    count++;
                    if(count>2){
                    	System.out.println("READER_IDLE 读超时");
                    	ctx.disconnect();	
                    }else{
                    	System.out.println("开始发心跳");
                    	logger.info("开始发心跳");
                		////0：表示ping;1:表示连接；-1：表示关闭连接；2:表示用户登录；3：表示数据推送
    					String pingJsons ="{\"respData\":[{\"flag\":\"0\",\"eachData\":\"ping\"}]}";
    					pingJsons=URLEncoder.encode(pingJsons, "UTF-8");//
    					ctx.write(pingJsons+"\r\n");
    					ctx.flush();
    					logger.info("开始发心跳结束");
                    }
                    
                } else if (event.state() == IdleState.WRITER_IDLE) {
                    /*写超时*/   
                    ///System.out.println("WRITER_IDLE 写超时");
                } else if (event.state() == IdleState.ALL_IDLE) {
                    /*总超时*/
                    ///System.out.println("ALL_IDLE 总超时");
                }
            }
    		
    	}catch(Exception e){
    		 logger.error("异常",e);
    	}
        
    }
    

//	public OffLineService getOffLineService() {
//		return offLineService;
//	}
//
//
//
//	@Autowired
//	public void setOffLineService(OffLineService offLineService) {
//		this.offLineService = offLineService;
//	}
    
}
