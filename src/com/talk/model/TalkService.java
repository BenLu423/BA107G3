package com.talk.model;

import com.friends_list.model.FriendsListVO;

public class TalkService {
	
	private TalkDAO_interface dao;
	public TalkService(){
		dao = new TalkDAO();
		
	}
	
	public String getOneTalk(FriendsListVO friends){
		TalkVO talk = dao.findTalkByFriends(friends);
		String content = talk.getTalk_cnt();
		
		return content;
	}

}
