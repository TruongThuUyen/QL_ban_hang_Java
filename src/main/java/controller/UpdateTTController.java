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
import bo.diachibo;
import bo.khachhangbo;
import dao.khachhangdao;

/**
 * Servlet implementation class UpdateTTController
 */
@WebServlet("/UpdateTTController")
public class UpdateTTController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTTController() {
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
			khachhangbo khbo = new khachhangbo();
			diachibo dcbo = new diachibo();
			
			if(request.getParameter("doidc") != null)
			{
				request.setAttribute("show", 1); //show input
				RequestDispatcher rd = request.getRequestDispatcher("ChiTietHD.jsp");
				rd.forward(request, response);
				return;
			}
			

			
			if(request.getParameter("doisdt") != null)
			{
				request.setAttribute("show2", 1); //show input
				RequestDispatcher rd = request.getRequestDispatcher("ChiTietHD.jsp");
				rd.forward(request, response);
				return;
			}
			
			long temp1= 0;
			String dc= "";
			//Update address
			if(request.getParameter("address") != null)
			{
				dc = request.getParameter("address");
				// Doi dc dat hang tai CTHD
				String tp = request.getParameter("tp");
				String quan = request.getParameter("huyen");
				String xa = request.getParameter("phuong");
				String tentp = "", tenquan = "", tenphuong = "";
				if (tp != null && quan != null && xa != null && dc != null) {
					if(tp.equals("") ||  quan.equals("") || xa.equals("") || dc.equals("")){
						request.setAttribute("loidc", 1);
						request.setAttribute("show", 1);
					}else {
						tentp = dcbo.getTP(Long.parseLong(tp));
						tenquan = dcbo.getQuan(Long.parseLong(quan));
						tenphuong = dcbo.getPhuong(Long.parseLong(xa));
						
						dc = dc + ", " + tenphuong + ", " + tenquan + ", " + tentp; 
						if(khbo.UpdateDC(dc, kh.getMakh()) != 0) {
							request.setAttribute("dc", "Cập nhật địa chỉ thành công!");
							temp1 = 1;
							request.setAttribute("dcnha", dc);
						};
						
					}
						
				}
					
				
			}else if(request.getParameter("mode") != null) {
				System.out.print("huy");
			}
			
			//Update sdt
			long temp2 = 0;
			String sdt= "";
			if(request.getParameter("phone") != null)
			{
				
				sdt = request.getParameter("phone");
				if(sdt.equals(""))
				{
					response.sendRedirect("CTHDController?sodt=null");
					return;
				}
				// Kiem tra dinh dang sdt
				String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
				boolean kt = sdt.matches(reg);
				if (kt == true) {
					if(khbo.UpdateSDT(sdt, kh.getMakh()) != 0)
						temp2 =1;
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("ChiTietHD.jsp?error=1");
					rd.forward(request, response);
				}
			}
			

			
			if(temp1 != 0)
				kh.setDiachi(dc);
			if(temp2 != 0)
				kh.setSodt(sdt);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("ChiTietHD.jsp");
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
