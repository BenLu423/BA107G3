package com.evec.model;

public class EvecService {
	
	private EvecDAO_interface dao;
	public EvecService(){
		dao = new EvecDAO();
	}
	
	public EvecVO getOne(String evec_no){
		EvecVO evecvo = dao.findByPrimaryKey(evec_no);
		return evecvo;
	}

}
