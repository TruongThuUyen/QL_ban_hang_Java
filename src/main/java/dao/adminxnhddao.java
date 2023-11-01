package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import bean.admindsmhbean;
import bean.adminxacnhanbean;

public class adminxnhddao {

	public ArrayList<adminxacnhanbean> getAllCTHD() throws Exception
	{	
		ArrayList<adminxacnhanbean> ds = new ArrayList<adminxacnhanbean>();
		
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "select * from AdminXNHD";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		
		while(rs.next())
		{
			long macthd = rs.getLong("MaCTHD");
			long mahd = rs.getLong("MaHD");
			String hoten = rs.getString("HoTen");
			String tensp = rs.getString("TenSanPham");
			long gia = rs.getLong("Gia") ;
			float mucgiam = rs.getFloat("GiamGia");
			long soluong = rs.getInt("SoLuongMua");
			float tt = rs.getFloat("ThanhTien");
			int status = rs.getInt("Status");
			Date ngaymua = rs.getDate("NgayLap");
			ds.add(new adminxacnhanbean(macthd, mahd, hoten, tensp, gia, mucgiam, soluong, tt, status, ngaymua) );
		}
		
		return ds; 
	}
	
	public int Sua(long macthd) throws Exception
	{
		String sql = "UPDATE ChiTietHoaDon SET Status = 1 WHERE MaCTHD =?";
		PreparedStatement cmd = CoSo.cn.prepareStatement(sql);
		cmd.setLong(1, macthd);
		int kq = cmd.executeUpdate();
		return kq;
	}
	
	public ArrayList<adminxacnhanbean> getListWithPage(int page, int pageSize, int tt) throws Exception {
		try {
			ArrayList<adminxacnhanbean> ds = new ArrayList<adminxacnhanbean>();
			CoSo kn = new CoSo();
			kn.KetNoi();
			String sql = "SELECT * FROM (SELECT MaCTHD, MaHD, Hoten, TenSanPham, Gia, SoLuongMua, GiamGia, Status, ThanhTien, NgayLap,\r\n"
					+ "	ROW_NUMBER() OVER(ORDER BY MaCTHD) AS RowNumber\r\n"
					+ "	FROM  AdminXNHD\r\n"
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
				long macthd = rs.getLong("MaCTHD");
				long mahd = rs.getLong("MaHD");
				String tenkh = rs.getString("HoTen");
				String tensp = rs.getString("TenSanPham");
				long gia = rs.getLong("Gia");
				long slm = rs.getLong("SoLuongMua");
				float ck = rs.getFloat("GiamGia");
				int status = rs.getInt("Status");
				float tongtien = rs.getFloat("ThanhTien"); 
				Date ngaymua = rs.getDate("NgayLap");
				ds.add(new adminxacnhanbean(macthd, mahd, tenkh, tensp, gia, ck, slm,tongtien, status, ngaymua));
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
			String sql = "SELECT COUNT(MaHD) AS SoLuong FROM AdminXNHD WHERE STATUS = ?";
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
	
	
	
	public int UpdateCTHD(long mhd)throws Exception {
		String sql = "UPDATE ChiTietHoaDon SET Status = 1 WHERE MaHD =?";
		PreparedStatement cmd = CoSo.cn.prepareStatement(sql);
		cmd.setLong(1, mhd);
		return cmd.executeUpdate();
	}
	
	//Xac nhan hoa don thanh cong sau khi toan bo cthd xac nhan thanh công
	public void UpdateHD(long mhd)throws Exception {
		String sql = "UPDATE hoadon SET Status = 1 WHERE MaHD=?";
		PreparedStatement cmd = CoSo.cn.prepareStatement(sql);
		cmd.setLong(1, mhd);
		cmd.executeUpdate();
	}

	public long Check(long mhd) throws Exception
	{
		String sql = "SELECT * FROM ChiTietHoaDon WHERE MaHD=?";
		PreparedStatement cmd = CoSo.cn.prepareStatement(sql);
		cmd.setLong(1, mhd);
		ResultSet rs = cmd.executeQuery();
		long tam = 0;
		if(rs.next())
		{
			tam = rs.getLong(1);
		}
		return tam;
		
	}
	
	
}
