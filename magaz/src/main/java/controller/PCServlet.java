package controller; 
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet; 
import jakarta.servlet.http.HttpServlet; 
import jakarta.servlet.http.HttpServletRequest; 
import jakarta.servlet.http.HttpServletResponse; 
import java.io.IOException; 
import java.io.PrintWriter;  
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
 protected void doGet(HttpServletRequest request, 
HttpServletResponse response) throws ServletException, IOException { 
        response.setContentType("text/html"); 
        PrintWriter writer = response.getWriter(); 
        try { 
            writer.println("<html><body><h2>Привет PCServlet</h2></body></html>"); 
        } finally { 
            writer.close();   
        } 
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("pc.jsp");
		dispatcher.forward(request, response);
 
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