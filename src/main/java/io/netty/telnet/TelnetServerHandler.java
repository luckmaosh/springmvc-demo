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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;
import io.netty.common.NettyConstants;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.http.websocketx.server.OffLine;
import io.netty.http.websocketx.server.OffLineService;
import io.netty.po.MobileChannel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
/**
 * Handles a server-side channel.
 */
@Sharable
public class TelnetServerHandler extends ChannelInboundMessageHandlerAdapter<String> {

	private static Log logger = LogFactory.getLog(TelnetServerHandler.class);
	
    private int count;
    private String userCode;
    
    /** ���߷��� */
	private OffLineService offLineService;

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//   maso     ctx.fireChannelUnregistered();
	}

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
        logger.info("���ܱ���1��"+request);
        try{
        	 if (request !=null && !"".equals(request)&&request.indexOf("respData")>-1){
            	 //request = new String(request.getBytes(),"utf-8");   
            	 logger.info("���ܱ���2��"+request);
            	 JSONObject myObj = JSONObject.parseObject(request);
                 JSONArray myArray = myObj.getJSONArray("respData");
                 if(myArray !=null && myArray.size()==1){
                	 logger.info("���ܱ���3��"+request);
                	 for(int i=0;i<myArray.size();i++)
                     {
                		 logger.info("���ܱ���4��"+request);
    	                 JSONObject o = myArray.getJSONObject(i);
    	                 ////0����ʾping;1:��ʾ���ӣ�-1����ʾ�ر����ӣ�2:��ʾ�û���¼��3����ʾ�������
    	                 String flag=o.get("flag")+"";
    	                 
    	                 String eachData="";
    	                 count=0;
    	                 if("0".equals(flag)){
    	                	 eachData=o.get("eachData")+"";
    	                	 System.out.println("�������");
    	                	 logger.info("�������");
    	                 }else if("1".equals(flag)){
    	                	 System.out.println("�ͻ���һ��ʼ���ӵ�ʱ�򣬻�û�е�¼����������");
    	                	 logger.info("�ͻ���һ��ʼ���ӵ�ʱ��");
    	                 }else if("2".equals(flag)){
    	                	 String mobileFlag=o.getString("mobileFlag");
    	                	 String userCode=o.get("userCode")+"";
    	                	 logger.info("ע���û��ŵ���"+userCode);
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
    	                	 ///�û���¼�����Ȳ��Ҹ��û���û��������ݣ�Ȼ���������͡�
    	                	 /**
    	                	  * flag=3 ��ʾ�������
    	                	  * mdFlag =01  ��ʾ����
    						  * mdFlag =02  ��ʾ��԰��ϵ
    						  * mdFlag =03  ��ʾ�ɳ��ֲ�
    						  * mdFlag =04  ��ʾ�༶Ȧ
    						  * mdFlag =05  �ϴ���Ƭ
    						  * mdFlag =06   ��ʾ֪ͨ��Ϣ
    	                	  * 
    	                	  */
//    	                		 offLineService=ContextUtils.getBean("offLineService");
    	                		 list=offLineService.queryOffLineListByUserCode(userCode);
    	                		 
    	                	 //}
    	                	 
    	                	 if(list !=null &&list.size()>0){
    	                		 logger.info("������ݼ�¼��"+list.size());
    	                		 for(int j=0;j<list.size();j++){
    	                			 String pushJsons ="{\"respData\":[{\"flag\":\"3\",\"mdFlag\":\""+list.get(j).getMdFlag()+"\",\"eachData\":\""+list.get(j).getMessage()+"\"}]}";
    	     		        		 pushJsons=URLEncoder.encode(pushJsons,NettyConstants.ENCODE);//
    	     		        		 ctx.write(pushJsons+"\r\n");
    	     		        		 ctx.flush();
    	     		        		offLineService.delOffLine(list.get(j).getId());//����������ͺ���ɾ��
    	                		 }
    	                	 }
    	                	 
    	                 }else if("-1".equals(flag)){
    	                	 if(userCode !=null &&!"".equals(userCode)&&NettyConstants.MAPCHANNEL.containsKey(userCode)){
    	                		 NettyConstants.MAPCHANNEL.remove(userCode);
    	                	 }
    	                	 ctx.disconnect();//�ر����� 
    	                 }else if("3".equals(flag)){
    	                	  ////0����ʾping;1:��ʾ���ӣ�-1����ʾ�ر����ӣ�2:��ʾ�û���¼��3����ʾ�������
    	                	 eachData=o.get("eachData")+"";
    	                	 System.out.println("eachData:"+eachData);
    	                	 System.out.println("Ŀǰ����Ҫʵ�֣�����ط��Ѿ�ʵ�֣�����");
    	                 }
    	             	 
                     } 
                 }
            }	
        }catch(Exception e){
        	ctx.disconnect();
        	System.out.println(e);
        	logger.error("���������쳣",e);
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
        logger.error("�쳣",cause);
        ctx.close();
    }
    
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {

    	try{
    		
    		/*������*/
            if (evt instanceof IdleStateEvent) {
                IdleStateEvent event = (IdleStateEvent) evt;
                if (event.state() == IdleState.READER_IDLE) {
                    /*����ʱ*/
                    
                    count++;
                    if(count>2){
                    	System.out.println("READER_IDLE ����ʱ");
                    	ctx.disconnect();	
                    }else{
                    	System.out.println("��ʼ������");
                    	logger.info("��ʼ������");
                		////0����ʾping;1:��ʾ���ӣ�-1����ʾ�ر����ӣ�2:��ʾ�û���¼��3����ʾ�������
    					String pingJsons ="{\"respData\":[{\"flag\":\"0\",\"eachData\":\"ping\"}]}";
    					pingJsons=URLEncoder.encode(pingJsons, "UTF-8");//
    					ctx.write(pingJsons+"\r\n");
    					ctx.flush();
    					logger.info("��ʼ���������");
                    }
                    
                } else if (event.state() == IdleState.WRITER_IDLE) {
                    /*д��ʱ*/   
                    ///System.out.println("WRITER_IDLE д��ʱ");
                } else if (event.state() == IdleState.ALL_IDLE) {
                    /*�ܳ�ʱ*/
                    ///System.out.println("ALL_IDLE �ܳ�ʱ");
                }
            }
    		
    	}catch(Exception e){
    		 logger.error("�쳣",e);
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
