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
import dao.PrinterDbDAO;
import domain.Printer;
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

		try {
	        new ConnectionProperty();
	        PrinterDbDAO prDAO = new PrinterDbDAO();
	        List<Printer> pr = prDAO.findAll();
	        request.setAttribute("pr", pr);

	        System.out.println("Список Printer установлен в атрибут: " + (pr != null ? pr.size() : "null")); // ADD THIS LINE

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
