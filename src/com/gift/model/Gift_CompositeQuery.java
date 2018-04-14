package com.gift.model;

import java.util.*;


public class Gift_CompositeQuery {

	public static String get_aCondition_For_Oracle(String columnName, String value){
		String aCondition = null;
		switch(columnName){
			case "gift_no":
			case "gift_is_on":
				aCondition = columnName + "='" + value +"'";
				break;
			case "gift_name":
			case "gift_cnt":
				aCondition = columnName + " like '%" + value + "%'";
				break;
			case "gift_price":				
			case "gift_track_qty":
			case "gift_buy_qty":
				break;
			case "keyWord":
				aCondition 	= "gift_no like '%" + value +"%' or " 
						   	+ "gift_name like '%" + value +"%' or "
							+ "gift_cnt like '%" + value +"%' or "
							+ "gift_price like '%" + value +"%'";
				break;
		}
		
		return aCondition + " ";
	}
	
	public static String get_WhereCondition(Map<String, String[]> map){
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		whereCondition.append("select * from gift ");
		int count=0;
		for(String key: keys){
			String value = map.get(key)[0];
			if(value != null && value.trim().length() != 0 && !"tabWho".equals(key)
					&& !"action".equals(key) && !"requestURL".equals(key) 
					&& !"whichPage".equals(key) && !"edit_gift_no".equals(key)){
				String aCondition = get_aCondition_For_Oracle(key, value.trim());
				if (count++ == 0)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);
			}
				
		} 
		whereCondition.append("order by gift_no desc");
		return whereCondition.toString();
	}
	
//	public static void main(String argv[]) {
//
//		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("gift_no", new String[] { "G001" });
//		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key
//
//		String finalSQL = Gift_CompositeQuery.get_WhereCondition(map);
//		System.out.println("●●finalSQL = " + finalSQL);
//
//	}
	
}
