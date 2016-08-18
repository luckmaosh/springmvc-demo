//package com.niux.springmvcdemo.push;
//
//import com.walker.cheetah.core.RemoteException;
//import com.walker.cheetah.server.ClientOfflineException;
//import com.walker.cheetah.server.support.*;
//import com.walker.infrastructure.utils.WaitConsoleInput;
//
//import java.util.Random;
//import java.util.concurrent.TimeUnit;
//
//public class TestServerMain {
//
//    /**
//     * @param args
//     */
//    public static void main(String[] args) {
//        //启动推送服务端的环境
//        PushServerProvider provider = new PushServerProvider(7001, new MyTestAuthentication());
//        provider.start();
//
//        WaitConsoleInput.waitInTimes(TimeUnit.SECONDS, 10);// 等待10秒
//        // 模拟给多个用户推送单个文字消息
//        StringPushedMessage message = new StringPushedMessage("My God! haha...");
//        message.addUser("shikeying");//指定要接收的用户ID
//        message.addUser("zhuzhuyuan");
//        message.addUser("sizhengjun");
//        provider.push(message);// 推送一个文本消息
//        System.out.println("1........服务端推送了一个公共消息：" + message);
//
//		/* 永不停止的向客户端推送实时数据，测试方便 */
//        String user = "shikeying";
//        PushDuration pd = new TestPushDuration(provider.getServer(), user).setMilliseconds(500);
//        while (true) {
//            provider.pushLive(pd);
//            System.out.println("给 '" + user + "' 推送一个实时消息。");
//            WaitConsoleInput.waitInTimes(TimeUnit.SECONDS, 20);
//        }
//
////		WaitConsoleInput.waitInput();
////		System.out.println("----------> 系统长连接服务停止。");
//    }
//
//    private static class TestPushDuration extends PushDuration {
//
//        private static final Random random = new Random(100);
//
//        public TestPushDuration(SimpleLongServer server, String user) {
//            super(server, user);
//        }
//
//        @Override
//        protected void onPush(String user)
//                throws RemoteException, ClientOfflineException {
//            // 模拟从数据库或者设备中实时提取数据，不过注意：
//            // 实时性需要有一定间隔，否则实时发送的数据太快，有些客户端就无法接收到。
//
//			/* 推送数据的个数，随机生成 */
//            int size = Math.abs(random.nextInt()) % 99;
//            for (int i = 0; i < size; i++) {
//                // 间隔一小段时间，可以设置
//                sleep();
//                pushing(Math.abs(random.nextInt()) % 50);
//            }
//        }
//    }
//
//}
