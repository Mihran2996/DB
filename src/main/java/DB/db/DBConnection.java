package DB.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String DB_URL = "jdbc:mysql://localhost:3306/usr";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static DBConnection instance = new DBConnection();
    Connection connection;

    private DBConnection() {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public  Connection getConnection() throws SQLException {
        if (connection==null || connection.isClosed()){
            try {
                connection= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            return connection;
    }
    public static DBConnection getInstance() {
        return instance;
    }


}
