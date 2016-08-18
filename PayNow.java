package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PayNow
 */
public class PayNow extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	int product_id;
	int quantity;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			conn=DriverManager.getConnection(url,"illuminati", "manager");
		} 
		catch (ClassNotFoundException |SQLException e)
		{
			e.printStackTrace();
		}
		
		
		PrintWriter out=response.getWriter();
		product_id=Integer.parseInt(request.getParameter("product_id"));
		quantity=Integer.parseInt(request.getParameter("quantity"));
		try {
			pst=conn.prepareStatement("select * from inventory_details where product_id=?");
			pst.setInt(1, product_id);
			rs=pst.executeQuery();
		while(rs.next())
		{
			pst=conn.prepareStatement("select available_stock from inventory_details where product_id=?");
			pst.setInt(1, product_id);
			out.println("stock available..");
		}
			
			
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
	}

}
