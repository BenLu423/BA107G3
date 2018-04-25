package com.event.model;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EventDAO implements EventDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO event (eve_no , evec_no , eve_name , eve_start , eve_end , eve_time , eve_cnt , eve_pic , eve_quota , eve_site , eve_regfee,eve_sts) VALUES ('E'||LPAD(TO_CHAR(EVENT_SEQ.NEXTVAL),3,'0') , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?,? )";
	private static final String GET_ALL_STMT = "SELECT EVE_NO, EVEC_NO, EVE_NAME, EVE_START, EVE_END,EVE_TIME, EVE_CNT, EVE_QUOTA, EVE_SITE, EVE_REGFEE, EVE_STS FROM EVENT ORDER BY EVE_NO";
	private static final String GET_ALL_STMT_STS_ON = "SELECT eve_no, evec_no, eve_name, eve_start, eve_end,eve_time, eve_cnt, eve_quota, eve_site, eve_regfee, eve_sts FROM event WHERE eve_sts = '上架' order by eve_no";
	private static final String GET_ALL_STMT_STS_OFF = "SELECT eve_no , evec_no , eve_name , eve_start,eve_end,eve_time,eve_cnt, eve_quota,eve_site , eve_regfee,eve_sts FROM event WHERE EVE_STS='下架' order by eve_no";
	private static final String GET_ONE_STMT = "SELECT eve_no , evec_no , eve_name , eve_start,eve_end,eve_time,eve_cnt,eve_pic, eve_quota,eve_site , eve_regfee,eve_sts FROM event where eve_no = ?";
	private static final String DELETE = "DELETE FROM event where eve_no = ?";
	private static final String UPDATE = "UPDATE event set evec_no = ? , eve_name = ? ,eve_start= ? ,eve_end = ?,eve_time = ?,eve_cnt =?,eve_pic=?,eve_quota=? ,eve_site =? ,eve_regfee=?,eve_sts=? where eve_no = ?";
	private static final String UPDATE_STS = "UPDATE event set eve_sts=? where eve_no = ?";
	private static final String SELECT_PIC = "select eve_pic from event where eve_no = ?";
	private static final String GET_EVENT_NUMBER = "SELECT EVE_NO FROM EVENT WHERE EVE_NAME = ?";
		
	public byte[] select(String eve_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] result = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_PIC);
			pstmt.setString(1, eve_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getBytes("eve_pic");
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public void insert(EventVO eventVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, eventVO.getEvec_no());
			pstmt.setString(2, eventVO.getEve_name());
			pstmt.setTimestamp(3, eventVO.getEve_start());
			pstmt.setTimestamp(4, eventVO.getEve_end());
			pstmt.setTimestamp(5, eventVO.getEve_time());
			pstmt.setString(6, eventVO.getEve_cnt());
			pstmt.setBytes(7, eventVO.getEve_pic());
			pstmt.setInt(8, eventVO.getEve_quota());
			pstmt.setString(9, eventVO.getEve_site());
			pstmt.setInt(10, eventVO.getEve_regfee());
			pstmt.setString(11, eventVO.getEve_sts());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(EventVO eventVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, eventVO.getEvec_no());
			pstmt.setString(2, eventVO.getEve_name());
			pstmt.setTimestamp(3, eventVO.getEve_start());
			pstmt.setTimestamp(4, eventVO.getEve_end());
			pstmt.setTimestamp(5, eventVO.getEve_time());
			pstmt.setString(6, eventVO.getEve_cnt());
			pstmt.setBytes(7, eventVO.getEve_pic());
			pstmt.setInt(8, eventVO.getEve_quota());
			pstmt.setString(9, eventVO.getEve_site());
			pstmt.setInt(10, eventVO.getEve_regfee());
			pstmt.setString(11, eventVO.getEve_sts());
			pstmt.setString(12, eventVO.getEve_no());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String eve_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, eve_no);

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public EventVO findByPrimaryKey(String eve_no) {

		EventVO eventVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, eve_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				eventVO = new EventVO();

				eventVO.setEve_no(rs.getString("eve_no"));
				eventVO.setEvec_no(rs.getString("evec_no"));
				eventVO.setEve_name(rs.getString("eve_name"));
				eventVO.setEve_start(rs.getTimestamp("eve_start"));
				eventVO.setEve_end(rs.getTimestamp("eve_end"));
				eventVO.setEve_time(rs.getTimestamp("eve_time"));
				eventVO.setEve_cnt(rs.getString("eve_cnt"));
				eventVO.setEve_pic(rs.getBytes("eve_pic"));
				eventVO.setEve_quota(rs.getInt("eve_quota"));
				eventVO.setEve_site(rs.getString("eve_site"));
				eventVO.setEve_regfee(rs.getInt("eve_regfee"));
				eventVO.setEve_sts(rs.getString("eve_sts"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return eventVO;
	}

	@Override
	public List<EventVO> getAll() {

		List<EventVO> list = new ArrayList<EventVO>();
		EventVO eventVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("=========================");
				// empVO 也稱為 Domain objects
				eventVO = new EventVO();
				eventVO.setEve_no(rs.getString("eve_no"));
				eventVO.setEvec_no(rs.getString("evec_no"));
				eventVO.setEve_name(rs.getString("eve_name"));
				eventVO.setEve_start(rs.getTimestamp("eve_Start"));
				eventVO.setEve_end(rs.getTimestamp("eve_end"));
				eventVO.setEve_time(rs.getTimestamp("eve_time"));
				eventVO.setEve_cnt(rs.getString("eve_cnt"));
				eventVO.setEve_quota(rs.getInt("eve_quota"));
				eventVO.setEve_site(rs.getString("eve_site"));
				eventVO.setEve_regfee(rs.getInt("eve_regfee"));
				eventVO.setEve_sts(rs.getString("eve_sts"));

				list.add(eventVO); // Store the row in the list
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public List<EventVO> getAll_sts_on() {
		List<EventVO> list = new ArrayList<EventVO>();
		EventVO eventVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_STS_ON);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects				
				eventVO = new EventVO();
				eventVO.setEve_no(rs.getString("eve_no"));
				eventVO.setEvec_no(rs.getString("evec_no"));
				eventVO.setEve_name(rs.getString("eve_name"));
				eventVO.setEve_start(rs.getTimestamp("eve_Start"));
				eventVO.setEve_end(rs.getTimestamp("eve_end"));
				eventVO.setEve_cnt(rs.getString("eve_cnt"));
				eventVO.setEve_quota(rs.getInt("eve_quota"));
				eventVO.setEve_site(rs.getString("eve_site"));
				eventVO.setEve_regfee(rs.getInt("eve_regfee"));
				eventVO.setEve_sts(rs.getString("eve_sts"));
				list.add(eventVO); // Store the row in the list
			}		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public List<EventVO> getAll_sts_off() {
		List<EventVO> list = new ArrayList<EventVO>();
		EventVO eventVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_STS_OFF);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects			
				eventVO = new EventVO();
				eventVO.setEve_no(rs.getString("eve_no"));
				eventVO.setEvec_no(rs.getString("evec_no"));
				eventVO.setEve_name(rs.getString("eve_name"));
				eventVO.setEve_start(rs.getTimestamp("eve_Start"));
				eventVO.setEve_end(rs.getTimestamp("eve_end"));
				eventVO.setEve_cnt(rs.getString("eve_cnt"));
				eventVO.setEve_quota(rs.getInt("eve_quota"));
				eventVO.setEve_site(rs.getString("eve_site"));
				eventVO.setEve_regfee(rs.getInt("eve_regfee"));
				eventVO.setEve_sts(rs.getString("eve_sts"));
				list.add(eventVO); // Store the row in the list
			}
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public void updateEventSts(String eve_sts,String eve_no) {
		Connection con = null;
		PreparedStatement pstmt = null;	
		try {
			

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STS);
			
			pstmt.setString(1,eve_sts);
			pstmt.setString(2,eve_no);

			pstmt.executeUpdate();
			System.out.println("執行更新");
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}


//	@Override
//	//透過名字取編號
//	public EventVO getEventNo(String eve_name) {
//		EventVO eventVO =null;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//	
//		try {
//			//建立連線
//			con = ds.getConnection();
//			//下sql語法
//			pstmt = con.prepareStatement(GET_EVENT_NUMBER);
//			//把問號填成eve_name
//			pstmt.setString(1, eve_name); //"SELECT EVE_NO FROM EVENT WHERE EVE_NAME = ? (eve_name)"
//			//送出去Sql, 獲得結果, 存入rs
//			//rs=[a.object]-->[b.object]-->[c.object]-->[d.object]-->...[x.object]
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				eventVO = new EventVO();
//				eventVO.setEve_no(rs.getString("eve_no"));
//				eventVO.setEvec_no(rs.getString("evec_no"));
//				eventVO.setEve_name(rs.getString("eve_name"));
//				eventVO.setEve_start(rs.getTimestamp("eve_Start"));
//				eventVO.setEve_end(rs.getTimestamp("eve_end"));
//				eventVO.setEve_cnt(rs.getString("eve_cnt"));
//				eventVO.setEve_quota(rs.getInt("eve_quota"));
//				eventVO.setEve_site(rs.getString("eve_site"));
//				eventVO.setEve_regfee(rs.getInt("eve_regfee"));
//				eventVO.setEve_sts(rs.getString("eve_sts"));
//			}
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return eventVO;
//	}




	@Override
	public String getEvent_No(String eve_name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String result = null;
		try {
			//建立連線
			con = ds.getConnection();
			//下sql語法
			pstmt = con.prepareStatement(GET_EVENT_NUMBER);
			//把問號填成eve_name
			pstmt.setString(1, eve_name); //"SELECT EVE_NO FROM EVENT WHERE EVE_NAME = ? (eve_name)"
			//送出去Sql, 獲得結果, 存入rs
			//rs=[a.object]-->[b.object]-->[c.object]-->[d.object]-->...[x.object]
			rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getString("eve_no");
				
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	


}
