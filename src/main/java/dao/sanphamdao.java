package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.loaibean;
import bean.sanphambean;

public class sanphamdao {
	CoSo kn = new CoSo();
	//Get masp
	public String getMaSP() throws Exception{
		String sql = "SELECT MAX(MaSanPham) FROM SanPham";
		PreparedStatement cmd= kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		
		String tam = "";
		if(rs.next())
		{
			tam = rs.getString(1);
		}

		rs.close(); 
		kn.cn.close();
		return tam;
	}
	
	//Get soluong cua moi sp trong bang sanpham
	public long getSL(String masp) throws Exception
	{
		long tong = 0;
		CoSo kn = new CoSo();
		kn.KetNoi();
		masp = "%" + masp + "%";
		String sql = "select SoLuong from SanPham WHERE MaSanPham LIKE ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, masp);
		ResultSet rs = cmd.executeQuery();
		if(rs.next())
		{
			tong = rs.getLong(1);
		}

		rs.close(); 
		kn.cn.close();
		return tong;
	}
	
	//Update lai so luong khi ban hang
	public int UpdateSL(long sl, String masp) throws Exception
	{
		CoSo kn = new CoSo();
		kn.KetNoi();
		System.out.print("ma :" + masp + "\n");
		masp = "%" + masp + "%";
		if(sl < 0)
			sl = 0;
		String sql = "update SanPham set SoLuong = ? where MaSanPham LIKE ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, sl);
		cmd.setString(2, masp);
		int kq = cmd.executeUpdate();

		return kq;
	}
	
	//Lay all sp
	public ArrayList<sanphambean> getSanPham() throws Exception
	{
		ArrayList<sanphambean> ds = new ArrayList<sanphambean>();
		
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "select * from SanPham";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		
		while(rs.next())
		{
			String masp = rs.getString("MaSanPham");
			String tensp = rs.getString("TenSanPham");
			String anh = rs.getString("Anh");
			long gia = rs.getLong("Gia") ;
			float giamgia = rs.getFloat("GiamGia");
			long soluong = rs.getLong("SoLuong");
			String maloai = rs.getString("MaLoai");
			ds.add(new sanphambean(masp, tensp, anh, gia, giamgia, soluong, maloai));
		}
		
		return ds;
	}
	
	public ArrayList<sanphambean> getListWithPage(int page, int pageSize, String ktim) throws Exception {
		try {
			ArrayList<sanphambean> ds = new ArrayList<sanphambean>();
			CoSo kn = new CoSo();
			kn.KetNoi();
			ktim = "%" + ktim + "%";
			String sql =	"SELECT * FROM (\r\n"
					+ "SELECT MaSanPham, TenSanPham, Anh, Gia, GiamGia, SoLuong, Loai.MaLoai,\r\n"
					+ "ROW_NUMBER() OVER(ORDER BY MaSanPham) AS RowNumber\r\n"
					+ "FROM SanPham INNER JOIN Loai ON SanPham.MaLoai = LOAI.MaLoai\r\n"
					+ "WHERE (? = N'') OR (TenSanPham LIKE ?) OR (MaSanPham LIKE ?) OR (Loai.MaLoai Like ?)\r\n"
					+ ") AS C\r\n"
					+ "WHERE (? = 0) OR (c.RowNumber BETWEEN (? - 1) * ? + 1 AND ? * ?)\r\n"
					+ "ORDER BY c.RowNumber;";
			PreparedStatement cmd = kn.cn.prepareStatement(sql);
			cmd.setNString(1, ktim);
			cmd.setNString(2, ktim);
			cmd.setNString(3, ktim);
			cmd.setNString(4, ktim);
			cmd.setInt(5, pageSize);
			cmd.setInt(6, page);
			cmd.setInt(7, pageSize);
			cmd.setInt(8, page);
			cmd.setInt(9, pageSize);
			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				String masp = rs.getString("MaSanPham");
				String tensp = rs.getString("TenSanPham");
				String anh = rs.getString("Anh");
				long gia = rs.getLong("Gia") ;
				float giamgia = rs.getFloat("GiamGia");
				long soluong = rs.getLong("SoLuong");
				String maloai = rs.getString("MaLoai");
				
				ds.add(new sanphambean(masp, tensp, anh, gia, giamgia, soluong, maloai));
			}
		
			return ds;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//get list by MaLoai
	public ArrayList<sanphambean> getListWithPageByML(int page, int pageSize, String ktim) throws Exception {
		try {
			ArrayList<sanphambean> ds = new ArrayList<sanphambean>();
			CoSo kn = new CoSo();
			kn.KetNoi();
			ktim = "%" + ktim + "%";
			String sql =	"SELECT * FROM (\r\n"
					+ "SELECT MaSanPham, TenSanPham, Anh, Gia, GiamGia, SoLuong, Loai.MaLoai,\r\n"
					+ "ROW_NUMBER() OVER(ORDER BY MaSanPham) AS RowNumber\r\n"
					+ "FROM SanPham INNER JOIN Loai ON SanPham.MaLoai = LOAI.MaLoai\r\n"
					+ "WHERE (? = N'') OR (Loai.MaLoai Like ?)\r\n"
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
				String masp = rs.getString("MaSanPham");
				String tensp = rs.getString("TenSanPham");
				String anh = rs.getString("Anh");
				long gia = rs.getLong("Gia") ;
				float giamgia = rs.getFloat("GiamGia");
				long soluong = rs.getLong("SoLuong");
				String maloai = rs.getString("MaLoai");
				
				ds.add(new sanphambean(masp, tensp, anh, gia, giamgia, soluong, maloai));
			}
		
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
			if(key.contains("null"))
				key = "%" + "" + "%";
			String sql = "SELECT COUNT(MaSanPham) AS SoLuong FROM SanPham INNER JOIN Loai ON SanPham.MaLoai = LOAI.MaLoai WHERE TenSanPham Like ? OR MaSanPham Like ? OR  Loai.MaLoai Like ?";
			PreparedStatement cmd = kn.cn.prepareStatement(sql);
			cmd.setString(1, key);
			cmd.setString(2, key);
			cmd.setString(3, key);
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
	
	public int ThemSP(String ma, String tensp, String anh, long gia, float gg, long soluong, String maloai) throws Exception
	{
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "INSERT INTO SanPham(MaSanPham, TenSanPham, Anh, Gia, GiamGia, SoLuong, MaLoai) VALUES(?,?,?,?,ROUND(?, 2),?,?)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, ma);
		cmd.setString(2, tensp);
		cmd.setString(3, anh);
		cmd.setLong(4, gia);
		cmd.setFloat(5, gg);
		cmd.setLong(6, soluong);
		cmd.setString(7, maloai);
		int kq = cmd.executeUpdate();
		return kq;
	}
	
	public int SuaSP(String ma, String tensp, String anh, long gia, float gg, long soluong, String maloai) throws Exception
	{
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "UPDATE SanPham SET TenSanPham = ?, Anh =?, Gia=?, GiamGia=ROUND(?, 2), SoLuong=?, MaLoai=? WHERE MaSanPham=?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, tensp);
		cmd.setString(2, anh);
		cmd.setLong(3, gia);
		cmd.setFloat(4, gg);
		cmd.setLong(5, soluong);
		cmd.setString(6, maloai);
		cmd.setString(7, ma);
		int kq = cmd.executeUpdate();
		return kq;
	}
	
	public int Xoa(String msp) throws Exception
	{
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "DELETE FROM SanPham WHERE MaSanPham=?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, msp);
		int kq = cmd.executeUpdate();
		return kq;
	}
}
