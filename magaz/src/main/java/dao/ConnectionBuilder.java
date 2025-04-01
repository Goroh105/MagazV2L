package dao;

import java.sql.Connection;
import java.sql.SQLException;

// Интерфейс получения соединения с базой данных
public interface ConnectionBuilder {
    Connection getConnection() throws SQLException;
}
