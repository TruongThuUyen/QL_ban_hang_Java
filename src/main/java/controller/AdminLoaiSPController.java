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
import bo.loaispbo;

/**
 * Servlet implementation class AdminLoaiSPController
 */
@WebServlet("/AdminLoaiSPController")
public class AdminLoaiSPController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoaiSPController() {
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
			String maloai = request.getParameter("txtmaloai");
			String tenloai = request.getParameter("txttenloai");
	
			loaispbo lbo = new loaispbo();
			HttpSession session = request.getSession();
			
			String pageStr = request.getParameter("page");
			String pageSizeStr = request.getParameter("ps");
			int page = 1, pageSize = 5;
			request.setAttribute("pageSize", pageSize);
			
			if (pageStr != null && pageSizeStr != null) {
				page = Integer.parseInt(pageStr);
				pageSize = Integer.parseInt(pageSizeStr);
			}
			String key = request.getParameter("ktim");
			if(key == null)
				key = "";
			ArrayList<loaibean> dsLoai = lbo.getListWithPage(page, pageSize, key);
	
			int soLuong = lbo.getSoLuong(key);
			//Khi bam chon select 
			String ma_select = request.getParameter("ma_select");
			if(ma_select != null)
			{
				if(ma_select.equals("") != true) {
					dsLoai = lbo.getLoaiById(ma_select);
					request.setAttribute("loai_select", ma_select);
					
					String tempt = "";
					//Lay ten loai tu maloai
					for(loaibean l: lbo.getLoai())
						if(l.getMaloai().equals(ma_select))
							tempt = l.getTenloai();
					dsLoai = lbo.getListWithPage(page, pageSize, tempt); 
					soLuong = lbo.getSoLuong(ma_select);
				}
			}
			
			int tongSoTrang = soLuong / pageSize;
			if (soLuong % pageSize != 0)
				tongSoTrang++;
			
			if(maloai != null && tenloai != null)
			{
				if(maloai.equals("") || tenloai.equals(""))
				{
					request.setAttribute("mal2", maloai);
					session.setAttribute("tenl2", tenloai);
					response.sendRedirect("AdminLoaiSPController?rong=1&ma2=" + maloai );
					return;
				}
			}
			
			
			request.setAttribute("tk", key);
			if(request.getParameter("btnadd") != null)
			{
				for(loaibean loai: lbo.getLoai())
				{
					if(loai.getMaloai().equals(maloai))
					{
						response.sendRedirect("AdminLoaiSPController?loi=1");
						return;
					}
				}
			
				if(lbo.ThemLoai(maloai, tenloai) > 0);
				{
					dsLoai = lbo.getListWithPage(page, pageSize, key);
					request.setAttribute("add", "Thêm mới thành công!");
				}
			}else {
				if(request.getParameter("btnupdate") != null)
				{
					String macu = (String) session.getAttribute("getml");
					lbo.Sua(maloai, tenloai, macu);
					dsLoai = lbo.getListWithPage(page, pageSize, key);
					request.setAttribute("update", "Update loại sản phẩm thành công!");
				}else {
					String tab = request.getParameter("tab");
					String ml = request.getParameter("ml"); //get maloai khi select
					
					if(tab != null)
					{
						if(tab.equals("select"))
						{
							session.setAttribute("getml", ml);
							request.setAttribute("tenloai", lbo.Tim(ml));
							request.setAttribute("maloai", ml);	
						}else {
							lbo.Xoa(ml);	
							dsLoai = lbo.getListWithPage(page, pageSize, key); //render lai list
							request.setAttribute("delete", "Xoá loại sản phẩm thành công!");
						}
					}
				}
			}
			
			if(request.getParameter("loi") != null)
				request.setAttribute("error", "Đã tồn tại mã loại này!");
			

			//Khi cac truong rong
			if(request.getParameter("rong") != null)
			{
				String getMa = request.getParameter("ma2");
				String getTen = (String) session.getAttribute("tenl2");
				request.setAttribute("ma2", getMa);
				request.setAttribute("tenl2", getTen);
				request.setAttribute("error2", "Vui lòng nhập đủ trường!");
			}
			
			//pagination
			request.setAttribute("dsloai", dsLoai);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("tsl", soLuong);
			request.setAttribute("tongsotrang", tongSoTrang);
			request.setAttribute("pageSelected", page);
			
			request.setAttribute("ds", lbo.getLoai());
			RequestDispatcher rd = request.getRequestDispatcher("AdminLoaiSP.jsp");
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
