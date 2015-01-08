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
package io.netty.http.websocketx.server;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;
import io.netty.common.NettyConstants;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.po.MobileChannel;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang.StringUtils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaders.Names.HOST;
import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaders.setContentLength;
import static io.netty.handler.codec.http.HttpMethod.GET;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Handles handshakes and messages
 */
public class WebSocketServerHandler extends ChannelInboundMessageHandlerAdapter<Object> {
    private static final Logger logger = Logger.getLogger(WebSocketServerHandler.class.getName());

    private static final String WEBSOCKET_PATH = "/websocket";
    private int count;
    private WebSocketServerHandshaker handshaker;
    /** ���߷��� */
	private OffLineService offLineService;
	private String userCode;
	
    @Override
    public void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
    	
    	count=0;
    	
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        // Handle a bad request.
        if (!req.getDecoderResult().isSuccess()) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, BAD_REQUEST));
            return;
        }

        // Allow only GET methods.
        if (req.getMethod() != GET) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, FORBIDDEN));
            return;
        }

        // Send the demo page and favicon.ico
        if ("/".equals(req.getUri())) {
            ByteBuf content = WebSocketServerIndexPage.getContent(getWebSocketLocation(req));
            FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, OK, content);

            res.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");
            setContentLength(res, content.readableBytes());

            sendHttpResponse(ctx, req, res);
            return;
        }
        if ("/favicon.ico".equals(req.getUri())) {
            FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, NOT_FOUND);
            sendHttpResponse(ctx, req, res);
            return;
        }

        // Handshake
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                getWebSocketLocation(req), null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {

    	try{
    		 if (frame instanceof CloseWebSocketFrame) {
    	            frame.retain();
    	            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame);
    	            return;
    	        }
    	        if (frame instanceof PingWebSocketFrame) {
    	            frame.content().retain();
    	            ctx.channel().write(new PongWebSocketFrame(frame.content()));
    	            return;
    	        }
    	        if (!(frame instanceof TextWebSocketFrame)) {
    	            throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass()
    	                    .getName()));
    	        }

    	        // Send the uppercase string back.
    	        String request = ((TextWebSocketFrame) frame).text();
    	        
    	        operateMessage(ctx,request);
    	        
    	        if (logger.isLoggable(Level.FINE)) {
    	            logger.fine(String.format("Channel %s received %s", ctx.channel().id(), request));
    	        }
    	        ctx.channel().write(new TextWebSocketFrame(request.toUpperCase()));
    	}catch(Exception e){
    		
    	}
        // Check for closing frame
       
    }

    private static void sendHttpResponse(
            ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
        // Generate an error page if response getStatus code is not OK (200).
        if (res.getStatus().code() != 200) {
            res.content().writeBytes(Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8));
            setContentLength(res, res.content().readableBytes());
        }

        // Send the response and close the connection if necessary.
        ChannelFuture f = ctx.channel().write(res);
        if (!isKeepAlive(req) || res.getStatus().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private static String getWebSocketLocation(FullHttpRequest req) {
        return "ws://" + req.headers().get(HOST) + WEBSOCKET_PATH;
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
    		/// logger.error("�쳣",e);
    	}
    }
    
    public void operateMessage(ChannelHandlerContext ctx, String request) throws Exception {
    	
        List<OffLine> list = new ArrayList<OffLine>();
        ////{"respData":[{"flag":"2","userCode":"9482"}]}
        try{
        	if (request !=null && !"".equals(request)&&request.indexOf("respData")>-1){
           	request = StringUtils.remove(request, ' ');
           	request = StringUtils.remove(request, '\n');
           	request = StringUtils.remove(request, '\r');
       	 
       	 	JSONObject myObj = JSONObject.parseObject(request);
       	 	JSONObject jsonObj =null;
       	 jsonObj = myObj.getJSONObject("respData");	
       	 	
       	 	String flag=jsonObj.getString("flag");
             //0����ʾping;1:��ʾ���ӣ�-1����ʾ�ر����ӣ�2:��ʾ�û���¼��3����ʾ�������
            if("2".equals(flag)){
	        	String userCode = jsonObj.getString("userCode");
	        	 String mobileFlag=jsonObj.getString("mobileFlag");
	        	logger.info("ע���û��ŵ���"+userCode);
	        	 MobileChannel mobileChannel=new MobileChannel();
            	 mobileChannel.setCtx(ctx);
            	 mobileChannel.setMmobilieFlag(mobileFlag);
	        	
	        	 if(userCode !=null &&!"".equals(userCode)&&!NettyConstants.MAPCHANNEL.containsKey(userCode)){
	        		 NettyConstants.MAPCHANNEL.put(userCode, mobileChannel);	 
	        	 }else  if(userCode !=null &&!"".equals(userCode)&&NettyConstants.MAPCHANNEL.containsKey(userCode)){
	        		 NettyConstants.MAPCHANNEL.remove(userCode);
	        		 NettyConstants.MAPCHANNEL.put(userCode, mobileChannel);
	        	 }
//        		 offLineService=ContextUtils.getBean("offLineService");
        		 list=offLineService.queryOffLineListByUserCode(userCode);
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
             }
                
           }
        }catch(Exception e){
        	
        }
    }
    
    
    
}
