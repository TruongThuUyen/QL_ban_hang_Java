package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GioHangbean;
import bean.khachhangbean;
import bo.GioHangbo;
import bo.chitiethdbo;
import bo.hoadonbo;
import bo.sanphambo;
import dao.khachhangdao;

/**
 * Servlet implementation class CTHDController
 */
@WebServlet("/CTHDController")
public class CTHDController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CTHDController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession();
			GioHangbo ghbo = new GioHangbo();
			hoadonbo hdbo = new hoadonbo();
			chitiethdbo cthdbo = new chitiethdbo();
			khachhangbean kh = (khachhangbean) session.getAttribute("dn2");
			khachhangdao khdao = new khachhangdao();
			sanphambo spbo = new sanphambo();
			
			String mahd = request.getParameter("ma");
			
			if(kh != null)
			{	
				if(mahd != null)
				{
					if(kh.getDiachi() != null  && kh.getSodt() != null)
					{
						response.sendRedirect("LSMHController?ma=" + mahd); //System.out.print("dc null ");
					}else{
						if(kh.getDiachi() == null && kh.getSodt() == null)
						{
							System.out.println(kh.getSodt());
							request.setAttribute("loinull","1"); 
							response.sendRedirect("CTHDController?dc=null"); 
							return;
						}else {
							if(kh.getDiachi() == null ) {
								request.setAttribute("loidc","Vui lòng thêm địa chỉ!"); 
								response.sendRedirect("CTHDController?email=null"); 
								return;
							}else if(kh.getSodt() == null) {
								request.setAttribute("loistd","Vui lòng thêm địa chỉ!"); 
								response.sendRedirect("CTHDController?sdt=null"); 
								return;
							}
						}
					}
				}
				
				if(request.getParameter("dc") != null)
					request.setAttribute("loinull","Vui lòng thêm địa chỉ và số điện thoại để vận chuyển đơn hàng!"); 
				
				if(request.getParameter("email") != null)
				{
					request.setAttribute("emailnull","Vui lòng thêm địa chỉ!"); 
				}
				
				if(request.getParameter("sdt") != null)
				{
					request.setAttribute("sdtnull","Vui lòng thêm số điện thoại!"); 
				}
			
				if(request.getParameter("sodt") != null) {
					request.setAttribute("sdttrong","Số điện thoại không được để trống!"); 
					request.setAttribute("show2", 1);
				}

				if(session.getAttribute("giohang") != null)
				{
					GioHangbo gh  = (GioHangbo) session.getAttribute("giohang");
					float tt = (float) session.getAttribute("tt2");
					if(gh.ds.size() > 0)
					{
						session.setAttribute("dsdh2", gh.ds);
						long n = gh.ds.size();
												
						hdbo.ThemHD(kh.getMakh(), gh.TongSP() , tt);
						long ma = hdbo.getMa(kh.getMakh());
						
						//set session chi tiet hoa don
						ArrayList<GioHangbean> giohang = gh.ds;
						long sl = 0;
						 for(int i = 0; i < n; i++) {
							 cthdbo.ThemCTHD(giohang.get(i).getMasp(),
									 Long.parseLong(String.valueOf(giohang.get(i).getSl())), ma, 
									 Float.parseFloat(String.valueOf(giohang.get(i).getGg())), 
									 Float.parseFloat(String.valueOf(giohang.get(i).getThanhtien()))
									 ); 
//							 Update lai so luong sp
							 sl = spbo.getSL(giohang.get(i).getMasp()) - giohang.get(i).getSl();
							 spbo.UpdateSL(sl, giohang.get(i).getMasp());
						 }
						 
				
						
						session.removeAttribute("empty2"); 
						session.setAttribute("hdtongtien", gh.TongTien());
						session.setAttribute("hdtongsach", gh.TongSP());
						session.removeAttribute("giohang");	
						session.removeAttribute("dsg2");						
						session.setAttribute("mathd2", ma);
						RequestDispatcher rd = request.getRequestDispatcher("ChiTietHD.jsp");
						rd.forward(request, response);
					}else {
						session.setAttribute("empty2", "1");
					}
				}else {
					RequestDispatcher rd = request.getRequestDispatcher("ChiTietHD.jsp");
					rd.forward(request, response);
				}
				
			}else {
				response.sendRedirect("DangNhapController");
			}
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
