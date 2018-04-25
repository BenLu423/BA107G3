package com.evep.model;

import java.util.*;

import com.event.model.EventDAO;
import com.event.model.EventDAO_interface;
import com.evep.model.EvepDAO_interface;
import com.evep.model.EvepVO;

public class EvepService {
	
	private EvepDAO_interface dao;
	
	public EvepService() {
		dao = new EvepDAO();
	}
	public Set<EvepVO> getEventMembers(String eve_no) {
		
		return dao.getEventMembers(eve_no);
			
	}
	public EvepVO addEvep(String mem_no , String eve_no) {    //,byte[] evep_qr

		EvepVO evepVO = new EvepVO();

		evepVO.setMem_no(mem_no);
		evepVO.setEve_no(eve_no);
		
		EvepQRcode evepQRcode = new EvepQRcode(mem_no, eve_no);
		evepVO.setEvep_qr(evepQRcode.generate());
		dao.insert(evepVO);

		return evepVO;
	}
	public void deleteEvep(String mem_no,String eve_no) {
		dao.delete(mem_no, eve_no);
	}

	public void update(String mem_no, String eve_no, String evep_sts){
		dao.update(mem_no, eve_no, evep_sts);
	}
	
	public List<String> getEvenMemNo(String eve_no){
		List<String>list = new ArrayList<String>();
		Set<EvepVO> set = getEventMembers(eve_no);
		for(EvepVO evep : set){
			list.add(evep.getMem_no());
		}
		
		return list;
	}

}
