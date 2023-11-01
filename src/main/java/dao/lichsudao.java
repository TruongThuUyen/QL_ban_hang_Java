package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import bean.lichsubean;

public class lichsudao {
	
	public ArrayList<lichsubean> getHDDTT(long mkh) throws Exception
	{
		ArrayList<lichsubean> ds = new ArrayList<lichsubean>();
		CoSo kn = new CoSo();
		kn.KetNoi();
		
		String sql = "Select * from HoaDon WHERE MaKH = ? AND Status = 1 ";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, mkh);
		ResultSet rs = cmd.executeQuery();
		
		while(rs.next())
		{
			long mahd = rs.getLong("MaHD");
			long slm = rs.getLong("SoLuong");
			float tongtien = rs.getFloat("TongTien");
			Date ngaymua = rs.getDate("NgayLap");
			int status = rs.getInt("Status");
			lichsubean ls = new lichsubean(mahd, slm,tongtien , ngaymua, status);
			ds.add(ls);
		}
		return ds;
	}
	
	
	public ArrayList<lichsubean> getHDCTT(long mkh) throws Exception
	{
		ArrayList<lichsubean> ds = new ArrayList<lichsubean>();
		CoSo kn = new CoSo();
		kn.KetNoi();
		
		String sql =  "Select * from HoaDon WHERE MaKH = ? AND Status = 0";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, mkh);
		ResultSet rs = cmd.executeQuery();
		
		while(rs.next())
		{
			java.util.Date date ;
			
//			Date ngaymua = rs.getDate("NgayLap");
	
			long mahd = rs.getLong("MaHD");
			long slm = rs.getLong("SoLuong");
			float tongtien = rs.getFloat("TongTien");
			Timestamp timestamp = rs.getTimestamp("NgayLap");
			date = new java.util.Date(timestamp.getTime());
			int status = rs.getInt("Status");
			lichsubean ls = new lichsubean(mahd, slm,tongtien , date, status);
			ds.add(ls);
		}
		return ds;
	}
	
	public ArrayList<lichsubean> getHDDaHT(long mkh) throws Exception
	{
		ArrayList<lichsubean> ds = new ArrayList<lichsubean>();
		CoSo kn = new CoSo();
		kn.KetNoi();
		
		String sql =  "Select * from HoaDon WHERE MaKH = ? AND Status = 2";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, mkh);
		ResultSet rs = cmd.executeQuery();
		
		while(rs.next())
		{
			java.util.Date date ;
			long mahd = rs.getLong("MaHD");
			long slm = rs.getLong("SoLuong");
			float tongtien = rs.getFloat("TongTien");
			Timestamp timestamp = rs.getTimestamp("NgayLap");
			date = new java.util.Date(timestamp.getTime());
			int status = rs.getInt("Status");
			lichsubean ls = new lichsubean(mahd, slm,tongtien , date, status);
			ds.add(ls);
		}
		return ds;
	}
}
