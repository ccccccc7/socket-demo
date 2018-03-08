package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author zyl
 */
public class JdbcUtil {
    private static final String DRIVERCLASS;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        DRIVERCLASS = ResourceBundle.getBundle("jdbc").getString("driverClass");
        URL = ResourceBundle.getBundle("jdbc").getString("url");
        USERNAME = ResourceBundle.getBundle("jdbc").getString("username");
        PASSWORD = ResourceBundle.getBundle("jdbc").getString("password");
    }

    static {
        try {
            Class.forName(DRIVERCLASS);
        } catch (ClassNotFoundException e) {
            System.out.println("can't find driver");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("database connect success");
        return connection;
    }
}
