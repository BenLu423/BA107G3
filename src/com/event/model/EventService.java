package com.event.model;
	import java.util.List;

import javax.servlet.http.Part;



public class EventService {


	private EventDAO_interface dao;

	public EventService() {
		dao = new EventDAO();
	}

	public EventVO addEvent(String evec_no , String eve_name , java.sql.Timestamp eve_start , java.sql.Timestamp eve_end , java.sql.Timestamp eve_time , String eve_cnt , byte[] eve_pic , Integer eve_quota ,String eve_site , Integer eve_regfee,String eve_sts) {

		EventVO eventVO = new EventVO();

		
		eventVO.setEvec_no(evec_no);
		eventVO.setEve_name(eve_name);
		eventVO.setEve_start(eve_start);
		eventVO.setEve_end(eve_end);
		eventVO.setEve_time(eve_time);
		eventVO.setEve_cnt(eve_cnt);
		eventVO.setEve_pic(eve_pic);
		eventVO.setEve_quota(eve_quota);
		eventVO.setEve_site(eve_site);
		eventVO.setEve_regfee(eve_regfee);
		eventVO.setEve_sts(eve_sts);
		dao.insert(eventVO);

		return eventVO;
	}

	public EventVO updateEvent(String eve_no,String evec_no ,String eve_name ,java.sql.Timestamp eve_start,  java.sql.Timestamp eve_end ,java.sql.Timestamp eve_time , String eve_cnt ,byte[] eve_pic , Integer eve_quota ,String eve_site , Integer eve_regfee,String eve_sts) {

		

		EventVO eventVO = new EventVO();
		
		eventVO.setEve_no(eve_no);
		eventVO.setEvec_no(evec_no);
		eventVO.setEve_name(eve_name);
		eventVO.setEve_start(eve_start);
		eventVO.setEve_end(eve_end);
		eventVO.setEve_time(eve_time);
		eventVO.setEve_cnt(eve_cnt);
		eventVO.setEve_pic(eve_pic);
		eventVO.setEve_quota(eve_quota);
		eventVO.setEve_site(eve_site);
		eventVO.setEve_regfee(eve_regfee);
		eventVO.setEve_sts(eve_sts);
		dao.update(eventVO);
		return eventVO;
	}
	
	public void updateEventSts(String eve_sts,String eve_no){
		
		dao.updateEventSts(eve_sts,eve_no);
	}
			
	public void deleteEvent(String eve_no) {
		dao.delete(eve_no);
	}

	public EventVO getOneEvent(String eve_no) {
		return dao.findByPrimaryKey(eve_no);
	}
	public String getEvent_No(String eve_name) {
//		return eve_name;
		return dao.getEvent_No(eve_name);
	}
	public List<EventVO> getAll() {
		return dao.getAll();
	}
	
	public List<EventVO> getAll_sts_on() {
		return dao.getAll_sts_on();
		
	}
	public List<EventVO> getAll_sts_off() {
		return dao.getAll_sts_off();
		
	}
	public byte[] select(String eve_no){
		return dao.select(eve_no);
	}
	
}
