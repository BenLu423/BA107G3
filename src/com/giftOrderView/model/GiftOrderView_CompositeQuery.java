package com.giftOrderView.model;

import java.util.*;


public class GiftOrderView_CompositeQuery {

	public static String get_aCondition_For_Oracle(String columnName, String value){
		String aCondition = null;
		switch(columnName){
			case "giftr_no":// ����§�s��
			case "mem_no_self":// ��§�|���s��
			case "mem_name_self":// ��§�|���m�W
			case "mem_no_other":// ��§�|���s��
			case "mem_name_other":// ��§�|���m�W
			case "gifto_no":// �q��s��
			case "coup_no":// �����s��
			case "giftod_no":// �q����ӽs��
			case "gift_no":// §���s��
			case "gift_name":// §���W��
				aCondition = columnName + "='" + value +"'";
				break;
			case "gifto_remark":// �q��Ƶ�
			case "coup_name":// �����W��
			case "gift_cnt":// §��²�z
				aCondition = columnName + " like '%" + value + "%'";
				break;
			case "giftr_time":// ����§�ɶ�
			case "gifto_time":// �q��ɶ�
			case "coup_value":// ����魱�B
			case "gift_price":// §������
			case "giftod_amount":// �ʶR�ƶq
			case "giftod_money":// �ʶR���B
			case "giftod_inventory":// �i�γѾl�ƶq
			case "giftr_amount":// ����§�ƶq
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
//		// �t�X req.getParameterMap()��k �^�� java.util.Map<java.lang.String,java.lang.String[]> ������
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("keyWord", new String[] { "20180402" });
//		map.put("action", new String[] { "getXXX" }); // �`�NMap�̭��|�t��action��key
//
//		String finalSQL = GiftOrderView_CompositeQuery.get_WhereCondition(map);
//		System.out.println("����finalSQL = " + finalSQL);
//
//	}
	
}
