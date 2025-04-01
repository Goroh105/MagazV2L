package dao;

import domain.Laptop;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import exception.DAOException;

public class LaptopDbDAO implements RepositoryDAO<Laptop> {

    // SQL-запросы к таблице Laptop базы данных
    private static final String SELECT_ALL_LAPTOPS = "SELECT id, model, speed, ram, hd, screen, price, count FROM Laptop";
    private static final String SELECT_LAPTOP_BY_ID = "SELECT id, model, speed, ram, hd, screen, price, count FROM Laptop WHERE id = ?";
    private static final String INSERT_LAPTOP = "INSERT INTO Laptop (model, speed, ram, hd, screen, price, count) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_LAPTOP = "UPDATE Laptop SET model = ?, speed = ?, ram = ?, hd = ?, screen = ?, price = ?, count = ? WHERE id = ?";
    private static final String DELETE_LAPTOP = "DELETE FROM Laptop WHERE id = ?";


    private final ConnectionBuilder builder = new DbConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Laptop laptop) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_LAPTOP, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, laptop.getModel());
            pst.setInt(2, laptop.getSpeed());
            pst.setInt(3, laptop.getRam());
            pst.setInt(4, laptop.getHd());
            pst.setDouble(5, laptop.getScreen());
            pst.setDouble(6, laptop.getPrice());
            pst.setInt(7, laptop.getCount());

            pst.executeUpdate();

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating Laptop failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Ошибка при добавлении Laptop: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Laptop laptop) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_LAPTOP)) {

            pst.setString(1, laptop.getModel());
            pst.setInt(2, laptop.getSpeed());
            pst.setInt(3, laptop.getRam());
            pst.setInt(4, laptop.getHd());
            pst.setDouble(5, laptop.getScreen());
            pst.setDouble(6, laptop.getPrice());
            pst.setInt(7, laptop.getCount());
            pst.setLong(8, laptop.getId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Ошибка при обновлении Laptop: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_LAPTOP)) {

            pst.setLong(1, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Ошибка при удалении Laptop: " + e.getMessage(), e);
        }
    }

    @Override
    public Laptop findById(Long id) throws DAOException {
        Laptop laptop = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_LAPTOP_BY_ID)) {

            pst.setLong(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    laptop = new Laptop();
                    laptop.setId(rs.getLong("id"));
                    laptop.setModel(rs.getString("model"));
                    laptop.setSpeed(rs.getInt("speed"));
                    laptop.setRam(rs.getInt("ram"));
                    laptop.setHd(rs.getInt("hd"));
                    laptop.setScreen(rs.getDouble("screen"));
                    laptop.setPrice(rs.getDouble("price"));
                    laptop.setCount(rs.getInt("count"));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Ошибка при поиске Laptop по ID: " + e.getMessage(), e);
        }
        return laptop;
    }

    @Override
    public List<Laptop> findAll() throws DAOException {
        List<Laptop> laptops = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_LAPTOPS);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Laptop laptop = new Laptop();
                laptop.setId(rs.getLong("id"));
                laptop.setModel(rs.getString("model"));
                laptop.setSpeed(rs.getInt("speed"));
                laptop.setRam(rs.getInt("ram"));
                laptop.setHd(rs.getInt("hd"));
                laptop.setScreen(rs.getDouble("screen"));
                laptop.setPrice(rs.getDouble("price"));
                laptop.setCount(rs.getInt("count"));
                laptops.add(laptop);
            }

        } catch (SQLException e) {
            throw new DAOException("Ошибка при получении списка Laptop: " + e.getMessage(), e);
        }
        return laptops;
    }
}