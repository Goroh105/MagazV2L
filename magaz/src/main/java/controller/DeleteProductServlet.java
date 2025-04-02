package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.ProductDbDAO;
import exception.DAOException;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/deleteproduct")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String model = request.getParameter("model");
        String maker = request.getParameter("maker");
        String type = request.getParameter("type");

        if (model == null || model.trim().isEmpty() || maker == null || maker.trim().isEmpty() || type == null || type.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Недостаточно данных для удаления продукта.");
            request.getRequestDispatcher("/view/product.jsp").forward(request, response); // Обратно на список
            return;
        }

        ProductDbDAO productDAO = new ProductDbDAO();
        try {
            productDAO.deleteWithoutId(model, maker, type);
            response.sendRedirect("product"); // Перенаправление на список продуктов
        } catch (DAOException e) {
            request.setAttribute("errorMessage", "Ошибка при удалении продукта: " + e.getMessage());
            request.getRequestDispatcher("/view/product.jsp").forward(request, response); // Обратно на список
        }
    }

}
