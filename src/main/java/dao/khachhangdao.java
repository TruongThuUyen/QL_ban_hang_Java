package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.khachhangbean;

public class khachhangdao {
	
	public khachhangbean KTDN(String username, String password)  throws Exception
	{
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "select * from KhachHang where TenDN=? and Password=?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, username);
		cmd.setString(2, password);
		ResultSet rs = cmd.executeQuery();
		
		khachhangbean kh = null;
		if(rs.next())
		{
			long makh= rs.getLong("MaKH");
			String hoten = rs.getString("HoTen");
			String diachi = rs.getString("DiaChi");
			String email = rs.getString("Email");
			String sodt = rs.getString("SDT");
			String tendn = rs.getString("TenDN");
			String pass = rs.getString("Password");
			kh = new khachhangbean(makh, hoten, diachi, email, sodt, tendn, pass);
		}

		rs.close();
		kn.cn.close();
		return kh;
	}
	
	public khachhangbean KTDKE(String email)  throws Exception
	{
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "select * from KhachHang where Email=?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, email);
		ResultSet rs = cmd.executeQuery();
		
		khachhangbean kh = null;
		if(rs.next())
		{
			long makh= rs.getLong("MaKH");
			String hoten = rs.getString("HoTen");
			String diachi = rs.getString("DiaChi");
			String email2 = rs.getString("Email");
			String sodt = rs.getString("SDT");
			String tendn = rs.getString("TenDN");
			String pass = rs.getString("Password");
			kh = new khachhangbean(makh, hoten, diachi, email2, sodt, tendn, pass);
		}
		return kh;
	}
	
	public ArrayList<khachhangbean> getDSKH()  throws Exception
	{
		ArrayList<khachhangbean> ds = new ArrayList<khachhangbean>();
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "select * from KhachHang";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		
		while(rs.next())
		{
			long makh= rs.getLong("MaKH");
			String hoten = rs.getString("HoTen");
			String diachi = rs.getString("DiaChi");
			String email2 = rs.getString("Email");
			String sodt = rs.getString("SDT");
			String tendn = rs.getString("TenDN");
			String pass = rs.getString("Password");
			ds.add(new khachhangbean(makh, hoten, diachi, email2, sodt, tendn, pass));
		}
		return ds;
	}
	

	public khachhangbean KTDKUN(String username)  throws Exception
	{
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "select * from KhachHang where TenDN=?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, username);
		ResultSet rs = cmd.executeQuery();
		
		khachhangbean kh = null;
		if(rs.next())
		{
			long makh= rs.getLong("MaKH");
			String hoten = rs.getString("HoTen");
			String diachi = rs.getString("DiaChi");
			String email = rs.getString("Email");
			String sodt = rs.getString("SDT");
			String tendn = rs.getString("TenDN");
			String pass = rs.getString("Password");
			kh = new khachhangbean(makh, hoten, diachi, email, sodt, tendn, pass);
		}

//		rs.close();
//		kn.cn.close();
		return kh;
	}
	
	public int ThemKH(String hoten, String email, String tendn, String pass)
		throws Exception{
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "INSERT INTO KhachHang(HoTen, Email, TenDN, Password) VALUES (?,?,?,?)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, hoten);
		cmd.setString(2, email);
		cmd.setString(3, tendn);
		cmd.setString(4, pass);

//		kn.cn.close();
		return cmd.executeUpdate();
	}
	
	public int UpdateAddress(String address, long makh) throws Exception
	{
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "UPDATE KhachHang SET DiaChi=? where MaKH=?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, address);
		cmd.setLong(2, makh);
		return cmd.executeUpdate();
	}
	

	public int UpdateSDT(String sdt, long makh) throws Exception
	{
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "UPDATE KhachHang SET SDT=? where MaKH=?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, sdt);
		cmd.setLong(2, makh);
		return cmd.executeUpdate();
	}
	
	public int UpdateTTKH(long makh, String hoten, String diachi, String email, String sodt, String tendn)
	throws Exception {
		CoSo kn = new CoSo();
		kn.KetNoi();
		String sql = "Update KhachHang SET HoTen=?, DiaChi=?, Email=?, SDT=?, TenDN=? WHERE  MaKH =?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, hoten);
		cmd.setString(2, diachi);
		cmd.setString(3, email);
		cmd.setString(4, sodt);
		cmd.setString(5, tendn);
		cmd.setLong(6, makh);
		return cmd.executeUpdate();
	}
} 
