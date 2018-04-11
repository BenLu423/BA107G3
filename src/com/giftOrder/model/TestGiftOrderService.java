package com.giftOrder.model;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.giftOrderDetail.model.GiftOrderDetailVO;
import com.giftReceive.model.GiftReceiveVO;

@WebServlet("/TestGiftOrderService")
public class TestGiftOrderService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("BIG5");
		PrintWriter out = res.getWriter();
		GiftOrderService giftOrderSvc = new GiftOrderService();
		String mem_no_self = "M001";
		
		
//測試增加1筆訂單有3筆明細，每筆明細有4筆收贈禮	
		GiftOrderVO giftOrderVO = null;//1筆訂單
		giftOrderVO = new GiftOrderVO();
		giftOrderVO.setMem_no(mem_no_self);
		giftOrderVO.setCoup_no("C001");
		giftOrderVO.setGifto_remark("訂單備註");

		Map<GiftOrderDetailVO, List<GiftReceiveVO>> map = null;//3筆明細
		GiftOrderDetailVO giftOrderDetailVO = null;
		List<GiftReceiveVO> list = null;//4筆收贈禮
		GiftReceiveVO giftReceiveVO = null;
		
		map = new LinkedHashMap<>();
		for(int i=1;i<=3;i++){
			giftOrderDetailVO = new GiftOrderDetailVO();
			giftOrderDetailVO.setGift_no("G00"+(i+5));
			giftOrderDetailVO.setGiftd_no("GD003");
			giftOrderDetailVO.setGiftod_unit(25);
			giftOrderDetailVO.setGiftod_amount(40);
			giftOrderDetailVO.setGiftod_money(1000);
			giftOrderDetailVO.setGiftod_inventory(10);
			list = new ArrayList<>();
			for(int j=1;j<=4;j++){
				giftReceiveVO = new GiftReceiveVO();
				giftReceiveVO.setMem_no_self(mem_no_self);
				giftReceiveVO.setMem_no_other("M00"+(j+1));
				giftReceiveVO.setGiftr_amount(j+5);
				list.add(giftReceiveVO);
			}
			map.put(giftOrderDetailVO, list);
		}
		giftOrderSvc.insert(giftOrderVO, map);
		out.println("插入12筆收贈禮資料");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
		
}
