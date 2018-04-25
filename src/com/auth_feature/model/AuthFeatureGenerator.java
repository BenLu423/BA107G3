package com.auth_feature.model;

import java.io.*;
import java.sql.*;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class AuthFeatureGenerator implements IdentifierGenerator{

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		
		String prefix = "AF";
		String adm_no = null;
		Connection con = session.connection();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT AUTH_FEATURE_SEQ.NEXTVAL as nextval FROM DUAL");
			rs.next();
			int nextval = rs.getInt("nextval");
			adm_no = prefix+String.format("%3d", nextval).replace(" ", "0");
			con.close();
		}catch(SQLException se){
			throw new HibernateException("Unable to generate Sequence");
		}
		
		
		return adm_no;
	}
	
}
