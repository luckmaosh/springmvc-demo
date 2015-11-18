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

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.common.NettyConstants;
/**
 * Simplistic telnet server.
 */
public class TelnetServer {

    private int port;
    private EventLoopGroup bossGroup = new NioEventLoopGroup(NettyConstants.BIZGROUPSIZE);
    private EventLoopGroup workerGroup = new NioEventLoopGroup(NettyConstants.BIZTHREADSIZE);
    private ServerBootstrap serverBootstrap =null;
    private ChannelFuture channelFuture;
    private static TelnetServer instance = null;

    private TelnetServer(int port) {
        this.port = port;
    }
    
    public static synchronized TelnetServer createInstance(int port) {
        if(instance == null) {
           instance = new TelnetServer(port);
        }
        return instance;
     }
    
    
	public void stopService(){
		try{
			if(serverBootstrap !=null && channelFuture!=null){
	    		channelFuture.channel().closeFuture().sync();
	    	}
	    	if(bossGroup !=null){
	    		 bossGroup.shutdownGracefully();
	    	}
	    	if(workerGroup !=null){
	    		 workerGroup.shutdownGracefully();
	    	}
		}catch(Exception e){
			
		}
    }

    public void startService(){
	   try {
       	serverBootstrap = new ServerBootstrap();
       	serverBootstrap.group(bossGroup,workerGroup)
            .channel(NioServerSocketChannel.class)
            .childHandler(new TelnetServerPipelineFactory());
           //b.bind(port).sync().channel().closeFuture().sync();
           //ChannelFuture f = b.bind(8080).sync();
       	channelFuture=serverBootstrap.bind(port).sync();
           //f.channel().closeFuture().sync();
       }catch(Exception e){
       	System.out.println(e);
       } 
       System.out.println("end");
    }
    

//    public static void main(String[] args) throws Exception {
//        int port;
//        if (args.length > 0) {
//            port = Integer.parseInt(args[0]);
//        } else {
//            port = 9999;
//        }
//        new TelnetServer(port).startService();
//    }
}
