package io.netty.common;

import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.po.MobileChannel;
import io.netty.telnet.TelnetServerHandler;

import java.util.HashMap;
import java.util.Map;

public class NettyConstants {
	
    public static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2;    //Ĭ��
    /**
     * ҵ������̴߳�С
     */
    public static final int BIZTHREADSIZE = 4;
    
    public static final int PORT=9091;
    public static final int IOS_PORT=9092;
    
    public static final String ENCODE="UTF-8";
    
    public static final StringDecoder DECODER = new StringDecoder();
    public static final StringEncoder ENCODER = new StringEncoder();
    public static final TelnetServerHandler SERVERHANDLER = new TelnetServerHandler();
    
    ///public static final List<OffLineService> SERVICELIST= new ArrayList<OffLineService>();
    
    public static final Map<String,MobileChannel> MAPCHANNEL= new HashMap<String,MobileChannel>();
    

}
