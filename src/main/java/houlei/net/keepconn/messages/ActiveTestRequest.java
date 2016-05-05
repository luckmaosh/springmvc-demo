package houlei.net.keepconn.messages;

import houlei.net.keepconn.utils.ByteArrayBuilder;

/**
 * 心跳包（请求包）
 * <p>
 * 创建时间：2009-10-28 下午02:48:36
 * @author 侯磊
 * @since 1.0
 */
public class ActiveTestRequest extends AbstractMessage implements Message {

	/* （非 Javadoc）
	 * @see houlei.net.keepconn.messages.Message#getBytes()
	 */
	public byte[] getBytes() {
		return new ByteArrayBuilder().write(MessageHeaderLength).write(ActiveTestRequest).toBytes();
	}

	/* （非 Javadoc）
	 * @see houlei.net.keepconn.messages.Message#getMessageLength()
	 */
	public int getMessageLength() {
		return MessageHeaderLength;
	}

	/* （非 Javadoc）
	 * @see houlei.net.keepconn.messages.Message#getMessageType()
	 */
	public int getMessageType() {
		return ActiveTestRequest;
	}

	/* （非 Javadoc）
	 * @see houlei.net.keepconn.messages.Message#parse(byte[])
	 */
	public void parse(byte[] b) throws ParseException {
		//空包体，所以空函数体。
	}

}