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
import dao.LaptopDbDAO;
import dao.ProductDbDAO;
import domain.Laptop;
import domain.Product;
import exception.DAOException;

/**
 * Servlet implementation class LaptopServlet
 */
@WebServlet("/laptop")
public class LaptopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LaptopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
	        new ConnectionProperty();
	        LaptopDbDAO lpDAO = new LaptopDbDAO();
	        List<Laptop> laps = lpDAO.findAll();
	        request.setAttribute("laps", laps);

	        System.out.println("Список PC установлен в атрибут: " + (laps != null ? laps.size() : "null")); // ADD THIS LINE

	    } catch (DAOException e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "Ошибка при получении списка PC: " + e.getMessage());
	    }
		
		response.setContentType("text/html");
		
	    String userPath;
	    List<Laptop> laps = null;
	    Map<String, Product> productMap = null; // Map to store Product data

	    try {
	        new ConnectionProperty();
	        LaptopDbDAO lapsDAO = new LaptopDbDAO();
	        ProductDbDAO productDAO = new ProductDbDAO(); // Create Product DAO
	        laps = lapsDAO.findAll();

	        // Fetch all products and store them in a map
	        List<Product> allProducts = productDAO.findAll();
	        productMap = new HashMap<>();
	        for (Product product : allProducts) {
	            productMap.put(product.getmodel(), product);
	        }

	        request.setAttribute("laps", laps);
	        request.setAttribute("productMap", productMap); // Set the product map to request

	    } catch (DAOException e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "Ошибка при получении списка Laptop: " + e.getMessage());
	    } 

	        request.getRequestDispatcher("/view/laptop.jsp").forward(request, response);
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String model = request.getParameter("inputModel");
		String speedStr = request.getParameter("inputSpeed");
		String ramStr = request.getParameter("inputRAM");
		String hdStr = request.getParameter("inputHD");
		String screenStr = request.getParameter("inputScreen");
        String priceStr = request.getParameter("inputPrice");
        String countStr = request.getParameter("inputCount");

        Integer speed = null;
        Integer ram = null;
        Integer hd = null;
        Double screen = null;
        Double price = null;
        Integer count = null;

        //  Проверка на null и пустые строки для всех текстовых полей
        if (model == null || model.isEmpty() ||
            speedStr == null || speedStr.isEmpty() ||
            ramStr == null || ramStr.isEmpty() ||
            hdStr == null || hdStr.isEmpty() ||
            screenStr == null || screenStr.isEmpty() ||
            priceStr == null || priceStr.isEmpty() ||
            countStr == null || countStr.isEmpty()) {

        request.setAttribute("errorMessage", "Пожалуйста, заполните все поля.");
        doGet(request, response);
        return;
        }

        //  Преобразование строк в числа и обработка NumberFormatException
        try {
            speed = Integer.parseInt(speedStr);
            ram = Integer.parseInt(ramStr);
            hd = Integer.parseInt(hdStr);
            screen = Double.parseDouble(screenStr);
            price = Double.parseDouble(priceStr);
            count = Integer.parseInt(countStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Неверный формат числа в одном из полей.");
            doGet(request, response);
            return;
        }

        Laptop newLaptop = new Laptop();
        newLaptop.setModel(model);
        newLaptop.setSpeed(speed);
        newLaptop.setRam(ram);
        newLaptop.setHd(hd);
        newLaptop.setScreen(screen);
        newLaptop.setPrice(price);
        newLaptop.setCount(count);

        LaptopDbDAO laptopDAO = null;

        try {
            new ConnectionProperty();
            laptopDAO = new LaptopDbDAO();
            laptopDAO.insert(newLaptop);
            System.out.println("Laptop added successfully!");
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Ошибка при добавлении Laptop: " + e.getMessage());
        } finally {
            doGet(request, response); // Refresh the PC list
        }
		
	}

}
