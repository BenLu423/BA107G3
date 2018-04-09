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
	
	private String authsForHtml;
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
	

	public String getAuthsForHtml(String adm_no){
		AuthFeatureService authFeatSvc = new AuthFeatureService();
		AuthService authSvc = new AuthService();
		List<AuthFeatureVO>allAuth = authFeatSvc.getAll();//全權限(六個)
		List<AdminAuthVO> allAdminAuth= authSvc.getAll();//所有員工的權限清單(正確答案)
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0 ; i <allAuth.size();i++){
			AdminAuthVO auth = new AdminAuthVO();
			auth.setAdm_no(adm_no);
			auth.setAuth_no((allAuth.get(i)).getAuth_no());
			if(allAdminAuth.contains(auth)){
				sb.append("<div class='auth-list checked'><span class='glyphicon glyphicon-ok hlyphicon-lg'></span><h5>").append((allAuth.get(i)).getAuth_name()).append("</h5></div>");
			}else{
				sb.append("<div class='auth-list'><span class='glyphicon glyphicon-ok hlyphicon-lg'></span><h5>").append((allAuth.get(i)).getAuth_name()).append("</h5></div>");
			}
		}
				
		return sb.toString();
	}
	
	public void setAuthsForHtml(String authsForHtml){
		this.authsForHtml = authsForHtml;
	}
	
	public List<Integer> getTag(){
		AuthFeatureService authFeatSvc = new AuthFeatureService();
		AuthService authSvc = new AuthService();
		List<AuthFeatureVO>allAuth = authFeatSvc.getAll();//全權限(六個)
		List<AdminAuthVO> allAdminAuth= authSvc.getAll();//所有員工的權限清單(正確答案)
		List<Integer> tag = new ArrayList<Integer>();
		for(int i = 0 ; i <allAuth.size();i++){
			AdminAuthVO auth = new AdminAuthVO();
			auth.setAdm_no(adm_no);
			auth.setAuth_no((allAuth.get(i)).getAuth_no());
			if(allAdminAuth.contains(auth)){
				tag.add(1);
			}else{
				tag.add(0);
			}
		}
				
		return tag;
	}
	
	public void setTag(List<Integer> tag){
		this.tag=tag;
	}
	
}
