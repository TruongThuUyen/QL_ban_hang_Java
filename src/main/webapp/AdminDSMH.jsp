<%@page import="bean.khachhangbean"%>
<%@page import="bean.hoadonbean"%>
<%@page import="bean.admindsmhbean"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<link>
    <meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Danh sách mua hàng</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
.btn-a {
	display: block;
	color: #000;
	padding: 4px 10px;
	outline: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 12px;
	text-decoration: none;
	width: 140px;
	position: absolute;
	text-align: center;
	left: 5%;
}

.nav-2 {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	z-index: 100;
	background: #f8f8f8;
}

.wrap-1
{
	display: flex;
    justify-content: space-between;
}

.btn-1{
    border-right: 1px solid #ccc !important;
    color: #000 !important;
        text-align: center;
    width: 25%;
        height: 40px;
    line-height: 40px;
}

.btn-2:hover , .btn-1:hover
{
	text-decoration: none;
	cursor: pointer;
	background: #f8f8f8;
}
.btn-2
{
	/* border-left: 1px solid #ccc !important; */
    color: #000 !important;
    text-align: center;
    width: 25%;
    height: 40px;
    line-height: 40px;
}

.go-xn
{
    display: block;
    margin-bottom: 10px;
    color: #000;
    /* font-size: 14px; */
    font-weight: 600;
}
</style>
</head>
<body>

	<%
		int n = 0;
		DateFormat dateFormat = null;
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	%>
	
	 <% 
	 	String mode = "";
	 	if(request.getParameter("mode") != null){
	    mode = request.getParameter("mode");
	 }
	%>
		
	<!-- Navigation -->
	<%String admin = (String) session.getAttribute("admindn"); %>
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
	

    <div class="root" style = "width: 100%; height: 100%; align-items: unset;position: relative; padding: 0 20px;">
    		<%if(request.getAttribute("dsmh") != null){ 
    			ArrayList<admindsmhbean> dsmh =  (ArrayList<admindsmhbean>) request.getAttribute("dsmh");
    			int size = dsmh.size();
    			if(dsmh.size() ==0){
    			
    		%>
    			
    			<p><b>Chưa có đơn hàng nào!</b></p>
    		<%}else {%>
    		<div class = "container wrap-1">
    	<!-- 		<a href  ="AdminDSMHController?mode=1" class = "btn-1">Chưa xác nhận</a> -->
    			<a href  ="AdminDSMHController?mode=2" class = "btn-1">Chờ vận chuyển</a>
    			<a href  ="AdminDSMHController?mode=3" class = "btn-1">Đang giao</a>
    			<a href  ="AdminDSMHController?mode=4" class = "btn-2">Đã hoàn thành</a>
    		</div>	
	  	    <div class="contain" style = "margin: auto; margin-top: 30px;width: 80%;">
	  	    <p>Có: <b><%=request.getAttribute("tsl") %></b> đơn đặt hàng trong tổng số  <b><%=request.getAttribute("tongsotrang") %></b> trang</p>
	  	    <p>Mỗi trang gồm: <b><%=request.getAttribute("pageSize") %></b> đơn hàng
	            <table style = "font-size: 14px;" class="table table-bordered mt-20">
	            	<%if(request.getParameter("mode") != null)
	            		{
	            		String mode2 = request.getParameter("mode");
	            			if(mode2.equals("1")){
	            		%>
	            				<p align="center" style = "font-size: 16px"> <b>Đơn hàng chưa xác nhận</b></p>	
	            				<a href = "AdminXacNhanController" class ="go-xn">Đi đến xác nhận</a>
	            			<%}else if(mode2.equals("2")){ %>
	            				<p align="center" style = "font-size: 16px"> <b>Đơn hàng đã xác nhận(Chờ vận chuyển)</b></p>	
	            			<%} %>
	            	<%}else{ %>
	               		<p align="center" style = "font-size: 16px"> <b>Đơn hàng chưa xác nhận</b></p>	
	               		<a href = "AdminXacNhanController" class = "go-xn">Đi đến xác nhận</a>
	               	<%} %>
	               	
	               	<!-- Danh sach theo tung mode  -->
	              <%--  	<%if(mode.equals("") || mode.equals("1")){ %>
		                <tr>
		                    <th>Mã đơn</th>
		                    <th>Tên sản phẩm</th>
		                    <th>Tên khách hàng</th>
		                    <th>Giá</th>
		                    <th>Mức giảm</th>
		                    <th>Số lượng mua</th>
		                    <th>Thành tiền</th>
		                    <th>Ngày đặt</th>
		                    <th>Trạng thái</th>
		                  
		                </tr>
		            
		                
		         		<%for(int i = 0; i < size; i++){ %>
		                <tr>
		                    <td><%=(size -i)%></td>
		                    <td><%=dsmh.get(i).getMahd() %></td>
		                    <td><%=dsmh.get(i).getTensp() %></td>
		                    <td><%=dsmh.get(i).getTenkh() %></td>
		                    <td><%=dsmh.get(i).getGia() %> nghàn đồng</td>
		                    <td><%=dsmh.get(i).getCk() %></td>
		                    <td><%=dsmh.get(i).getSlm() %></td>
		                    <td><%=dsmh.get(i).getTongtien() %> nghàn đồng</td>
		                    <td><%=dateFormat.format(dsmh.get(i).getNgaymua()) %></td>
		                    <td><%=(dsmh.get(i).isStatus() == 0 ? "Chờ duyệt" : (dsmh.get(i).isStatus() == 1 ? "Chờ vận chuyển": (dsmh.get(i).isStatus() == 2 ? "Đang vận chuyển" : "Đơn hàng thành công")) ) %></td> 
		          
		                </tr>
		                <%} %>
		             <%}else if(mode.equals("2") || mode.equals("3") || mode.equals("4")){ %> --%>
		             <%if(mode.equals("2") || mode.equals("3") || mode.equals("4") || mode.equals("")){ %>
		             	<tr>
		                    <th>Mã đơn</th>
		                    <th>Mã khách hàng</th>
		                    <th>Số lượng mua</th>
		                    <th>Tổng tiền</th>
		                    <th>Ngày đặt</th>
		                    <th>Trạng thái</th>
		                   <th></th>
		                </tr>
		                <%ArrayList<hoadonbean> dshd = (ArrayList<hoadonbean>) request.getAttribute("dshd"); 
		                	int size2 = dshd.size();
		                	if(size2 > 0){
		                %>
			                <%for(int i = 0; i < size2; i++){ %>
			                <tr>
			                    <%-- <td><%=(size -i)%></td> --%>
			                    <td><%=dshd.get(i).getMahd() %></td>
			                 <%--    <%
			                    long tam = dshd.get(i).getMakh();
			                    ArrayList<khachhangbean> dskh = (ArrayList<khachhangbean>) request.getAttribute("dsachkh");
			                    for(khachhangbean kh2: dskh){
			        					if(kh2.getMakh() == tam)
			        					{
			                    	%>
			                    		<td><%=kh2.getHoten() %></td>
			                    <%} }%>
			                     --%>
			                    <td><%=dshd.get(i).getMakh() %></td>
			                    <td><%=dshd.get(i).getSl() %></td>
			                    <td><%=dshd.get(i).getTongtien() %> nghàn đồng</td>
			                    <td><%=dateFormat.format(dshd.get(i).getNgaymua()) %></td>
			                    <td><%=(dshd.get(i).isDamua() == 0 ? "Chờ duyệt" : (dshd.get(i).isDamua() == 1 ? "Chờ vận chuyển": (dshd.get(i).isDamua() == 2 ? "Đang vận chuyển" : "Đơn hàng thành công")) ) %></td> 
			          			<%if(mode.equals("2") || mode.equals("")){ %>
				          			<td><a href = "AdminDSMHController?tab=2&mahd=<%=dshd.get(i).getMahd()%>">Tiến hành vận chuyển</a></td>
			          			<%}else if(mode.equals("3")){ %>
			          				<td><a href = "AdminDSMHController?tab=3&mahd=<%=dshd.get(i).getMahd()%>">Đánh dấu hoàn thành</a></td>
			          			<%}else if(mode.equals("4")){ %>
			          				<td><a href = "AdminDSMHController?tab=4&mahd=<%=dshd.get(i).getMahd()%>">Đơn hàng đã hoàn thành</a></td>
			          			<%} %>
			          			
			                </tr>
			                <%} %>
			              <%} %>
		             <%} %>
	            </table>
        </div>
        <%} } %>
        
        <!-- Modal  -->
        	   <div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">
					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Vận chuyển đơn hàng</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						
						<div class="modal-body">
							<div class="container mt-3">
								<h5>Chọn đơn vị vận chuyển</h5>
								<p></p>
								<% 
									if(request.getAttribute("mahd") != null){ 
										long ma = (long)request.getAttribute("mahd"); 
								%>  
								<%=ma %>
								<form action="AdminDSMHController?ma=" method="post">
									<div class="form-check">
										<input type="checkbox" class="form-check-input" id="radio1"
											name="optradio" value="option1" > <label
											class="form-check-label" for="radio1">Giao hàng tiết kiệm</label>
									</div>
									<!-- <div class="form-check">
										<input type="radio" class="form-check-input" id="radio2"
											name="optradio" value="option2"> 
											<label class="form-check-label" for="radio2">Thẻ ngân hàng</label>
									</div> -->
									<!-- <div class="form-check">
										<input type="radio" class="form-check-input" name="optradio"
											value="option2"> <label class="form-check-label"
											for="radio3s">Ghi nợ tín dụng</label>
									</div> -->
									<button type="submit" class="btn btn-primary mt-3">Hoàn tất</button>
								</form>
						 	<%} %>  
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
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
			let url = "http://localhost:8080/BT/AdminDSMHController" + `?ktim=${ktim}&mode=<%=request.getAttribute("mode")%>&page=\${pageNumber}&ps=<%=request.getAttribute("pageSize")%> `;
	
			if (pageNumber === '...') $('#pagination').append(`<li class="page-item disabled"><a class="page-link" href="#">\${pageNumber}</a></li>`)
			else if (pageNumber === ${pageSelected}) $('#pagination').append(`<li class="page-item active"><a class="page-link" href=\${url}>\${pageNumber}</a></li>`)
			else 
				$('#pagination').append(`<li class="page-item"><a class="page-link" href=\${url}>\${pageNumber}</a></li>`)
		}
	});
	</script>
</body>
</html>