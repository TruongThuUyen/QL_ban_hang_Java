package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GioHangbean;
import bean.loaibean;
import bean.sanphambean;
import bo.GioHangbo;
import bo.loaispbo;
import bo.sanphambo;

/**
 * Servlet implementation class GioHangController
 */
@WebServlet("/GioHangController")
public class GioHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GioHangController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			HttpSession session = request.getSession();
			String msp = request.getParameter("msp");
			String tsp = request.getParameter("ten");
			String anh = request.getParameter("anh");
			String giaban = request.getParameter("gia");
			String gg = request.getParameter("gg");
			String ml = request.getParameter("ml");
			sanphambo spbo = new sanphambo();
			float tongtien = 0;
			GioHangbo gh = (GioHangbo) session.getAttribute("giohang");

			if (msp != null && tsp != null && giaban != null && anh != null && gg != null && ml != null) {
				loaispbo lbo = new loaispbo();
				if (session.getAttribute("giohang") == null) {
					gh = new GioHangbo();
					session.setAttribute("giohang", gh);
				}

				gh = (GioHangbo) session.getAttribute("giohang");
				gh.Them(msp, tsp, anh, Long.parseLong(giaban), Float.parseFloat(gg), (long)1);
				
				
				
				//Luu lai so luong tam thoi khi them moi vao gio
				ArrayList<sanphambean> dstam = (ArrayList<sanphambean>) session.getAttribute("dstam");		
				if(dstam != null) {
					long sltam = 0;
					String masp = "";
					for(sanphambean sp: dstam)
					{
						if(sp.getMasp().equals(msp)) {
							sp.setSl(sp.getSl() - 1);
							sltam = sp.getSl();
							masp = sp.getMasp();
						}
					}
					dstam.add(new sanphambean(masp, "", "", 0, 0, sltam, "" ));	
					session.setAttribute("dstam", dstam);
				}
			
				session.setAttribute("giohang", gh);
				session.setAttribute("dsg2", gh.ds);

				if (session.getAttribute("dsg2") != null) {
					tongtien = gh.TongTien();
				}
				
				if(session.getAttribute("loiquasl") != null) {
					request.setAttribute("quasl", "Số lượng đang chọn vượt quá số lượng hiện có!");
					System.out.print("co loi");
					
				}
	
					
				session.setAttribute("tt2", tongtien);
				session.setAttribute("dsloai2", lbo.getLoai());
				response.sendRedirect("TrangChuController?msp=" + msp);
			} else {
				if (session.getAttribute("dsg2") != null) {
					if(session.getAttribute("giohang") != null) {
						tongtien = gh.TongTien();
						session.setAttribute("tt2", tongtien);	
					}
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("GioHang.jsp");
				rd.forward(request, response);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
