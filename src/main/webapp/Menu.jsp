<%@page import="bean.GioHangbean"%>
<%@page import="bean.khachhangbean"%>
<%@page import="java.awt.RenderingHints"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.search-btn
    	{
    	    margin-top: 20px;
	   		background: transparent !important;
	  	  	border: 1px solid #fe980f !important;
	   	 	border-radius: 4px;
	   	 	color: #000 !important;
		    width: 100px !important;
		    margin-top: 10px;
		    height: 28px  !important;
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
					<div class="col-sm-4">
						<form class="search_box pull-right" action = "TrangChuController" method = "post" style = "width: 100%">
							<%if(session.getAttribute("empty_main") != null){ %>
								<p  style = "width: 100%; font-weight: 600; margin-top: 8px;">Vui lòng nhập từ khoá để tìm kiếm!</p>
							<%} %>
							<input type="text" name = "key" placeholder="Search" style = "width: 100%"/>
							<input type ="submit" value = "Search" class ="search-btn"/>
						</form>
					</div>
				</div>
			</div>
		</div><!--/header-bottom-->
		<%session.removeAttribute("empty_main"); %>
	</header><!--/header-->
</body>
</html>