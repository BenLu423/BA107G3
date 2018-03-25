package com.talk_picture.model;

public class TalkPictureVO implements java.io.Serializable{
	private String talkp_no;
	private String talk_no;
	private byte[] talkp_pic;
	
	public TalkPictureVO(){
		
	}

	public String getTalkp_no() {
		return talkp_no;
	}

	public void setTalkp_no(String talkp_no) {
		this.talkp_no = talkp_no;
	}

	public String getTalk_no() {
		return talk_no;
	}

	public void setTalk_no(String talk_no) {
		this.talk_no = talk_no;
	}

	public byte[] getTalkp_pic() {
		return talkp_pic;
	}

	public void setTalkp_pic(byte[] talkp_pic) {
		this.talkp_pic = talkp_pic;
	}
	
}
