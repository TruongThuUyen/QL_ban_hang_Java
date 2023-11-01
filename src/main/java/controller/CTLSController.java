package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ctlichsubean;
import bo.ctlichsubo;

/**
 * Servlet implementation class CTLSController
 */
@WebServlet("/CTLSController")
public class CTLSController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CTLSController() {
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
			if(request.getParameter("mahd") != null)
			{
				long ma = Long.parseLong(request.getParameter("mahd"));
				session.setAttribute("mahoadon", ma);
			}
			
			if(session.getAttribute("mahoadon") != null) 
			{	
				long ma =(long) session.getAttribute("mahoadon");
				ctlichsubo ctlsbo = new ctlichsubo();
				ArrayList<ctlichsubean> ctls = ctlsbo.getHD(ma);
				
				if(request.getParameter("tkctls") != null)
				{
					String tk = request.getParameter("tkctls");
					if(tk != "")
						ctls = ctlsbo.TimKiem(tk);
					else {
						RequestDispatcher rd = request.getRequestDispatcher("CTLS.jsp?empty=2");
						rd.forward(request, response);
						return;
					}
				}
				
				long s= 0;
				for(ctlichsubean h: ctls)
					s+= h.getThanhtien();
				BigDecimal payment = new BigDecimal(s).movePointLeft(3);
				request.setAttribute("tongtien", payment);
				request.setAttribute("ctlichsu", ctls);
				RequestDispatcher rd = request.getRequestDispatcher("CTLS.jsp");
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
