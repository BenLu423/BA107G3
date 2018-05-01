package com.evem.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EvemDAO implements EvemDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = 
			"INSERT INTO event_message (evemes_no , eve_no , mem_no , evemes_cnt) VALUES ('EM'||LPAD(to_char(EVENT_MESSAGE_SEQ.NEXTVAL),3,'0'), ?, ?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT evemes_no,eve_no,mem_no,evemes_time,evemes_cnt,evemes_restime,evemes_rescnt FROM event_message order by evemes_no";
		private static final String GET_ONE_STMT = 
			"SELECT evemes_no,eve_no,mem_no,evemes_time,evemes_cnt,evemes_restime,evemes_rescnt FROM event_message where evemes_no = ?";
		private static final String DELETE = 
			"DELETE FROM event_message where evemes_no = ?";
		private static final String UPDATE = 
			"UPDATE event_message set evemes_rescnt=? where evemes_no = ?";

		@Override
		public void insert(EvemVO evemVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				
				pstmt.setString(1, evemVO.getEve_no());
				pstmt.setString(2, evemVO.getMem_no());
//				pstmt.setTimestamp(3, evemVO.getEvemes_time());
				pstmt.setString(3, evemVO.getEvemes_cnt());
//				pstmt.setTimestamp(5, evemVO.getEvemes_restime());
//				pstmt.setString(6, evemVO.getEvemes_rescnt());
				
				
				pstmt.executeUpdate();
			
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public void update(EvemVO evemVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

			
				pstmt.setString(1, evemVO.getEvemes_rescnt());
				pstmt.setString(2, evemVO.getEvemes_no());
				
				pstmt.executeUpdate();

			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public void delete(String evemes_no) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				
				con =ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, evemes_no);

				pstmt.executeUpdate();
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public EvemVO findByPrimaryKey(String evemes_no) {

			EvemVO evemVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, evemes_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					evemVO = new EvemVO();
					evemVO.setEvemes_no(rs.getString("evemes_no"));
					evemVO.setEve_no(rs.getString("eve_no"));
					evemVO.setMem_no(rs.getString("mem_no"));
					evemVO.setEvemes_time(rs.getTimestamp("evemes_time"));
					evemVO.setEvemes_cnt(rs.getString("evemes_cnt"));
					evemVO.setEvemes_restime(rs.getTimestamp("evemes_restime"));
					evemVO.setEvemes_rescnt(rs.getString("evemes_rescnt"));
					
				}
				
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
			return evemVO;
		}

	@Override
	public List<EvemVO> getAll() {
		List<EvemVO> list = new ArrayList<EvemVO>();
		EvemVO evemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				evemVO = new EvemVO();
				evemVO.setEvemes_no(rs.getString("evemes_no"));
				evemVO.setEve_no(rs.getString("eve_no"));
				evemVO.setMem_no(rs.getString("mem_no"));
				evemVO.setEvemes_time(rs.getTimestamp("evemes_time"));
				evemVO.setEvemes_cnt(rs.getString("evemes_cnt"));
				evemVO.setEvemes_restime(rs.getTimestamp("evemes_restime"));
				evemVO.setEvemes_rescnt(rs.getString("evemes_rescnt"));
				
				list.add(evemVO); // Store the row in the list
			}
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	

}
