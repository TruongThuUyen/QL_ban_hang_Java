<%@page import="java.math.BigDecimal"%>
<%@page import="bean.GioHangbean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="bean.ctlichsubean"%>
<%@page import="bean.khachhangbean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Cart | E-Shopper</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
<!--     <link href="css/animate.css" rel="stylesheet"> -->
	<link href="css/main.css" rel="stylesheet">
	<link href="css/responsive.css" rel="stylesheet">      
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
   
    <style type="text/css">
    	.quantity{
    		width: 50px;
    	
    	}
    	.btn-update{
    		background: transparent;
		    outline: none;
		    border: 1px solid #fe980f;
		    margin-left: 10px;
		    color: black;
		    border-radius: 4px;
    		font-size: 12px;
    	}
    	.selecte-multi
    	{
    		display: flex;
    	}
    	
    	.f-select{
    		width: 180px;
    		 margin-right: 20px;
    	}
    	
    	.a-comeback
    	{
		    color: #000;
		    border: 1px solid #ccc;
		    border-radius: 4px;
		    display: flex;
		    font-size: 13px;
		    align-items: center;
		    padding: 0 10px;
		    height: 32px;

    	}
    	
    	.a-byit
    	{
		    color: #000;
		    border: 1px solid #fe980f;
		    border-radius: 4px;
		    display: flex;
		    font-size: 13px;
		    align-items: center;
		    float: right;
		    padding: 0 20px;
		    height: 32px !important;
    	}
    	
    	.wrap{
	    	display: flex;
	    	justify-content:  space-between;
    	}
    	
    	.search-btn
    	{
	   		background: transparent !important;
	  	  	border: 1px solid #fe980f !important;
	   	 	border-radius: 4px;
	   	 	color: #fe980f !important;
	   	 	margin-top: 10px;
		    padding: 0 !important;
		    height: 30px !important;
		    width: 100px !important;
	   	 	
    	}
    </style>
