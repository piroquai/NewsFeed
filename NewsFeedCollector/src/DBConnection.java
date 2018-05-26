import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String username = "root";
    private static final String password = "kiwi";
    private static final String CONN = "jdbc:mysql://localhost:3306/newsfeed?useSSL=false";
    public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection(CONN, username, password);
    }
}
