package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import bean.ctlichsubean;
import bean.lichsubean;

public class ctlichsudao {
	public ArrayList<ctlichsubean> getHD(long mhd) throws Exception
	{
		CoSo kn = new CoSo();
		kn.KetNoi(); 
		ArrayList<ctlichsubean> ds = new ArrayList<ctlichsubean>();
		
		String sql = "SELECT * FROM CTLichSU WHERE MaHD =? ";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, mhd);
		ResultSet rs = cmd.executeQuery();
		
		while(rs.next())
		{
			java.util.Date date;
			Timestamp timestamp = rs.getTimestamp("NgayLap");
			date = new java.util.Date(timestamp.getTime());

			Date date2 = rs.getDate("NgayGH");
			Date ngayht = rs.getDate("NgayHT");
			long makh = rs.getLong("MaHD");
			String hoten = rs.getString("HoTen");
			String masp = rs.getString("MaSanPham");
			String tensp = rs.getString("TenSanPham");
			String anh = rs.getString("Anh");
			long gia = rs.getLong("Gia");
			long slm = rs.getLong("SoLuongMua");
			float gg = rs.getFloat("GiamGia");
			float tongtien = rs.getFloat("ThanhTien");
//			Date ngaymua = rs.getDate("NgayLap");
			int status = rs.getInt("Status");
			ctlichsubean ct = new ctlichsubean(makh, hoten, masp, tensp, anh, gia, gg, slm, tongtien,date, date2, ngayht ,status);
			ds.add(ct);
		}
		return ds;
	}
}
