package com.niux.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

/**
 * 报文格式：request=a+b, response=c
 * 
 * @author 陈坚 2013年6月18日下午2:47:04
 */

public class PlayNettyAddServer {
    //server关闭时会用到这个变量
    static final ChannelGroup allChannels = new DefaultChannelGroup();

    //真正处理报文的
    private static final class AddServerHandler extends SimpleChannelHandler {

        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            //拿到request报文
            ChannelBuffer inputBuf = (ChannelBuffer) e.getMessage();
            String requestStr = new String(inputBuf.array());

            //解析request报文并计算
            String[] params = requestStr.split("\\+");
            if (params.length != 2) {
                return;
            }
            int a = Integer.parseInt(params[0].trim());
            int b = Integer.parseInt(params[1].trim());
            String result = String.valueOf(a + b);

            //回送response报文
            Channel channel = e.getChannel();
            ChannelBuffer outputBuf = ChannelBuffers.buffer(result.length());
            outputBuf.writeBytes(result.getBytes());
            channel.write(outputBuf);
        }

        @Override
        //异常处理
        public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
            e.getCause().printStackTrace();
            e.getChannel().close();
        }

    }

    //启动入口
    public static void main(String[] args) throws InterruptedException {
        ExecutorService bossThreadPool = Executors.newCachedThreadPool(); //侦听线程池
        ExecutorService workerThreadPool = Executors.newCachedThreadPool(); //工作线程池        
        ChannelFactory channelFactory = new NioServerSocketChannelFactory(bossThreadPool,
                workerThreadPool); //channelFactory用来产生和管理channel

        ServerBootstrap bootstrap = new ServerBootstrap(channelFactory); //a helper class that sets up a server
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception { //pipeline可以理解为handler的集合，一个接一个地处理
                return Channels.pipeline(new AddServerHandler());
            }
        });

        //设定一些通信参数
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);

        //在某端口启动
        Channel channel = bootstrap.bind(new InetSocketAddress(18318));
        System.out.println("服务器已启动，并将在20秒后关闭");
        allChannels.add(channel);
        Thread.sleep(20 * 1000);

        //开始释放资源
        System.out.println("服务器开始关闭...");
        System.out.println("关闭所有channel");
        ChannelGroupFuture closeFuture = allChannels.close();
        closeFuture.awaitUninterruptibly();
        System.out.println("释放外部资源");
        channelFactory.releaseExternalResources();
    }
}
