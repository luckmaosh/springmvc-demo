package io.netty.listener;

import io.netty.common.NettyConstants;
import io.netty.http.websocketx.server.WebSocketServer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


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
		
		logger.info("���ios����netty����ʼ");
		if(webSocketServer==null){
			int port = NettyConstants.IOS_PORT;
			try {
				webSocketServer =new WebSocketServer(port);
				
				webSocketServer.run();
				logger.info("���ios����netty����ɹ�");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("���ios����netty����ʧ��");
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
