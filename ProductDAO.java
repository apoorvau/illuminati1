package test;
import test.Product;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleDriver;

public class ProductDAO {

	Connection conn;
	private	PreparedStatement pst;
		ResultSet rs;
  int count1;
  int remaining;
  int storeId;
  int pid;
  
		public void getConnection()
		{
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				String url="jdbc:oracle:thin:@localhost:1521:xe";
				conn=DriverManager.getConnection(url,"illuminati", "manager");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		public int searchProduct1(int product_id,int quantity,int store_id)
		{
			Product pr=new Product();
			getConnection();
			System.out.println("connected..");
			System.out.println("hi");
			
			try {
				pst=conn.prepareStatement("select * from inventory_details where product_id=? and store_id=?");
				pst.setInt(1,product_id);
				pst.setInt(2,store_id);
				rs=pst.executeQuery();
				System.out.println("connected to inventory table..");
				while(rs.next())
				{
					System.out.println(""+rs.getInt(3));
				
					remaining=rs.getInt(3)-quantity;
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return remaining;
		}
		
		public void updateInventory1(int prod_id,int remain,int store_id)
		{
			pid=prod_id;
			 storeId=store_id;
			 remaining=remain;
			getConnection();
			try {
				pst=conn.prepareStatement("update inventory_details set available_stock=? where product_id=? and store_id=?");
				pst.setInt(1, remaining);
				pst.setInt(2,pid);
				pst.setInt(3,storeId);
				rs=pst.executeQuery();
				System.out.println("stock updated..in store "+storeId);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public void requestWarehouse(int store_id,int prod_id)
		{
			int storeId=store_id;
			int product_id=prod_id;
			getConnection();
			try {
				pst=conn.prepareStatement("update inventory_details set available_stock=? where product_id=? and store_id=1");
				pst.setInt(1,remaining+200);
				pst.setInt(2,product_id);
				rs=pst.executeQuery();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		
		public void showAdminTable()
		{
			getConnection();
			try {
				pst=conn.prepareStatement("select * from inventory_details ");
				pst.executeQuery();
				rs=pst.executeQuery();
				while(rs.next())
				{
					System.out.println("Store ID: "+rs.getInt(1));
					System.out.println("Item ID:"+rs.getInt(2));
					System.out.println("Available Quantity in Store:"+rs.getInt(3));
					System.out.println();
					
				}
				
			} catch (SQLException e) {
								e.printStackTrace();
			}
		}

}
