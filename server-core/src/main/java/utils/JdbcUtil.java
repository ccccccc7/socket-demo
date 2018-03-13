package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zyl
 */
public class JdbcUtil {
    private static final String DRIVERCLASS = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://193.112.57.142:3306/rooter?zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2b8&useSSL=true";
    private static final String USERNAME = "dev";
    private static final String PASSWORD = "developer";

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

    public static int insert(List<String> list) {
        return 1;
    }
}
