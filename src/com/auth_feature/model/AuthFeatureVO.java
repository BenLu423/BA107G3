package com.auth_feature.model;

public class AuthFeatureVO implements java.io.Serializable{
	private String auth_no;
	private String auth_name;
	
	public AuthFeatureVO(){
		
	}

	public String getAuth_no() {
		return auth_no;
	}

	public void setAuth_no(String auth_no) {
		this.auth_no = auth_no;
	}

	public String getAuth_name() {
		return auth_name;
	}

	public void setAuth_name(String auth_name) {
		this.auth_name = auth_name;
	}
	

}
