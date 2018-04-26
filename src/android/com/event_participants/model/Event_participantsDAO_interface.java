package android.com.event_participants.model;

import java.util.List;

public interface Event_participantsDAO_interface {
	
	public void insert(Event_participantsVO event_participantsVO);
	
	public void update(Event_participantsVO event_participantsVO);
	
	public List<Event_participantsVO> getOneEve(String mem_no);
	
	public List<Event_participantsVO> getOneEvep(String eve_no);
	
}
