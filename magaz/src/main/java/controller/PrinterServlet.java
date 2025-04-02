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
		 //  Обработка POST-запроса (добавление нового Printer)
        String model = request.getParameter("inputModel");
        String colorStr = request.getParameter("inputColor");
        boolean color = (colorStr != null && colorStr.equals("true")); // Преобразование строки в boolean
        String type = request.getParameter("inputType");
        String priceStr = request.getParameter("inputPrice");
        String countStr = request.getParameter("inputCount");

        Double price = 0.0; // Значения по умолчанию
        Integer count = 0;

        //  Проверка на null и пустые строки
           if (model == null || model.isEmpty() ||
            type == null || type.isEmpty()||
                priceStr == null || priceStr.isEmpty() ||
               countStr == null || countStr.isEmpty()) {

         request.setAttribute("errorMessage", "Пожалуйста, заполните все поля.");
        doGet(request, response);
        return;
        }

        try {
            price = Double.parseDouble(priceStr);
            count = Integer.parseInt(countStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Неверный формат числа в одном из полей.");
           doGet(request, response);
            return;
        }
        Printer newPrinter = new Printer();
        newPrinter.setModel(model);
        newPrinter.setColor(color);
        newPrinter.setType(type);
        newPrinter.setPrice(price);
        newPrinter.setCount(count);

        PrinterDbDAO printerDAO = null;

        try {
            new ConnectionProperty();
            printerDAO = new PrinterDbDAO();
            printerDAO.insert(newPrinter);
            System.out.println("PC added successfully!");
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Ошибка при добавлении Printer: " + e.getMessage());
        } finally {
            doGet(request, response); // Refresh the PC list
        }
    }
		

}
