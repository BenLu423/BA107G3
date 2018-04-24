 package com.friends_list.model;

import java.util.ArrayList;
import java.util.List;

import com.member.model.MemberService;
import com.member.model.MemberVO;

public class FriendsService {
	
	private FriendsListDAO_interface dao;
	public FriendsService(){
		dao = new FriendsListDAO();
	}
	
	public void insertFri(String self ,String other){
		dao.insert(self,other);
		dao.insert(other,self);
	}
	
	public void deleteFri(String self , String other){
		dao.delete(self, other);
		dao.delete(other, self);
	}
	
	public List<MemberVO> getMemFri(MemberVO member){
		String mem_no = member.getMem_no();
		List<FriendsListVO> list = dao.getMemberFriends(mem_no);
		List<MemberVO> friends = new ArrayList<MemberVO>();
		MemberService memSvc = new MemberService();
		
		for(FriendsListVO fr : list){
			//等拿到以no取會員物件，以及我這邊修改假資料，再做修改
			friends.add(memSvc.getOneMem(fr.getMem_no_other()));
		}
		
		
		return friends;
	}
	
	public FriendsListVO getOne(String self , String other){
		return dao.findByPrimaryKey(self, other);
	}
	public void webAddFri(String self,String other){
		dao.webAddFriends(self, other);
	}
	public boolean checkisfri(String self,String other,Integer count){
		return dao.isFriend(self, other, count);
	}
	public void webUpdateFri(String modify,String self, String other){
		dao.webUpdateFrinds(modify, self, other);
	}
	public void webDelFri(String self,String other){
		dao.webDeleteFriends(self, other);
	}
	public List<FriendsListVO> browsFriList(String modify,String self){
		return dao.webFriendsList(modify, self);
	}
}
