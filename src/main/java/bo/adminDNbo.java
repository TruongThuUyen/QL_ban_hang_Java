package bo;

import bean.adminDNbean;
import dao.adminDNdao;

public class adminDNbo {
	adminDNdao tkdao = new adminDNdao();
	
	public adminDNbean KTDN(String username, String password) throws Exception
	{
		return tkdao.KTDN(username, password);
	}
	
}
