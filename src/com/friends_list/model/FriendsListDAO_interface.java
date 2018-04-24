package com.friends_list.model;

import java.util.List;

public interface FriendsListDAO_interface {
	public void insert(String self , String other);
	public void update(FriendsListVO frilistVO);
	public void delete(String mem_no_self,String mem_no_other); //解除好友
	public FriendsListVO findByPrimaryKey(String mem_no_self,String mem_no_other);
	public List<FriendsListVO> getAll();
	public List<FriendsListVO> getMemberFriends(String mem_no);
	
	public void webAddFriends(String self,String other);
	public Boolean isFriend(String self,String other,Integer count);
	public void webUpdateFrinds(String modify,String self, String other);
	public void webDeleteFriends(String self,String other);
	public List<FriendsListVO> webFriendsList(String modify,String self);
}
