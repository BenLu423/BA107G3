package com.news.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsJDBCDAO implements NewsDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TOAST";
	String passwd = "123456";

	private static final String INSERT_NEWS = "INSERT INTO NEWS(NEWS_NO,NEWS_DATE,NEWS_TITLE,NEWS_CNT)VALUES('N'||LPAD(TO_CHAR(NEWS_SEQ.NEXTVAL),3,'0'),?,?,?)";
	private static final String UPDATE_NEWS = "UPDATE NEWS SET NEWS_DATE=?,NEWS_TITLE=?,NEWS_CNT=? WHERE NEWS_NO=?";
	private static final String DELETE_NEWS = "DELETE FROM NEWS WHERE NEWS_NO = ?";
	private static final String GET_ONE_NEWS = "SELECT * FROM NEWS WHERE NEWS_NO = ?";
	private static final String GET_ALL_NEWS = "SELECT * FROM NEWS";
	
	@Override
	public void insert(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_NEWS);
			
			pstmt.setDate(1, (Date) newsVO.getNews_date());
			pstmt.setString(2, newsVO.getNews_title());
			pstmt.setString(3,newsVO.getNews_cnt());
			
			pstmt.executeUpdate();
			System.out.println("新增成功");
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE_NEWS);
			
			pstmt.setDate(1, (Date) newsVO.getNews_date());
			pstmt.setString(2, newsVO.getNews_title());
			pstmt.setString(3,newsVO.getNews_cnt());
			pstmt.setString(4, newsVO.getNews_no());
			
			pstmt.executeUpdate();
			System.out.println("修改成功");
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE_NEWS);
			
			pstmt.setString(1,news_no);
			
			pstmt.executeUpdate();
			System.out.println("刪除成功");
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ONE_NEWS);
			pstmt.setString(1, news_no);
			rs = pstmt.executeQuery();
			
			rs.next();
			newsvo = new NewsVO();
			newsvo.setNews_no(rs.getString("news_no"));
			newsvo.setNews_date(rs.getDate("news_date"));
			newsvo.setNews_title(rs.getString("news_title"));
			newsvo.setNews_cnt(rs.getString("news_cnt"));
			
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
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
			
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	
	public static void main(String[]args){
		
		NewsJDBCDAO news = new NewsJDBCDAO();
		
		
		//新增
//		NewsVO newsvo1 = new NewsVO();
//		java.util.Date today = new java.util.Date();
//		long time = today.getTime();
//		Date date = new Date(time);
//		
//		newsvo1.setNews_date(date);
//		newsvo1.setNews_title("[公告]測試");
//		newsvo1.setNews_cnt("測試內容");
//		news.insert(newsvo1);
		
		//修改
		NewsVO newsvo2 = new NewsVO();
		java.util.Date today = new java.util.Date();
		long time = today.getTime();
		Date date = new Date(time);
		newsvo2.setNews_no("N006");
		newsvo2.setNews_date(date);
		newsvo2.setNews_title("測試修改");
		newsvo2.setNews_cnt("測試修改內容");
		news.update(newsvo2);
		
		//查詢單一資料
//		NewsVO newsvo3 = news.findByPrimaryKey("N001");
//		System.out.println("news_no="+newsvo3.getNews_no());
//		System.out.println("news_date="+newsvo3.getNews_date());
//		System.out.println("news_title="+newsvo3.getNews_title());
//		System.out.println("news_cnt="+newsvo3.getNews_cnt());
		
		//刪除
//		news.delete("N004");
		
		//getAll
//		List<NewsVO> list = news.getAll();
//		for(NewsVO newsvo : list){
//			System.out.println("news_no"+newsvo.getNews_no());
//			System.out.println("news_date"+newsvo.getNews_date());
//			System.out.println("news_title"+newsvo.getNews_title());
//			System.out.println("news_cnt"+newsvo.getNews_cnt());
//			System.out.println("==============================");
//		}
	}
	
}
