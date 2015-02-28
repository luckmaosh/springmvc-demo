package org.format.demo.transaction;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


/**
 * Created by maso on 15/2/28.
 */

public class UserServiceImpl implements UserService {

    private DataSource ds;

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public long insert(String name) {
        JdbcTemplate jt = new JdbcTemplate(ds);

        jt.execute("INSERT INTO user (name) values ('" + name + "')");

        jt.execute("INSERT INTO user (name) values ('" + name + "')");

        return 0;
    }


}
