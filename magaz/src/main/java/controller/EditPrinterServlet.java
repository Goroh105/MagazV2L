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
import domain.Printer;


/**
 * Servlet implementation class EditPrinterServlet
 */
@WebServlet("/editprinter")
public class EditPrinterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private PrinterDbDAO printerDAO; // Замените на ваш DAO

    @Override
    public void init() throws ServletException {
    	 super.init(); // Обязательно вызовите super.init()

         // Инициализация DAO
         // Пример:
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");


        // Получаем ID принтера из запроса (предполагается, что ID передается как параметр)
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            request.setAttribute("errorMessage", "Не указан ID принтера для редактирования.");
            request.getRequestDispatcher("/view/editprinter.jsp").forward(request, response);
            return;
        }

        Long printerId;
        try {
            printerId = Long.parseLong(idParam);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Неверный формат ID принтера.");
            request.getRequestDispatcher("/view/editprinter.jsp").forward(request, response);
            return;
        }


        Printer printer; //Предполагается, что есть класс Printer
        try {
            printer = printerDAO.getPrinterById(printerId);  // Получаем принтер из DAO
            if (printer == null) {
                request.setAttribute("errorMessage", "Принтер с указанным ID не найден.");
                request.getRequestDispatcher("/view/editprinter.jsp").forward(request, response);
                return;
            }
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Ошибка при получении данных о принтере: " + e.getMessage());
            request.getRequestDispatcher("/view/editprinter.jsp").forward(request, response);
            return;
        }


        request.setAttribute("printer", printer); //  Устанавливаем атрибут для JSP
        request.getRequestDispatcher("/view/editprinter.jsp").forward(request, response); //  Перенаправляем на JSP
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        // Получаем параметры из запроса
        String model = request.getParameter("inputModel");
        String colorStr = request.getParameter("inputColor");
        Boolean color = Boolean.valueOf(colorStr);
        String type = request.getParameter("inputType");
        String priceStr = request.getParameter("inputPrice");
        String countStr = request.getParameter("inputCount");


        // Валидация данных (минимум проверка на null и пустые строки)
        if (model == null || model.isEmpty() || priceStr == null || priceStr.isEmpty() || countStr == null || countStr.isEmpty() || type == null || type.isEmpty()) {
            request.setAttribute("errorMessage", "Пожалуйста, заполните все поля.");
            request.getRequestDispatcher("/view/editprinter.jsp").forward(request, response);
            return;
        }

         Double price;
        Integer count;

        try {
            price = Double.parseDouble(priceStr);
            count = Integer.parseInt(countStr);
             // Дополнительная проверка на положительные значения
            if (price < 0 || count < 0) {
                request.setAttribute("errorMessage", "Цена и количество должны быть неотрицательными.");
                request.getRequestDispatcher("/view/editprinter.jsp").forward(request, response);
                return;
            }

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Неверный формат цены или количества.");
            request.getRequestDispatcher("/view/editprinter.jsp").forward(request, response);
            return;
        }

        // Получаем ID принтера из запроса (предполагается, что ID передается как параметр)
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            request.setAttribute("errorMessage", "Не указан ID принтера для редактирования.");
            request.getRequestDispatcher("/view/editprinter.jsp").forward(request, response);
            return;
        }

        Long printerId;
        try {
            printerId = Long.parseLong(idParam);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Неверный формат ID принтера.");
            request.getRequestDispatcher("/view/editprinter.jsp").forward(request, response);
            return;
        }


        // Создаем объект Printer с данными из формы (Предполагается, что есть класс Printer и соответствующие сеттеры)
        Printer printer = new Printer();
        printer.setId(printerId);
        printer.setModel(model);
        printer.setColor(color);
        printer.setType(type);
        printer.setPrice(price);
        printer.setCount(count);


        // Обновляем данные в базе данных
        try {
            printerDAO.updatePrinter(printer); // Используйте ваш метод обновления в DAO
            // После успешного обновления перенаправляем на страницу со списком принтеров
            response.sendRedirect("printer");  // Замените на URL вашего списка принтеров
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Ошибка при обновлении данных: " + e.getMessage());
            request.getRequestDispatcher("/view/editprinter.jsp").forward(request, response);  // Перенаправляем обратно на форму редактирования с сообщением об ошибке
        }
    }
}
