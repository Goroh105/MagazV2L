package controller;

import domain.Product;
import dao.ConnectionProperty;
import dao.ProductDbDAO;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/editproduct")
public class EditProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String model = request.getParameter("model");
        String maker = request.getParameter("maker");
        String type = request.getParameter("type");

        if (model == null || model.isEmpty() || maker == null || maker.isEmpty() || type == null || type.isEmpty()) {
            request.setAttribute("errorMessage", "Недостаточно данных для редактирования.");
            request.getRequestDispatcher("/view/editproduct.jsp").forward(request, response);
            return;
        }

        Product productEdit = new Product();
        productEdit.setmodel(model);
        productEdit.setmaker(maker);
        productEdit.settype(type);

        request.setAttribute("productEdit", productEdit);
        request.getRequestDispatcher("/view/editproduct.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String model = request.getParameter("inputmodel");
        String maker = request.getParameter("inputmaker");
        String type = request.getParameter("inputtype");

        // Проверка на null или пустые значения
        if (model == null || model.trim().isEmpty() ||
            maker == null || maker.trim().isEmpty() ||
            type == null || type.trim().isEmpty()) {

            request.setAttribute("errorMessage", "Пожалуйста, заполните все поля.");
            request.getRequestDispatcher("/view/editproduct.jsp").forward(request, response);
            return;
        }

        Product product = new Product();
        product.setmodel(model);
        product.setmaker(maker);
        product.settype(type);

        ProductDbDAO productDAO = new ProductDbDAO();
        try {
            productDAO.updateWithoutId(product); // Обновляем продукт в базе данных (без ID)
            response.sendRedirect("product");  //  Redirection
        } catch (DAOException e) {
            request.setAttribute("errorMessage", "Ошибка при обновлении данных продукта: " + e.getMessage());
            request.getRequestDispatcher("/view/editproduct.jsp").forward(request, response);
        }
    }

    private Product getProduct(HttpServletRequest request, HttpServletResponse response, Long productId) throws ServletException, IOException{
        ProductDbDAO productDAO = new ProductDbDAO();
        Product productEdit = null;
        try {
            productEdit = productDAO.findById(productId); // Получаем продукт из базы данных
        } catch (DAOException e) {
            request.setAttribute("errorMessage", "Ошибка при получении данных продукта: " + e.getMessage());
            request.getRequestDispatcher("/view/editproduct.jsp").forward(request, response);
        }
        return productEdit;
    }
}