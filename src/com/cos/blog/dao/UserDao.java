package com.cos.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cos.blog.config.DBConn;
import com.cos.blog.model.User;
import com.mysql.cj.protocol.Resultset;

public class UserDao {
	
	private static UserDao instance = new UserDao();
	public static UserDao getInstance() {
		return instance;
	}	
	private UserDao() {}
	
	public int 회원가입(User user) {
		String sql = "INSERT INTO user(username,password,email,address,createDate) "
					+ "VALUES	 (?,?,?,?,NOW()) ";
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());
			
			return pstmt.executeUpdate();
		
		}catch (Exception e) {
		// TODO: handle exception
			System.out.println("회원가입 error : " + e.getMessage());
		} 			
		return -1;
	}
	
	public User 로그인(User user) {
		String sql = "SELECT id,username,email,address FROM user WHERE username = ? AND password=?";
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());

			ResultSet rs =  pstmt.executeQuery();
			if(rs.next()) {
				/*
				 * User userEntity = new User( rs.getInt("id"), rs.getString("username"),
				 * rs.getString("email"), rs.getString("address") );
				 */
				User userEntity = User.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.email(rs.getString("email"))
						.address(rs.getString("address"))
						.build();
				return userEntity;
			}
		
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("로그인 error : " + e.getMessage());
		} 		
		
		return null;
	}
	
	public int 회원수정(User user) {
		String sql = "UPDATE user SET password=?,email =?,address =? ,createDate=now()"
					+"WHERE id=?";
		
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getAddress());
			pstmt.setInt(4,user.getId());
			 
			return  pstmt.executeUpdate();
			
		}catch (Exception e) {
		// TODO: handle exception
			System.out.println("회원수정 error : " + e.getMessage());
		} 			
		return -1;
	}
}
