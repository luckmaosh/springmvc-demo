package com.niux.spring.socket;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by maoshaohui [maoshh88@gmail.com]
 * on 15/8/24.下午9:00
 */
public class SocketServer {

    Properties p = null;

    public SocketServer() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("servers.properties");
        p = new Properties();
        try {
            p.load(inputStream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }


        System.out.println("ip:" + p.getProperty("ip") + ",port:" + p.getProperty("port"));
    }

    public static void main(String[] args) {
        new SocketServer();



    }
}
