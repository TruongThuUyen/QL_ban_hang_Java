package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class chitiethddao {
	CoSo kn = new CoSo();
	
	public int ThemCTHD(String masp, long soluong, long mahd, float gg, float tt) throws Exception 
	{
		kn.KetNoi();
		String sql = "INSERT INTO ChiTietHoaDon(MaSanPham, SoLuongMua, MaHD, Status, MucGiam, ThanhTien) VALUES(?,?,?,'0',ROUND(?, 2),?)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, masp);
		cmd.setLong(2, soluong);
		cmd.setLong(3, mahd);
		cmd.setFloat(4, gg); 
		cmd.setFloat(5, tt);
		
		return cmd.executeUpdate();
	}
	
	public int UpdateCTHD(long mhd)throws Exception {
		String sql = "UPDATE ChiTietHoaDon SET Status = '1' WHERE MaHD=?";
		PreparedStatement cmd = CoSo.cn.prepareStatement(sql);
		cmd.setLong(1, mhd);
		return cmd.executeUpdate();
	}
	
	public int UpdateCTHD(long mahd, int status) throws Exception
	{
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "UPDATE ChiTietHoaDon SET Status = ? WHERE MaHD = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, status);
		cmd.setLong(2, mahd);
		int kq = cmd.executeUpdate();

		return kq;
	}
	

	//Truy xuat ma hd tu cthd
	public long GetMaHD(long macthd) throws Exception
	{
		String sql = "select MaHD from ChiTietHoaDon where  MaCTHD =?";
		PreparedStatement cmd = CoSo.cn.prepareStatement(sql);
		cmd.setLong(1, macthd);
		ResultSet rs = cmd.executeQuery();
		long tam = 0;
		
		if(rs.next())
		{
			tam = rs.getLong(1);
		}

		return tam;
	}
	
	//Dem so cthd da thanh toan co cung mahd
	public long DemCTHDDaTT(long mhd)throws Exception {
		String sql = "select COUNT(MaHD) from ChiTietHoaDon where MaHD=? and Status = 1";
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
	
	
	//Dem so chitiethoadon co cung mahd 
	public long DemCTHD(long mhd)throws Exception {
		String sql = "select COUNT(MaHD) from ChiTietHoaDon where MaHD = ?";
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

	
	public int XoaCTHD(long mahd) throws Exception
	{
		String sql = "DELETE FROM ChiTietHoaDon WHERE MaHD = ?";
		PreparedStatement cmd= kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		return cmd.executeUpdate();
	}
	
}
