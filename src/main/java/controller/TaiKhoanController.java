package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.khachhangbean;
import bo.khachhangbo;

/**
 * Servlet implementation class TaiKhoanController
 */
@WebServlet("/TaiKhoanController")
public class TaiKhoanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaiKhoanController() {
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
			khachhangbean kh = (khachhangbean) session.getAttribute("dn2");
			String hoten = request.getParameter("txtname");
			String dc = request.getParameter("txtdc");
			String email = request.getParameter("txtemail");
			String sdt = request.getParameter("txtsdt");
			String un = request.getParameter("txtun");
			String pass = request.getParameter("txtpass"); 
			String makh = request.getParameter("makh");
			
			if(makh != null && hoten != null && dc != null && sdt != null && email != null && un != null)
			{
				if(hoten.equals("") || dc.equals("")  || email.equals("") || un.equals(""))
				{
					response.sendRedirect("TaiKhoanController?rong=true");
					return;
				}
				khachhangbo khbo = new khachhangbo();
				if(khbo.UpdateTTKH(Long.parseLong(makh), hoten,dc, email, sdt, un) > 0)
				{
					kh.setHoten(hoten);
					kh.setDiachi(dc);
					kh.setEmail(email);
					kh.setSodt(sdt);
					kh.setTendn(un);
					kh.setPass(pass);
					request.setAttribute("update", "Update thông tin thành công!");		
				}
				
			}
			
			if(request.getParameter("rong")!= null)
				request.setAttribute("rong", "Vui lòng điền đầy đủ thông tin!");
			
			RequestDispatcher rd = request.getRequestDispatcher("Account.jsp");
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
