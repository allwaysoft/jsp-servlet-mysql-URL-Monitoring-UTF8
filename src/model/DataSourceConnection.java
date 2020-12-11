//中文UTF-8 NO BOM
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceConnection {
    private static final String DATABASE = "jdbc:mysql://localhost:3306/url_monitoring?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";

    public DataSourceConnection() throws ClassNotFoundException {
        String driverName = "com.mysql.cj.jdbc.Driver";
        Class.forName(driverName);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE, USER, PASS);
    }
}
