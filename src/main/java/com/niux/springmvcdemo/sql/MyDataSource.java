package com.niux.springmvcdemo.sql;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by shaohui.msh on 2017/1/12.
 */
public class MyDataSource implements DataSource {

    private static List<Connection> pool = new LinkedList<Connection>();
    private static final int POOL_SIZE = 50;


    static {
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection("", "", "");
                pool.add(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (pool.size() > 0) {

            final Connection connection = pool.remove(0);

            Object proxyInstance = Proxy.newProxyInstance(connection.getClass().getClassLoader(), connection.getClass().getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if ("close".equals(method.getName())) {
                                return pool.add(connection);
                            } else {
                                return method.invoke(proxy, args);
                            }
                        }
                    });


            return (Connection) proxyInstance;
        } else {
            throw new RuntimeException("数据连接池用尽");
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
