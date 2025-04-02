package dao;

import domain.Printer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import exception.DAOException;

public class PrinterDbDAO implements RepositoryDAO<Printer> {

    // SQL-запросы к таблице Printer базы данных
    private static final String SELECT_ALL_PRINTER = "SELECT id, model, color, type, price, count FROM Printer";
    private static final String SELECT_PRINTER_BY_ID = "SELECT id, model, color, type, price, count FROM Printer WHERE id = ?";
    private static final String INSERT_PRINTER = "INSERT INTO Printer (model, color, type, price, count) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_PRINTER = "UPDATE Printer SET model = ?, color = ?, type = ?, price = ?, count = ? WHERE id = ?";
    private static final String DELETE_PRINTER = "DELETE FROM Printer WHERE id = ?";


    private final ConnectionBuilder builder = new DbConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Printer printer) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_PRINTER, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, printer.getModel());
            pst.setBoolean(2, printer.getColor());
            pst.setString(3, printer.getType());
            pst.setDouble(4, printer.getPrice());
            pst.setInt(5, printer.getCount());

            pst.executeUpdate();

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating Printer failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Ошибка при добавлении Printer: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Printer printer) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_PRINTER)) {

            pst.setString(1, printer.getModel());
            pst.setBoolean(2, printer.getColor());
            pst.setString(3, printer.getType());
            pst.setDouble(4, printer.getPrice());
            pst.setInt(5, printer.getCount());
            pst.setLong(6, printer.getId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Ошибка при обновлении Printer: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_PRINTER)) {

            pst.setLong(1, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Ошибка при удалении Printer: " + e.getMessage(), e);
        }
    }

    @Override
    public Printer findById(Long id) throws DAOException {
        Printer printer = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_PRINTER_BY_ID)) {

            pst.setLong(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    printer = new Printer();
                    printer.setId(rs.getLong("id"));
                    printer.setModel(rs.getString("model"));
                    printer.setColor(rs.getBoolean("color"));
                    printer.setType(rs.getString("type"));
                    printer.setPrice(rs.getDouble("price"));
                    printer.setCount(rs.getInt("count"));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Ошибка при поиске Printer по ID: " + e.getMessage(), e);
        }
        return printer;
    }

    @Override
    public List<Printer> findAll() throws DAOException {
        List<Printer> printers = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_PRINTER);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Printer printer = new Printer();
                printer.setId(rs.getLong("id"));
                printer.setModel(rs.getString("model"));
                printer.setColor(rs.getBoolean("color"));
                printer.setType(rs.getString("type"));
                printer.setPrice(rs.getDouble("price"));
                printer.setCount(rs.getInt("count"));
                printers.add(printer);
            }

        } catch (SQLException e) {
            throw new DAOException("Ошибка при получении списка Printer: " + e.getMessage(), e);
        }
        return printers;
    }
    
    public void updatePrinter(Printer printer) throws SQLException {
        String sql = "UPDATE printer SET model = ?, color = ?, type = ?, price = ?, count = ? WHERE id = ?";

        try (Connection connection = connectionBuilder.getConnection();  // Используем ConnectionBuilder
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, printer.getModel());
            statement.setBoolean(2, printer.getColor());
            statement.setString(3, printer.getType());
            statement.setDouble(4, printer.getPrice());
            statement.setInt(5, printer.getCount());
            statement.setLong(6, printer.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("Ошибка при обновлении данных принтера", e);
        }
    }
    
    private final DbConnectionBuilder connectionBuilder;

    public PrinterDbDAO(DbConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    public Printer getPrinterById(Long printerId) throws SQLException {
        String sql = "SELECT id, model, color, type, price, count FROM printer WHERE id = ?";
        try (Connection connection = connectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, printerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Printer(
                            resultSet.getLong("id"),
                            resultSet.getString("model"),
                            resultSet.getBoolean("color"),
                            resultSet.getString("type"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("count")
                    );
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Ошибка при получении принтера по ID: " + e.getMessage(), e);
        }
    }
    
    public void deletePrinter(Long id) throws SQLException {
        String sql = "DELETE FROM printer WHERE id = ?"; 

        try (Connection connection = connectionBuilder.getConnection(); // Используйте ConnectionBuilder
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id); // Используйте setLong для Long id

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Принтер с указанным ID не найден.");
            }

        } catch (SQLException e) {
            throw new SQLException("Ошибка при удалении принтера", e);
        }
    }
}