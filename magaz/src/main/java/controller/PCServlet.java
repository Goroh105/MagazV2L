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
import dao.PCDbDAO;
import dao.ProductDbDAO;
import domain.PC;
import domain.Product;
import exception.DAOException;
/** 
 * 
 */ 
@WebServlet("/pc") 
public class PCServlet extends HttpServlet { 
 private static final long serialVersionUID = 1L; 
        
    /** 
     * @see HttpServlet#HttpServlet() 
     */ 
    public PCServlet() { 
        super(); 
        // TODO Auto-generated constructor stub 
    } 
 
 /** 
  * @see HttpServlet#doGet(HttpServletRequest request, 
HttpServletResponse response) 
  */ 
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	response.setContentType("text/html");
    String userPath;
    List<PC> pcs = null;
    Map<String, Product> productMap = null; // Map to store Product data

    try {
        new ConnectionProperty();
        PCDbDAO pcDAO = new PCDbDAO();
        ProductDbDAO productDAO = new ProductDbDAO(); // Create Product DAO
        pcs = pcDAO.findAll();

        // Fetch all products and store them in a map
        List<Product> allProducts = productDAO.findAll();
        productMap = new HashMap<>();
        for (Product product : allProducts) {
            productMap.put(product.getmodel(), product);
        }

        request.setAttribute("pcs", pcs);
        request.setAttribute("productMap", productMap); // Set the product map to request

    } catch (DAOException e) {
        e.printStackTrace();
        request.setAttribute("errorMessage", "Ошибка при получении списка PC: " + e.getMessage());
    } 
    request.getRequestDispatcher("/view/pc.jsp").forward(request, response);
    
   }
 
 /** 
  * @see HttpServlet#doPost(HttpServletRequest request, 
HttpServletResponse response) 
  */ 
 protected void doPost(HttpServletRequest request, 
HttpServletResponse response) throws ServletException, IOException { 
	 
  // TODO Auto-generated method stub 
  doGet(request, response); 
 
 } 
 
}