
<%@page import="com.dao.Dao"%>
<%@page import="com.model.ImageModel"%>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Product Grid View</title>

    <style>
        .product-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            grid-gap: 20px;
            padding: 20px;
        }
        .product {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        .product img {
            max-width: 100px;
            max-height: 100px;
        }
        .swd-button 
			{
				background: #f2db18;
				border: 1px solid white;
				border-radius: 5px;
				color: white;
				display: inline-block;
				font: bold 12px Arial, Helvetica, sans-serif;
				padding: 10px 15px;
				text-decoration: none;
				text-transform: uppercase;
				margin-top: 15px;
			}
        
    </style>
</head>
<body>

	<jsp:include page="header.jsp"/>   
	<%
	
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setDateHeader("Expires", 0);
	%> 
	<%
	if(session.getAttribute("fruit")!=null)
	{
	%>
    <div class="product-grid">
        <% 
        List<ImageModel> list = Dao.getAll();
       
         for (ImageModel m : list) 
        {
        %>
        <div class="product">
            <img src="data:image/jpeg;base64,<%=m.getP_image()%>" width="150px" height="200px" />
            <h3><%= m.getP_name() %></h3>
            <p>Price: <%= m.getP_price() %></p>
            
           <form action="addtowishlist.jsp">
				 <div class="col-md-12">
                <input class="form-control" type="hidden" name="id" placeholder="Product Name" value="<%=m.getP_id() %>" required>
				</div>
				<button id="submit" type="submit" class="btn btn-primary">Add to Wishlist </button>
            </form>
            <form action="addtocart" method="post" enctype="multipart/form-data" class="requires-validation" novalidate style="text-align: center;">
			<div class="col-md-12">
                               <input class="form-control" type="hidden" name="id" placeholder="Product Name" value="<%=m.getP_id() %>" required>
                            </div>
                            <div class="col-md-12">
                               <input class="form-control" type="hidden" name="p_name" placeholder="Product Name" value="<%=m.getP_name() %>" required>
                            </div>
                            
                            <div class="col-md-12">
                               <input class="form-control" type="hidden" name="p_price" placeholder="Product Price" value="<%=m.getP_price() %>" required>
                            </div>
                            
                            <div class="col-md-12">
                               <input class="form-control" type="hidden" name="p_des" placeholder="Product Description" value="<%=m.getP_des() %>" readonly="readonly">
                            </div>
                            
                            <br>
                            <div class="col-md-12">
                            	<input type="hidden" name="p_image" value="data:image/jpeg;base64,<%=m.getP_image() %>" /> 
                             </div>
                            
                            <div class="form-button mt-3">
                                <button id="submit" type="submit" class="btn btn-primary">Add to Cart </button>
                            </div>
 		</form>
        </div>
        <% } %>
    </div>
    
    <% }
	else
	{
		response.sendRedirect("index.jsp");
	}
    %>
    	<jsp:include page="footer.jsp"/>    
    
</body>
</html>
