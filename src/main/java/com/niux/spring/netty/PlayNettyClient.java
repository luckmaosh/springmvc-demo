package com.niux.spring.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class PlayNettyClient {

    private static final class AddClientHandler extends SimpleChannelHandler {

        @Override
        public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
                throws Exception {
            System.out.println("channel连通");
        }

        @Override
        public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            System.out.println("channel关闭");
        }

        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            ChannelBuffer buf = (ChannelBuffer) e.getMessage();
            String result = new String(buf.array());
            System.out.println("The add result is: " + result);
        }

        @Override
        //异常处理
        public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
            e.getCause().printStackTrace();
            e.getChannel().close();
        }

    }

    //client入口
    public static void main(String[] args) throws InterruptedException {

        ExecutorService bossThreadPool = Executors.newCachedThreadPool(); //主线程池
        ExecutorService workerThreadPool = Executors.newCachedThreadPool(); //从线程池        
        ChannelFactory channelFactory = new NioClientSocketChannelFactory(bossThreadPool,
                workerThreadPool);

        ClientBootstrap bootstrap = new ClientBootstrap(channelFactory);
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new AddClientHandler());
            }
        });

        bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);

        //连上服务器
        ChannelFuture connectFuture = bootstrap.connect(new InetSocketAddress(18318));

        //发出一个请求
        Channel channel = connectFuture.awaitUninterruptibly().getChannel();
        ChannelBuffer requestBuf = ChannelBuffers.dynamicBuffer();
        requestBuf.writeBytes(" 82 + 28 ".getBytes());
        ChannelFuture writeFuture = channel.write(requestBuf);
        writeFuture.awaitUninterruptibly();

        //等待断开
        ChannelFuture closeFuture = connectFuture.getChannel().getCloseFuture();
        closeFuture.awaitUninterruptibly();
        System.out.println("连接被断开");

        //释放资源
        channelFactory.releaseExternalResources();

    }

}

