package com.evem.model;

import java.util.List;

public class EvemService {
	
	private EvemDAO_interface dao;
	
	public EvemService(){
		dao = new EvemDAO();
	}
	public EvemVO addEvem(String evemes_no, String eve_no , String mem_no , java.sql.Timestamp evemes_time , String evemes_cnt , java.sql.Timestamp evemes_restime,String evemes_rescnt){
		EvemVO evemVO = new EvemVO();
		evemVO.setEvemes_no(evemes_no);
		evemVO.setEve_no(eve_no);
		evemVO.setMem_no(mem_no);
		evemVO.setEvemes_time(evemes_time);
		evemVO.setEvemes_cnt(evemes_cnt);
		evemVO.setEvemes_restime(evemes_restime);
		evemVO.setEvemes_rescnt(evemes_rescnt);
		
		return evemVO;
	
	}
	public EvemVO updateEvem(String evemes_no, String eve_no , String mem_no , java.sql.Timestamp evemes_time , String evemes_cnt , java.sql.Timestamp evemes_restime,String evemes_rescnt){
	
	EvemVO evemVO= new EvemVO();
	evemVO.setEvemes_no(evemes_no);
	evemVO.setEve_no(eve_no);
	evemVO.setMem_no(mem_no);
	evemVO.setEvemes_time(evemes_time);
	evemVO.setEvemes_cnt(evemes_cnt);
	evemVO.setEvemes_restime(evemes_restime);
	evemVO.setEvemes_rescnt(evemes_rescnt);

	return evemVO;

	}
	public EvemVO getOneEvem(String evemes_no){
		return dao.findByPrimaryKey(evemes_no);	
	}
	public List<EvemVO> getAll(){
		return dao.getAll();	
	}
	
	
}