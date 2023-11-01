package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.loaibean;
import bean.sanphambean;
import bo.chitiethdbo;
import bo.loaispbo;
import bo.sanphambo;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.buf.StringUtils;

/**
 * Servlet implementation class AdminSanPhamController
 */
@WebServlet("/AdminSanPhamController")
public class AdminSanPhamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSanPhamController() {
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
			sanphambo spbo = new sanphambo();
			loaispbo lbo = new loaispbo();
			chitiethdbo cthdbo = new chitiethdbo();
			
			//Pagination
			String pageStr = request.getParameter("page");
			String pageSizeStr = request.getParameter("ps");
			int page = 1, pageSize = 10;
			request.setAttribute("pageSize", pageSize);
			
			if (pageStr != null && pageSizeStr != null) {
				page = Integer.parseInt(pageStr);
				pageSize = Integer.parseInt(pageSizeStr);
			}

			// Khi chua nhap key
			ArrayList<sanphambean> dssanpham = spbo.getListWithPage(page, pageSize, "");
			// Tim Kiem
			String key2 = null;
			if(request.getParameter("key") != null) //get key tu input
			{
			 	key2 = request.getParameter("key");
			 	if(key2.equals("null"))
			 		key2 = "";
				dssanpham = spbo.getListWithPage(page, pageSize, key2);
				request.setAttribute("key2", key2);	
			}
			
			int soLuong = spbo.getSoLuong(key2);
			int tongSoTrang = soLuong / pageSize;
			if (soLuong % pageSize != 0)
				tongSoTrang++;
			

			//Update / Add
			String tab = request.getParameter("tab");
			String msp = request.getParameter("msp");
			if(tab != null)
			{
				if(tab.equals("select"))
				{
					request.setAttribute("sanpham", spbo.Tim(msp));
					request.setAttribute("updating", 1);
				}else {				
						if(spbo.Xoa(msp) > 0)
							request.setAttribute("delete", "Xoá sản phẩm thành công!");
				}
			}
			

			
			if(request.getParameter("loi") != null)
				request.setAttribute("error1", "Mã sản phẩm đã bị trùng!");
			
			if(request.getParameter("rong") != null)
			{
				request.setAttribute("error2", "Vui lòng nhập đủ trường!");
			}
			
			if(request.getParameter("them") != null)
				request.setAttribute("add", "Thêm sản phẩm thành công!");
			
			if(request.getParameter("sua") != null)
				request.setAttribute("update", "Update sản phẩm thành công!");
			
			if(request.getParameter("xoa") != null)
				request.setAttribute("delete", "Xoá sản phẩm không thành công!");
			
			
			request.setAttribute("dsloai", lbo.getLoai());
			request.setAttribute("masp", spbo.getMaSP());
			
			//Pagination
			request.setAttribute("dssp", dssanpham);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("tsl", soLuong);
			request.setAttribute("tongsotrang", tongSoTrang);
			request.setAttribute("pageSelected", page);
			
