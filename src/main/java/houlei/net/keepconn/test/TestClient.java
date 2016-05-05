package houlei.net.keepconn.test;

import houlei.net.keepconn.client.ConnectionManager;

import java.io.IOException;

/**
 * 测试心跳包的简单客户端程序
 * <p>
 * 创建时间：2009-10-28 下午05:28:10
 * 
 * @author 侯磊
 * @since 1.0
 */
public class TestClient {

	public static void main(String[] args) throws IOException {
		ConnectionManager.createConnection("localhost", 65432);
	}

}