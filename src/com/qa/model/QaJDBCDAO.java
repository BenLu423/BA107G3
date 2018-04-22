package com.qa.model;

	import java.util.*;
	import java.sql.*;

		public class QaJDBCDAO implements QaDAO_interface {

						// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String userid = "BA107G3";
			String passwd = "123456";

			private static final String INSERT_STMT = 
				"INSERT INTO qa (qa_no , qa_date , qa_title , qa_cnt) VALUES (? , ? , ? , ?)";
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
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url, userid, passwd);
					pstmt = con.prepareStatement(INSERT_STMT);
					
					pstmt.setString(1, qaVO.getQa_no());
					pstmt.setDate(2, qaVO.getQa_date());
					pstmt.setString(3, qaVO.getQa_title());
					pstmt.setString(4, qaVO.getQa_cnt());
			
					pstmt.executeUpdate();
				

					// Handle any SQL errors
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Couldn't load database driver. "
							+ e.getMessage());
					// Handle any SQL errors
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
			public void update(QaVO qaVO) {

				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url, userid, passwd);
								pstmt = con.prepareStatement(UPDATE);

				
					pstmt.setDate(1, qaVO.getQa_date());
					pstmt.setString(2, qaVO.getQa_title());
					pstmt.setString(3, qaVO.getQa_cnt());
					pstmt.setString(4, qaVO.getQa_no());
					
					pstmt.executeUpdate();

					// Handle any driver errors
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Couldn't load database driver. "
							+ e.getMessage());
					// Handle any SQL errors
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

					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url, userid, passwd);
					pstmt = con.prepareStatement(DELETE);

					pstmt.setString(1, qa_no);
					

					pstmt.executeUpdate();

					// Handle any driver errors
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Couldn't load database driver. "
							+ e.getMessage());
					// Handle any SQL errors
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

					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url, userid, passwd);
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

					// Handle any driver errors
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Couldn't load database driver. "
							+ e.getMessage());
					// Handle any SQL errors
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

					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url, userid, passwd);
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

					// Handle any driver errors
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Couldn't load database driver. "
							+ e.getMessage());
					// Handle any SQL errors
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
			public static void main(String[] args) {
//
				QaJDBCDAO dao = new QaJDBCDAO();
//						
				//新增
				
				QaVO  qaVO1= new QaVO();
				qaVO1.setQa_no("Q005");
				qaVO1.setQa_date(java.sql.Date.valueOf("2005-01-01"));
				qaVO1.setQa_title("dkkd");
				qaVO1.setQa_cnt("ewj;irhj");
			
				dao.insert(qaVO1);
				//修改
//							
//				QaVO  evemVO2= new QaVO();
//				evemVO2.setQa_no("Q001");
//				evemVO2.setQa_date(java.sql.Date.valueOf("2005-01-01"));
//				evemVO2.setQa_title("dsgju");
//				evemVO2.setQa_cnt("ewj;irhj");
//		
//				dao.update(evemVO2);
//	
//				//刪除
//
//				dao.delete("Q005");
//				//查詢
//				QaVO qaVO3 =dao.findByPrimaryKey("Q001");
//				System.out.println(qaVO3.getQa_no()+",");
//				System.out.println(qaVO3.getQa_date()+",");
//				System.out.println(qaVO3.getQa_title()+",");
//				System.out.println(qaVO3.getQa_cnt());
//				System.out.println("--------------------------");
//				
				
//				//查詢
//				List<QaVO>list=dao.getAll();
//				for(QaVO aQa:list){
//					System.out.println(aQa.getQa_no()+",");
//					System.out.println(aQa.getQa_date()+",");
//					System.out.println(aQa.getQa_title()+",");
//					System.out.println(aQa.getQa_cnt());
//					System.out.println();
				
				
				
				
				
//				}
	
		}

}
