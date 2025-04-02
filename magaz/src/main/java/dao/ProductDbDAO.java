package dao;

import domain.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import exception.DAOException;

public class ProductDbDAO implements RepositoryDAO<Product> {

    // SQL-запросы к таблице produkt базы данных
    private static final String SELECT_ALL_PRODUCT = "SELECT model, maker, type FROM Product";
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
        List<Product> pr = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_PRODUCT);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setmodel(rs.getString("model"));
                product.setmaker(rs.getString("maker"));
                product.settype(rs.getString("type"));
                pr.add(product);
            }

        } catch (SQLException e) {
            throw new DAOException("Ошибка при получении списка продуктов: " + e.getMessage(), e);
        }
        return pr;
    }
    
    public void updateWithoutId(Product product) throws DAOException {
        String sql = "UPDATE product SET maker = ?, type = ? WHERE model = ?";  // SQL-запрос
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, product.getmaker()); // Устанавливаем новое значение производителя
            pst.setString(2, product.gettype());  // Устанавливаем новый тип товара
            pst.setString(3, product.getmodel()); // Используем модель для поиска записи

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated == 0) {
                throw new DAOException("Не удалось обновить продукт (возможно, продукт не найден).");
            }
        } catch (SQLException e) {
            throw new DAOException("Ошибка при обновлении продукта: " + e.getMessage(), e);
        }
    }
    
    public void deleteWithoutId(String model, String maker, String type) throws DAOException {
        Connection con = null; // Объявляем переменную con вне блока try
        try {
            con = getConnection();
            con.setAutoCommit(false); // Отключаем автоматический коммит

         // 1. Удаляем связанные записи из таблицы "printer"
            String deletePrintersSql = "DELETE FROM printer WHERE model = ?";
            try (PreparedStatement deletePrintersPst = con.prepareStatement(deletePrintersSql)) {
                deletePrintersPst.setString(1, model);
                deletePrintersPst.executeUpdate();
            }

            // 2. Удаляем связанные записи из таблицы "pc"
            String deletePcsSql = "DELETE FROM pc WHERE model = ?";
            try (PreparedStatement deletePcsPst = con.prepareStatement(deletePcsSql)) {
                deletePcsPst.setString(1, model);
                deletePcsPst.executeUpdate();
            }

            // 3. Удаляем связанные записи из таблицы "laptop"
            String deleteLaptopsSql = "DELETE FROM laptop WHERE model = ?";
            try (PreparedStatement deleteLaptopsPst = con.prepareStatement(deleteLaptopsSql)) {
                deleteLaptopsPst.setString(1, model);
                deleteLaptopsPst.executeUpdate();
            }

            // 4. Удаляем запись из таблицы "product"
            String deleteProductSql = "DELETE FROM product WHERE model = ? AND maker = ? AND type = ?";
            try (PreparedStatement deleteProductPst = con.prepareStatement(deleteProductSql)) {
                deleteProductPst.setString(1, model);
                deleteProductPst.setString(2, maker);
                deleteProductPst.setString(3, type);

                int rowsDeleted = deleteProductPst.executeUpdate();
                if (rowsDeleted == 0) {
                    throw new DAOException("Не удалось удалить продукт (возможно, продукт не найден).");
                }
            }

            con.commit(); // Подтверждаем транзакцию
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Откатываем транзакцию в случае ошибки
                } catch (SQLException rollbackException) {
                    // Логируем ошибку отката транзакции
                    rollbackException.printStackTrace();
                }
            }
            throw new DAOException("Ошибка при удалении продукта: " + e.getMessage(), e);
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true); // Возвращаем автоматический коммит
                    con.close(); // Закрываем соединение
                } catch (SQLException closeException) {
                    // Логируем ошибку закрытия соединения
                    closeException.printStackTrace();
                }
            }
        }
    }
}