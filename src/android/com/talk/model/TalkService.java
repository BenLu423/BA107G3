package android.com.talk.model;

import android.com.friends_list.model.FriendsListVO;

public class TalkService {
	
	private TalkDAO_interface dao;
	public TalkService(){
		dao = new TalkDAO();
		
	}
	
	public TalkVO getOneTalk(FriendsListVO friends){
		TalkVO talk = dao.findTalkByFriends(friends);
	//	String content = talk.getTalk_cnt();
		
		return talk;
	}
	
	public void updateCnt(TalkVO talk){
		dao.update(talk);
	}
	
	public void addTalk(TalkVO talk){
		dao.insert(talk);
	}
	
	

}
