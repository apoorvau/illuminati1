package test;
import test.ProductDAO;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;


public class PayNow extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
	
	int product_id;
	int quantity;
	int remaining1;
	int store_id=1;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		product_id=Integer.parseInt(request.getParameter("product_id"));
		quantity=Integer.parseInt(request.getParameter("quantity"));
		ProductDAO pdao=new ProductDAO();
		remaining1=pdao.searchProduct1(product_id, quantity, 1);
		System.out.println("returned from searchProduct function..");
	
			if(remaining1>25)
			{
				out.println("stock available in  current store ");
				out.println("The available stock before order is:" + (remaining1+quantity)+"<br>");
				pdao.updateInventory1(product_id, remaining1,store_id);
				out.println("<h2>INVENTORY UPDATED..</h2><br>");
				out.println("items now left in store "+ store_id + " are: "+ remaining1);
				out.println("<h1>Order Successfully Placed</h1>");
			}
			else if(remaining1>0 && remaining1<=25)
			{

				pdao.requestWarehouse(store_id,product_id);
				out.println("Order succesfully placed in current store..");
				out.println("requested warehouse and updated the inventory ..");
				out.println("<br><br>");
				out.println("items now available in store "+store_id +" are: "+(remaining1+200));
			}
			
			
			else {
				out.println("Item Unavailable in requested store..redirecting to nearest store..<br>");
			    store_id=store_id+1;
				out.println("<br> Present store  ID: "+store_id);
				remaining1=pdao.searchProduct1(product_id, quantity, store_id);
				if(store_id==2)
				{
					if(remaining1>0)
					{
						out.println("<br>stock available in  store "+store_id);
						out.println("<br>items now left in store " + store_id +" are:  "+remaining1);
						pdao.updateInventory1(product_id, remaining1,store_id);
						out.println("<br><h2>Order Successfully Placed..</h2>");
					}
				}
				else{
					
				store_id=store_id+1;
				out.println("<br>order redirected to store " + store_id);
				if(store_id==3)
				{
					if(remaining1>0)
					{
						out.println("<br>stock available in  store "+store_id);
						out.println("<br>items now left in store " + store_id +" are:  "+remaining1);
						pdao.updateInventory1(product_id, remaining1,store_id);
						out.println("<br><h2>Order Successfully Placed..</h2>");
					}
					else 
					{
						out.println("Item Unavailable! Please try again later");
						RequestDispatcher view= request.getRequestDispatcher("VerizonStores.html");
						view.forward(request,response);
					}
				}
				
				
				}
	}
	}
}

