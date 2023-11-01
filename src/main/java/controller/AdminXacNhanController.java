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
import bean.adminxacnhanbean;
import bo.adminxacnhanbo;
import bo.chitiethdbo;
import bo.hoadonbo;

/**
 * Servlet implementation class AdminXacNhanController
 */
@WebServlet("/AdminXacNhanController")
public class AdminXacNhanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminXacNhanController() {
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
			String mact = request.getParameter("mact");
			adminxacnhanbo xnbo = new adminxacnhanbo();
			chitiethdbo cthdbo = new chitiethdbo();
			hoadonbo hdbo = new hoadonbo();
			hdbo.XoaHD();
			
			//Pagination
			String pageStr = request.getParameter("page");
			String pageSizeStr = request.getParameter("ps");
			int page = 1, pageSize = 10;
			request.setAttribute("pageSize", pageSize);

			if (pageStr != null && pageSizeStr != null) {
				page = Integer.parseInt(pageStr);
				pageSize = Integer.parseInt(pageSizeStr);
			}
		
			
			long mahd = 0;
			if(mact != null) //damua = 0 -> damua = 1
			{
				long macthd  = Long.parseLong(mact);
				mahd = cthdbo.GetMaHD(macthd); //lay ra mahd cua cthd
				long demsocthd = cthdbo.DemCTHD(mahd); //dem so cthd co cung mahd
				
				xnbo.Sua(macthd);
				
				long demsocthddatt = cthdbo.DemCTHDDaTT(mahd);
				if(demsocthd == demsocthddatt)
					xnbo.UpdateHD(mahd);
			}
			
			ArrayList<adminxacnhanbean> ds = xnbo.getListWithPage(page, pageSize, 0);
	
			int soLuong = xnbo.getSoLuong(0);
			//Tinh tong so trang
			int tongSoTrang = soLuong / pageSize;
			if (soLuong % pageSize != 0)
				tongSoTrang++;
			request.setAttribute("ds", ds);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("tsl", soLuong);
			request.setAttribute("tongsotrang", tongSoTrang);
			request.setAttribute("pageSelected", page);
			
			RequestDispatcher rd = request.getRequestDispatcher("AdminXacNhanDH.jsp");
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
