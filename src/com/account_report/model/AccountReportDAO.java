package com.account_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class AccountReportDAO implements AccountReportDAO_interface{

	
	private static final String INSERTREP = "INSERT INTO ACCOUNT_REPORT(MEM_NO_SELF,MEM_NO_OTHER,ACCREP_REASON,ACCREP_CNT) VALUES (?,?,?,?) ";
	private static final String UPDATE_REP_STATEMENTS = "UPDATE ACCOUNT_REPORT SET ACCREP_PERMIT = ? WHERE MEM_NO_SELF = ? AND MEM_NO_OTHER = ?";
	
	private static final String SEARCH_STATEMENT = "SELECT * FROM ACCOUNT_REPORT WHERE ACCREP_PERMIT = ?";
	private static final String GETALL = "SELECT * FROM ACCOUNT_REPORT";
	
	private static DataSource ds = null;
	static {
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/BA107G3");
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void accountReportAdd(AccountReportVO memrep) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERTREP);
			ps.setString(1, memrep.getMem_no_self());
			ps.setString(2, memrep.getMem_no_other());
			ps.setString(3, memrep.getAccrep_reason());
			ps.setString(4, memrep.getAccrep_cnt());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
					
			try{
				if(ps != null){
					ps.close();
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}	
			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void accountReportUpdate(AccountReportVO memrep) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
		con = ds.getConnection();
		ps = con.prepareStatement(UPDATE_REP_STATEMENTS);
		ps.setString(1, memrep.getAccrep_permit());
		ps.setString(2, memrep.getMem_no_self());
		ps.setString(3, memrep.getMem_no_other());
		ps.executeUpdate();
		
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			try{
				if(ps != null){
					ps.close();
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}	
			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<AccountReportVO> findStatement(String memrep) {
		List<AccountReportVO> listmemrep = new ArrayList<AccountReportVO>();
		AccountReportVO mrvo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(SEARCH_STATEMENT);
			ps.setString(1, memrep);
			rs = ps.executeQuery();
			while(rs.next()){
				mrvo = new AccountReportVO();
				mrvo.setMem_no_self(rs.getString("MEM_NO_SELF"));
				mrvo.setMem_no_other(rs.getString("MEM_NO_OTHER"));
				mrvo.setAccrep_reason(rs.getString("ACCREP_REASON"));
				mrvo.setAccrep_cnt(rs.getString("ACCREP_CNT"));
				mrvo.setAccrep_time(rs.getDate("ACCREP_TIME"));
				mrvo.setAccrep_permit(rs.getString("ACCREP_PERMIT"));
				listmemrep.add(mrvo);
			}
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		} finally{
			
			try{
				if(rs != null){
					rs.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			try{
				if(ps != null){
					ps.close();
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}	
			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return listmemrep;
	}

	@Override
	public List<AccountReportVO> getAll() {
		List<AccountReportVO> listmemrep = new ArrayList<AccountReportVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AccountReportVO mrvo = null;
		try{
			con = ds.getConnection();
			ps = con.prepareStatement(GETALL);
			rs = ps.executeQuery();
			
			while(rs.next()){
				mrvo = new AccountReportVO();
				mrvo.setMem_no_self(rs.getString("MEM_NO_SELF"));
				mrvo.setMem_no_other(rs.getString("MEM_NO_OTHER"));
				mrvo.setAccrep_reason(rs.getString("ACCREP_REASON"));
				mrvo.setAccrep_cnt(rs.getString("ACCREP_CNT"));
				mrvo.setAccrep_time(rs.getDate("ACCREP_TIME"));
				mrvo.setAccrep_permit(rs.getString("ACCREP_PERMIT"));
				listmemrep.add(mrvo);
			}
					
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			
			try{
				if(rs != null){
					rs.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			try{
				if(ps != null){
					ps.close();
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}	
			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return listmemrep;
	}

	
}
