package android.com.friends_list.model;

import java.io.Serializable;
import java.sql.Date;

import com.member.model.MemberVO;

import android.com.talk.model.TalkVO;

public class FriendsListVO implements Serializable {
	private String frilist_modify;
	private Date frilist_time;
	private String frilist_notice;
	private MemberVO memVO_self;
	private MemberVO memVO_other;

	
	public FriendsListVO(){
		
	}

	public MemberVO getMemVO_self() {
		return memVO_self;
	}

	public void setMemVO_self(MemberVO memVO_self) {
		this.memVO_self = memVO_self;
	}

	public MemberVO getMemVO_other() {
		return memVO_other;
	}

	public void setMemVO_other(MemberVO memVO_other) {
		this.memVO_other = memVO_other;
	}
	
	public String getFrilist_modify() {
		return frilist_modify;
	}
	public void setFrilist_modify(String frilist_modify) {
		this.frilist_modify = frilist_modify;
	}
	public Date getFrilist_time() {
		return frilist_time;
	}
	public void setFrilist_time(Date frilist_time) {
		this.frilist_time = frilist_time;
	}
	public String getFrilist_notice() {
		return frilist_notice;
	}
	public void setFrilist_notice(String frilist_notice) {
		this.frilist_notice = frilist_notice;
	}
	
}
