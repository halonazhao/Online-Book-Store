package com.mvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mvc.entity.TUser;

public class UserDao extends BaseDao{
	public TUser login(String uname, String pwd) throws Exception{
		TUser auser = null;
		String sql = "select * from userbook where username = ? and password = ?";
		try {
			this.openConnection();
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				auser = new TUser();
				auser.setPwd(pwd);
				auser.setRole(rs.getString("role"));
				auser.setUname(uname);
				auser.setBalance(rs.getDouble("balance"));
				break;
			}
			rs.close();
			ps.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auser;
		
	}
	
	public void cutBalance(String uname, double money) throws Exception{
		String sql = "update userbook set balance = balance-? where uname=?";
		this.openConnection();
		PreparedStatement ps = this.connection.prepareStatement(sql);
		ps.setDouble(1, money);
		ps.setString(2, uname);
		ps.executeUpdate();
		ps.close();
		
	}
	public void fillBalance(double money) throws Exception{
		
	}
}
