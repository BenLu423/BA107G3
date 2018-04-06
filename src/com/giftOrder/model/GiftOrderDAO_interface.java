package com.giftOrder.model;

import java.util.*;

import com.giftOrderDetail.model.*;
import com.giftReceive.model.*;

public interface GiftOrderDAO_interface {
	public void insert(GiftOrderVO giftOrderVO, Map<GiftOrderDetailVO,List<GiftReceiveVO>> giftOrderDetailMap);

}
