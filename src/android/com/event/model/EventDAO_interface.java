package android.com.event.model;

import java.util.*;

public interface EventDAO_interface {

	public void insert(EventVO eventVO);

	public void update(EventVO eventVO);

	public void delete(String eve_no);

	public EventVO findByPrimaryKey(String eve_no);

	public List<EventVO> getAll();
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
	// public List<EmpVO> getAll(Map<String, String[]> map);
	public byte[] select(String eve_no);
	public List<EventVO> getAll_sts_on() ;

	public List<EventVO> getAll_sts_off();
	
	public void updateEventSts(String eve_sts, String eve_no);
	
	public String getEvent_No(String eve_name);

	

}
