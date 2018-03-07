import util.JdbcUtil;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            JdbcUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
