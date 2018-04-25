package com.admin.model;

import java.util.*;

import com.admin_auth.model.AdminAuthVO;
import com.admin_auth.model.AuthService;
import com.auth_feature.model.AuthFeatureService;
import com.auth_feature.model.AuthFeatureVO;

public class AdminVO implements java.io.Serializable{
	private String adm_no;
	private String adm_acct;
	private String adm_pwd;
	private String adm_name;
	private String adm_mail;
	
	private List<Integer> tag;
	
	public AdminVO(){
		
	}

	
	public String getAdm_mail() {
		return adm_mail;
	}

	public void setAdm_mail(String adm_mail) {
		this.adm_mail = adm_mail;
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
	
	
	public List<Integer> getTag(){
		AuthFeatureService authFeatSvc = new AuthFeatureService();
		AuthService authSvc = new AuthService();
		AdminService adminSvc = new AdminService();
		List<AuthFeatureVO>allAuth = authFeatSvc.getAll();//全權限(六個)
		List<AdminAuthVO> allAdminAuth= authSvc.getAll();//所有員工的權限清單(正確答案)
		List<Integer> tag = new ArrayList<Integer>();
		for(int i = 0 ; i <allAuth.size();i++){
			AdminVO admin = adminSvc.getOneAdmin(adm_no);
			AdminAuthVO auth = new AdminAuthVO();
			auth.setAdmin(admin);
			auth.setAuth((allAuth.get(i)));
			if(allAdminAuth.contains(auth)){
				tag.add(1);
			}else{
				tag.add(0);
			}
		}
		
		for(Integer tags :tag){
			System.out.println(tags);
		}
		
		
		return tag;
	}
	
	public void setTag(List<Integer> tag){
		this.tag=tag;
	}
	
}
