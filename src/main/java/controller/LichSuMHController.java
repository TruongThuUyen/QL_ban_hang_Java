package controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.khachhangbean;
import bean.lichsubean;
import bo.chitiethdbo;
import bo.ctlichsubo;
import bo.hoadonbo;
import bo.lichsubo;

/**
 * Servlet implementation class LichSuMHController
 */
@WebServlet("/LichSuMHController")
public class LichSuMHController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LichSuMHController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		try {
			HttpSession session = request.getSession();
			if(session.getAttribute("dn2") != null)
			{
	
				khachhangbean kh = (khachhangbean) session.getAttribute("dn2");
				chitiethdbo cthd = new chitiethdbo(); 
				ctlichsubo ctldbo = new ctlichsubo();
				hoadonbo hdbo = new hoadonbo();
				lichsubo lsbo = new lichsubo();

				//Tim trong trang lich su
				ArrayList<lichsubean> hdchuatt = lsbo.getHDCTT(kh.getMakh());
				ArrayList<lichsubean> hdchuatt2 = new ArrayList<lichsubean>();
				ArrayList<lichsubean> hddatt = lsbo.getHDDTT(kh.getMakh());
				ArrayList<lichsubean> hddatt2 = new ArrayList<lichsubean>();
				ArrayList<lichsubean> hddaht = lsbo.getHDDaHT(kh.getMakh()); //hoa don da hoan thanh
				ArrayList<lichsubean> hddaht2 = new ArrayList<lichsubean>();
				ArrayList<lichsubean> lshoadon = new ArrayList<lichsubean>();
		
				//Pagination
				String pageStr = request.getParameter("page");
				String pageSizeStr = request.getParameter("ps");
				int page = 1, pageSize = 10;
				request.setAttribute("pageSize", pageSize);

				if (pageStr != null && pageSizeStr != null) {
					page = Integer.parseInt(pageStr);
					pageSize = Integer.parseInt(pageSizeStr);
				}
				
				
			
				//ArrayList Hoadon
				for(lichsubean v : hdchuatt)
				{
					lichsubean h = new lichsubean(v.getMahd(), v.getSlm(), v.getTongtien(), v.getNgaymua(),0);
					hdchuatt2.add(h);
					
				}
				
				
				for(lichsubean v : hddatt)
				{
					lichsubean h = new lichsubean(v.getMahd(), v.getSlm(), v.getTongtien(), v.getNgaymua(), 1);
					hddatt2.add(h);	
				}			
				
				
				if(request.getParameter("mode") != null)
				{
					String mode = request.getParameter("mode");
					if(mode.equals("1"))
					{
						//Tim kiem trong hd chua thanh toan
						if(request.getParameter("tkls") != null)
						{
							String tkls = request.getParameter("tkls");
							lshoadon = lsbo.TimHDChuaTT(tkls, kh.getMakh());
						}else
							lshoadon = hdchuatt2;
						
					}else if(mode.equals("2")) {
						
						//Tim kiem trong hd da thanh toan
						if(request.getParameter("tkls") != null)
						{
							String tkls = request.getParameter("tkls");
							lshoadon = lsbo.TimHDDaTT(tkls, kh.getMakh());
						}else
							lshoadon = hddatt2;
					}
				}else
					lshoadon = hdchuatt2;
				
				
				request.setAttribute("hdchuatt2", lshoadon); 
				request.setAttribute("hddatt2", hddatt2);
				RequestDispatcher rd = request.getRequestDispatcher("LichSuMuaHang.jsp");
				rd.forward(request, response);
			}else {
				response.sendRedirect("DangNhapController");
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
