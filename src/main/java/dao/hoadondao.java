package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import bean.admindsmhbean;
import bean.hoadonbean;

public class hoadondao {
	CoSo kn = new CoSo();
	public long getMaHD(long makh) throws Exception{
		String sql = "SELECT MAX(MaHD) FROM HoaDon";
		PreparedStatement cmd= kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		
		long tam = 0;
		if(rs.next())
		{
			tam = rs.getLong(1);
		}

		rs.close(); 
		kn.cn.close();
		return tam;
	}
	
	public void ThemHD( long makh, long sl, float tt) throws Exception 
	{
		kn.KetNoi();
		String sql = "INSERT INTO HoaDon(MaKH, SoLuong, Status, TongTien) VALUES (?,?,'0',?)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, makh);
		Date n = new Date();
//		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String nn = dd.format(n);
//		Date n2  = dd.parse(nn);
//		cmd.setDate(2, new java.sql.Date(n2.getTime()));
		cmd.setLong(2, sl);
		cmd.setFloat(3, tt);
		cmd.executeUpdate();
	}
	
	
	// get List with page HoaDon
	public ArrayList<hoadonbean> getListWithPageHD(int page, int pageSize, int tt) throws Exception {
		try {
			ArrayList<hoadonbean> ds = new ArrayList<hoadonbean>();
			CoSo kn = new CoSo();
			kn.KetNoi();
			String sql = "SELECT * FROM (SELECT MaHD, MaKH, SoLuong, Status, TongTien, NgayLap,\r\n"
					+ "ROW_NUMBER() OVER(ORDER BY MaHD) AS RowNumber\r\n"
					+ "FROM HoaDon\r\n"
					+ "WHERE Status = ?) AS C\r\n"
					+ "WHERE (? = 0) OR (c.RowNumber BETWEEN (? - 1) * ? + 1 AND ? * ?)\r\n"
					+ "ORDER BY c.RowNumber";
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
				long makh = rs.getLong("MaKH");
				Date ngaymua = rs.getDate("NgayLap");
				long slm = rs.getLong("SoLuong");
				int status = rs.getInt("Status");
				float tongtien = rs.getFloat("TongTien");

				ds.add(new hoadonbean(mahd, makh, ngaymua, slm, status, tongtien));
			}
			rs.close();
			kn.cn.close();
			return ds;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//GET SO LUONG HOADON DA DUOC DUOC DUYET
		public int getSoLuongHD(int tt) throws Exception{
			try {
				int soLuong = 0;
				CoSo kn = new CoSo();
				kn.KetNoi();
				String sql = "SELECT COUNT(MaHD) as SoLuong FROM HoaDon WHERE Status = ?";
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
		
		//Lay hoadon theo ma so trang thai
		public ArrayList<hoadonbean> getHDByID(long mkh, int tt) throws Exception
		{
			ArrayList<hoadonbean> ds = new ArrayList<hoadonbean>();
			CoSo kn = new CoSo();
			kn.KetNoi();
			String sql = "SELECT * FROM HoaDon WHERE Status =? and MaKH =?";
			PreparedStatement cmd = kn.cn.prepareStatement(sql);
			cmd.setInt(1, tt);
			cmd.setLong(2, mkh);	

			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				long mahd = rs.getLong("MaHD");
				long makh = rs.getLong("MaKH");
				Date ngaymua = rs.getDate("NgayLap");
				long slm = rs.getLong("SoLuong");
				int status = rs.getInt("Status");
				float tongtien = rs.getFloat("TongTien");
				ds.add(new hoadonbean(mahd, makh, ngaymua, slm, status, tongtien));
			}
			rs.close();
			kn.cn.close();
			return ds;
			
		}
		
		// get List with page HoaDon dv moi kh
		public ArrayList<hoadonbean> getListWithPageHDKH(int page, int pageSize, int tt, long mkh) throws Exception {
			try {
				ArrayList<hoadonbean> ds = new ArrayList<hoadonbean>();
				CoSo kn = new CoSo();
				kn.KetNoi();
				String sql = "SELECT * FROM (SELECT MaHD, MaKH, SoLuong, Status, TongTien, NgayLap,\r\n"
						+ "ROW_NUMBER() OVER(ORDER BY MaHD) AS RowNumber\r\n" + "FROM HoaDon\r\n"
						+ "WHERE Status = ? and MaKH = ?) AS C\r\n"
						+ "WHERE (? = 0) OR (c.RowNumber BETWEEN (? - 1) * ? + 1 AND ? * ?)\r\n"
						+ "ORDER BY c.RowNumber";
				PreparedStatement cmd = kn.cn.prepareStatement(sql);
				cmd.setInt(1, tt);
				cmd.setLong(2, mkh);
				cmd.setInt(3, pageSize);
				cmd.setInt(4, page);
				cmd.setInt(5, pageSize);
				cmd.setInt(6, page);
				cmd.setInt(7, pageSize);
				ResultSet rs = cmd.executeQuery();
				while (rs.next()) {
					long mahd = rs.getLong("MaHD");
					long makh = rs.getLong("MaKH");
					Date ngaymua = rs.getDate("NgayLap");
					long slm = rs.getLong("SoLuong");
					int status = rs.getInt("Status");
					float tongtien = rs.getFloat("TongTien");

					ds.add(new hoadonbean(mahd, makh, ngaymua, slm, status, tongtien));
				}
				rs.close();
				kn.cn.close();
				return ds;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

		// GET SO LUONG HOADON DA DUOC DUOC DUYET DOI VOI MOI KHACH HANG
		public int getSoLuongHDKH(int tt, long makh) throws Exception {
			try {
				int soLuong = 0;
				CoSo kn = new CoSo();
				kn.KetNoi();
				String sql = "SELECT COUNT(MaHD) as SoLuong FROM HoaDon WHERE Status = ? and MaKH =?";
				PreparedStatement cmd = kn.cn.prepareStatement(sql);
				cmd.setInt(1, tt);
				cmd.setLong(2, makh);
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
	
	
	public int XoaHD() throws Exception
	{ 
		kn.KetNoi();
		String sql = "DELETE FROM HoaDon WHERE DATEDIFF(day,  NgayLap, GETDATE()) > 15 AND Status = 0";
		PreparedStatement cmd= kn.cn.prepareStatement(sql);

		return cmd.executeUpdate();
	}

	
	public int UpdateHD(long mahd, int status) throws Exception
	{
		CoSo kn = new CoSo();
		kn.KetNoi();
		Date n = new Date();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nn = dd.format(n);
		Date n2  = dd.parse(nn);
		
		String sql = "UPDATE HoaDon SET Status = ?, NgayGH = ? WHERE MaHD = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, status);
		cmd.setDate(2, new java.sql.Date(n2.getTime()));
		cmd.setLong(3, mahd);
		int kq = cmd.executeUpdate();

		return kq;
	}
	
	public int UpdateHDHT(long mahd, int status) throws Exception
	{
		CoSo kn = new CoSo();
		kn.KetNoi();
		Date n = new Date();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nn = dd.format(n);
		Date n2  = dd.parse(nn);
		
		String sql = "UPDATE HoaDon SET Status = ?, NgayHT = ? WHERE MaHD = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, status);
		cmd.setDate(2, new java.sql.Date(n2.getTime()));
		cmd.setLong(3, mahd);
		int kq = cmd.executeUpdate();

		return kq;
	}
}
