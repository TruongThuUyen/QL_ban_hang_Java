package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.loaibean;

public class loaispdao {
	//Get All Loai 
	public ArrayList<loaibean> getloai() throws Exception{
		ArrayList<loaibean> ds = new ArrayList<loaibean>();
		
		CoSo kn = new CoSo();
		String sql = "SELECT * FROM Loai";
		kn.KetNoi();
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		
		while(rs.next())
		{
			String maloai = rs.getString("MaLoai");
			String tenloai = rs.getString("TenLoai");
			ds.add(new loaibean(maloai, tenloai));
		}
		
		return ds;
	}
	
	//Get Loai by id 
	public ArrayList<loaibean> getLoaiById(String ml) throws Exception{
		ArrayList<loaibean> ds = new ArrayList<loaibean>();
		
		CoSo kn = new CoSo();
		kn.KetNoi();
		ml = "%" + ml + "%";
		String sql = "SELECT * FROM Loai WHERE MaLoai Like ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, ml);
		ResultSet rs = cmd.executeQuery();
		
		while(rs.next())
		{
			String maloai = rs.getString("MaLoai");
			String tenloai = rs.getString("TenLoai");
			ds.add(new loaibean(maloai, tenloai));
		}
		
		return ds;
	}
	
	//Kiem tra xem: loai hang nay co san pham hay chua?
	public int Check(String ml) throws Exception{
		try {
			int soLuong = 0;
			CoSo kn = new CoSo();
			kn.KetNoi();
			String sql = "SELECT COUNT(Loai.MaLoai) AS SoLuong, TenLoai FROM Loai inner JOIN SanPham ON Loai.MaLoai = sanpham.MaLoai WHERE Loai.MaLoai LIKE ?\r\n"
					+ "GROUP BY TenLoai";
			PreparedStatement cmd = kn.cn.prepareStatement(sql);
			cmd.setString(1, ml);
			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				soLuong = rs.getInt("SoLuong");
			}

			return soLuong;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public ArrayList<loaibean> getListWithPage(int page, int pageSize, String ktim) throws Exception {
		try {
			ArrayList<loaibean> ds = new ArrayList<loaibean>();
			CoSo kn = new CoSo();
			kn.KetNoi();
			ktim = "%" + ktim + "%";
			String sql =	"SELECT * FROM (\r\n"
					+ "SELECT MaLoai, TenLoai, \r\n"
					+ "	ROW_NUMBER() OVER(ORDER BY MaLoai) AS RowNumber\r\n"
					+ "FROM Loai\r\n"
					+ "WHERE (? = N'') OR (TenLoai LIKE ?)\r\n"
					+ ") AS C\r\n"
					+ "WHERE (? = 0) OR (c.RowNumber BETWEEN (? - 1) * ? + 1 AND ? * ?)\r\n"
					+ "ORDER BY c.RowNumber;";
			PreparedStatement cmd = kn.cn.prepareStatement(sql);
			cmd.setNString(1, ktim);
			cmd.setNString(2, ktim);
			cmd.setInt(3, pageSize);
			cmd.setInt(4, page);
			cmd.setInt(5, pageSize);
			cmd.setInt(6, page);
			cmd.setInt(7, pageSize);
			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				String MaLoai = rs.getString("MaLoai");
				String TenLoai = rs.getString("TenLoai");
				
				ds.add(new loaibean(MaLoai, TenLoai));
			}
			rs.close();
			kn.cn.close();
			return ds;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getSoLuong(String key) throws Exception{
		try {
			int soLuong = 0;
			CoSo kn = new CoSo();
			kn.KetNoi();
			key = "%" + key + "%";
			String sql = "SELECT COUNT(MaLoai) AS SoLuong FROM Loai WHERE MaLoai LIKE ?";
			PreparedStatement cmd = kn.cn.prepareStatement(sql);
			cmd.setString(1, key);
			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				soLuong = rs.getInt("SoLuong");
			}
			rs.close();
			kn.cn.close();
			return soLuong;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public int ThemLoai(String ml, String tl) throws Exception
	{
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "INSERT INTO Loai(MaLoai, TenLoai) values(?,?)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, ml);
		cmd.setString(2, tl);
		int kq = cmd.executeUpdate();
//		cmd.close();
//		kn.cn.close();
		return kq;
	}
	
	public int Sua(String mlmoi, String tl, String ml) throws Exception
	{
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "UPDATE Loai SET MaLoai=?, TenLoai=? where MaLoai=?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, mlmoi);
		cmd.setString(2, tl);
		cmd.setString(3, ml);
		int kq = cmd.executeUpdate();
//		cmd.close();
//		kn.cn.close();
		return kq;
	}
	
	public int Xoa(String ml) throws Exception
	{
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "DELETE FROM Loai WHERE MaLoai=?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, ml);
		int kq = cmd.executeUpdate();
//		cmd.close();
//		kn.cn.close();
		return kq;
	}
	
	
}
