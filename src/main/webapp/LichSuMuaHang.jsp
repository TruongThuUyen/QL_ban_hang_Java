<%@page import="bean.hoadonbean"%>
<%@page import="bean.GioHangbean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="bean.lichsubean"%>
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
    <title>Lịch sử mua hàng</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
	<link href="css/main.css" rel="stylesheet">
	<link href="css/responsive.css" rel="stylesheet">
    
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
								<li><a href = "<%= ((session.getAttribute("dn2") == null) ? "DangNhapController" : "LSMHController")%>"><i class="fa fa-user"></i> History</a></li>
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
				<ol class="breadcrumb col-sm-6" >
				  <li><a href="TrangChuController">Home</a></li>
				  <li class="active">Shopping Cart</li>
				</ol>
				<!-- Search -->
				<div class="col-sm-6">
						<form class="search_box pull-right" action = "LSMHController?mode=<%=(request.getParameter("mode") != null ? request.getParameter("mode") : "1") %>"
							 method = "post" style = "width: 100%">
							<input type="text" name = "tkls" placeholder="Nhập ngày để tìm kiếm" 
								style = "width: 100%;background: transparent;color: #000; border: 1px solid #ccc;"/>
							<%if(request.getParameter("empty") != null){ %>
							<p  style = "width: 100%; font-weight: 600; margin-top: 8px;">Vui lòng nhập từ khoá để tìm kiếm!</p>
							<%} %>
							<input type ="submit" class ="search-btn"/>
						</form>
				</div>
			</div>
			
			<p style = "width: 100%; text-align: center; font-size: 20px">Lịch sử mua hàng</p>	
			
			<div class ="address">
			
				<%if(session.getAttribute("dn2") != null){
					khachhangbean kh = (khachhangbean) session.getAttribute("dn2");	
				%>
				
				<div class ="user-info" style = "margin: 20px 0;">
					Khách hàng: <b><%=kh.getHoten() %></b>
				</div>
				<%} %>
		
			</div>
			
			<nav class="navbar navbar-inverse"style = "background-color: #fe980f; color: #fff; border: none">
					<div class="container-fluid">
						<ul  class="nav navbar-nav" >
							<!-- <li><a href="LichSuMHController?mode=1" style = "color: #fff">Đơn hàng chưa thanh toán</a></li> -->
							<li><a href="LSMHController?mode=1" style = "color: #fff">Đơn hàng đang chờ duyệt</a></li>
							<li><a href="LSMHController?mode=2" style = "color: #fff">Đơn hàng đang chờ vận chuyển</a></li>
							<li><a href="LSMHController?mode=3" style = "color: #fff">Đơn hàng đang vận chuyển</a></li>
							<li><a href="LSMHController?mode=4" style = "color: #fff">Đơn hàng đã hoàn thành</a></li>
						</ul>
						
					</div>
			</nav>

			<%if(request.getParameter("mode") != null){
				String mode = request.getParameter("mode");
				if(mode.equals("2")){
			%>
				<h4 align="center" style = "width: 100%;">Đơn hàng đang chờ vận chuyển</h4>
				<%}else if(mode.equals("1")){ %>
					<h4 align="center" style = "width: 100%;">Đơn hàng đang chờ duyệt</h4>
				<%}else if(mode.equals("3")){%>
					<h4 align="center" style = "width: 100%;">Đơn hàng đang vận chuyển</h4>
				<%}else{ %>
					<h4 align="center" style = "width: 100%;">Đơn hàng đã hoàn thành</h4>
				<%} %>
				
			<%}else{%>
				<h4 align="center" style = "width: 100%;">Đơn hàng đang chờ duyệt</h4>
			<%} %>
			
						<%
			int sh = 0;
			if (request.getAttribute("dshd") != null) {
				ArrayList<hoadonbean> gh = (ArrayList<hoadonbean>) request.getAttribute("dshd");
				sh = gh.size();
				DateFormat dateFormat = null;
	   			dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				if(sh > 0){
			%>
			<div class="table-responsive cart_info" style ="margin-top: 20px;">
				<table class="table table-condensed">
						<thead >
							<tr class="cart_menu" > 
								<td class="image" style = "background-color: #fe980f; color: #fff">Code</td>
								<td class="quantity" style = "background-color: #fe980f;  color: #fff;">Quantity</td>
								<td class="price" style = "background-color: #fe980f; color: #fff;">Total</td>
								<td class="discount" style = "background-color: #fe980f; color: #fff;">Date</td>
								<td class="total" style = "background-color: #fe980f;  color: #fff;">Status</td>
								<td>Show Details</td>
					<!-- 			<td>Pay</td> -->
							</tr>
						</thead>
						<tbody>
		
						<% for (int i = sh-1 ; i >=0 ; i--) { %>
							<tr>
								
								<td class="cart_description">
									<p><%=gh.get(i).getMahd() %></p>
								</td>
								<td class="cart_quantity" >
									<p  class="cart_quantity_input quantity"  type="number" name="quantity" autocomplete="off" size="2">
									<%=gh.get(i).getSl() %> </p>
								</td>
								<td class="cart_pricee">
									<p><%= (long) gh.get(i).getTongtien()%> VND</p>
								</td>
								<td class="cart_gg">
									<p><%= dateFormat.format(gh.get(i).getNgaymua())%></p>
								</td>
								<td class="cart_gg">
									<p><%=(gh.get(i).isDamua() == 0 ? "Đang chờ duyệt" :  (gh.get(i).isDamua() == 1 ? "Chờ vận chuyển" : (gh.get(i).isDamua() == 2 ? "Đang vận chuyển" : "Đã hoàn thành") )) %></p>
								</td>
								<td>
									<a style = "display: block; color: #000;" href ="CTLSController?mahd=<%=gh.get(i).getMahd() %>&modeht=<%=request.getParameter("mode") %>">Xem chi tiết</a>
								</td>
								<%-- <td><a href = "CTHDController?mahd=<%=gh.get(i).getMahd()%>" style = "display: block; color: #000;" >Đi đến thanh toán</a></td> --%>
							</tr>
								<%} %>
						</tbody>
					</table>
				<%}else{ %>
					<p style = "margin: 30px; text-align: center;"><b>Không có đơn hàng nào!</b></p>
				<%} %>
			</div>
		</div>
		<%} %>
		<%session.removeAttribute("empty_main"); %>
	</section> <!--/#cart_items-->

		<div class="text-center">
			<ul class="pagination" id="pagination">
			</ul>
		</div>
	    <div class = "container" style = "display: flex; justify-content: center">
		<div class="card-footer clearfix">
			<ul class="pagination m-0 float-right" id="pagination">

			</ul>
		</div> 
	</div>
    <%@include file ="Pagination.jsp" %>
	
	<script>
	$(function () {
    // Pagination
    const pageArray = pagination(<%=request.getAttribute("pageSelected")%> ,<%=request.getAttribute("tongsotrang") %>);
		console.log(pageArray);	
    for (let pageNumber of pageArray) {
			let url = "http://localhost:8080/BT/LSMHController" + `?ktim=${ktim}&mode=<%=request.getAttribute("mode")%>&page=\${pageNumber}&ps=<%=request.getAttribute("pageSize")%> `;
			
			if (pageNumber === '...') $('#pagination').append(`<li class="page-item disabled"><a class="page-link" href="#">\${pageNumber}</a></li>`)
			else if (pageNumber === ${pageSelected}) $('#pagination').append(`<li class="page-item active"><a class="page-link" href=\${url}>\${pageNumber}</a></li>`)
			else 
				$('#pagination').append(`<li class="page-item"><a class="page-link" href=\${url}>\${pageNumber}</a></li>`)
		}
	});
	</script>
</body>
</html>