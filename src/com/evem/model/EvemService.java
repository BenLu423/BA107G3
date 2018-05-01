package com.evem.model;

import java.util.List;

public class EvemService {
	
	private EvemDAO_interface dao;
	
	public EvemService(){
		dao = new EvemDAO();
	}
	public void addEvem(EvemVO evemvo){
		try{
		dao.insert(evemvo);
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	public void updateEvem(EvemVO evemVO){
	
	dao.update(evemVO);

	}
	public EvemVO getOneEvem(String evemes_no){
		return dao.findByPrimaryKey(evemes_no);	
	}
	public List<EvemVO> getAll(){
		return dao.getAll();	
	}
	public void deleteEvem(String evemes_no) {
		dao.delete(evemes_no);
		
	}
	
	
}