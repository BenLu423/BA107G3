package com.friends_list.model;

import java.util.List;

public class FriendsListService {
	private FriendsListDAO_interface dao;
	
	public FriendsListService(){
		dao = new FriendsListDAO();
	}
	
	public void insert(String self, String other){
		dao.insert(self, other);
	}
	
	public void update(FriendsListVO frilistVO){
		dao.update(frilistVO);
	}
	
	public void delete(String mem_no_self, String mem_no_other){
		dao.delete(mem_no_self, mem_no_other);
	}
	
	public FriendsListVO getOne(String mem_no_self, String mem_no_other){
		return dao.findByPrimaryKey(mem_no_self, mem_no_other);
	}
	
	public List<FriendsListVO> getAll(){
		return dao.getAll();
	}
	
	public List<FriendsListVO> getMemberFriends(String mem_no){
		return dao.getMemberFriends(mem_no);
	}
}
