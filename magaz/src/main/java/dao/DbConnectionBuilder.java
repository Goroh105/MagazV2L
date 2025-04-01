package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс Построителя соединения с базой данных
 */
public class DbConnectionBuilder implements ConnectionBuilder {

    public DbConnectionBuilder() {
        try {
            // Настройка драйвера базы данных
            Class.forName(ConnectionProperty.getProperty("db.driver.class"));
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    // Получение соединения с базой данных
    @Override
    public Connection getConnection() throws SQLException {
        String url = ConnectionProperty.getProperty("db.url");
        String login = ConnectionProperty.getProperty("db.login");
        String password = ConnectionProperty.getProperty("db.password");
        return DriverManager.getConnection(url, login, password);
    }
}