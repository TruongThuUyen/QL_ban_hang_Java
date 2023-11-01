<%@page import="bean.sanphambean"%>
<%@page import="bean.GioHangbean"%>
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
    </style>
</head>
<body>
	<%
		ArrayList<sanphambean> dstam = (ArrayList<sanphambean>) session.getAttribute("dstam"); 
		long sl = 0;
		long test = -1;
		
	%>
	<%@include file = "Menu.jsp" %>
	
	<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
				  <li><a href="TrangChuController">Home</a></li>
				  <li class="active">Shopping Cart</li>
				</ol>
			</div>
			<%
			if(session.getAttribute("errordl") != null){
			%>
				<b style = "margin: 20px 0;text-align: center">Vui lòng chọn mặt hàng muốn xoá</b>
			<%} %>
			<div class="table-responsive cart_info" style ="margin-top: 20px;">
				<form method = 'post' action= "BoChonController">
					<% long sh = 0;
					 	if(session.getAttribute("dsg2") != null){
							ArrayList<GioHangbean> gh = (ArrayList<GioHangbean>) session.getAttribute("dsg2"); 
							sh = gh.size(); 
							if(sh > 0){
							%>
					<table class="table table-condensed">
						<thead>
							<tr class="cart_menu">
								<td class="ck">Remove</td>
								<td class="image">Image</td>
								<td class="item">Item</td>
								<td class="description"></td>
								<td class="price">Price</td>
								<td class="price">Discount</td>
								<td class="quantity">Quantity</td>
								<td class="total">Total</td>
								<td></td>
							</tr>
						</thead>
						<tbody>
						<%
							
								for(int i = 0; i < sh; i++)
								{
						%>
						
							<tr>
								<td class="cart_ck" class="form-check-input" >
									<input type = "checkbox" name = "cbXoa" value = "<%=gh.get(i).getMasp()%>" style= "width: 20px; height: 20px;"/>
								</td>
								<td class="cart_product" style = "width: 160px; margin-left: 0; margin-right: 20px;">
									<a href=""><img src="<%=gh.get(i).getAnh() %>" alt="" style = "width: 100%; height: 210px; object-fit: cover;"></a>
								</td>
									<%
										String ma  = "";
										long slk = 0;
										if(dstam != null){
											String tam = gh.get(i).getMasp();
											
											for(sanphambean s: dstam)
											{
												if(s.getMasp().equals(tam))
												{
													if(s.getSl() == 0){
														test = 0;
														break;
													}else{
														test = 1;
														slk = s.getSl();
													}
												}
												
											}
										}
									%>
								<td class="cart_description">
									<h4><a href=""><%=gh.get(i).getTensp() %></a></h4>
									<p style = "margin-top: 12px;">Số hàng trong kho: <b><%=slk %></b></p>
								</td>
								<td class="cart_price">
									<p><%=gh.get(i).getGia() %> VND</p>
								</td>
								<td class="cart_gg">
									<p><%=gh.get(i).getGg() %></p>
								</td>
								<td class="cart_quantity" >
									<form></form>
									<form method = "post" action ="CapNhatSLController?masp=<%=gh.get(i).getMasp()%>"
										class="cart_quantity_button" style = "display: flex;">
									<input min="0" class="cart_quantity_input quantity"  type="number" name="quantity" 
										value="<%=gh.get(i).getSl() %>" autocomplete="off" size="2">
										
										<!-- Cap nhat so luong khong vuot qua sl kho -->
										 <%if(test == 0){ %>
										 		<input type = "submit" value = "Cập nhật" class = "btn-update" disabled style = "opacity: 0.5" />
										 <%}else{ %>
											<input type = "submit" value = "Cập nhật" class = "btn-update" />
										<%} %>
									</form>
									<%if(request.getAttribute("quasl")!= null){ %>
										<p><b><%=request.getAttribute("quasl") %></b></p>
									<%} %>
								</td>
								<td class="cart_total">
									<p class="" style= "font-size: 16px; color: #fe980f;"><%=(long)gh.get(i).getThanhtien() %> VND</p>
								</td>
								<td class="cart_delete">
									<a class="cart_quantity_delete" href="BoChonController?msp=<%=gh.get(i).getMasp()%>&sl=<%=gh.get(i).getSl() %>"><i class="fa fa-times"></i></a>
								</td>
							</tr>
								<%} %>
							<%} %>
	
						</tbody>
					</table>
						<%if(sh > 0){%>
							<div align='' style  ="margin:20px 0 20px 20px;" >
								Tổng tiền thanh toán:
								<% float tt = (float) session.getAttribute("tt2"); %>
								 <p style= "font-size: 18px; color: #fe980f; display: inline; font-weight: 600"><%=(long) tt%> VND</p> <br> 
								<button type="submit" class="btn" 
									style="margin: 24px 0; padding: 6px 10px; font-size: 13px; border:1px solid #fe980f; background-color: #fff;" name="xoaluachon">
									Xóa lựa chọn</button> 
								<div style  ="margin:0 0 20px 0" >
									<a href ="BoChonController" class ="btn-danger btn-remove"
										style="margin: 24px 0; padding: 6px 10px; font-size: 13px; border-radius: 4px;"
									 >
										Xoá giỏ hàng
									</a>
								</div>
								<%if(session.getAttribute("dn2") != null){ %>
									<div style  ="margin:0 0 20px 0" >
										<a href ="CTHDController" class =""
											style="margin: 24px 0; padding: 6px 10px; font-size: 13px; background-color: #fe980f; color: #fff; border-radius: 4px;"
										 >
											Đặt mua
										</a>
									</div>
								<%}else{ %>
									<div style  ="margin:0 0 20px 0" >
										<a href ="DangNhapController" class =""
											style="margin: 24px 0; padding: 6px 10px; font-size: 13px; background-color: #fe980f; color: #fff; border-radius: 4px;"
										 >
											Đặt mua
										</a>
									</div>
								<%} %>
							</div>
							<%}else{ %>	
							<div  align='left' style  ="width: 80%;margin:20px auto 40px auto;display: flex;align-items: center;" >
								Giỏ hàng trống:
								<a href = "TrangChuController" class ="btn btn-xh">Lướt xem hàng</a>
							</div>
						<%} %>
				<%} %>
				</form>
			</div>
		</div>
	</section> <!--/#cart_items-->
	<%session.removeAttribute("empty_main"); %>
</body>
</html>