</head>
<body>
	<header id="header"><!--header-->
		<div class="header-middle"><!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-sm-4">
						<div class="logo pull-left">
							<a href="index.html"><img src="images/home/logo.png" alt="" /></a>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="shop-menu pull-right">
							<ul class="nav navbar-nav">
								<%if(session.getAttribute("dn2") != null)
								{
									khachhangbean kh = (khachhangbean) session.getAttribute("dn2");
								%>
									<li><a href="index.html" class="active">Xin chào: <%=kh.getHoten() %></a></li>
									<li><a href="DangXuatController"><i class="fa fa-lock"></i> Log out</a></li>
								<%}else{ %>
									<li><a href="DangKyController"><i class="fa fa-user"></i>Register</a></li>
									<li><a href="DangNhap.jsp"><i class="fa fa-lock"></i> Login</a></li>
								<%} %>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-middle-->
	
		<div class="header-bottom"><!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="col-sm-8">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
						</div>
						<div class="mainmenu pull-left">
							<ul class="nav navbar-nav collapse navbar-collapse">
								<li><a href="TrangChuController">Home</a></li>
								<li><a href = "<%= ((session.getAttribute("dn2") == null) ? "DangNhapController" : "TaiKhoanController")%>"><i class="fa fa-user"></i> Account</a></li>
								<%if(session.getAttribute("dsg2") != null){
									ArrayList<GioHangbean> gh = (ArrayList<GioHangbean>) session.getAttribute("dsg2");
									long n = gh.size();
								%>
								<li><a href="GioHangController"><i class="fa fa-shopping-cart"></i> Cart(<%=n %>)</a></li>
								<%}else{ %>
								<li><a href="GioHangController"><i class="fa fa-shopping-cart"></i> Cart</a></li>
								<%} %>
								<li><a href = "<%= ((session.getAttribute("dn2") == null) ? "DangNhapController" : "LichSuMHController")%>"><i class="fa fa-user"></i> History</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-bottom-->
	</header><!--/header-->
	
	<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs row">
				<ol class="breadcrumb col-sm-6">
				  <li><a href="TrangChuController">Home</a></li>
				  <li class="active">Shopping Cart</li>
				</ol>
				<!-- Search -->
				<div class="col-sm-6">
					<form class="search_box pull-right" action = "CTLSController" method = "post" style = "width: 100%; margin-bottom: 20px;">
						<input type="text" name = "tkctls" placeholder="Nhập tên sản phẩm" 
								style = "width: 100%;background: transparent;color: #000; border: 1px solid #ccc;"/>
						<%if(request.getParameter("empty") != null){ %>
							<p  style = "width: 100%; font-weight: 600; margin-top: 8px;">Vui lòng nhập từ khoá để tìm kiếm!</p>
						<%} %>
						<input type ="submit" class ="search-btn"/>
					</form>
				</div>
			</div>
			
			
			<p style = "width: 100%; text-align: center; font-size: 20px; margin-top: 30px;">Chi tiết lịch sử mua hàng</p>	
			
			<div class ="address" style = "margin-top: 10px;">
			
				<%if(session.getAttribute("dn2") != null){
					khachhangbean kh = (khachhangbean) session.getAttribute("dn2");	
				%>
				
				<div class ="user-info" style = "margin:10px 0 20px 0; width: 100%; text-align: center;">
					Khách hàng: <b style = "font-size: 18px"><%=kh.getHoten() %></b>
				</div>
				<%} %>
		
			</div>
			<div class ="wrap">
			<%	String tam = request.getParameter("modeht");
				if(tam.equals("null"))
					tam = "1";
			%>
				<a class ="a-comeback" href = "LSMHController?mode=<%=tam%>">Quay lại</a>
				<a class ="a-byit" href = "GioHangController">Mua lại</a>
			</div>
			<%
			int sh = 0;
			if (request.getAttribute("ctlichsu") != null) {
				ArrayList<ctlichsubean> gh = (ArrayList<ctlichsubean>) request.getAttribute("ctlichsu");
				sh = gh.size();
				DateFormat dateFormat = null;
	   			dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				if(sh > 0){
			%>
			<div class="table-responsive cart_info" style ="margin-top: 20px;">
				<table class="table table-condensed">
						<thead >
							<tr class="cart_menu" > 
								<td></td>
								<td class="image" style = "background-color: #fe980f; color: #fff">Code Bill</td>
								<td class="price" style = "background-color: #fe980f; color: #fff;">Image</td>
								<td class="quantity" style = "background-color: #fe980f;  color: #fff;">Product Name</td>
								<td class="quantity" style = "background-color: #fe980f;  color: #fff;">Price</td>
								<td class="quantity" style = "background-color: #fe980f;  color: #fff;">Quantity</td>
								<td class="quantity" style = "background-color: #fe980f;  color: #fff;">Discount</td>
								<td class="quantity" style = "background-color: #fe980f;  color: #fff;">Total</td>
								<td class="discount" style = "background-color: #fe980f; color: #fff;">Date</td>
								<td class="total" style = "background-color: #fe980f;  color: #fff;">Status</td>
							</tr>
						</thead>
						<tbody>
		
						<% for (int i = sh - 1 ; i >=0 ; i--) { %>
							<tr>
								<td class="cart_ck" class="form-check-input" >
									<input type = "checkbox" name = "cbXoa" value = "<%=gh.get(i).getMasp()%>" style= "width: 20px; height: 20px;"/>
								</td>
								<td class="cart_description">
									<p><%=gh.get(i).getMahd() %></p>
								</td>
								<td class="cart_product" style = "width: 120px; margin-left: 0; margin-right: 20px;">
									<p><img src="<%=gh.get(i).getAnh() %>" alt="" style = "width: 100%; height: 210px; object-fit: cover;" /></p>
								</td>
								<td class="cart_description">
									<p><%=gh.get(i).getTensp()%></p>
								</td>
								<td class="cart_description">
									<p><%=gh.get(i).getGia()%></p>
								</td>
								<td class="cart_quantity" >
									<p class="cart_quantity_input quantity"  type="number" name="quantity" autocomplete="off" size="2">
									<%=gh.get(i).getSlm() %> </p>
								</td>
								<td class="cart_description">
									<p><%=gh.get(i).getGg()%></p>
								</td>
								<td class="">
									<p><%= (long) gh.get(i).getThanhtien() %> VND</p>
								</td>
								<td class="cart_gg">
									<p><%=dateFormat.format(gh.get(i).getNgaylap()) %></p>
								</td>
								<td class="cart_gg">
								
									<p><%=((gh.get(i).getStatus() == 0 ? "Đang chờ duyệt" :  (gh.get(i).getStatus()  == 1 ? "Chờ vận chuyển" : (gh.get(i).getStatus()  == 2 ? "Đang vận chuyển" : "Đã hoàn thành") ))) %></p>
								</td>
								
							</tr>
								<%} %>
						</tbody>
					</table>
					<!-- String.format("%,.2f", amount) -->
					<p style = "display: inline;">Tổng tiền:  <h4 style="margin-left: 6px;color: #fe980f; display: inline;"><%=request.getAttribute("tongtien") %> VND </h4></p>
					
				<%}else{%>
					<p  style = "width: 100%; font-weight: 600; margin-top: 8px; TEXT-ALIGN: center; font-size: 16px">Không tìm thấy kết quả!</p>
				<%} %>
			</div>
		</div>
		<%} %>
	
	</section> <!--/#cart_items-->
	
</body>
</html>