			RequestDispatcher rd = request.getRequestDispatcher("AdminSanPham.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
		String dirUrl1 = request.getServletContext().getRealPath("") +  File.separator + "images";
	
		sanphambo spbo = new sanphambo();
		String masp = null;
		String tensp = null;
		String anh = null;
		String gia = null;
		String ck = null;
		String sl = null;
		String ml = null;
		String btnadd = null;
		String btnupdate = null;
		String btncancel = null;

		try {

			List<FileItem> fileItems = upload.parseRequest(request);// Lấy về các đối tượng gửi lên
		// duyệt qua các đối tượng gửi lên từ client gồm file và các control
			for (FileItem fileItem : fileItems) {
			if (!fileItem.isFormField()) {// Nếu ko phải các control=>upfile lên
				// xử lý file
				String nameimg = fileItem.getName();
				if (!nameimg.equals("")) {
					// Lấy đường dẫn hiện tại, chủ ý xử lý trên dirUrl để có đường dẫn đúng
					String dirUrl = request.getServletContext().getRealPath("") + File.separator + "images";
					File dir = new File(dirUrl);
					if (!dir.exists()) {// nếu ko có thư mục thì tạo ra
						dir.mkdir();
					}
					String fileImg = dirUrl + File.separator + nameimg;
					String filePath = "images/" + nameimg;
					anh = filePath;
					File file = new File(fileImg);// tạo file
					try {
						fileItem.write(file);// lưu file
						System.out.println("UPLOAD THÀNH CÔNG...!");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else{// Neu la control

				String fieldName = fileItem.getFieldName();
				if (fieldName.equals("txtmasp"))
					masp = fileItem.getString("utf-8");
				if (fieldName.equals("txttensp"))
					tensp = fileItem.getString("utf-8");
		
				if (fieldName.equals("txtgia"))
					gia = fileItem.getString();
		
				if (fieldName.equals("txtck"))
					ck = fileItem.getString();
			
				if (fieldName.equals("txtsl"))
					sl = fileItem.getString();
				if (fieldName.equals("lang"))
					ml = fileItem.getString("utf-8");
				if (fieldName.equals("btnadd")) 
					btnadd = fileItem.getString();
				if (fieldName.equals("btnupdate")) 
					btnupdate = fileItem.getString();	
				if (fieldName.equals("btncancel")) 
					btncancel = fileItem.getString();	
				}
			}
			
//			System.out.print("anh: " + anh);
//			System.out.print(StringUtils.removeAccent("Sinh Viên Công Nghệ Thông Tin"));
			if (masp.equals("") || tensp.equals("") || gia.equals("") || ck.equals("") || sl.equals("") || ml.equals("") || anh == null) {
				
				if(btncancel != null){ //neu bam nut cancel
					tensp = "";
					gia = "";
					ck =  "";
					sl = "";
					ml = "";
					response.sendRedirect("AdminSanPhamController");
					return;
				}
				sanphambean s = new sanphambean();
				s.setTensp(tensp);
				
				if(gia.equals("")) {
					long gia2 = 0;
					s.setGia(gia2);
				}else
					s.setGia(Long.parseLong(gia));
				if(ck.equals("")) {
					float ck2 = 0;
					s.setGg(ck2);
				}else
					s.setGg(Float.parseFloat(ck));
				
				if(sl.equals("")) {
					long tt2 = 0;
					s.setSl(tt2);
				}else
					s.setSl(Long.parseLong(sl));
				
				s.setMaloai(ml);
				
				session.setAttribute("spbean", s);
				response.sendRedirect("AdminSanPhamController?rong=1"); // k du cac truong
				return;
			}
					
			
			if (btnadd != null) {
				for (sanphambean sp : spbo.getSP()) {
					if (sp.getMasp().equals(masp)) {
						response.sendRedirect("AdminSanPhamController?loi=1"); // trung ma sp
						return;
					}
				}
				
				if(spbo.ThemSP(masp, tensp, anh, Long.parseLong(gia), Float.parseFloat(ck), Long.parseLong(sl), ml) > 0)
				{
					response.sendRedirect("AdminSanPhamController?them=1");
					return;
				}
			}else if(btnupdate != null){
					String img ="";
					if(anh == null)
						 img = spbo.Tim(masp).getAnh();
					else
						img = anh;
					
					if(spbo.SuaSP(masp, tensp, img, Long.parseLong(gia), Float.parseFloat(ck), Long.parseLong(sl), ml) > 0)
					{
						response.sendRedirect("AdminSanPhamController?sua=1");
						return;
					}
			}else if(btncancel != null){
				tensp = "";
				gia = "";
				ck =  "";
				sl = "";
				ml = "";
				response.sendRedirect("AdminSanPhamController");
			}
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}
//		doGet(request, response);
	}

}
