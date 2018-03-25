package com.talk_picture.model;

import java.util.List;

import com.friends_list.model.FriendsListVO;

public interface TalkPictureDAO_interface {
	public void insert(TalkPictureVO pic);
	public void update(TalkPictureVO pic);
	public void delete(String talk_no);//�R����Ӳ�ѫǸ̪��Ϥ�
	public TalkPictureVO findByPrimaryKey(String talkp_no);
	public List<TalkPictureVO> getPicByTalkNo(FriendsListVO friends);

}
