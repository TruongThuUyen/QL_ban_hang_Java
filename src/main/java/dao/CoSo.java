package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class CoSo {
	public static Connection cn;

	public void KetNoi() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String kn = "jdbc:sqlserver://localhost:50889;databaseName=QLBanHang; user=sa; password=123";

		cn = DriverManager.getConnection(kn);

	}
	public static void main(String[] args) {
 
		try {
			CoSo cs = new CoSo(); 
			cs.KetNoi();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
