package com.giftOrderView.model;

import java.util.*;


public class GiftOrderView_CompositeQuery {

	public static String get_aCondition_For_Oracle(String columnName, String value){
		String aCondition = null;
		switch(columnName){
			case "giftr_no":// 收贈禮編號
			case "mem_no_self":// 贈禮會員編號
			case "mem_name_self":// 贈禮會員姓名
			case "mem_no_other":// 收禮會員編號
			case "mem_name_other":// 收禮會員姓名
			case "gifto_no":// 訂單編號
			case "coup_no":// 折價券編號
			case "giftod_no":// 訂單明細編號
			case "gift_no":// 禮物編號
			case "gift_name":// 禮物名稱
				aCondition = columnName + "='" + value +"'";
				break;
			case "gifto_remark":// 訂單備註
			case "coup_name":// 折價券名稱
			case "gift_cnt":// 禮物簡述
				aCondition = columnName + " like '%" + value + "%'";
				break;
			case "giftr_time":// 收贈禮時間
			case "gifto_time":// 訂單時間
			case "coup_value":// 折價券面額
			case "gift_price":// 禮物價格
			case "giftod_amount":// 購買數量
			case "giftod_money":// 購買金額
			case "giftod_inventory":// 可用剩餘數量
			case "giftr_amount":// 收贈禮數量
				break;
			case "keyWord":
				aCondition 	= "giftr_no like '%" + value +"%' or " 
						   	+ "gifto_no like '%" + value +"%' or "
							+ "giftod_no like '%" + value +"%'";
				break;
		}
		
		return aCondition + " ";
	}
	
	public static String get_WhereCondition(Map<String, String[]> map){
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		whereCondition.append("select * from gift_order_view ");
		int count=0;
		for(String key: keys){
			String value = map.get(key)[0];
			if(value != null && value.trim().length() != 0 && !"action".equals(key) && !"requestURL".equals(key)){
				String aCondition = get_aCondition_For_Oracle(key, value.trim());
				if (count++ == 0)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);
			}
				
		} 
		whereCondition.append("order by giftr_no desc");
		return whereCondition.toString();
	}
	
//	public static void main(String[] args) {
//
//		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("keyWord", new String[] { "20180402" });
//		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key
//
//		String finalSQL = GiftOrderView_CompositeQuery.get_WhereCondition(map);
//		System.out.println("●●finalSQL = " + finalSQL);
//
//	}
	
}
