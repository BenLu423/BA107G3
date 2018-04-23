package com.giftTrack.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gift.model.GiftVO;
import com.member.model.MemberVO;

@SuppressWarnings("serial")
public class GiftTrackVO implements Serializable{
	private MemberVO memberVO;
	private GiftVO giftVO;
	private Timestamp giftt_time;
	
	public GiftTrackVO() {
		super();
	}
	
	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public GiftVO getGiftVO() {
		return giftVO;
	}

	public void setGiftVO(GiftVO giftVO) {
		this.giftVO = giftVO;
	}

	public Timestamp getGiftt_time() {
		return giftt_time;
	}

	public void setGiftt_time(Timestamp giftt_time) {
		this.giftt_time = giftt_time;
	}
	
}
