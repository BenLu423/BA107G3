package com.evep.model;

import java.util.*;

import com.event.model.EventVO;
import com.gift.model.GiftVO;
import com.giftLabelDetail.model.GiftLabelDetailVO;


public interface EvepDAO_interface {
	
	public void insert(EvepVO evepVO);
	public void delete(String mem_no, String eve_no);
	public void update(String mem_no, String eve_no, String evep_sts);

	public Set<EvepVO> getEventMembers(String eve_no);
//	// �U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
	// public List<EmpVO> getAll(Map<String, String[]> map);
	List<EvepVO> getAll_EVEP();
	byte[] getEVEP_Pic(String mem_no, String eve_no);

	


}
