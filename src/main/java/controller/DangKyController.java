package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.dangkybo;

/**
 * Servlet implementation class DangKyController
 */
@WebServlet("/DangKyController")
public class DangKyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DangKyController() {
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
			HttpSession session = request.getSession();
			
			String fullname = request.getParameter("txtname2");
//			String address = request.getParameter("txtad2");
			String email = request.getParameter("txtemail2");
			String username = request.getParameter("txtun2");
			String password = request.getParameter("txtpass2");			

			if (fullname != null && email != null  && username != null  && password != null ){
					if(fullname.equals("") || email.equals("") || username.equals("")|| password.equals("")){
							RequestDispatcher rd = request.getRequestDispatcher("DangKy.jsp?error=1");
							rd.forward(request, response);	
					}else {
				
						dangkybo dkbo = new dangkybo();
						if(dkbo.ktdke(email) == null && dkbo.ktdkun(username) == null)
						{
							dkbo.ThemKH(fullname, email, username, password);
							response.sendRedirect("DangNhapController");
						}else if(dkbo.ktdke(email) != null){
							request.setAttribute("un", username);
							request.setAttribute("fullname", fullname);
//							request.setAttribute("emaildk", email);
							RequestDispatcher rd = request.getRequestDispatcher("DangKy.jsp?error=2");
							rd.forward(request, response);
						}else{
//							request.setAttribute("un", username);
							request.setAttribute("fullname", fullname);
							request.setAttribute("emaildk", email);
							RequestDispatcher rd = request.getRequestDispatcher("DangKy.jsp?error=3");
							rd.forward(request, response);
						}
					}
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("DangKy.jsp");
				rd.forward(request, response);	
			}

			 
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
