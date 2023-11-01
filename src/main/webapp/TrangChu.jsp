<%@page import="bean.sanphambean"%>
<%@page import="bean.loaibean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Home | E-Shopper</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
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
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	  
	  <style type="text/css">
	  		.sold-out
	  		{
	  			height: 30px;
			    display: flex;
			    align-items: center;
			    justify-content: center;
			    width: 112px;
			    margin: 0 auto;
			    margin-bottom: 10px;
			    background-color: #F5F5ED;
			    opacity: 0.7;
	  		}
	  </style>
</head><!--/head-->

<body>
	<% 	ArrayList<sanphambean> ds = (ArrayList<sanphambean>) request.getAttribute("dssp"); %>
	<%	 
		ArrayList<sanphambean> dstam = (ArrayList<sanphambean>) session.getAttribute("dstam");
		String masp = ""; 
		long sphh = -1;
		if(request.getAttribute("sphh") != null){
			sphh = (long) request.getAttribute("sphh");
			masp = (String) request.getAttribute("masp");
		}
	%>
	<%@include file = "Menu.jsp" %>

	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="left-sidebar">
						<h2>Category</h2>
						<div class="panel-group category-products" id="accordian"><!--category-productsr-->
						
							<%if(session.getAttribute("dsl2") != null )
								{
									ArrayList<loaibean> dsl = (ArrayList<loaibean>) session.getAttribute("dsl2");
									for(loaibean  loai: dsl)
									{
							%>
							
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title"><a href="TrangChuController?maloaitc=<%=loai.getMaloai()%>"><%=loai.getTenloai()%></a></h4>
									</div>
								</div>
								
							<%   } 
							} %>
							
						</div><!--/category-products-->
				
					</div>
				</div>
				
				
				<div class="col-sm-9 padding-right">
					<%
						if( ds.size() <= 0){
					%>
					<br><div style = "text-align: center">
							<b>Không tìm thấy sản phẩm!</b>
						</div>
					<%}else{ %>
					<div class="features_items"><!--features_items-->
						<h2 class="title text-center">Products</h2>
						<%
							int n = ds.size();
							for(int i = 0; i < n; i++)
							{
								sanphambean s = ds.get(i);
								
						%>
							<div class="col-sm-4" style ="height: 440px">
								<div class="product-image-wrapper">
									<div class="single-products">
										<div class="productinfo text-center">
											<img src="<%=s.getAnh() %>" alt="picture" style = "height: 260px;object-fit: cover;"/>
											<p style = "margin-top: 10px; margin-bottom: 0">Giá bán: <b><%=s.getGia() %></b></p>
											<h4><%=s.getTensp() %></h4> 
											<% 
												if(s.getGg() > 0)
												{
											%>
											<p> Đang giảm: <%= (int)(s.getGg() * 100)%>%</p>
											<%	}else{ %>
												<br>
											<%} %>	
											
												<%	String masp2 = ""; 
													long sl2 = -1;
													for(sanphambean sp : dstam){ 
													if(sp.getMasp().equals(s.getMasp())){
														masp2 = sp.getMasp();
														sl2 = sp.getSl();
														}
													}
												%>
												<%if(sl2 >0){ %>
													<p> Số lượng kho: <b><%=sl2 %></b> <p>
												<%} %>
												<%if(sl2 == 0){ %>
													<p class="sold-out" >Hết hàng</p>
												<%}else{ %>
													
													<a href="GioHangController?msp=<%=s.getMasp() %>
													&ten=<%=s.getTensp()%>
													&anh=<%=s.getAnh()%>
													&gia=<%=s.getGia()%>
													&gg=<%=s.getGg()%>
													&ml=<%=s.getMaloai() %>
												" class="btn btn-default add-to-cart">
												<i class="fa fa-shopping-cart"></i>Add to cart </a>
											 <%} %> 
										</div>
									</div>
								</div>
							</div>
						<%	} %>
						
					</div><!--features_items-->
					<!--Pagination  -->
						<div class="card-footer clearfix" style = "    text-align: center; margin-top: 30px;">
							<ul class="pagination m-0 float-right" id="pagination">

							</ul>
						</div>
				</div>
				<%} %>
			</div>
		</div>
	</section>
	
	
	<%@include file ="Pagination.jsp" %>
	
	<script>
	$(function () {
    // Pagination
    const pageArray = pagination(<%=request.getAttribute("pageSelected")%> ,<%=request.getAttribute("tongsotrang") %>);
		for (let pageNumber of pageArray) {
			let url = "http://localhost:8080/BT/TrangChuController" + `?key=<%=request.getAttribute("key2") %>&maloai=<%=request.getAttribute("maloai")%>&page=\${pageNumber}&ps=<%=request.getAttribute("pageSize")%> `;
			if (pageNumber === '...') $('#pagination').append(`<li class="page-item disabled"><a class="page-link" href="#">\${pageNumber}</a></li>`)
			else if (pageNumber === ${pageSelected}) $('#pagination').append(`<li class="page-item active"><a class="page-link" href=\${url}>\${pageNumber}</a></li>`)
			else 
				$('#pagination').append(`<li class="page-item"><a class="page-link" href=\${url}>\${pageNumber}</a></li>`)
		}
	});
	</script>
	

  
    <script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.scrollUp.min.js"></script>
	<script src="js/price-range.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
</body>
</html></html>