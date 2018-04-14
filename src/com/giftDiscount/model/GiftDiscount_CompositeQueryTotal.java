package com.giftDiscount.model;

import java.util.*;


public class GiftDiscount_CompositeQueryTotal {

	public static String get_aCondition_For_Oracle(String columnName, String value){
		String aCondition = null;
		switch(columnName){
			case "giftd_no":
			case "gift_no":
				aCondition = columnName + "='" + value +"'";
				break;
			case "giftd_percent":
				aCondition = columnName + " like '%" + value + "%'";
				break;
			case "giftd_start":				
			case "giftd_end":
			case "giftd_amount":
				break;
			case "keyWord":
				aCondition 	= "giftd_no like '%" + value +"%' or " 
						   	+ "gift_no like '%" + value +"%'";
				break;
		}
		
		return aCondition + " ";
	}
	
	public static String get_WhereCondition(Map<String, String[]> map){
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		whereCondition.append("select * from gift_discount ");
		int count=0;
		for(String key: keys){
			String value = map.get(key)[0];
			if(value != null && value.trim().length() != 0 && !"action".equals(key) 
					&& !"requestURL".equals(key) && !"edit_giftd_no".equals(key)
					&& !"whichPage".equals(key)){
				String aCondition = get_aCondition_For_Oracle(key, value.trim());
				if (count++ == 0)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);
			}
				
		}
		whereCondition.append(" order by giftd_no desc");
		return whereCondition.toString();
	}
	
//	public static void main(String argv[]) {
//
//		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("gift_no", new String[] { "G003" });
//		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key
//
//		String finalSQL = GiftDiscount_CompositeQuery.get_WhereCondition(map);
//		System.out.println("●●finalSQL = " + finalSQL);
//
//	}
	
}
