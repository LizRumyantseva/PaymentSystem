package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionJDBC {
    static Connection connection = null;
    static final String userName = "root";
    static final String password = "1234";
    static final String connectionURL = "jdbc:mysql://localhost:3306/payment_system";
    static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(JDBC_Driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Connection connection = DriverManager.getConnection(connectionURL, userName, password);
                return connection;
            } catch (SQLException | NullPointerException e) {
                System.err.println("Connection failed.");
            }
        }
        return connection;
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
