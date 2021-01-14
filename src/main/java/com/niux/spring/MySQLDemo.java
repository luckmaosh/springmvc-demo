package com.niux.spring;

import java.sql.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySQLDemo {

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/tt";

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    //static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicInteger integer = new AtomicInteger();

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "nothing";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
//            String sql;
//            sql = "SELECT id, item_id, num FROM items";
//            ResultSet rs = stmt.executeQuery(sql);
//
//            // 展开结果集数据库
//            while (rs.next()) {
//                // 通过字段检索
//                int id = rs.getInt("id");
//                String itemId = rs.getString("item_id");
//                String num = rs.getString("num");
//
//                // 输出数据
//                System.out.print("ID: " + id);
//                System.out.print(", 商品ID: " + itemId);
//                System.out.print(", 商品数量: " + num);
//                System.out.print("\n");
//            }


            int c = 1000;
            CountDownLatch countDownLatch = new CountDownLatch(c);

            for (int i = 0; i < c; i++) {
                MyThread thread = new MyThread(countDownLatch, conn, lock, integer);
                thread.start();
            }


            // 完成后关闭
            countDownLatch.await();

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}

class MyThread extends Thread {


    private CountDownLatch countDownLatch;
    private Lock lock;
    private AtomicInteger integer;

    public MyThread(CountDownLatch countDownLatch, Connection stmt, Lock lock, AtomicInteger integer) {
        this.countDownLatch = countDownLatch;
        this.connection = stmt;
        this.lock = lock;
        this.integer = integer;
    }

    private Connection connection;

    @Override
    public void run() {
        //悲观锁
//        lock.lock();

        String select = "SELECT id, item_id, num FROM items where item_id = 1 for update ";
        ResultSet resultSet = null;
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(select);
            if (resultSet.next()) {

                int num = resultSet.getInt("num");
                num = num - 1;
                String update = "update items set num = " + num + "  where item_id = 1";
                int i = stmt.executeUpdate(update);
                System.out.println("update success " + integer.incrementAndGet());

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                countDownLatch.countDown();
                if (resultSet != null) {
                    resultSet.close();
                }

                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

//            lock.unlock();
        }

    }

    ;


}