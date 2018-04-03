package com.friends_list.model;

import java.util.List;

public interface FriendsListDAO_interface {
	public void insert(String self , String other);
	public void update(FriendsListVO frilistVO);
	public void delete(String mem_no_self,String mem_no_other); //�Ѱ��n��
	public FriendsListVO findByPrimaryKey(String mem_no_self,String mem_no_other);
	public List<FriendsListVO> getAll();
	public List<FriendsListVO> getMemberFriends(String mem_no);
	
	
}
