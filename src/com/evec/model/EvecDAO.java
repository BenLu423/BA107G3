package com.evec.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

		public class EvecDAO implements EvecDAO_interface{
			
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
					"INSERT INTO event_class (evec_no,evec_name) VALUES (?,?)";
				private static final String GET_ALL_STMT = 
					"SELECT evec_no,evec_name FROM event_class order by evec_no";
				private static final String GET_ONE_STMT = 
					"SELECT evec_no,evec_name FROM event_class where evec_no = ?";
				private static final String DELETE = 
					"DELETE FROM event_class where evec_no = ?";
				private static final String UPDATE = 
					"UPDATE event_class set evec_name=? where evec_no= ?";

			@Override
			public void insert(EvecVO evecVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				
				con = ds.getConnection();
				
				pstmt = con.prepareStatement(INSERT_STMT);
			
				pstmt.setString(1, evecVO.getEvec_no());
				pstmt.setString(2, evecVO.getEvec_name());
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
			public void update(EvecVO evecVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
			
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
			
				pstmt.setString(1, evecVO.getEvec_name());
				pstmt.setString(2, evecVO.getEvec_no());
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
					public void delete(String evec_no) {

						Connection con = null;
						PreparedStatement pstmt = null;

						try {

							
							con = ds.getConnection();
							pstmt = con.prepareStatement(DELETE);
							
							pstmt.setString(1, evec_no);

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
					public EvecVO findByPrimaryKey(String evec_no) {

						EvecVO evecVO = null;
						Connection con = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;

						try {

							
							con = ds.getConnection();
							pstmt = con.prepareStatement(GET_ONE_STMT);

							pstmt.setString(1, evec_no);

							rs = pstmt.executeQuery();

							while (rs.next()) {
								// empVo 也稱為 Domain objects
								evecVO = new EvecVO();
								evecVO.setEvec_no(rs.getString("evec_no"));
								evecVO.setEvec_name(rs.getString("evec_name"));
							
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
						return evecVO;
					}

					@Override
					public List<EvecVO> getAll() {
						List<EvecVO> list = new ArrayList<EvecVO>();
						EvecVO evecVO = null;

						Connection con = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;

						try {

						
							con = ds.getConnection();
							pstmt = con.prepareStatement(GET_ALL_STMT);
							rs = pstmt.executeQuery();

							while (rs.next()) {
								// empVO 也稱為 Domain objects
								evecVO = new EvecVO();
								evecVO.setEvec_no(rs.getString("evec_no"));
								evecVO.setEvec_name(rs.getString("evec_name"));
								
								list.add(evecVO); // Store the row in the list
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
