package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private final String url;
    private final Properties properties;

    public DatabaseConnectionManager(String hostname, String databaseName, String user, String password) {
        this.url = "jdbc:postgresql://"+hostname+"/"+databaseName;
        this.properties = new Properties();
        this.properties.setProperty("user",user);
        this.properties.setProperty("password",password);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url,this.properties);
    }
}
