<%@page import="bean.sanphambean"%>
<%@page import="bean.khachhangbean"%>
<%@page import="bean.loaibean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!--   	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> -->
<title>Admin Sản Phẩm</title>
<style type="text/css">
	.btn-add
	{
		padding: 4px 40px !important;
  	 	background: transparent !important;
   		border: 1px solid #000 !important;
   		outline: none !important;
	}
	
	.btn-search
	{
		padding: 4px 20px !important;
  	 	background: transparent !important;
   		outline: none !important;
   		background-color: #000 !important;
   		color: #fff ;
	}
	
	.btn-search:hover
	{
		color: #fff;
		opacity: 0.7;
	}
	
	.nav-2 {
		position: fixed;
	    top: 0;
	    left: 0;
	    right: 0;
	    z-index: 100;
	    background: #f8f8f8;
	}
	.btn-cancel
	{
		padding: 4px 10px !important;
		background: transparent !important;
   		border: 1px solid #000 !important;
   		outline: none !important;;
	}
</style>
</head>
<body>
	<!-- Navigation -->
		<%String admin = (String) session.getAttribute("admindn"); %>
	<!-- Navigation -->
	<nav class="navbar navbar-default">
		<div class="container-fluid nav-2" >
			<ul class="nav navbar-nav">
				<li class=""><a href="<%=(admin == null ? "AdminDNController": "AdminLoaiSPController")%>">Quản lý loại</a></li>
				<li class=""><a href="<%=(admin == null ? "AdminDNController": "AdminSanPhamController")%>">Quản lý SP</a></li>
				<li class  =""><a href="<%=(admin == null ? "AdminDNController": "AdminXacNhanController")%>">Xác nhận HD</a></li>
				<li><a href="<%=(admin == null ? "AdminDNController": "AdminDSMHController")%>"> Danh sách mua hàng</a></li>
				li><a href="<%=(admin == null ? "AdminDNController": "DanhSachKHController")%>"> Danh sách KH</a></li>
				<li style = "    height: 50px;display: flex;">
					 <form action="AdminSanPhamController" method="get" style = "display: flex; align-items: center;margin-left: auto;">
					Tìm kiếm: <input type="text" name="key" placeholder="Nhập mã loại, mã sản phẩm hoặc tên sản phẩm" class="form-control" 
									style = "width: 400px;margin: 0 10px;">
					<input type="submit" value="search" class = "btn btn-search" style = "">
					</form>
				</li>
			</ul>

			<ul class="nav navbar-nav pull-right">
				
					<li style="margin-right: 14px;"><a href="#" class="nav-item nav-link "> Xin chào: <%=admin %> </a></li>
					<li style="margin-right: 14px;"><a href="AdminDNController?mode=dx" class="nav-item nav-link">Đăng xuất</a></li>				
			</ul>
		
		</div>
	</nav>

	<%
		sanphambean spbean = new sanphambean();
		if(request.getAttribute("sanpham") != null)
		{
		 	spbean = (sanphambean) request.getAttribute("sanpham");
		 	
		}
	%>
	
	<div class="container" >
		<%if(request.getAttribute("add") != null){
		%>
			<h4 style= "text-align: center; color:  #fe980f"><%=request.getAttribute("add") %></h4>
		<%} %>
		
		<%if(request.getAttribute("update") != null){
		%>
			<h4 style= "text-align: center; color:  #fe980f"><%=request.getAttribute("update") %></h4>
		<%} %>
		
		<%if(request.getAttribute("delete") != null){
		%>
			<h4 style= "text-align: center; color:  #fe980f"><%=request.getAttribute("delete") %></h4>
		<%} %>
		<div class = "row">
			 <!--Search  -->
		     <div  class = "col-sm-6">
		     
		     </div>
		</div>
		
		
		<% String ma1 = "", ma2 = "", mahd = "";
			int soma = 0;
			if(request.getAttribute("masp") != null){
				ma1 = (String) request.getAttribute("masp");
				ma2 = ma1.substring(0, 2);
				ma1 = ma1.substring(2, ma1.length());
				soma = Integer.parseInt(ma1) + 1;
				mahd = ma2.concat(String.valueOf(soma)) ;
				
		%>
		<%} %>
	  <form class="form-horizontal" method = "post" action="AdminSanPhamController"  enctype= "multipart/form-data">
		<div class ="row">
			     <%if(request.getAttribute("error1") != null){ %>
					<p style = "margin-top: 10px; margin-left: 14px;"><b><%=request.getAttribute("error1")%></b></p>
				<%} %>
			<div class="col-sm-4">
		      	<label class="control-label " for="">Mã sản phẩm:</label>
		        <input type="text" class="form-control" id="email" placeholder="Mã sản phẩm" name="txtmasp" value = "<%=(spbean.getMasp() == null ? mahd : spbean.getMasp())%>">
	      	</div>
	      	
	      	<div class="col-sm-4">
		      	<label class="control-label " for="">Tên sản phẩm:</label>
		      	<%if(request.getParameter("rong") != null)
		      		{
		      			sanphambean sp = (sanphambean) session.getAttribute("spbean");
		      			String getTen = sp.getTensp();
		      	%>
		      		<input type="text" class="form-control" id="email" placeholder="Tên sản phẩm" name="txttensp" 
		       		value = "<%=(sp.getTensp())%>">
		      	<%}else{ %>
		       	<input type="text" class="form-control" id="email" placeholder="Tên sản phẩm" name="txttensp" 
		       		value = "<%=(spbean.getTensp() == null ? "" : spbean.getTensp())%>">
	      		<%} %>
	      	</div>
	      	
	      	<div class="col-sm-4">
		      	<label class="control-label " for="">Giá:</label>
		      	     <%if(request.getParameter("rong") != null)
		      		{
		      			sanphambean sp = (sanphambean) session.getAttribute("spbean");
		      			long getGia = sp.getGia();
		      	%>
		      		<input type="text" class="form-control" id="email" placeholder="Giá" name="txtgia" 
		       		value = "<%=(sp.getGia() <= 0 ? "" : getGia)%>">
		      	<%}else{ %>
		        <input type="text" class="form-control" id="email" placeholder="Giá" name="txtgia" value = "<%=(spbean.getGia() == 0 ? "" : spbean.getGia()) %>">
	      		<%} %>
	      	</div>
	    	
	      	<div class="col-sm-4">
		      	<label class="control-label " for="">Chiết khấu:</label>
		      	    <%if(request.getParameter("rong") != null)
		      		{
		      			sanphambean sp = (sanphambean) session.getAttribute("spbean");
		      			float getGGia = sp.getGg();
		      			
		      	%>
		      		<input type="text" class="form-control" id="email" placeholder="Chiết khấu" name="txtck" 
		       		value = "<%=(sp.getGg() <= 0.0 ? 0 : getGGia)%>">
		      	<%}else{ %>
		        <input type="text" class="form-control" id="" placeholder="Chiết khấu" name="txtck" value = "<%=(spbean.getGg() == 0.0 ? 0 : String.valueOf(spbean.getGg())) %>">
	      		<%} %>
	      	</div>
	      	
	      	<div class="col-sm-4">
		      	<label class="control-label " for="email">Số lượng:</label>
		      	    <%if(request.getParameter("rong") != null)
		      		{
		      			sanphambean sp = (sanphambean) session.getAttribute("spbean");
		      			long getSL = sp.getSl();
		      	%>
		      		<input type="text" class="form-control" id="email" placeholder="Số lượng" name="txtsl" 
		       		value = "<%=(sp.getSl() <= 0 ? "" : getSL)%>">
		      	<%}else{ %>
		        <input type="text" class="form-control" id="" placeholder="Số lượng" name="txtsl" value = "<%=(spbean.getSl() == 0 ? "" : spbean.getSl()) %>">
	      		<%} %>
	      		<%if(request.getAttribute("error2") != null){ %>
					<p style = "margin-top: 10px; font-size: 18px;"><b><%=request.getAttribute("error2")%></b></p>
				<%} %>
	      	</div>
	   	
		    <div class="col-sm-4">          
		     	<label class="control-label " for="pwd">Mã loại:</label>
		      	 <select class="form-control" id= "loaisp" name ="lang" 
			     	 		style = "height: 36px;width: 100% !important;text-align: end;">
			     	 		<option value = "">-- Chọn loại --</option>
			     	 <%	ArrayList<loaibean> dsl = (ArrayList<loaibean>) request.getAttribute("dsloai");
			     	 	for(loaibean loai: dsl)
			     	 	{
			     	 	%>
			     	 		<% if(spbean.getMasp() != null) {
			     	 				if(spbean.getMaloai().equals(loai.getMaloai()) ){ %>
			     	 				<option value = "<%=loai.getMaloai()%>" selected><%=spbean.getMaloai()%></option>
			     	 		<%}else{ %>
			     	 			<option value = "<%=loai.getMaloai()%>"><%=loai.getMaloai()%></option>
			     	 		
			     	 	<%}
			     	 		}else{  %>
			     	 		<option value = "<%=loai.getMaloai()%>"><%=loai.getMaloai()%></option>
			     	 	<%} } %>	
			     </select>
		      
	     	</div> 
	   		<div class= "row">
		     	 <div class="col-sm-6" style = "margin-top: 20px;align-items: center;">     
		     	 		<small style = "display: block;"><i>Click để chọn ảnh</i></small>
		     	 	   	<label for="uploadPhoto" style="cursor:pointer" title="Click để chọn ảnh">
		     	 		<img id="employeeImage" src = "<%=(spbean.getAnh() != null ? spbean.getAnh()  :"images/")%>" alt ="picture"
		     	 			style = "width: 120px; height: 140px; object-fit: cover; margin: 10px 0" />
		     	 		</label> 
		     	 		<input type="file" id="uploadPhoto" name="uploadPhoto" accept="image/*" style="display:none"
	                       onchange="document.getElementById('employeeImage').src = window.URL.createObjectURL(this.files[0])" />
		     	 </div>
		     	
	     	</div>
		   	 <div class="form-group" > 
		   		<div class="col-sm-2" >   </div>
		   		<div class="col-sm-4" >        
		   			<% if(request.getAttribute("updating") != null){%>
		   				 <input type="submit" class="btn btn-add" name ="btnadd" value= "Add" style ="border: 1px solid blue;" disabled>
		   			<%}else{ %>
			     	  <input type="submit" class="btn btn-add" name ="btnadd" value= "Add" style ="border: 1px solid blue;" >
			     	<%} %> 
			     	<%
			     	String tab = request.getParameter("tab");
			     	if(tab != null && tab.equals("select")){
					 %>
			       		<input type="submit" class="btn btn-add" name ="btnupdate" value= "Update">
			       	<%}else if(mahd != null) { %>
			       		 <input type="submit" class="btn btn-add" name ="btnupdate" value= "Update" disabled>
			       <%} %>
			       <input type="submit" class="btn btn-cancel" name ="btncancel" value= "Cancle">
			     </div>
			  </div>
 	   	</div> 
	  </form>
	</div>
	<a class="page-link" href = "AdminLoaiController"></a>
	
	<br>
	<%  ArrayList<sanphambean> ds =  (ArrayList<sanphambean>) request.getAttribute("dssp");
		if(ds.size() > 0){
	%>
	<div class ="row" align="center" style ="display: flex; justify-content: center; margin-top: 20px;">
		<div class ="col-md-10"  >
		<p style = "text-align: start">Có: <b><%=request.getAttribute("tsl") %></b> sản phẩm trong tổng số  <b><%=request.getAttribute("tongsotrang") %></b> trang</p>
	  	<p style = "text-align: start">Mỗi trang gồm: <b><%=request.getAttribute("pageSize") %></b> sản phẩm
			<%if(request.getAttribute("error") != null){ %>
				<p><b><%=request.getAttribute("error")%></b></p>
			<%} %>
			<table class="table table-bordered">
				<thead>
				      <tr>
				    <!--   	<th>STT</th> -->
				        <th>Mã sản phẩm</th>
				        <th>Tên sản phẩm</th>
				        <th>Ảnh</th>
				        <th>Giá</th>
				        <th>Chiết khấu</th>
				        <th>Số lượng</th>
				        <th>Mã loại</th>
				        <th>Select</th>
				        <th>Delete</th>
				      </tr>
				   </thead>
			<%
				int i = 0;
				for(sanphambean sp : ds)
				{
					i = i + 1;
			%>
				<tr>
					<%-- <td><%=i %></td> --%>
					<td><%=sp.getMasp() %></td>
					<td><%=sp.getTensp() %></td>
					<td><image src = "<%=sp.getAnh()%>"  alt = "Ảnh" style = "width: 120px; height: 140px; object-fit: cover;"/> </td>
					<td><%=sp.getGia() %></td>
					<td><%=sp.getGg() %></td>
					<td><%=sp.getSl() %></td>
					<td><%=sp.getMaloai() %></td>
					<td><a href = "AdminSanPhamController?msp=<%=sp.getMasp()%>&tab=select">Select</a></td>
					<td><a href = "AdminSanPhamController?msp=<%=sp.getMasp()%>&tab=delete">Delete</a></td>
				</tr>
			<%} %> 
			
			
			</table>
		</div>
	</div>
	<%}else{ %>
		<div class = "container" style = "text-align: center"><h4>Không tìm thấy sản phẩm!</h4></div>
	<%} %>
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
			let url = "http://localhost:8080/BT/AdminSanPhamController" + `?key=<%=request.getAttribute("key2") %>&page=\${pageNumber}&ps=<%=request.getAttribute("pageSize")%> `;
			if (pageNumber === '...') $('#pagination').append(`<li class="page-item disabled"><a class="page-link" href="#">\${pageNumber}</a></li>`)
			else if (pageNumber === ${pageSelected}) $('#pagination').append(`<li class="page-item active"><a class="page-link" href=\${url}>\${pageNumber}</a></li>`)
			else 
				$('#pagination').append(`<li class="page-item"><a class="page-link" href=\${url}>\${pageNumber}</a></li>`)
		}
	});
	</script>
</body>
</html>

