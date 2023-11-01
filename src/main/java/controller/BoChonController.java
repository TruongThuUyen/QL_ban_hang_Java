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
 * Servlet implementation class BoChonController
 */
@WebServlet("/BoChonController")
public class BoChonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoChonController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
		   	if(session.getAttribute("giohang") != null)
	    	{
	        	ArrayList<sanphambean> dstam = (ArrayList<sanphambean>) session.getAttribute("dstam");	
	        	String msp =  request.getParameter("msp");
	        	String sl = request.getParameter("sl");
	        	GioHangbo ghs = new GioHangbo();
	        	sanphambo spbo = new sanphambo();

	        	ghs = (GioHangbo) session.getAttribute("giohang");
	        	int sh = ghs.ds.size();
	        	if(msp != null)
	        	{
	        		ghs.Xoa(msp);
		        	session.setAttribute("giohang", ghs);
		        	session.removeAttribute("errordl");
		        	
		        	//Cap nhat lai so luong trong dstam khi bo chon 	
					if (dstam != null) {
						long tam = Long.parseLong(sl);
						for (sanphambean sp : dstam) {
							if (sp.getMasp().equals(msp))
								sp.setSl(sp.getSl() + tam);
						}
					}
		        	
		        	response.sendRedirect("GioHangController");
					return; 
	        	}else if(request.getParameter("xoaluachon") != null){
	        		if(request.getParameterValues("cbXoa") == null)
	        		{
	        			session.setAttribute("errordl", "1");
	        			response.sendRedirect("GioHangController");
	        		}else {
	        			session.removeAttribute("errordl");
		        		String []array = request.getParameterValues("cbXoa"); 
		        		String slbyck = request.getParameter("slbyck");
//		        		if (dstam != null) {
//		        			for(String element : array) {
//		        				for (sanphambean sp : dstam) {
//		        					if (sp.getMasp().equals(element))
//		        						sp.setSl(sp.getSl() );
//		        				}
//		        			}
//						}
		        		for(String element : array) 
							for(int i = 0; i < sh; i++) 
								if(ghs.ds.get(i).getMasp().equals(element)) {
									ghs.ds.remove(i); 
									break; 
								}
		        		response.sendRedirect("GioHangController");
						return; 
	        		}
	        		
				 }else{ 
					//Khi xoa tat ca cac mat hang trong gio; so luong = so luong ban dau
					 for(sanphambean s: spbo.getSP())
					{
						dstam.add( new sanphambean(s.getMasp(), "", "", 0, 0, s.getSl(), ""));
					}
					session.removeAttribute("errordl");
					session.removeAttribute("giohang");
				 	ghs.ds.clear(); 
				 	response.sendRedirect("GioHangController");
					return;
				}
					 
	    	}else{
				  RequestDispatcher rd = request.getRequestDispatcher("GioHang.jsp?error=1");
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
