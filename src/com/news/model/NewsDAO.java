package com.news.model;

import java.util.List;

import java.util.*;
import java.sql.*;
import java.sql.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class NewsDAO implements NewsDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_NEWS = "INSERT INTO NEWS(NEWS_NO,NEWS_DATE,NEWS_TITLE,NEWS_CNT)VALUES('N'||LPAD(TO_CHAR(NEWS_SEQ.NEXTVAL),4,'0'),?,?,?)";
	private static final String UPDATE_NEWS = "UPDATE NEWS SET NEWS_DATE=?,NEWS_TITLE=?,NEWS_CNT=? WHERE NEWS_NO=?";
	private static final String GET_ONE_NEWS = "SELECT * FROM NEWS WHERE NEWS_NO = ?";
	private static final String DELETE_NEWS = "DELETE FROM NEWS WHERE NEWS_NO = ?";
	private static final String GET_ALL_NEWS = "SELECT * FROM NEWS";
	
	@Override
	
	public void insert(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_NEWS);
			
			pstmt.setDate(1, (Date) newsVO.getNews_date());
			pstmt.setString(2, newsVO.getNews_title());
			pstmt.setString(3,newsVO.getNews_cnt());
			
			pstmt.executeUpdate();
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
				
			}	
		}

	}

	@Override
	public void update(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_NEWS);
			
			pstmt.setDate(1, (Date) newsVO.getNews_date());
			pstmt.setString(2, newsVO.getNews_title());
			pstmt.setString(3,newsVO.getNews_cnt());
			pstmt.setString(4, newsVO.getNews_no());
			
			pstmt.executeUpdate();
			System.out.println("修改成功");
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
				
			}	
		}

	}

	@Override
	public void delete(String news_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_NEWS);
			
			pstmt.setString(1,news_no);
			
			pstmt.executeUpdate();
			System.out.println("刪除成功");
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
				
			}	
		}

	}

	@Override
	public NewsVO findByPrimaryKey(String news_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NewsVO newsvo = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_NEWS);
			pstmt.setString(1, news_no);
			rs = pstmt.executeQuery();
			
			rs.next();
			newsvo = new NewsVO();
			newsvo.setNews_no(rs.getString("news_no"));
			newsvo.setNews_date(rs.getDate("news_date"));
			newsvo.setNews_title(rs.getString("news_title"));
			newsvo.setNews_cnt(rs.getString("news_cnt"));
			
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if(rs!=null){
				try{
					rs.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception se){
					se.printStackTrace(System.err);
				}
			}
			
		}
		return newsvo;
	}

	@Override
	public List<NewsVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NewsVO newsvo = null;
		List<NewsVO> newsvos = new ArrayList<NewsVO>() ;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_NEWS);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				newsvo = new NewsVO();
				newsvo.setNews_no(rs.getString("news_no"));
				newsvo.setNews_date(rs.getDate("news_date"));
				newsvo.setNews_title(rs.getString("news_title"));
				newsvo.setNews_cnt(rs.getString("news_cnt"));
				newsvos.add(newsvo);
			}
			
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if(rs!=null){
				try{
					rs.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(Exception se){
					se.printStackTrace(System.err);
				}
			}
			
		}
		return newsvos;
	}

}
