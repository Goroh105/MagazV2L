package dao;

import domain.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import exception.DAOException;

public class ProductDbDAO implements RepositoryDAO<Product> {

    // SQL-запросы к таблице produkt базы данных
    private static final String SELECT_ALL_PRODUCTS = "SELECT model, maker, type FROM Product";
    private static final String SELECT_PRODUCT_BY_MODEL = "SELECT model, maker, type FROM Product WHERE model = ?";
    private static final String INSERT_PRODUCT = "INSERT INTO Product (model, maker, type) VALUES (?, ?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE Product SET maker = ?, type = ? WHERE model = ?"; 
    private static final String DELETE_PRODUCT = "DELETE FROM Product WHERE model = ?"; 


    private final ConnectionBuilder builder = new DbConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Product product) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_PRODUCT)) {

            pst.setString(1, product.getmodel());
            pst.setString(2, product.getmaker());
            pst.setString(3, product.gettype());
            pst.executeUpdate(); 
            return null; 
        } catch (SQLException e) {
            throw new DAOException("Ошибка при добавлении продукта: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Product product) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_PRODUCT)) {

            pst.setString(1, product.getmaker());
            pst.setString(2, product.gettype());
            pst.setString(3, product.getmodel()); 
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Ошибка при обновлении продукта: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long model) throws DAOException {
        // Model is a String, not a Long
        throw new UnsupportedOperationException("Удаление по ID (Long) не поддерживается, используйте delete(String model)");
    }
    public void delete(String model) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_PRODUCT)) {

            pst.setString(1, model);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Ошибка при удалении продукта: " + e.getMessage(), e);
        }
    }

    @Override
    public Product findById(Long model) throws DAOException {
        // Model is a String, not a Long
        throw new UnsupportedOperationException("Поиск по ID (Long) не поддерживается, используйте findById(String model)");
    }

    public Product findById(String model) throws DAOException {
        Product product = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_PRODUCT_BY_MODEL)) {

            pst.setString(1, model);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    product = new Product();
                    product.setmodel(rs.getString("model"));
                    product.setmaker(rs.getString("maker"));
                    product.settype(rs.getString("type"));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Ошибка при поиске продукта по модели: " + e.getMessage(), e);
        }
        return product;
    }

    @Override
    public List<Product> findAll() throws DAOException {
        List<Product> products = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_PRODUCTS);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setmodel(rs.getString("model"));
                product.setmaker(rs.getString("maker"));
                product.settype(rs.getString("type"));
                products.add(product);
            }

        } catch (SQLException e) {
            throw new DAOException("Ошибка при получении списка продуктов: " + e.getMessage(), e);
        }
        return products;
    }
}