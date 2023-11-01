package bo;

import dao.diachidao;

public class diachibo {
	diachidao dcdao = new diachidao();
	
	public String getTP(long matp) throws Exception
	{
		return dcdao.getTP(matp);
	}
	
	// Get quan/ huyen
	public String getQuan(long maquan) throws Exception {
		return dcdao.getQuan(maquan);
	}

	// Get phuong, xa
	public String getPhuong(long maphuong) throws Exception {
		return dcdao.getPhuong(maphuong);
	}
}
