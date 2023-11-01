package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import bean.admindsmhbean;
import bean.loaibean;

public class admindsmhdao {

	public ArrayList<admindsmhbean> getDSDaXN() throws Exception
	{
		ArrayList<admindsmhbean> ds = new ArrayList<admindsmhbean>();
		
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "SELECT * FROM CTLichSu WHERE Status = 1";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		
		while(rs.next())
		{
			long mahd = rs.getLong("MaHD");
			String tenkh = rs.getString("HoTen");
			String tensp = rs.getString("TenSanPham");
			long gia = rs.getLong("Gia");
			long slm = rs.getLong("SoLuongMua");
			float ck = rs.getFloat("GiamGia");
			int status = rs.getInt("Status");
			float tongtien = rs.getFloat("ThanhTien");
			Date ngaymua = rs.getDate("NgayLap");
			ds.add(new admindsmhbean(mahd, tenkh, tensp, gia, slm, ck, status, tongtien, ngaymua));
		}
		
		return ds;
	}
	
	public ArrayList<admindsmhbean> getDSChuaXN() throws Exception
	{
		ArrayList<admindsmhbean> ds = new ArrayList<admindsmhbean>();
		
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "SELECT * FROM CTLichSu WHERE Status = 0";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		
		while(rs.next())
		{
			long mahd = rs.getLong("MaHD");
			String tenkh = rs.getString("HoTen");
			String tensp = rs.getString("MaSanPham");
			long gia = rs.getLong("Gia");
			long slm = rs.getLong("SoLuongMua");
			float ck = rs.getFloat("GiamGia");
			int status = rs.getInt("Status");
			float tongtien = rs.getFloat("ThanhTien");
			Date ngaymua = rs.getDate("NgayLap");
			ds.add(new admindsmhbean(mahd, tenkh, tensp, gia, slm, ck, status, tongtien, ngaymua));
		}
		
		return ds;
	}
	
	public ArrayList<admindsmhbean> getListWithPage(int page, int pageSize, int tt) throws Exception {
		try {
			ArrayList<admindsmhbean> ds = new ArrayList<admindsmhbean>();
			CoSo kn = new CoSo();
			kn.KetNoi();
			String sql = "SELECT * FROM (SELECT MaHD, Hoten, TenSanPham, Gia, SoLuongMua, GiamGia, Status, ThanhTien, NgayLap,\r\n"
					+ "	ROW_NUMBER() OVER(ORDER BY MaCTHD) AS RowNumber\r\n"
					+ "	FROM CTLichSu\r\n"
					+ "	WHERE Status = ?) AS C\r\n"
					+ "	WHERE (? = 0) OR (c.RowNumber BETWEEN (? - 1) * ? + 1 AND ? * ?)\r\n"
					+ "	ORDER BY c.RowNumber";
			PreparedStatement cmd = kn.cn.prepareStatement(sql);
			cmd.setInt(1, tt);
			cmd.setInt(2, pageSize);
			cmd.setInt(3, page);
			cmd.setInt(4, pageSize);
			cmd.setInt(5, page);
			cmd.setInt(6, pageSize);
			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				long mahd = rs.getLong("MaHD");
				String tenkh = rs.getString("HoTen");
				String tensp = rs.getString("TenSanPham");
				long gia = rs.getLong("Gia");
				long slm = rs.getLong("SoLuongMua");
				float ck = rs.getFloat("GiamGia");
				int status = rs.getInt("Status");
				float tongtien = rs.getFloat("ThanhTien");
				Date ngaymua = rs.getDate("NgayLap");
				
				ds.add(new admindsmhbean(mahd, tenkh, tensp, gia, slm, ck, status, tongtien, ngaymua));
			}
			rs.close();
			kn.cn.close();
			return ds;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
		
	
	public int getSoLuong(int tt) throws Exception{
		try {
			int soLuong = 0;
			CoSo kn = new CoSo();
			kn.KetNoi();
			String sql = "SELECT COUNT(MaHD) AS SoLuong FROM CTLichSu WHERE STATUS = ?";
			PreparedStatement cmd = kn.cn.prepareStatement(sql);
			cmd.setInt(1, tt);
			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				soLuong = rs.getInt("SoLuong");
			}
		
			rs.close();
			kn.cn.close();

			return soLuong;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}
