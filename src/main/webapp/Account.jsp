<%@page import="bean.khachhangbean"%>
<%@page import="bean.GioHangbean"%>
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
    <title>Login | E-Shopper</title>
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
    <style>
   		 .wrap-info
   		 {
   		 	display: flex;
   		 }
   		 
   		 .but
   		 {
   		 	display: flex;
		    align-items: center;
		    justify-content: center;
		   /*  border: 1px solid #ccc; */
		    background: #fe9911;
		    height: 30px;
		    padding: 0px 17px;
		    font-size: 13px;
		    color: #fff;
	/* 	    border-radius: 4px; */
   		 }
   		 .but:hover
   		 {
   		 	background: #f2f2f2;
   		 	color: #000;
   		 }
   		 
   		 	.input-1{
    		color: #000 !important;
		    background: #fff !important;
		    border: 1px solid #ccc !important;
		    border-radius: 4px ;
    	}
    </style>
</head><!--/head-->

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
								<li><a href = "<%= ((session.getAttribute("dn2") == null) ? "DangNhapController" : "LichSuMHController")%>"><i class="fa fa-user"></i> History</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-bottom-->
		<%session.removeAttribute("empty_main"); %>
	</header>
	<section id="form" style ="margin-top: 30px;"><!--form-->
		<div class="container">
			<%if(request.getAttribute("update") != null){
			%>
			<h4 style= "text-align: center; color:  #fe980f"><%=request.getAttribute("update") %></h4>
			<%} %>
			<div class="row"  style = "display: flex; justify-content: center;">
				<div class="col-sm-4">
					<div class="signup-form"><!--sign up form-->
					<%if(session.getAttribute("dn2") != null){
						khachhangbean kh = (khachhangbean) session.getAttribute("dn2");
					%>
						<h2>Your Account</h2>
						<form action="TaiKhoanController?makh=<%=kh.getMakh() %>" method = "post" style = "margin-bottom: 10px;">
							<input class  = "input-1" type="text" placeholder="Name" value ="<%=kh.getHoten() %>" name = "txtname"/>
							<input  class  = "input-1" type="text" placeholder="Address" name ="txtdc"  value ="<%=(kh.getDiachi() == null ? "": kh.getDiachi() ) %>" />
							<input class  = "input-1" type="text" placeholder="Phone number" name ="txtsdt"  value ="<%=(kh.getSodt() == null ? "": kh.getSodt() ) %>" />
							<input class  = "input-1" type="text" placeholder="Username" name = "txtun" value = "<%=kh.getTendn()%>"/>
							<input class  = "input-1" type="email" placeholder="Email Address" name = "txtemail" value = "<%=kh.getEmail() %>" />
							<input  class  = "input-1" type="password" placeholder="Password" name="" value = "<%=kh.getPass()%>" disabled="disabled" style="opacity: 0.6"/>
							<div class = "wrap-info">
								<button type="submit" class="btn btn-default" style = "height: 30px;margin-right: 20px;">Save</button>
								<a href = "TrangChuController" class="but" name = "back"  >Back</a>
							</div>
							<!-- <div class = "wrap-info">
								<a href ="TaiKhoanController"  class="but" name = "edit" style = " margin-right: 10px;">Edit</a>
								<a href = "TrangChuController" class="but" name = "back"  >Back</a>
							</div> -->
						</form>
						
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
						
						<%if(request.getAttribute("rong") != null){ %>
							<p><b><%=request.getAttribute("rong")  %></b></p>
						<%} %>
						
						<% if( request.getParameter("error") != null)
						{
							String error = request.getParameter("error");
							if (error.equals("1"))
								out.print("<b>Vui lòng nhập đầy đủ thông tin!</b>");
							else if(error.equals("2"))
								out.print("<b>Email đã tồn tại!</b>");
							else if(error.equals("3"))
								out.print("<b>Tên người dùng đã tồn tại!</b>");
						}%>
	
					</div><!--/sign up form-->
					<%} %>
				</div>
			</div>
		</div>
	</section><!--/form-->
	
</body>
</html>