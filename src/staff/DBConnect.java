package staff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/staff_dbs";  // Replace with your database details
            String user = "root"; // Replace with your DB username
            String password = "root"; // Replace with your DB password
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
