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
	<% ArrayList<loaibean> ds = (ArrayList<loaibean>) request.getAttribute("dsloai"); 
	  ArrayList<loaibean> dsall = (ArrayList<loaibean>) request.getAttribute("ds"); 
	
	%>
	
	<!-- Navigation -->
	<%@include file ="AdminMenu.jsp" %>

	<%String ml = (String) request.getAttribute("maloai");
	String tl = (String) request.getAttribute("tenloai");
	%>
	<div class="container" >
		<%if(request.getAttribute("add") != null){%>
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
		
		
	  <form class="form-horizontal" action="AdminLoaiSPController">
		    <div class="form-group" style = "margin-bottom: 0">
		      <label class="control-label col-sm-2" for="email">Mã loại:</label>
		      <div class="col-sm-4">
		      <%
		      	if(request.getAttribute("ma2") != null){
		      		String getMa = (String) request.getAttribute("ma2");
		    	  %>
		    	   <input type="text" class="form-control" id="email" placeholder="Mã loại" name="txtmaloai" value = "<%=(getMa)%>">
		      <%}else{%>
		        <input type="text" class="form-control" id="email" placeholder="Mã loại" name="txtmaloai" value = "<%=(ml == null ? "" : ml)%>">
		        <%} %>
		      </div>

				<div class="form-group">
					<label class="control-label " for="pwd">Loại</label>
					<script type="text/javascript">
						function getval(sel)
						{
						    const ma = sel.value;
						   	location.replace("AdminLoaiSPController?ma_select=" + ma); 
						}
					</script>
					<div class="col-sm-4">
						<select onchange="getval(this);" id="test" class = "form-group"  name ="lang" 
					     	 		style = "height: 36px;width: 100% !important;text-align: end; margin-bottom: 0;     border-radius: 4px">
							<option value="">-- Loại --</option>
							<%
							for (int i = 0; i < dsall.size(); i++) {
							%>
								<%if(request.getAttribute("loai_select") != null){
									String ml2 = (String) request.getAttribute("loai_select");
										if(dsall.get(i).getMaloai().equals(ml2)){
									%>
									<option value = "<%=dsall.get(i).getMaloai()%>" selected><%=dsall.get(i).getMaloai()%></option>
								<%} } %>
								<option value="<%=dsall.get(i).getMaloai()%>"><%=dsall.get(i).getMaloai()%></option>
							<%}%>	
						</select>
					</div>
				</div>
			</div>
		    <div class="form-group">
		      <label class="control-label col-sm-2" for="pwd">Tên loại:</label>
		      <div class="col-sm-4">   
		      	<% if(request.getAttribute("tenl2") != null){
		      		String getTen = (String) request.getAttribute("tenl2");
		    	  %>
		    	   <input type="text" class="form-control" id="email" placeholder="Mã loại" name="txttenloai" value = "<%=(getTen)%>">
		      <%}else{%>       
		       	 <input type="text" class="form-control" id="pwd" placeholder="Tên loại" name="txttenloai" value = "<%=(tl == null ? "" : tl)%>">
		      <%} %>
		      	<%if(request.getAttribute("error2") != null){ %>
					<p style = "margin-top: 10px;"><%=request.getAttribute("error2")%></p>
				<%} %>
		      </div>
		    </div>
	    
			
		    <div class="form-group"> 
		    	<div class = "col-sm-2"></div>
		   		<div class="col-sm-4">        
		   			<%if(request.getParameter("tab") != null){
		   				String tab = request.getParameter("tab");
		   				if(tab.equals("select")){
		   			%>
		   				<input type="submit" class="btn btn-add" name ="btnadd" value= "Add" disabled>
		   				 <input type="submit" class="btn btn-add" name ="btnupdate" value= "Update" >
		   			<%} 
		   			}else{ %>
			     	  <input type="submit" class="btn btn-add" name ="btnadd" value= "Add" >
			     	<input type="submit" class="btn btn-add" name ="btnupdate" value= "Update" disabled>
					<%} %>
			      </div>
			 </div>
	  </form>
	</div>
	
	<div class ="row" align="center" style ="display: flex; justify-content: center;">
		<div class ="col-md-6"  >
		  <p style = "text-align: start;">Có: <b><%=request.getAttribute("tsl") %></b> loại sản phẩm trong tổng số  <b><%=request.getAttribute("tongsotrang") %></b> trang</p>
	  	  <%-- <p style = "text-align: start;">Mỗi trang gồm: <b><%=request.getAttribute("pageSize") %></b> loại sản phẩm --%>
			<%if(request.getAttribute("error") != null){ %>
				<p><%=request.getAttribute("error")%></p>
			<%} %>
			<table class="table table-bordered">
				<thead>
				      <tr>
				     <!--  	<th>STT</th> -->
				        <th>Mã loại</th>
				        <th>Tên loại</th>
				        <th>Select</th>
				        <th>Delete</th>
				      </tr>
				   </thead>
			<%
				for(int i = 0; i < ds.size(); i++)
				{
			%>
				<tr>
					<%-- <td><%=i + 1  %></td> --%>
					<td><%=ds.get(i).getMaloai() %></td>
					<td><%=ds.get(i).getTenloai() %></td>
					<td><a href = "AdminLoaiSPController?ml=<%=ds.get(i).getMaloai()%>&tab=select">Select</a></td>
					<td><a href = "AdminLoaiSPController?ml=<%=ds.get(i).getMaloai()%>&tab=delete">Delete</a></td>
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

