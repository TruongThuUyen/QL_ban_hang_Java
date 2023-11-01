package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.adminDNbean;

public class adminDNdao {
	
	public adminDNbean KTDN(String username, String password) throws Exception{
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "select * from AdminDN where TenDN = ? and MatKhau =?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, username);
		cmd.setString(2, password);
		ResultSet rs = cmd.executeQuery();
		
		adminDNbean tk = null;
		if(rs.next())
		{
			String maloai = rs.getString("TenDN");
			String tenloai = rs.getString("MatKhau");
			tk = new adminDNbean(username,  password);
		}
		
		return tk;
	}
	
}
