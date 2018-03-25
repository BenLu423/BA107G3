package com.admin.model;

public class AdminVO implements java.io.Serializable{
	private String adm_no;
	private String adm_acct;
	private String adm_pwd;
	private String adm_name;
	
	public AdminVO(){
		
	}
	
	public String getAdm_no() {
		return adm_no;
	}
	public void setAdm_no(String adm_no) {
		this.adm_no = adm_no;
	}
	public String getAdm_acct() {
		return adm_acct;
	}
	public void setAdm_acct(String adm_acct) {
		this.adm_acct = adm_acct;
	}
	public String getAdm_pwd() {
		return adm_pwd;
	}
	public void setAdm_pwd(String adm_pwd) {
		this.adm_pwd = adm_pwd;
	}
	public String getAdm_name() {
		return adm_name;
	}
	public void setAdm_name(String adm_name) {
		this.adm_name = adm_name;
	}
	
	

}
