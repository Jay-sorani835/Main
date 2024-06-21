import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Base64;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addtocart")
@MultipartConfig()
public class addtocart extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession ss = req.getSession(false);
		
		if(ss != null)
		{
		
		String usl = "jdbc:mysql://localhost:3306/fruit";
		String user = "root";
		String pass = "";
		
		String id = req.getParameter("id");
		int id2 = Integer.parseInt(id);
		String name = req.getParameter("p_name");
		String price = req.getParameter("p_price");
		String description = req.getParameter("p_des");
		String image = req.getParameter("p_image");
		
		String base64ImageData = image.split(",")[1];
		 byte[] imageData = Base64.getDecoder().decode(base64ImageData);
		 InputStream io = new ByteArrayInputStream(imageData);
//		System.out.println(image);
		
		
		
		int r = 0;
		Connection con = null;
	
		
			try {
				
			Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(usl, user, pass);
				
				PreparedStatement ps = con.prepareStatement("insert into cart(p_name,p_price,p_des,p_image) values(?,?,?,?)");

				ps.setString(1, name);
				ps.setString(2, price);				
				ps.setString(3, description);
				ps.setBlob(4,io);
				
				r = ps.executeUpdate();
			
				if(r>0)
				{
					System.out.println("done");
					PreparedStatement ps2 = con.prepareStatement("delete from wishlist where id = ?");
					ps2.setInt(1, id2);
					
					int status = ps2.executeUpdate();
					
					resp.sendRedirect("cart.jsp");
				}
				else				{
				System.out.println("error");
				}
				
				
			
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
		}
		
	}
}
