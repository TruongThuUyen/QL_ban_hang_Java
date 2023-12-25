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

import bean.loaibean;
import bean.sanphambean;
import bo.GioHangbo;
import bo.loaispbo;
import bo.sanphambo;

/**
 * Servlet implementation class TrangChuController
 */
@WebServlet("/TrangChuController")
public class TrangChuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrangChuController() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		try {	
			
		
//			String key = request.getParameter("key2");
			
			loaispbo lbo = new loaispbo();
			ArrayList<loaibean> getdsLoai = lbo.getLoai();
			ArrayList<loaibean> dsloai = new ArrayList<loaibean>();
			//lay ve ds sach tu sachbo
			sanphambo spbo = new sanphambo();	
			ArrayList<sanphambean> dssp = spbo.getSP();
		
			//Pagination
			String pageStr = request.getParameter("page");
			String pageSizeStr = request.getParameter("ps");
			int page = 1, pageSize = 9;
			int soLuong = 0;
			request.setAttribute("pageSize", pageSize);
			
			if (pageStr != null && pageSizeStr != null) {
				page = Integer.parseInt(pageStr);
				pageSize = Integer.parseInt(pageSizeStr);
			}
			
			// Khi chua nhap key
			String key2 = null;
			ArrayList<sanphambean> dssanpham = spbo.getListWithPage(page, pageSize, "");
			soLuong = spbo.getSoLuong(key2); //ban dau vao chua tim kiem
		

			// Tim Kiem			
			if(request.getParameter("key") != null) //get key tu input
			{
			 	key2 = request.getParameter("key");
			 	if(key2.equals("null"))
			 	{
			 		key2 = "";
			 	}
			 		soLuong = spbo.getSoLuong(key2);
			 		dssanpham = spbo.getListWithPage(page, pageSize, key2);
			 		request.setAttribute("key2", key2);	
			}
			
			//Category
			String mlbyloai = request.getParameter("maloaitc");
			if(mlbyloai != null)
			{
				soLuong = spbo.getSoLuong(mlbyloai);
				dssanpham = spbo.getListWithPageByML(page, pageSize, mlbyloai);
				session.removeAttribute("empty_main");
				request.setAttribute("maloai", mlbyloai);
			}
			
			//Phan trang khi tien hang tim theo ma loai
			String ml = request.getParameter("maloai"); // ma loai tu url
			if(request.getParameter("key") != null && ml != null)
			{
				key2 = request.getParameter("key");
				if(ml.equals("null") == false && key2.equals("null"))
				{
					soLuong = spbo.getSoLuong(ml);
					dssanpham = spbo.getListWithPageByML(page, pageSize, ml);
					session.removeAttribute("empty_main");
					request.setAttribute("maloai", ml); //gui ve pagination
				}
			}
	
			int tongSoTrang = soLuong / pageSize;
			if (soLuong % pageSize != 0)
				tongSoTrang++;

			
			for(loaibean loai : getdsLoai)
			{
				if(lbo.Check(loai.getMaloai()) > 0)
					dsloai.add(loai);	
			}
			
			
			GioHangbo g = new GioHangbo();
			float ts = 0;
			if(session.getAttribute("giohang") == null)
			{
				session.setAttribute("giohang", g);
			}else { 
				g = (GioHangbo) session.getAttribute("giohang");
				ts= g.TongTien();
			}
			
			//Danh sách tạm lưu trữ masp và số lượng của sản phẩm
			ArrayList<sanphambean> dstam = new ArrayList<sanphambean>();
			if(session.getAttribute("dstam") == null)	
			{
				for(sanphambean s: spbo.getSP())
				{
					dstam.add( new sanphambean(s.getMasp(), "", "", 0, 0, s.getSl(), ""));
				}
				session.setAttribute("dstam", dstam);
			}
			
			//Chuyen dsloai va dssach ve htsach
			session.setAttribute("tsl2", ts);
			session.setAttribute("dsl2", dsloai);
			request.setAttribute("dssp", dssanpham);
			request.setAttribute("tongsotrang", tongSoTrang);
			request.setAttribute("pageSelected", page);
			
			RequestDispatcher rd = request.getRequestDispatcher("TrangChu.jsp");
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
