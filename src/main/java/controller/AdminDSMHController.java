package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.admindsmhbean;
import bean.hoadonbean;
import bean.loaibean;
import bo.admindsmhbo;
import bo.chitiethdbo;
import bo.hoadonbo;
import bo.khachhangbo;

/**
 * Servlet implementation class AdminDSMHController
 */
@WebServlet("/AdminDSMHController")
public class AdminDSMHController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDSMHController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			admindsmhbo dsmh = new admindsmhbo();
			ArrayList<admindsmhbean> dsmh2 = new ArrayList<admindsmhbean>(); 
			chitiethdbo cthdbo = new chitiethdbo();
			hoadonbo hdbo = new hoadonbo();
			khachhangbo khbo = new khachhangbo();
			dsmh2 = dsmh.getDSChuaXN();
			
			String mahd = request.getParameter("mahd");
			String tab = request.getParameter("tab");

			//Pagination
			String pageStr = request.getParameter("page");
			String pageSizeStr = request.getParameter("ps");
			int page = 1, pageSize = 10;
			request.setAttribute("pageSize", pageSize);

			if (pageStr != null && pageSizeStr != null) {
				page = Integer.parseInt(pageStr);
				pageSize = Integer.parseInt(pageSizeStr);
			}
			ArrayList<admindsmhbean> ds = dsmh.getListWithPage(page, pageSize, 0);
			ArrayList<hoadonbean> dshd = null;
			
			int soLuong = dsmh.getSoLuong(0);
			
		
			String mode = request.getParameter("mode");
			if(mode != null)
			{
				/*
				 * if(mode.equals("1")) { ds = dsmh.getListWithPage(page, pageSize, 0); soLuong
				 * = dsmh.getSoLuong(0); request.setAttribute("mode", 1); //gui ve cho page
				 * }else
				 */
				if(mode.equals("2")) {
					dshd = hdbo.getListWithPageHD(page, pageSize, 1);
					soLuong = hdbo.getSoLuongHD(1);
					request.setAttribute("mode", 2); //gui mode da thanh toan thanh cong ve cho page
				}else if(mode.equals("3")) {
					dshd = hdbo.getListWithPageHD(page, pageSize, 2);
					soLuong = hdbo.getSoLuongHD(2);
					request.setAttribute("mode", 3);
				}else if(mode.equals("4")) {
					dshd = hdbo.getListWithPageHD(page, pageSize, 3);
					soLuong = hdbo.getSoLuongHD(3);
					request.setAttribute("mode", 4);
				}
					
			}else {
//				ds = dsmh.getListWithPage(page, pageSize, 0);
//				soLuong = dsmh.getSoLuong(0);
//				request.setAttribute("mode", 1); //gui ve cho page
				
				dshd = hdbo.getListWithPageHD(page, pageSize, 1);
				soLuong = hdbo.getSoLuongHD(1);
				request.setAttribute("mode", 2);
			}
			
			//Khi bam -> hien thi modal
			if(mahd != null && tab != null)
			{
				long ma = Long.parseLong(mahd);
				if(tab.equals("2")) { // tien hanh giao cho dv van chuyen
					hdbo.UpdateHD(ma, 2);
					if(hdbo.UpdateHD(ma, 2) > 0)
						cthdbo.UpdateCTHD(ma, 2);
					response.sendRedirect("AdminDSMHController?mode=2");
					return;
				}else if(tab.equals("3")) { // Đánh dấu hoàn thành đơn hàng
					hdbo.UpdateHDHT(ma, 3);
					if(hdbo.UpdateHD(ma, 3) > 0)
						cthdbo.UpdateCTHD(ma, 3);
					response.sendRedirect("AdminDSMHController?mode=3");
					return;
				}
				 	
			}
			
			//Tinh tong so trang
			int tongSoTrang = soLuong / pageSize;
			if (soLuong % pageSize != 0)
				tongSoTrang++;
			request.setAttribute("dsachkh", khbo.getDSKH());
			request.setAttribute("dsmh", ds);
			request.setAttribute("dshd", dshd);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("tsl", soLuong);
			request.setAttribute("tongsotrang", tongSoTrang);
			request.setAttribute("pageSelected", page);
			RequestDispatcher rd = request.getRequestDispatcher("AdminDSMH.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
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
