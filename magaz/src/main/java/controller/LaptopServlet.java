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
import dao.LaptopDbDAO;
import domain.Laptop;
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

	        request.getRequestDispatcher("/view/laptop.jsp").forward(request, response);
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
