package com.giftOrderView.model;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class GiftOrderViewVO implements Serializable {
	private String giftr_no;// 收贈禮編號
	private String mem_no_self;// 贈禮會員編號
	private String mem_name_self;// 贈禮會員姓名
	private byte[] mem_photo_self;// 贈禮會員照片
	private String mem_no_other;// 收禮會員編號
	private String mem_name_other;// 收禮會員姓名
	private byte[] mem_photo_other;// 收禮會員照片
	private Integer giftr_amount;// 收贈禮數量
	private String giftr_message;// 收贈禮留言
	private Timestamp giftr_time;// 收贈禮時間
	private String gifto_no;// 訂單編號
	private Timestamp gifto_time;// 訂單時間
	private String gifto_remark;// 訂單備註
	private String coup_no;// 折價券編號
	private String coup_name;// 折價券名稱
	private Integer coup_value;// 折價券面額
	private String giftod_no;// 訂單明細編號
	private String gift_no;// 禮物編號
	private String gift_name;// 禮物名稱
	private String gift_cnt;// 禮物簡述
	private Integer gift_price;// 禮物價格
	private byte[] gift_pic;// 禮物圖片
	private Integer giftod_amount;// 購買數量
	private Integer giftod_money;// 購買金額
	private Integer giftod_inventory;// 可用剩餘數量

	public GiftOrderViewVO() {
		super();
	}

	public String getGiftr_no() {
		return giftr_no;
	}

	public void setGiftr_no(String giftr_no) {
		this.giftr_no = giftr_no;
	}

	public String getMem_no_self() {
		return mem_no_self;
	}

	public void setMem_no_self(String mem_no_self) {
		this.mem_no_self = mem_no_self;
	}

	public String getMem_name_self() {
		return mem_name_self;
	}

	public void setMem_name_self(String mem_name_self) {
		this.mem_name_self = mem_name_self;
	}

	public byte[] getMem_photo_self() {
		return mem_photo_self;
	}

	public void setMem_photo_self(byte[] mem_photo_self) {
		this.mem_photo_self = mem_photo_self;
	}

	public String getMem_no_other() {
		return mem_no_other;
	}

	public void setMem_no_other(String mem_no_other) {
		this.mem_no_other = mem_no_other;
	}

	public String getMem_name_other() {
		return mem_name_other;
	}

	public void setMem_name_other(String mem_name_other) {
		this.mem_name_other = mem_name_other;
	}

	public byte[] getMem_photo_other() {
		return mem_photo_other;
	}

	public void setMem_photo_other(byte[] mem_photo_other) {
		this.mem_photo_other = mem_photo_other;
	}

	public Integer getGiftr_amount() {
		return giftr_amount;
	}

	public void setGiftr_amount(Integer giftr_amount) {
		this.giftr_amount = giftr_amount;
	}

	public String getGiftr_message() {
		return giftr_message;
	}

	public void setGiftr_message(String giftr_message) {
		this.giftr_message = giftr_message;
	}

	public Timestamp getGiftr_time() {
		return giftr_time;
	}

	public void setGiftr_time(Timestamp giftr_time) {
		this.giftr_time = giftr_time;
	}

	public String getGifto_no() {
		return gifto_no;
	}

	public void setGifto_no(String gifto_no) {
		this.gifto_no = gifto_no;
	}

	public Timestamp getGifto_time() {
		return gifto_time;
	}

	public void setGifto_time(Timestamp gifto_time) {
		this.gifto_time = gifto_time;
	}

	public String getGifto_remark() {
		return gifto_remark;
	}

	public void setGifto_remark(String gifto_remark) {
		this.gifto_remark = gifto_remark;
	}

	public String getCoup_no() {
		return coup_no;
	}

	public void setCoup_no(String coup_no) {
		this.coup_no = coup_no;
	}

	public String getCoup_name() {
		return coup_name;
	}

	public void setCoup_name(String coup_name) {
		this.coup_name = coup_name;
	}

	public Integer getCoup_value() {
		return coup_value;
	}

	public void setCoup_value(Integer coup_value) {
		this.coup_value = coup_value;
	}

	public String getGiftod_no() {
		return giftod_no;
	}

	public void setGiftod_no(String giftod_no) {
		this.giftod_no = giftod_no;
	}

	public String getGift_no() {
		return gift_no;
	}

	public void setGift_no(String gift_no) {
		this.gift_no = gift_no;
	}

	public String getGift_name() {
		return gift_name;
	}

	public void setGift_name(String gift_name) {
		this.gift_name = gift_name;
	}

	public String getGift_cnt() {
		return gift_cnt;
	}

	public void setGift_cnt(String gift_cnt) {
		this.gift_cnt = gift_cnt;
	}

	public Integer getGift_price() {
		return gift_price;
	}

	public void setGift_price(Integer gift_price) {
		this.gift_price = gift_price;
	}

	public byte[] getGift_pic() {
		return gift_pic;
	}

	public void setGift_pic(byte[] gift_pic) {
		this.gift_pic = gift_pic;
	}

	public Integer getGiftod_amount() {
		return giftod_amount;
	}

	public void setGiftod_amount(Integer giftod_amount) {
		this.giftod_amount = giftod_amount;
	}

	public Integer getGiftod_money() {
		return giftod_money;
	}

	public void setGiftod_money(Integer giftod_money) {
		this.giftod_money = giftod_money;
	}

	public Integer getGiftod_inventory() {
		return giftod_inventory;
	}

	public void setGiftod_inventory(Integer giftod_inventory) {
		this.giftod_inventory = giftod_inventory;
	}

}
