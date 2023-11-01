<%@page import="bean.loaibean"%>
<%@page import="bean.khachhangbean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bo.loaispbo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>

<html>
<head>
    <meta name="viewport" content="width=device-width" />
    <title> </title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  

<style type="text/css">
	a:hover{
	    text-decoration: none;
	}
	
	.nav-2 {
		position: fixed;
	    top: 0;
	    left: 0;
	    right: 0;
	    z-index: 100;
	    background: #f8f8f8;
	}
</style>

</head>
<body style = "padding-top: 0;">
	<%String admin = (String) session.getAttribute("admindn"); %>
	<!-- Navigation -->
	<nav class="navbar navbar-default">
		<div class="container-fluid nav-2">
			<ul class="nav navbar-nav">
				<!-- <li class=""><a href="AdminController">Trang chủ</a></li> -->
				<li class=""><a href="<%=(admin == null ? "AdminDNController": "AdminLoaiSPController")%>">Quản lý loại</a></li>
				<li class=""><a href="<%=(admin == null ? "AdminDNController": "AdminSanPhamController")%>">Quản lý SP</a></li>
				<li class  =""><a href="<%=(admin == null ? "AdminDNController": "AdminXacNhanController")%>">Xác nhận HD</a></li>
				<li><a href="<%=(admin == null ? "AdminDNController": "AdminDSMHController")%>"> Danh sách mua hàng</a></li>
				<li><a href="<%=(admin == null ? "AdminDNController": "DanhSachKHController")%>"> Danh sách KH</a></li>
			</ul>

			<ul class="nav navbar-nav pull-right">
				
					<li style="margin-right: 14px;"><a href="#" class="nav-item nav-link "> Xin chào: <%=admin %> </a></li>
					<li style="margin-right: 14px;"><a href="AdminDNController?mode=dx" class="nav-item nav-link">Đăng xuất</a></li>				
			</ul>
		
		</div>
	</nav>
</body>
</html>