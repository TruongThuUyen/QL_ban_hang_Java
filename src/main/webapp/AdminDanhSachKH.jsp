<%@page import="bean.khachhangbean"%>
<%@page import="bean.loaibean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Admin Loại Sản Phẩm</title>

<style type="text/css">
	.btn-add
	{
		padding: 4px 26px !important;
  	 	background: transparent !important;
   		border: 1px solid #000 !important;
   		outline: none !important;
	}
</style>
</head>
<body>
	<!-- Navigation -->
	<%@include file ="AdminMenu.jsp" %>
	
	<div class ="row" align="center" style ="display: flex; justify-content: center;">
		<div class ="col-md-6"  >
			<% ArrayList<khachhangbean> dskh = (ArrayList<khachhangbean>) request.getAttribute("dskh"); %> 
			<table class="table table-bordered">
			<h4 align="center"><b>Danh sách khách hàng</b></h4>
				<thead>
				      <tr>
				     <!--  	<th>STT</th> -->
				        <th>Mã khách hàng</th>
				        <th>Tên khách hàng</th>
				        <th>Địa chỉ</th>
				        <th>Số điện thoại</th>
				        <th>Email</th>
				      </tr>
				   </thead>
			<%
				for(int i = 0; i < dskh.size(); i++)
				{
			%>
				<tr>
					<%-- <td><%=i + 1  %></td> --%>
					<td><%=dskh.get(i).getMakh()%></td>
					<td><%=dskh.get(i).getHoten() %></td>
					<td><%=(dskh.get(i).getDiachi() ==  null ? "Khách hàng thêm nhập thông tin" : dskh.get(i).getDiachi() )%></td>
					<td><%=(dskh.get(i).getSodt() == null ? "Khách hàng thêm nhập thông tin" : dskh.get(i).getSodt()) %></td>
					<td><%=dskh.get(i).getEmail() %></td>
				</tr>
			<%} %>
			</table>
		</div>
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
			let url = "http://localhost:8080/BT/AdminLoaiSPController" + `?ktim=${ktim}&page=\${pageNumber}&ps=<%=request.getAttribute("pageSize")%> `;
			console.log(url);
			if (pageNumber === '...') $('#pagination').append(`<li class="page-item disabled"><a class="page-link" href="#">\${pageNumber}</a></li>`)
			else if (pageNumber === ${pageSelected}) $('#pagination').append(`<li class="page-item active"><a class="page-link" href=\${url}>\${pageNumber}</a></li>`)
			else 
				$('#pagination').append(`<li class="page-item"><a class="page-link" href=\${url}>\${pageNumber}</a></li>`)
		}
	});
	</script>
</body>
</html>

