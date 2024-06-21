package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import com.model.AdminModel;
import com.model.CartModel;
import com.model.ImageModel;
import com.model.SignupModel;
import com.model.WishlistModel;


public class Dao 
{
	public static Connection getconnect()
	{
		Connection con = null;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fruit","root","");
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return con;
	}
	
	public static int signup(SignupModel m)
	{
		Connection con =Dao.getconnect();
		int status = 0;
		
		try 
		{
			PreparedStatement ps = con.prepareStatement("insert  into user (name,email,phone,password) values (?,?,?,?)");
			ps.setString(1,m.getName());
			ps.setString(2,m.getEmail());
			ps.setString(3,m.getPhone());
			ps.setString(4,m.getPassword());
			
			status = ps.executeUpdate();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static SignupModel login(SignupModel m)
	{
		SignupModel m2=null;
		
		Connection con = Dao.getconnect();
		try 
		{
			PreparedStatement ps = con.prepareStatement("select * from user where email=? and password=?");
			ps.setString(1,m.getEmail());
			ps.setString(2,m.getPassword());
			
			ResultSet set = ps.executeQuery();
			
			while(set.next())
			{
				int id = set.getInt(1);
				String name = set.getString(2);
				String email = set.getString(3);
				String phone = set.getString(4);
				String password = set.getString(5);
				
				m2 = new SignupModel();
				
				m2.setId(id);
				m2.setName(name);
				m2.setEmail(email);
				m2.setPhone(phone);
				m2.setPassword(password);
				
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m2;
		
	}
	
	  public static AdminModel adminlogin(AdminModel m) { AdminModel m2=null;
	  
	  Connection con = Dao.getconnect(); try { PreparedStatement ps = con.
	  prepareStatement("select * from adminlogin where username=? and password=?");
	  ps.setString(1,m.getUsername()); ps.setString(2,m.getPassword());
	  
	  ResultSet set = ps.executeQuery();
	  
	  while(set.next()) { int id = set.getInt(1); String username =
	  set.getString(2); String firsrname = set.getString(3); String email =
	  set.getString(4); String password = set.getString(5);
	  
	  m2 = new AdminModel();
	  
	  m2.setId(id); m2.setUsername(username); m2.setFirstname(firsrname);
	  m2.setEmail(email); m2.setPassword(password);
	  
	  } } catch (Exception e) { // TODO Auto-generated catch block
	  e.printStackTrace(); }
	  
	  return m2;
	  
	  }
	  
		public static List<ImageModel> getAll()
		{
			List<ImageModel> i = new ArrayList<ImageModel>();
			try {
				
				Connection con = Dao.getconnect();
				PreparedStatement ps= con.prepareStatement("select * from products");
				
				ResultSet rs = (ResultSet) ps.executeQuery();
				
				while(rs.next())
				{
					ImageModel d1 = new ImageModel();
					d1 = new ImageModel();
					d1.setP_id(rs.getInt(1));
					d1.setP_name(rs.getString(2));
					d1.setP_price(rs.getString(3));
					d1.setP_des(rs.getString(4));
					//d1.setP_quantity(rs.getString("p_quantity"));
					//d1.setP_image(rs.getString("p_image"));
					
					 byte[] imgData = rs.getBytes(5); // blob field 
			         String encode = Base64.getEncoder().encodeToString(imgData);
			         d1.setP_image(encode);
			         //request.setAttribute("imgBase", encode);
			         i.add(d1);
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return i;
		}
		
		
		public static ImageModel editdata(int id)
		{
			Connection con = Dao.getconnect();
			ImageModel m = new ImageModel();
			
			try 
			{
				PreparedStatement ps = con.prepareStatement("select * from products where id=?");
				ps.setInt(1,id);
				
				ResultSet set = ps.executeQuery();
				
				if(set.next())
				{
					
					int id1 = set.getInt(1);
					String name = set.getString(2);
					String price = set.getString(3);
					String des = set.getString(4);
					byte[] imgData = set.getBytes(5); 
					String encode = Base64.getEncoder().encodeToString(imgData);
			         
					m.setP_id(id1);
					m.setP_name(name);
					m.setP_price(price);
					m.setP_des(des);
					m.setP_image(encode);
					
				}
			}
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return m;
		}
		
		public static List<WishlistModel> getAllwishlist()
		{
			List<WishlistModel> i = new ArrayList<WishlistModel>();
			try {
				
				Connection con = Dao.getconnect();
				PreparedStatement ps= con.prepareStatement("select * from wishlist");
				
				ResultSet rs = (ResultSet) ps.executeQuery();
				
				while(rs.next())
				{
					WishlistModel d1 = new WishlistModel();
					d1 = new WishlistModel();
					d1.setId(rs.getInt("id"));
					d1.setP_name(rs.getString("p_name"));
					d1.setP_price(rs.getString("p_price"));
					d1.setP_des(rs.getString("p_des"));
					//d1.setP_image(rs.getString("p_image"));
					
					 byte[] imgData = rs.getBytes("p_image"); // blob field 
			         String encode = Base64.getEncoder().encodeToString(imgData);
			         d1.setP_image(encode);
			         //request.setAttribute("imgBase", encode);
					i.add(d1);
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return i;
		}
		public static List<CartModel> getAllCart()
		{
			List<CartModel> i = new ArrayList<CartModel>();
			try {
				
				Connection con = Dao.getconnect();
				PreparedStatement ps= con.prepareStatement("select * from cart");
				
				ResultSet rs = (ResultSet) ps.executeQuery();
				
				while(rs.next())
				{
					CartModel d1 = new CartModel();
					d1 = new CartModel();
					d1.setId(rs.getInt("id"));
					d1.setP_name(rs.getString("p_name"));
					d1.setP_price(rs.getString("p_price"));
					d1.setP_des(rs.getString("p_des"));
					//d1.setP_image(rs.getString("p_image"));
					
					 byte[] imgData = rs.getBytes("p_image"); // blob field 
			         String encode = Base64.getEncoder().encodeToString(imgData);
			         d1.setP_image(encode);
			         //request.setAttribute("imgBase", encode);
					i.add(d1);
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return i;
		}
	public static int remove(int id)
	{
		Connection con = Dao.getconnect();
		int status = 0;
		
		try 
		{
			PreparedStatement ps = con.prepareStatement("delete from wishlist where id = ?");
			ps.setInt(1, id);
			
			status = ps.executeUpdate();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		return status;
	}
	public static int removecart(int id)
	{
		Connection con = Dao.getconnect();
		int status = 0;
		
		try 
		{
			PreparedStatement ps = con.prepareStatement("delete from cart where id = ?");
			ps.setInt(1, id);
			
			status = ps.executeUpdate();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		return status;
	}
}
