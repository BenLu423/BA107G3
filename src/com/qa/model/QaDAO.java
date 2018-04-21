package com.qa.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class QaDAO implements QaDAO_interface {

	
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
			"INSERT INTO qa (qa_no , qa_date , qa_title , qa_cnt) VALUES ('Q'||LPAD(TO_CHAR(QA_SEQ.NEXTVAL),3,'0') , ? , ? , ?)";
		private static final String GET_ALL_STMT = 
			"SELECT qa_no,qa_date,qa_title,qa_cnt FROM qa order by Qa_no";
		private static final String GET_ONE_STMT = 
			"SELECT qa_no,qa_date,qa_title,qa_cnt FROM qa where qa_no = ?";
		private static final String DELETE = 
			"DELETE FROM qa where qa_no = ?";
		private static final String UPDATE = 
			"UPDATE qa set qa_date=? , qa_title=? , qa_cnt=? where qa_no = ?";
		
		@Override
		public void insert(QaVO qaVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				
				pstmt.setDate(1, qaVO.getQa_date());
				pstmt.setString(2, qaVO.getQa_title());
				pstmt.setString(3, qaVO.getQa_cnt());
		
				pstmt.executeUpdate();
				
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
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
		public void update(QaVO qaVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

			
				pstmt.setDate(1, qaVO.getQa_date());
				pstmt.setString(2, qaVO.getQa_title());
				pstmt.setString(3, qaVO.getQa_cnt());
				pstmt.setString(4, qaVO.getQa_no());
				
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
		public void delete(String qa_no) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				
				con =  ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, qa_no);
				

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
		public QaVO findByPrimaryKey(String qa_no) {
		
			QaVO qaVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				
				con =  ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, qa_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					qaVO = new QaVO();
					qaVO.setQa_no(rs.getString("qa_no"));
					qaVO.setQa_date(rs.getDate("qa_date"));
					qaVO.setQa_title(rs.getString("qa_title"));
					qaVO.setQa_cnt(rs.getString("qa_cnt"));
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
			return qaVO;
		}
//
		@Override
		public List<QaVO> getAll() {
			List<QaVO> list = new ArrayList<QaVO>();
			QaVO qaVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

			
				con =  ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					qaVO = new QaVO();
					qaVO.setQa_no(rs.getString("qa_no"));
					qaVO.setQa_date(rs.getDate("qa_date"));
					qaVO.setQa_title(rs.getString("qa_title"));
					qaVO.setQa_cnt(rs.getString("qa_cnt"));
					
					list.add(qaVO); // Store the row in the list
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
