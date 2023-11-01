package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class diachidao {

	
	//Get ten tp
	public String getTP(long matp) throws Exception
	{
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "select * from provinces where Code = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, matp);
		ResultSet rs = cmd.executeQuery();
		String tentp = "";
		if(rs.next())
		{
			 tentp = rs.getString("name");
		}
		return tentp;
	}
	
	//Get quan/ huyen
	public String getQuan(long maquan) throws Exception
	{
		//
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "select * from districts where Code = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, maquan);
		ResultSet rs = cmd.executeQuery();
		String tentp = "";
		if(rs.next())
		{
			 tentp = rs.getString("name");
		}
		return tentp;
	}
	
	//Get phuong, xa
	public String getPhuong(long maphuong) throws Exception
	{
		//
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "select * from wards where Code = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, maphuong);
		ResultSet rs = cmd.executeQuery();
		String tentp = "";
		if(rs.next())
		{
			 tentp = rs.getString("name");
		}
		return tentp;
	}
	
}
