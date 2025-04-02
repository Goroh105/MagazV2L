package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import dao.DbConnectionBuilder;
import dao.PrinterDbDAO;

/**
 * Servlet implementation class DeletePrinterServlet
 */
@WebServlet("/deleteprinter")
public class DeletePrinterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePrinterServlet() {
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
	private PrinterDbDAO printerDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        // Инициализация DAO
        try {
            // Предполагаем, что у вас есть класс DbConnectionBuilder для получения соединений
            // Замените на ваши параметры подключения к базе данных
            DbConnectionBuilder dbConnectionBuilder = new DbConnectionBuilder();
            printerDAO = new PrinterDbDAO(dbConnectionBuilder); // Создаем экземпляр PrinterDbDAO
        } catch (Exception e) { // Обработка ошибок при создании DAO (например, ошибки подключения)
            // Логирование ошибки (обязательно!)
            System.err.println("Ошибка при инициализации PrinterDbDAO: " + e.getMessage());
            e.printStackTrace();
            throw new ServletException("Ошибка при инициализации сервлета", e); // Перебрасываем исключение, чтобы остановить запуск сервлета
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            request.setAttribute("errorMessage", "Не указан ID принтера для удаления.");
            request.getRequestDispatcher("printer.jsp").forward(request, response);
            return;
        }

        Long printerId;
        try {
            printerId = Long.parseLong(idParam);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Неверный формат ID принтера.");
            request.getRequestDispatcher("printer.jsp").forward(request, response);
            return;
        }

        try {
            printerDAO.deletePrinter(printerId); // Вызываем метод deletePrinter
            response.sendRedirect("printer"); // Перенаправление на страницу со списком принтеров
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Ошибка при удалении принтера: " + e.getMessage());
            request.getRequestDispatcher("printer.jsp").forward(request, response);
        }
    }
	
	

}
