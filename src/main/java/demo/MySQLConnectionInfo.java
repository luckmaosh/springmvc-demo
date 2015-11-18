package demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MySQLConnectionInfo {

    @Value("#{configProperties['mysql.url']}")
    private String url;
    @Value("#{configProperties['mysql.userName']}")
    private String userName;
    @Value("#{configProperties['mysql.password']}")
    private String password;

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}