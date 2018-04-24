package android.com.giftOrder.model;
import java.util.List;
import java.util.Map;

import android.com.giftOrderDetail.model.GiftOrderDetailVO;
import android.com.giftReceive.model.GiftReceiveVOA;


public class GiftOrderService {

	GiftOrderDAO_interface dao;

	public GiftOrderService() {
		dao = new GiftOrderDAO();
	}

	public void insert(GiftOrderVO giftOrderVO, Map<GiftOrderDetailVO, List<GiftReceiveVOA>> giftOrderDetailMap) {
		dao.insert(giftOrderVO, giftOrderDetailMap);
	}

}
