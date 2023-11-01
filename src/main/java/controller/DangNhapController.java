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
 * Servlet implementation class DangNhapController
 */
@WebServlet("/DangNhapController")
public class DangNhapController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DangNhapController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("un-lg");
			String password = request.getParameter("pass-lg");
			HttpSession session = request.getSession();

			if (username != null && password != null) {
				khachhangbo khbo = new khachhangbo();
				khachhangbean kh = khbo.KTDK(username, password);
				if (kh != null) {
					//Luu un vao session
					session.setAttribute("dn2", kh);
					response.sendRedirect("TrangChuController");
				}else {
					RequestDispatcher rd = request.getRequestDispatcher("DangNhap.jsp?tb=1");
					rd.forward(request, response);
				} 
			}
	
			 RequestDispatcher rd = request.getRequestDispatcher("DangNhap.jsp");
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
