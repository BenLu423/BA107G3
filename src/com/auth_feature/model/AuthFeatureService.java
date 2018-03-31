package com.auth_feature.model;

import java.util.List;

public class AuthFeatureService {
	
	private AuthFeatureDAO_interface dao;
	public AuthFeatureService (){
		dao = new AuthFeatureDAO();
	}
	
	public List<AuthFeatureVO> getAll(){
		return dao.getAll();
	}

}
