package houlei.net.keepconn.messages;

/**
 *
 * <p>
 * 创建时间：2009-10-28 下午04:37:53
 * @author 侯磊
 * @since 1.0
 */
public class MessageFactory {
	public static Message getInstance(int messageType){
		Message m = null;
		switch(messageType){
		case Message.ActiveTestRequest : m= new ActiveTestRequest();break;
		case Message.ActiveTestResponse : m= new ActiveTestResponse();break;
		default:throw new RuntimeException("所需求的数据包类未能提供");
		}
		return m;
	}
}