package com.evep.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class EvepDAO  implements EvepDAO_interface {
	
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
			"INSERT INTO event_participants (mem_no,eve_no,evep_qr) VALUES (?,?,?)";
		private static final String GET_MEMBER_EVENT = 
			"SELECT * FROM event_participants where eve_no=?";
		private static final String DELETE = 
			"DELETE FROM event_participants where mem_no = ? and eve_no = ?";
		private static final String UPDATE_BY_STS = 
			"UPDATE EVENT_PARTICIPANTS SET EVEP_STS=? WHERE MEM_NO=? AND EVE_NO=?";
//		private static final String GET_EVE_NO = 
//			"SELECT EVE_NO FROM EVENT WHERE EVE_NAME = ?";
		private static final String GET_EVEP_STS = 
			"SELECT MEM_NO, EVE_NO, EVEP_STS FROM event_participants";
		private static final String GET_EVEP_PIC = 
			"SELECT EVEP_QR FROM event_participants WHERE mem_no = ? AND eve_no = ?";
		
		@Override
		public List<EvepVO> getAll_EVEP() {
			List<EvepVO> list = new ArrayList<EvepVO>();
			EvepVO evepVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_EVEP_STS);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					evepVO = new EvepVO();
					evepVO.setMem_no(rs.getString("mem_no"));
					evepVO.setEve_no(rs.getString("eve_no"));
					evepVO.setEvep_sts(rs.getString("evep_sts"));
					
					list.add(evepVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
				// Clean up JDBC resources
				se.printStackTrace();
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
		public void insert(EvepVO evepVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
			
				con =  ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, evepVO.getMem_no());
				pstmt.setString(2, evepVO.getEve_no());
				pstmt.setBytes(3, evepVO.getEvep_qr());
				
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
			public void delete(String eve_no,String mem_no) {
				
				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					
					con =  ds.getConnection();
					pstmt = con.prepareStatement(DELETE);

					pstmt.setString(1, eve_no);
					pstmt.setString(2, mem_no);
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
			public Set<EvepVO> getEventMembers(String eve_no) {
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				EvepVO evepVO = null;
				Set<EvepVO> set = new LinkedHashSet<EvepVO>();

				try {

					
					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_MEMBER_EVENT);
					//"SELECT * FROM event_participants where eve_no=?";
					pstmt.setString(1, eve_no);
					
					
					rs = pstmt.executeQuery();

					while (rs.next()) {
						// empVO 也稱為 Domain objects
						evepVO = new EvepVO();
						evepVO.setMem_no(rs.getString("mem_no"));
						evepVO.setEvep_sts(rs.getString("evep_sts"));
						evepVO.setEvep_qr(rs.getBytes("evep_qr"));
						set.add(evepVO); // Store the row in the list
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
				return set;
			
			}



			@Override
			public void update(String mem_no, String eve_no, String evep_sts) {
				Connection con = null;
				PreparedStatement pstmt = null;
				try {
					con =  ds.getConnection();
					pstmt = con.prepareStatement(UPDATE_BY_STS);

					pstmt.setString(1, evep_sts);
					pstmt.setString(2, mem_no);
					pstmt.setString(3, eve_no);
					
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
			public byte[] getEVEP_Pic(String mem_no, String eve_no) {
				Connection con = null;//建立連線
				PreparedStatement pstmt = null;//代表sql語句的資料類型
				ResultSet rs = null;//代表查詢資料庫的結果
				byte[] result = null;
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_EVEP_PIC);
					pstmt.setString(1, mem_no);
					pstmt.setString(2, eve_no);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						result = rs.getBytes("evep_qr");
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
