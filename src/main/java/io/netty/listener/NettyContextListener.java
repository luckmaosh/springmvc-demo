package io.netty.listener;

import io.netty.common.NettyConstants;
import io.netty.telnet.TelnetServer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.djt.v2.controller.netty.OffLineJsonController;


public class NettyContextListener implements ServletContextListener{
	
	private static Log logger = LogFactory.getLog(NettyContextListener.class);
	private TelnetServer telnetServer;
	
	public void contextDestroyed(ServletContextEvent e) {
		if(telnetServer!=null){
			telnetServer.stopService();
			telnetServer=null;
		}
	}
	private void initTelnetServer(ServletContext servletContext) {
		
		logger.info("启动netty服务开始");
		if(telnetServer==null){
			int port = NettyConstants.PORT;
			try {
				telnetServer =TelnetServer.createInstance(port);
				//new NettyThread().start();
				//NettyThread nthread = new NettyThread(telnetServer);
				//nthread.start();
				telnetServer.startService();
				logger.info("启动netty服务成功");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("启动netty服务失败");
				if(telnetServer !=null){
					telnetServer.stopService();
					telnetServer=null;
				}
				e.printStackTrace();
			}
		}
	}


	@Override
	public void contextInitialized(ServletContextEvent e) {
		// TODO Auto-generated method stub
		
		ServletContext servletContext = e.getServletContext();
		
		initTelnetServer(servletContext);
		
	}

}
