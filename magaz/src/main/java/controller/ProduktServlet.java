package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dao.ConnectionProperty;
import dao.ProductDbDAO;
import domain.Product;
import exception.DAOException;

/**
 * Servlet implementation class ProduktServlet
 */
@WebServlet("/product")
public class ProduktServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L; 
	
    public ProduktServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
        
		try {
	        new ConnectionProperty();
	        ProductDbDAO proDAO = new ProductDbDAO();
	        List<Product> pro = proDAO.findAll();
	        request.setAttribute("pro", pro);

	        System.out.println("Список Product установлен в атрибут: " + (pro != null ? pro.size() : "null")); // ADD THIS LINE

	    } catch (DAOException e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "Ошибка при получении списка Product: " + e.getMessage());
	    }

	        request.getRequestDispatcher("/view/product.jsp").forward(request, response);
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
