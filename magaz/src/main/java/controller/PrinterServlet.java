package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ConnectionProperty;
import dao.PrinterDbDAO;
import dao.ProductDbDAO;
import domain.Printer;
import domain.Product;
import exception.DAOException;

/**
 * Servlet implementation class PrinterServlet
 */
@WebServlet("/printer")
public class PrinterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrinterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
	    String userPath;
	    List<Printer> pr = null;
	    Map<String, Product> productMap = null; // Map to store Product data

	    try {
	        new ConnectionProperty();
	        PrinterDbDAO prDAO = new PrinterDbDAO();
	        ProductDbDAO productDAO = new ProductDbDAO(); // Create Product DAO
	        pr = prDAO.findAll();

	        // Fetch all products and store them in a map
	        List<Product> allProducts = productDAO.findAll();
	        productMap = new HashMap<>();
	        for (Product product : allProducts) {
	            productMap.put(product.getmodel(), product);
	        }

	        request.setAttribute("pr", pr);
	        request.setAttribute("productMap", productMap); // Set the product map to request

	    } catch (DAOException e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "Ошибка при получении списка Printer: " + e.getMessage());
	    } 
	    request.getRequestDispatcher("/view/printer.jsp").forward(request, response);
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
