package com.qa.model;


import java.util.List;

public class QaService {

	private QaDAO_interface dao;

	public QaService() {
		dao = new QaDAO();
	}

	public QaVO addQa(java.sql.Date qa_date, 
			String qa_title, String qa_cnt) {

		QaVO qaVO = new QaVO();

		//qaVO.setQa_no(qa_no);
		qaVO.setQa_date(qa_date);
		qaVO.setQa_title(qa_title);
		qaVO.setQa_cnt(qa_cnt);
		
		dao.insert(qaVO);

		return qaVO;
	}

	public QaVO updateQa(String qa_no,java.sql.Date qa_date, 
			String qa_title, String qa_cnt) {

		QaVO qaVO = new QaVO();

		qaVO.setQa_no(qa_no);
		qaVO.setQa_date(qa_date);
		qaVO.setQa_title(qa_title);
		qaVO.setQa_cnt(qa_cnt);
		dao.update(qaVO);

		return qaVO;
	}

	public void deleteQa(String qa_no) {
		dao.delete(qa_no);
	}

	public QaVO getOneQa(String qa_no) {
		return dao.findByPrimaryKey(qa_no);
	}

	public List<QaVO> getAll() {
		return dao.getAll();
	}
}
