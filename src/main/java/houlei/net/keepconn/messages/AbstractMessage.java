package houlei.net.keepconn.messages;

/**
 * 网络数据包对应类的抽象类
 * <p>
 * 创建时间：2009-10-28 下午02:47:15
 * @author 侯磊
 * @since 1.0
 */
public abstract class AbstractMessage {
	/**
	 * 包头长度。（协议采用定长包头，长度为8)
	 */
	public static final int MessageHeaderLength = 8;
	
}