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

import bean.sanphambean;
import bo.GioHangbo;
import bo.sanphambo;

/**
 * Servlet implementation class CapNhatSLController
 */
@WebServlet("/CapNhatSLController")
public class CapNhatSLController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CapNhatSLController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			GioHangbo gh = (GioHangbo) session.getAttribute("giohang");
			String slm = request.getParameter("quantity");
			String masp = request.getParameter("masp");
			sanphambo spbo = new sanphambo();
			ArrayList<sanphambean> dstam = (ArrayList<sanphambean>) session.getAttribute("dstam");	
		
			if(masp != null && slm != null)
			{
				long temp2 = Long.parseLong(slm);	
				//ktra so luong trong kho co be hon soluong muon cp nhat hay k?
				if(dstam != null)
				{
					for(sanphambean s : dstam)
					{
						if(s.getMasp().equals(masp))
						{
							if(temp2 > spbo.GetSLBean(masp))
							{
								System.out.print("tem: " + temp2 + "-"+ spbo.GetSLBean(masp) + "\n");
								session.setAttribute("loiquasl", 1);
								response.sendRedirect("GioHangController");
								return;
							}else {
								s.setSl(spbo.GetSLBean(masp) - temp2);
								gh.Sua(masp, temp2);
							}
						}
					}
					
				}
				
				session.setAttribute("giohang", gh);
				session.setAttribute("tt2", gh.TongTien());
				response.sendRedirect("GioHangController");
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("GioHang.jsp?tb=1"); 
				rd.forward(request,response); 
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
