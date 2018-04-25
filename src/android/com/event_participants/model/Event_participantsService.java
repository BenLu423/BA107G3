package android.com.event_participants.model;

import java.util.List;

public class Event_participantsService {
	
	Event_participantsDAO_interface dao = null;

	public Event_participantsService() {
		dao = new Event_participantsDAO();
	}
	
	public void insert(String mem_no , String eve_no , String evep_sts , byte[] evep_qr){
		Event_participantsVO event_participantsVO = new Event_participantsVO();
		event_participantsVO.setMem_no(mem_no);
		event_participantsVO.setEve_no(eve_no);
		event_participantsVO.setEvep_sts(evep_sts);
		event_participantsVO.setEvep_qr(evep_qr);
		dao.insert(event_participantsVO);
	}
	
	public void update(String mem_no , String eve_no){
		Event_participantsVO event_participantsVO = new Event_participantsVO();
		event_participantsVO.setMem_no(mem_no);
		event_participantsVO.setEve_no(eve_no);
		dao.update(event_participantsVO);
	}
	
	public List<Event_participantsVO> getOneEve(String mem_no){
		return dao.getOneEve(mem_no);
	}

}
