package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private String url;
    private String user;
    private String password;

    MyConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
