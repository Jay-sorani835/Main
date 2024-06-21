<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
   <!-- basic -->
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <!-- mobile metas -->
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <meta name="viewport" content="initial-scale=1, maximum-scale=1">
   <!-- site metas -->
   <title>webwing</title>
   <meta name="keywords" content="">
   <meta name="description" content="">
   <meta name="author" content="">
   <!-- bootstrap css -->
   <link rel="stylesheet" href="css/bootstrap.min.css">
   <!-- style css -->
   <link rel="stylesheet" href="css/style.css">
   <!-- Responsive-->
   <link rel="stylesheet" href="css/responsive.css">
   <link rel="stylesheet" href="css/owl.carousel.min.css">
   <!-- fevicon -->
   <link rel="icon" href="images/fevicon.png" type="image/gif" />
   <!-- Scrollbar Custom CSS -->
   <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
   <!-- Tweaks for older IEs-->
   <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
   <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->

	<style type="text/css">
      
      	

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





<!-- body -->

<body class="main-layout">
   <!-- loader  -->
   <div class="loader_bg">
      <div class="loader"><img src="images/loading.gif" alt="#" /></div>
   </div>
   <!-- end loader -->
   <!-- header -->
   <header>
      <!-- header inner -->
      <div class="header">
         <div class="header_to d_none">
            <div class="container">
               <div class="row">
               
               <div>
                     <ul class="social_icon1" style="color: white; margin-right: 300px;">
                        <li> Follow Us
                        </li>
                        <li> <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i>
                           </a>
                        </li>
                        <li> <a href="#"><i class="fa fa-twitter"></i></a></li>
                        <li> <a href="#"> <i class="fa fa-linkedin" aria-hidden="true"></i></a></li>
                        <li> <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i>
                           </a>
                        </li>
                     </ul>
                   
                  </div>
               
               
               
               <div class="col-md-6 col-sm-6 " style="margin-left: 550px;">
                     <ul>
                       
                       <%
                       response.setHeader("Cache-Control", "no-cache");
             			
                       	if(session.getAttribute("fruit")!=null)
                       	{
                       		
                       	
                       %> 
                        	
                      <b><p style="color: white;">Currently Loged In By <%=session.getAttribute("email") %></b>
                        	
                       <%
                       	}
                       	else
                       	{
                       		
                       	}
                        %>	 
                     </ul>
                  </div>
               </div>
            </div>
         </div>
         <div class="header_midil">
            <div class="container">
               <div class="row d_flex">
                  <div class="col-md-4 col-sm-4 d_none">
                     <ul class="conta_icon">
                        <li><a href="#"><i class="fa fa-phone" aria-hidden="true"></i> Call Us : +01 1234567890</a> </li>
                     </ul>
                  </div>
                  <div class="col-md-4 col-sm-4 ">
                     <a class="logo" href="#"><img src="images/logo.png" alt="#" /></a>
                  </div>
                  <div class="col-md-4 col-sm-4 d_none">
                     <ul class="conta_icon ">
                        <li><a href="#"><i class="fa fa-envelope" aria-hidden="true"></i> demo@gmail.com</a> </li>
                     </ul>
                  </div>
               </div>
            </div>
         </div>
         <div class="header_bo">
            <div class="container">
               <div class="row">
                  <div class="col-md-9 col-sm-7">
                     <nav class="navigation navbar navbar-expand-md navbar-dark ">
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
                           <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarsExample04">
                           <ul class="navbar-nav mr-auto">
                              <li class="nav-item active">
                                 <a class="nav-link" href="index.jsp"> Home </a>
                              </li>
                              <li class="nav-item">
                                 <a class="nav-link" href="about.jsp">about</a>
                              </li>
                              
                                <li class="nav-item">
                                 <a class="nav-link" href="contact1.jsp"> contact us </a>
                              </li>
                              
                           <%
                              	if(session.getAttribute("fruit")!=null)
                              	{
                              		
                              	
                              %> 
                              
                              <li class="nav-item">
                                 <a class="nav-link" href="product.jsp">products</a>
                              </li>
                              <li class="nav-item">
                                 <a class="nav-link" href="wishlist.jsp">wishlist</a>
                              </li>
                              <li class="nav-item">
                                 <a class="nav-link" href="cart.jsp">Cart</a>
                              </li>
                            
                              <%
                              	}
                              %>
                            
                           </ul>
                        </div>
                     </nav>
                  </div>
                  <div class="col-md-3 col-sm-5 d_none" style="">
                   
                  		<%

            				if(session.getAttribute("fruit")!=null)
                  			{
                  				
                  			
                  		%>
                  		
                    	 <a class="swd-button" href="logout.jsp">Logout</a>
                  		<%
                  			}
                  			else
                  			{
                  				
                  			
                  		%>
                  
                         <a class="swd-button" href="signup.jsp">sign up </a>
                     	<a class="swd-button" href="signin.jsp">sign in </a>
                     	
                 		 	<%
                  			}
                     	%> 
                  </div>
               </div>
            </div>
         </div>
      </div>
   </header>
   <!-- end header inner -->
   <!-- end header -->