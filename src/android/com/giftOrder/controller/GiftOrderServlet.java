package android.com.giftOrder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.giftDiscount.model.GiftDiscountService;
import com.giftDiscount.model.GiftDiscountVO;
import com.giftOrder.model.GiftOrderService;
import com.giftOrder.model.GiftOrderVO;
import com.giftOrder.ws.GiftOrderWS;
import com.giftOrderDetail.model.GiftOrderDetailVO;
import android.com.giftReceive.model.GiftReceiveVOA;
import com.giftReceive.model.GiftReceiveVO;

public class GiftOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		String outStr = "";
		GiftOrderService goSvc = new GiftOrderService();
		GiftDiscountService gdSvc = new GiftDiscountService();
		List<GiftDiscountVO> giftDlist = new ArrayList<>();
		List<GiftOrderDetailVO> goDetailList = new ArrayList<>();
		Map<GiftOrderDetailVO, List<GiftReceiveVO>> orderMap = new HashMap<>();
		String action = req.getParameter("action");
		System.out.println(action);
		if ("insertGiftOrder".equals(action)) {
			giftDlist = gdSvc.getAll();
			String jsonDetailList = req.getParameter("goDetailList");
			goDetailList = gson.fromJson(jsonDetailList.toString(), new TypeToken<List<GiftOrderDetailVO>>() {
			}.getType());
			for (int i = 0; i < giftDlist.size(); i++) {
				for (int j = 0; j < goDetailList.size(); j++) {
					if (giftDlist.get(i).getGift_no().equals(goDetailList.get(j).getGift_no())
							&& goDetailList.get(j).getGiftd_no() != null) {
						if (giftDlist.get(i).getGiftd_amount() < goDetailList.get(j).getGiftod_amount()) {
							outStr = gson.toJson(giftDlist);
							break;
						}
					}
				}
			}
			if (outStr.isEmpty()) {
				String jsonReceiveList = req.getParameter("gReceiveList");
				List<GiftReceiveVOA> gReceiveList = gson.fromJson(jsonReceiveList.toString(),
						new TypeToken<List<GiftReceiveVOA>>() {
						}.getType());

				for (int i = 0; i < goDetailList.size(); i++) {
					List<GiftReceiveVO> list = new ArrayList<>();
					for (int j = 0; j < gReceiveList.size(); j++) {
						GiftReceiveVOA giftReceiveVOA  = gReceiveList.get(j);
						if (goDetailList.get(i).getGift_no().equals(giftReceiveVOA.getGift_no())) {
							GiftReceiveVO giftReceiveVO = new GiftReceiveVO();
							giftReceiveVO.setMem_no_self(giftReceiveVOA.getMem_no_self());
							giftReceiveVO.setMem_no_other(giftReceiveVOA.getMem_no_other());
							giftReceiveVO.setGiftr_amount(giftReceiveVOA.getGiftr_amount());
							list.add(giftReceiveVO);
						}
					}
					orderMap.put(goDetailList.get(i), list);
				}
				String jsonGiftOrderVO = req.getParameter("jsonGiftOrderVO");
				GiftOrderVO giftOrderVO = new Gson().fromJson(jsonGiftOrderVO.toString(), GiftOrderVO.class);
				try {
					goSvc.insert(giftOrderVO, orderMap);
					outStr = "���b���\";
					GiftOrderWS giftOrderWS = new GiftOrderWS();
					giftOrderWS.multipleSendGift("sendGift", orderMap);
				} catch (Exception e) {
					System.out.println(e.getStackTrace());
					outStr = "���B����";
				}
			}
		}

		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		System.out.println(outStr);
		out.print(outStr);
		out.close();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
