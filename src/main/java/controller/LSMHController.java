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

import bean.admindsmhbean;
import bean.hoadonbean;
import bean.khachhangbean;
import bo.admindsmhbo;
import bo.hoadonbo;

/**
 * Servlet implementation class LSMHController
 */
@WebServlet("/LSMHController")
public class LSMHController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LSMHController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			hoadonbo hdbo = new hoadonbo();
			
			HttpSession session = request.getSession();
			khachhangbean kh = (khachhangbean) session.getAttribute("dn2");

			//Pagination
			String pageStr = request.getParameter("page");
			String pageSizeStr = request.getParameter("ps");
			int page = 1, pageSize = 10;
			request.setAttribute("pageSize", pageSize);

			if (pageStr != null && pageSizeStr != null) {
				page = Integer.parseInt(pageStr);
				pageSize = Integer.parseInt(pageSizeStr);
			}
			
			ArrayList<hoadonbean> dshd = null;
			
			int soLuong = 0;
			
		
			String mode = request.getParameter("mode");
			if(mode != null)
			{
				if(mode.equals("1"))
				{
					dshd = hdbo.getListWithPageHDKH(page, pageSize, 0, kh.getMakh());
					soLuong = hdbo.getSoLuongHDKH(0, kh.getMakh());
					if(request.getParameter("tkls") != null)
					{
						String tkls = request.getParameter("tkls");
						dshd = hdbo.TimHDTheoNgay(hdbo.getHDByID(kh.getMakh(), 0) , tkls, 0);
						soLuong = dshd.size();
						
					}
					request.setAttribute("mode", 1); 
				}else if(mode.equals("2")) {
					dshd = hdbo.getListWithPageHDKH(page, pageSize, 1, kh.getMakh());
					soLuong = hdbo.getSoLuongHDKH(1, kh.getMakh());
					if(request.getParameter("tkls") != null)
					{
						String tkls = request.getParameter("tkls");
						dshd = hdbo.TimHDTheoNgay(hdbo.getHDByID(kh.getMakh(), 1) , tkls, 1);
						soLuong = dshd.size();
					}
					request.setAttribute("mode", 2);
				}else if(mode.equals("3")) {
					dshd = hdbo.getListWithPageHDKH(page, pageSize, 2, kh.getMakh());
					soLuong = hdbo.getSoLuongHDKH(2, kh.getMakh());
					if(request.getParameter("tkls") != null)
					{
						String tkls = request.getParameter("tkls");
						dshd = hdbo.TimHDTheoNgay(hdbo.getHDByID(kh.getMakh(), 2) , tkls, 2);
						soLuong = dshd.size();
					}
					request.setAttribute("mode", 3);
				}else if(mode.equals("4")) {
					dshd = hdbo.getListWithPageHDKH(page, pageSize, 3, kh.getMakh());
					soLuong = hdbo.getSoLuongHDKH(3, kh.getMakh());
					if(request.getParameter("tkls") != null)
					{
						String tkls = request.getParameter("tkls");
						dshd = hdbo.TimHDTheoNgay(hdbo.getHDByID(kh.getMakh(), 3) , tkls, 3);
						soLuong = dshd.size();
					}
					request.setAttribute("mode", 4);
				}
					
			}else {
				dshd = hdbo.getListWithPageHDKH(page, pageSize, 0, kh.getMakh());
				soLuong = hdbo.getSoLuongHDKH(0, kh.getMakh());
				request.setAttribute("mode", 1);
			}

			//Tinh tong so trang
			int tongSoTrang = soLuong / pageSize;
			if (soLuong % pageSize != 0)
				tongSoTrang++;
			
			request.setAttribute("dshd", dshd);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("tsl", soLuong);
			request.setAttribute("tongsotrang", tongSoTrang);
			request.setAttribute("pageSelected", page);
			RequestDispatcher rd = request.getRequestDispatcher("LichSuMuaHang.jsp");
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
