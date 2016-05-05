package com.niux.springmvcdemo.push;


import com.walker.cheetah.client.support.PushConnectorService;
import com.walker.cheetah.client.support.Pushable;

public class TestClientMain {

	/**
	 * @param args
	 */
	public static void main(String[] args){
		PushConnectorService service = new PushConnectorService("127.0.0.1", 7001);
		service.setPrincipal("shikeying", "123456");
		boolean isLogin = service.authenticate();
		if(isLogin){
			try{
				Pushable push = service.receivePushed();
				while(true){
					if(push == null) break;
					if(push.isTypeNormal()){
						if(!push.isLiveStart() && !push.isLiveEnd())
							System.out.println("1---> 这是一个普通文本推送: " + push.getNormalText());
						else if(push.isLiveStart()){
							System.out.println("2---> 这是Live推送的开始: " + push.getNormalText());
						} else if(push.isLiveEnd()){
							System.out.println("2---> 这是Live推送的结束: " + push.getNormalText());
						}
					} else if(push.isTypeLive()){
						System.out.println("2...> live message: " + push.getLiveValue());
					}
					push = service.receivePushed();
				}
			} catch(Exception e){
				System.out.println("网络中断，结束接收数据");
			}
		} else
			System.out.println("认证未通过");
	}
}
