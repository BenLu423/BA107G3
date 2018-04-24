package android.com.friends_list.model;

import java.util.List;

import com.member.model.MemberVO;

import android.com.talk.model.TalkService;


public class FriendsListService {
	
	private FriendsListDAO_interface dao;
	
	public FriendsListService(){
		dao = new FriendsListDAO();
	}
	
	public void insert(String mem_no_self,String mem_no_other){
		FriendsListVO frilistVO = new FriendsListVO();
		MemberVO memSelf = new MemberVO();
		memSelf.setMem_no(mem_no_self);
		MemberVO memOther = new MemberVO();
		memOther.setMem_no(mem_no_other);
		frilistVO.setMemVO_self(memSelf);
		frilistVO.setMemVO_other(memOther);
		dao.insert(frilistVO);
		frilistVO.setMemVO_self(memOther);
		frilistVO.setMemVO_other(memSelf);
		dao.insert(frilistVO);
	}
	
	public void update(FriendsListVO frilistVO){
		
	}
	
	public void add(String mem_no_self,String mem_no_other){
		FriendsListVO frilistVO = new FriendsListVO();
		MemberVO memSelf = new MemberVO();
		memSelf.setMem_no(mem_no_self);
		MemberVO memOther = new MemberVO();
		memOther.setMem_no(mem_no_other);
		frilistVO.setMemVO_self(memSelf);
		frilistVO.setMemVO_other(memOther);
		frilistVO.setFrilist_modify("¬O");
		dao.add(frilistVO);
	}
	
	public void delete(String mem_no_self,String mem_no_other){
		FriendsListVO frilistVO = new FriendsListVO();
		TalkService tSvc = new TalkService();
		MemberVO memSelf = new MemberVO();
		memSelf.setMem_no(mem_no_self);
		MemberVO memOther = new MemberVO();
		memOther.setMem_no(mem_no_other);
		frilistVO.setMemVO_self(memSelf);
		frilistVO.setMemVO_other(memOther);
		tSvc.deleteTalk(frilistVO);
		dao.delete(mem_no_self, mem_no_other);
	}
	
	public FriendsListVO findByPrimaryKey(String mem_no_self,String mem_no_other){
		return dao.findByPrimaryKey(mem_no_self, mem_no_other);
	}
	
	public List<FriendsListVO> getAll(){
		return dao.getAll();
	}
	
	public List<FriendsListVO> getMemberFriends(String mem_no){
		return dao.getMemberFriends(mem_no);
	}
	
	public Boolean havewait(String mem_no_self,String mem_no_other){
		return dao.havewait(mem_no_self, mem_no_other);
	}

}
