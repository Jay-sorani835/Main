<%@ page import="com.dao.Dao" %>
<%@ page import="com.model.ImageModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"/>
	<%
	if(session.getAttribute("fruit")!=null)
	{
	%>
	<%
			String id = request.getParameter("id");
			int id2 = Integer.parseInt(id);
			ImageModel m = Dao.editdata(id2);	
		%>
		
		<br>
		<br>
		<div style="text-align: center; margin-top: 20px">
		<h2><%=m.getP_name() %></h2>
		<h3><%=m.getP_price() %></h3>
		<h3><%=m.getP_des() %></h3>
		<img src="data:image/jpeg;base64,<%=m.getP_image()%>" width="150px" height="200px" />
		</div>
		<form action="addtowish" method="post" enctype="multipart/form-data" class="requires-validation" novalidate style="text-align: center;">
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
                                <button id="submit" type="submit" class="btn btn-primary">Add to Wishlist </button>
                            </div>
 		</form>
		<% } %>
		  		
<jsp:include page="footer.jsp"/>
</body>
</html>