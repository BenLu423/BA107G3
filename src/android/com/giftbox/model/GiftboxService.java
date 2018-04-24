package android.com.giftbox.model;

import java.util.List;

public class GiftboxService {
	
	GiftboxDAO_interface dao;
	
	public GiftboxService(){
		dao = (GiftboxDAO_interface) new GiftboxDAO();
	}
	
	public List<GiftboxVO> getByMemGift(String mem_no){
		return dao.getByMemGift(mem_no);
	}

}
