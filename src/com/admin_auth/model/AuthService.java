package com.admin_auth.model;

import java.util.ArrayList;
import java.util.List;

public class AuthService {
	private AdminAuthDAO_interface dao;
	public AuthService(){
		dao = new AdminAuthDAO();
	}
	
	public List<AdminAuthVO> addAuths(String admin_no,String [] auths){
		List<AdminAuthVO> list = new ArrayList<AdminAuthVO>();
		AdminAuthVO authVO =null;
		
		for(int i = 0; i<auths.length;i++){
			authVO = new AdminAuthVO();
			authVO.setAdm_no(admin_no);
			authVO.setAuth_no(auths[i]);
			dao.insert(authVO);	
		}
		return list;
	}
	
	public void deleteAuths(String admin_no,String[]auths){
		for(int i =0 ;i<auths.length;i++){
			dao.delete(admin_no, auths[i]);
		}		
	}

}
