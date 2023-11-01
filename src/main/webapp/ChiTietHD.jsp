<%@page import="bean.GioHangbean"%>
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
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
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
    		
    		 font-size: 13px;
    	}
    	
    	.input-add{
    	    width: 300px;
    		padding: 4px 10px;
    	}
    	.btn-submit
    	{
    		margin: 0 20px;
		    background: transparent;
		    outline: none;
		    border: 1px solid #ccc;
		    border-radius: 4px;
		    font-size: 12px;
		    padding: 4px 12px;
    	}
    
    	.cancel
    	{
    		display: flex;
		    align-items: center;
		    justify-content: center;
		    margin: 0 10px;
		    border: 1px solid #ccc;
		    height: 30px;
		    padding: 0px 17px;
		    font-size: 13px;
		    color: #000;
    	}
    </style>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>

</head>
<body>

	<%@include file = "Menu.jsp" %>
	

	<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
				  <li><a href="TrangChuController">Home</a></li>
				  <li class="active">Shopping Cart</li>
				</ol>
			</div>
			
			<p style = "width: 100%; text-align: center; font-size: 20px">Chi tiết đặt hàng</p>	
			
		
			<h4>Thông tin đặt hàng</h4>
			<div class ="address2" >
			<%
				if(session.getAttribute("dn2") != null){
				khachhangbean kh = (khachhangbean) session.getAttribute("dn2");	
			%>
				<div class ="user-info"  style ="margin-bottom: 20px;">
					<% long ma = (long) session.getAttribute("mathd2"); 
					
					%>
					<p>Hoá đơn số: <b><%=ma %></b> </p>
					Khách hàng: <p style = "display: inline; margin: 0 0px 10px 6px;"><b><%=kh.getHoten() %></b> </p> 
					<br>
					<br>
					<%if(request.getAttribute("show2") != null){ %>
						<form action = "UpdateTTController" method = "post" style = "margin-top: 14px;">
							Số điện thoại: <input  name = "phone" class  = "input-add" 
							type = "text" placeholder="Nhập số điện thoại" 
							value = "<%=(kh.getSodt() == null ? "" : kh.getSodt()) %>"
							style ="width: 160px; margin-right: 6px">
							<input type ="submit" value ="Lưu số điện thoại"  class ="btn-submit" /> 
						</form>
					
					<%}else if(kh.getSodt() != null){%>
						Số điện thoại: 
							<p style = "display: inline; margin:0 6px;"> <b><%=kh.getSodt() %></b></p>
							<a href = "UpdateTTController?doisdt=1">Thay đổi số điện thoại</a>
					<%}else{ %>
						<a href = "UpdateTTController?doisdt=1"> Thêm số điện thoại</a>
					<%} %>
				</div>
				<!-- Khi co email nhung chua co sdt  -->
				<%
					if (request.getAttribute("sdtnull") != null) {
					%>
					<p>
						<b><%=request.getAttribute("sdtnull") %></b>
					</p>
					<%
					}
				%>
				
				<%
					if (request.getAttribute("sdttrong") != null) {
					%>
					<p>
						<b><%=request.getAttribute("sdttrong") %></b>
					</p>
					<%
					}
				%>
			<%} %> 
				
				<%
					if(session.getAttribute("dn2") != null){
						khachhangbean kh = (khachhangbean) session.getAttribute("dn2");	
				%>
				
				
				
				<%if(request.getAttribute("show") != null){ %>
				<form class ="selecte-multi" action = "UpdateTTController" method ="post">
	
					<select class="form-select form-select-sm mb-3 f-select" id="city" aria-label=".form-select-sm" name = "tp">
						<option value="" selected>Chọn tỉnh thành</option>           
					</select>
					          
					<select class="form-select form-select-sm mb-3 f-select" id="district" aria-label=".form-select-sm" name = "huyen">
					<option value="" selected>Chọn quận huyện</option>
					</select>
					
					<select class="form-select form-select-sm mb-3 f-select" id="ward" aria-label=".form-select-sm" name ="phuong">
					<option value="" selected>Chọn phường xã</option>
					</select>
					
					<script>
						const e = document.getElementById("city");
						const giaTri = e.options[e.selectedIndex].text;
						var label = document.getElementById('city')[document.getElementById('city').selectedIndex].innerHTML;
						console.log(label);
					</script>
					<!-- <%=kh.getDiachi() %>  -->
					<input type = "text" name ="address" value = "" placehoder= "Nhập địa chỉ cụ thể" class= "input-add"/>
					<input type = "submit" value = "Lưu địa chỉ" class = "cancel" style = "background: transparent;" />
					<a href = "UpdateTTController?mode=cancel" class ="cancel" >Huỷ</a> 
 				</form>
					<%
					if (request.getAttribute("loidc") != null) {
					%>
					<p>
						<b>Vui lòng thêm địa chỉ</b>
					</p>
					<%
					}
					%>
					
				<%}else if(kh.getDiachi() != null){ %>
 					Địa chỉ:		
 						<p style = "margin: 20px 10px; display: inline"><%=kh.getDiachi() %></p>
 						<a href = "UpdateTTController?doidc=1">Thay đổi địa chỉ</a>
 				
 				<%}else if(kh.getDiachi() == null){%>
 					<a href = "UpdateTTController?doidc=1">Thêm địa chỉ</a>
				<%} }%>
				
					<%
					if (request.getAttribute("emailnull") != null) {
					%>
					<p>
						<b><%=request.getAttribute("emailnull") %></b>
					</p>
					<%
					}
					%>
				
 					<%
					if (request.getAttribute("loinull") != null) {
					%>
					<p style = "text-align: center;">
						<b><%=request.getAttribute("loinull") %></b>
					</p>
					<%
					}
					%>
 				   <script>
						var citis = document.getElementById("city");
						var districts = document.getElementById("district");
						var wards = document.getElementById("ward");
						var Parameter = {
						  url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json", 
						  method: "GET", 
						  responseType: "application/json", 
						};
						var promise = axios(Parameter);
						promise.then(function (result) {
						  renderCity(result.data);
						});
					
							function renderCity(data) {
							  for (const x of data) {
							    citis.options[citis.options.length] = new Option(x.Name, x.Id);
							  }
							  citis.onchange = function () {
							    district.length = 1;
							    ward.length = 1;
							    if(this.value != ""){
							      const result = data.filter(n => n.Id === this.value);
							
							      for (const k of result[0].Districts) {
							        district.options[district.options.length] = new Option(k.Name, k.Id);
							      }
							    }
							  };
							  district.onchange = function () {
							    ward.length = 1;
							    const dataCity = data.filter((n) => n.Id === citis.value);
							    if (this.value != "") {
							      const dataWards = dataCity[0].Districts.filter(n => n.Id === this.value)[0].Wards;
							
							      for (const w of dataWards) {
							        wards.options[wards.options.length] = new Option(w.Name, w.Id);
							      }
							    }
							  };
							}
						</script>   
				
			
			</div>
			<div class="table-responsive cart_info" style ="margin-top: 20px;">
					<table class="table table-condensed">
						<thead >
							<tr class="cart_menu" > 
								<td class="image" style = "background-color: #fe980f; color: #fff">Image</td>
								<td class="item" style = "background-color: #fe980f; color: #fff;">Item</td>
								<td class="quantity" style = "background-color: #fe980f;  color: #fff;">Quantity</td>
								<td class="price" style = "background-color: #fe980f; color: #fff;">Price</td>
								<td class="discount" style = "background-color: #fe980f; color: #fff;">Discount</td>
								<td class="total" style = "background-color: #fe980f;  color: #fff;">Total</td>
								<td></td>
							</tr>
						</thead>
						<tbody>
						<%
							long sh = 0;
							if(session.getAttribute("dsdh2") != null){
								ArrayList<GioHangbean> gh = (ArrayList<GioHangbean>) session.getAttribute("dsdh2"); 
								sh = gh.size();
								for(int i = 0; i < sh; i++)
								{
						%>
						
							<tr>
								<td class="cart_product" style = "width: 160px; margin-left: 0; margin-right: 20px;">
									<a href=""><img src="<%=gh.get(i).getAnh() %>" alt="" style = "width: 100%; height: 210px; object-fit: cover;"></a>
								</td>
								<td class="cart_description">
									<h4><a href=""><%=gh.get(i).getTensp() %></a></h4>
								</td>
								<td class="cart_quantity" >
									<p  class="cart_quantity_input quantity"  type="number" name="quantity" autocomplete="off" size="2">
									<%=gh.get(i).getSl() %> </p>
								</td>
								<td class="cart_price">
									<p><%=gh.get(i).getGia() %> VND</p>
								</td>
								<td class="cart_gg">
									<p><%=gh.get(i).getGg() %></p>
								</td>
								<td class="cart_total">
									<p class="cart_total_price" style = "font-size: 16px;"><%=(long)gh.get(i).getThanhtien() %> VND</p>
								</td>
							</tr>
								<%} %>
						</tbody>
					</table>
						
				<%} %>
				
				<%if(sh > 0){%>
						<div  style  ="margin:20px 0 20px 20px;" >
						Tổng tiền thanh toán:
						<% float tt = (float) session.getAttribute("tt2");  %>
							<p style = "display: inline ;color: #fe980f; font-weight: 600; font-size: 20px"><%= (long) tt%> VND</p>  
						</div>
				<%} %>
		</div>
			<div class ="" style = "margin-bottom: 20px;">
				<a href = "#" class ="btn-xh"
			        class = "btn-comback" style = "width: 180px; margin: 20px 0; font-size: 12px; background-color: transparent; 
				    text-align: center;border: 1px solid #ccc; border-radius: 4px;padding: 6px 8px;"
				    data-toggle="modal" data-target="#myModal">Phương thức thanh toán
			    </a> 
			    
			   <div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">
					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Thanh toán</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						
						<div class="modal-body">
							<div class="container mt-3">
								<h5>Chọn phương thức thanh toán</h5>
								<p></p>
								<% 
									if(session.getAttribute("mathd2") != null){ 
										long ma = (long) session.getAttribute("mathd2");
								%> 
								<form action="CTHDController?ma=<%=ma %>" method="post">
									<div class="form-check">
										<input type="radio" class="form-check-input" id="radio1"
											name="optradio" value="option1" checked> <label
											class="form-check-label" for="radio1">Thanh toán tại
											ngân hàng</label>
									</div>
									<div class="form-check">
										<input type="radio" class="form-check-input" id="radio2"
											name="optradio" value="option2"> 
											<label class="form-check-label" for="radio2">Thẻ ngân hàng</label>
									</div>
									<div class="form-check">
										<input type="radio" class="form-check-input" name="optradio"
											value="option2"> <label class="form-check-label"
											for="radio3s">Ghi nợ tín dụng</label>
									</div>
									<button type="submit" class="btn btn-primary mt-3">Thanh toán</button>
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
	</section> <!--/#cart_items-->
	<%session.removeAttribute("empty_main"); %>
</body>
</html>