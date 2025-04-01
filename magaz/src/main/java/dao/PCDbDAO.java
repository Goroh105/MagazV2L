package dao;

import domain.PC;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import exception.DAOException;

public class PCDbDAO implements RepositoryDAO<PC> {

    // SQL-запросы к таблице PC базы данных
    private static final String SELECT_ALL_PCS = "SELECT id, model, speed, ram, hd, cd, price, count FROM PC";
    private static final String SELECT_PC_BY_ID = "SELECT id, model, speed, ram, hd, cd, price, count FROM PC WHERE id = ?";
    private static final String INSERT_PC = "INSERT INTO PC (model, speed, ram, hd, cd, price, count) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_PC = "UPDATE PC SET model = ?, speed = ?, ram = ?, hd = ?, cd = ?, price = ?, count = ? WHERE id = ?";
    private static final String DELETE_PC = "DELETE FROM PC WHERE id = ?";


    private final ConnectionBuilder builder = new DbConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(PC pc) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_PC, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, pc.getModel());
            pst.setFloat(2, pc.getSpeed());
            pst.setInt(3, pc.getRam());
            pst.setInt(4, pc.getHd());
            pst.setString(5, pc.getCd());
            pst.setDouble(6, pc.getPrice());
            pst.setInt(7, pc.getCount());

            pst.executeUpdate();

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating PC failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Ошибка при добавлении PC: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(PC pc) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_PC)) {

            pst.setString(1, pc.getModel());
            pst.setFloat(2, pc.getSpeed());
            pst.setInt(3, pc.getRam());
            pst.setInt(4, pc.getHd());
            pst.setString(5, pc.getCd());
            pst.setDouble(6, pc.getPrice());
            pst.setInt(7, pc.getCount());
            pst.setLong(8, pc.getId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Ошибка при обновлении PC: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_PC)) {

            pst.setLong(1, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Ошибка при удалении PC: " + e.getMessage(), e);
        }
    }

    @Override
    public PC findById(Long id) throws DAOException {
        PC pc = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_PC_BY_ID)) {

            pst.setLong(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    pc = new PC();
                    pc.setId(rs.getLong("id"));
                    pc.setModel(rs.getString("model"));
                    pc.setSpeed(rs.getFloat("speed"));
                    pc.setRam(rs.getInt("ram"));
                    pc.setHd(rs.getInt("hd"));
                    pc.setCd(rs.getString("cd"));
                    pc.setPrice(rs.getDouble("price"));
                    pc.setCount(rs.getInt("count"));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Ошибка при поиске PC по ID: " + e.getMessage(), e);
        }
        return pc;
    }

    @Override
    public List<PC> findAll() throws DAOException {
        List<PC> pcs = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_PCS);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                PC pc = new PC();
                pc.setId(rs.getLong("id"));
                pc.setModel(rs.getString("model"));
                pc.setSpeed(rs.getFloat("speed"));
                pc.setRam(rs.getInt("ram"));
                pc.setHd(rs.getInt("hd"));
                pc.setCd(rs.getString("cd"));
                pc.setPrice(rs.getDouble("price"));
                pc.setCount(rs.getInt("count"));
                pcs.add(pc);
            }

            System.out.println("Найдено PC: " + pcs.size()); // ADD THIS LINE

        } catch (SQLException e) {
            throw new DAOException("Ошибка при получении списка PC: " + e.getMessage(), e);
        }
        return pcs;
    }
}
