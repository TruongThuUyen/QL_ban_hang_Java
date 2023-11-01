<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
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
    <title>Xác nhận hoá đơn</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Shop Homepage - Start Bootstrap Template</title>
    <!-- Bootstrap Core CSS -->
    <link href="Content/bootstrap.css" rel="stylesheet" />

    <!-- Custom CSS -->
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
</style>

</head>
<body style = "padding-top: 0;">
	<%
		DateFormat dateFormat = null;
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	%>
<%-- 	<c:>
		DateFormat dateFormat = null;
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");>
	</c:>  --%>
	<%@include file ="AdminMenu.jsp" %>

	
    <!-- Page Content -->
    <div class="container">
    	  <p>Có: <b><%=request.getAttribute("tsl") %></b> mặt hàng trong tổng số  <b><%=request.getAttribute("tongsotrang") %></b> trang</p>
	  	  <p>Mỗi trang gồm: <b><%=request.getAttribute("pageSize") %></b> sản phẩm
        <div class="row">
        <table class="table table-bordered">
        	<thead>
		      <tr>
		        <th>Mã HD</th>
		        <th>Họ Tên</th>
		        <th>Tên sản phẩm</th>
		        <th>Giá</th>
		        <th>Mức giảm</th>
		        <th>Số lượng mua</th>
		        <th>Thành tiền</th>
		        <!-- <th>Trạng thái </th> -->
		      	<th> Ngày mua </th> 
		        <th>Xác nhận</th>
		      </tr>
		    </thead>
        	<c:forEach var="xn" items="${ds}">
        	<tr>
        		<td>${xn.getMahd()}</td>
        		<td>${xn.getHoten()}</td>
        		<td>${xn.getTensp()}</td>
        		<td>${xn.getGia()}</td>
        		<td>${xn.getMucgiam() }</td>
        		<td>${xn.getSoluong()}</td>
        		<td>${xn.getThanhtien() }</td>
        		<%-- <td>${xn.isTrangthai() }</td> --%>
        		<td>${xn.getNgaymua()}</td>
        		<td><a href = "AdminXacNhanController?mact=${xn.getMacthd()}">Xác nhận đơn hàng</a></td>
        	</tr>
        	</c:forEach>
        </table>
       
   		</div>
    </div>
    <!-- /.container -->

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
		for (let pageNumber of pageArray) {
			let url = "http://localhost:8080/BT/AdminXacNhanController" + `?key=<%=request.getAttribute("key2") %>&page=\${pageNumber}&ps=<%=request.getAttribute("pageSize")%> `;
			if (pageNumber === '...') $('#pagination').append(`<li class="page-item disabled"><a class="page-link" href="#">\${pageNumber}</a></li>`)
			else if (pageNumber === ${pageSelected}) $('#pagination').append(`<li class="page-item active"><a class="page-link" href=\${url}>\${pageNumber}</a></li>`)
			else 
				$('#pagination').append(`<li class="page-item"><a class="page-link" href=\${url}>\${pageNumber}</a></li>`)
		}
	});
</script>

	
</body>
</html>