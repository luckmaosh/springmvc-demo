package io.netty.listener;

import io.netty.common.NettyConstants;
import io.netty.http.websocketx.server.WebSocketServer;
import io.netty.telnet.TelnetServer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.djt.v2.controller.netty.OffLineJsonController;


public class IosNettyContextListener implements ServletContextListener{
	
	private static Log logger = LogFactory.getLog(IosNettyContextListener.class);
	
	private WebSocketServer webSocketServer;
	/// new WebSocketServer(port).run();
	public void contextDestroyed(ServletContextEvent e) {
		
		if(webSocketServer!=null){
			try {
				webSocketServer.stopService();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			webSocketServer=null;
		}
	}
	private void initIosTelnetServer(ServletContext servletContext) {
		
		logger.info("针对ios启动netty服务开始");
		if(webSocketServer==null){
			int port = NettyConstants.IOS_PORT;
			try {
				webSocketServer =new WebSocketServer(port);
				
				webSocketServer.run();
				logger.info("针对ios启动netty服务成功");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("针对ios启动netty服务失败");
				if(webSocketServer !=null){
					webSocketServer=null;
				}
				e.printStackTrace();
			}
		}
		
	
		
	}


	@Override
	public void contextInitialized(ServletContextEvent e) {
		// TODO Auto-generated method stub
		
		ServletContext servletContext = e.getServletContext();
		
		initIosTelnetServer(servletContext);
		
	}

}
