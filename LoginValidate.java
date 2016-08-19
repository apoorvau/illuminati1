package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginValidate
 */
public class LoginValidate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out =response.getWriter();
		String username= request.getParameter("uname");
		String password=request.getParameter("password");
		RequestDispatcher rd; 
		if(username.equals("admin") && password.equals("admin"))
		{
			rd=request.getRequestDispatcher("admin.html");
			rd.forward(request, response);
		}
		else
		{
			rd=request.getRequestDispatcher("homeUser.html");
			rd.forward(request, response);
		}
		
			
			
	
		
	}

}
