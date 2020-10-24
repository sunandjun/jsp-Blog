package com.cos.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.config.DBConn;
import com.cos.blog.model.Post;

public class PostDao {
	
	private PostDao() {};
	private static PostDao instance = new PostDao();
	public static PostDao getInstance() {
		return instance;
	}
	public int 글쓰기(Post post) {
		String sql = "INSERT INTO post (title,content,readCount,createDate,userId) "
				+ "VALUES	 (?,?,?,NOW(),?) ";
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, post.getTitle());
			pstmt.setString(2, post.getContent());
			pstmt.setInt(3,post.getReadCount());
			pstmt.setInt(4, post.getUserId());
			
			return pstmt.executeUpdate();
		
		}catch (Exception e) {
		// TODO: handle exception
			System.out.println("글쓰기 error : " + e.getMessage());
		} 			
		return -1;
	}
	

	public int 글쓰기2(Post post) {
		String sql = "INSERT INTO post2(title,content,readCount,createDate,userId) "
				+ "VALUES	 (?,?,?,NOW(),?) ";
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, post.getTitle());
			pstmt.setString(2, post.getContent());
			pstmt.setInt(3,post.getReadCount());
			pstmt.setInt(4, post.getUserId());
			
			return pstmt.executeUpdate();
		
		}catch (Exception e) {
		// TODO: handle exception
			System.out.println("글쓰기 error : " + e.getMessage());
		} 			
		return -1;
	}
	
	public List<Post> 글목록(int page, String keyword) {
		String sql = "SELECT * FROM post WHERE title like ? OR content like ? ORDER BY id DESC limit ?,3";  //DESC 내림차순 정리 ASC 오름차순
		List<Post> posts = new ArrayList<>();
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword +"%");
			pstmt.setString(2, "%"+keyword +"%");
			pstmt.setInt(3, page * 3);
					
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				/*
				 * Post post = new Post( rs.getInt("id"), rs.getString("title"),
				 * rs.getString("content"), rs.getInt("readCount"),
				 * rs.getTimestamp("createDate"), rs.getInt("userId") );
				 */
				Post post = Post.builder()
						.id(rs.getInt("id"))
						.title( rs.getString("title"))
						.content( rs.getString("content"))
						.readCount( rs.getInt("readCount"))
						.createDate(rs.getTimestamp("createDate"))
						.userId(rs.getInt("userId"))
						.build();
				posts.add(post);
			}
			
			return posts;
		
		}catch (Exception e) {
		// TODO: handle exception
			System.out.println("글목록 error : " + e.getMessage());
		} 			
		
		
		return null;
	}
	public List<Post> 글목록(int page) {
		String sql = "SELECT * FROM post ORDER BY id DESC limit ?,3";  //DESC 내림차순 정리 ASC 오름차순
		List<Post> posts = new ArrayList<>();
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page * 3);
					
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				/*
				 * Post post = new Post( rs.getInt("id"), rs.getString("title"),
				 * rs.getString("content"), rs.getInt("readCount"),
				 * rs.getTimestamp("createDate"), rs.getInt("userId") );
				 */
				Post post = Post.builder()
						.id(rs.getInt("id"))
						.title( rs.getString("title"))
						.content( rs.getString("content"))
						.readCount( rs.getInt("readCount"))
						.createDate(rs.getTimestamp("createDate"))
						.userId(rs.getInt("userId"))
						.build();
				posts.add(post);
			}
			
			return posts;
		
		}catch (Exception e) {
		// TODO: handle exception
			System.out.println("글목록 error : " + e.getMessage());
		} 			
		
		
		return null;
	}
	
	public Post 상세보기(int id) {
		String sql = "SELECT * FROM post WHERE id = ?";  
		Post post = Post.builder().build();
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				post.setId(rs.getInt("id"));
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));						
				post.setReadCount(rs.getInt("readCount"));
				post.setCreateDate(rs.getTimestamp("createDate"));
				post.setUserId(rs.getInt("userId"));											
			}			
			return post;
		
		}catch (Exception e) {
		// TODO: handle exception
			System.out.println("상세보기 error : " + e.getMessage());
		} 			
		return null;
	}
	
	public int 조회수증가(int id) {
		String sql = "UPDATE post SET readCount = readCount +1 WHERE  id=? ";
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			return pstmt.executeUpdate();
		
		}catch (Exception e) {
		// TODO: handle exception
			System.out.println("조회수증가" + e.getMessage());
		} 			
		return -1;
	}
	public int 삭제하기(int id) {
		String sql = "DELETE FROM post WHERE id=?";
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			return pstmt.executeUpdate();
		
		}catch (Exception e) {
		// TODO: handle exception
			System.out.println("삭제하기" + e.getMessage());
		} 			
		return -1;
	}
	
	public int 수정하기(Post post) {
		String sql = "UPDATE  post SET title =?,content=?,createDate=now() "
				+ "WHERE  id=? ";
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, post.getTitle());
			pstmt.setString(2, post.getContent());
			pstmt.setInt(3, post.getId());
			
			return pstmt.executeUpdate();
		
		}catch (Exception e) {
		// TODO: handle exception
			System.out.println("수정하기 error : " + e.getMessage());
		} 			
		return -1;
	}
	public int 글총갯수(String keyword) {
		String sql = "SELECT count(*) FROM post WHERE title like ? OR content like ?;";  
		int count=0;
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword + "%");
			pstmt.setString(2, "%"+keyword + "%");
					
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}			
			System.out.println("글 총 갯수는 : "+count);
			return count;
		
		}catch (Exception e) {
		// TODO: handle exception
			System.out.println("글총갯수 error : " + e.getMessage());
		} 			
		return 0;
	}
	public int 글총갯수() {
		String sql = "SELECT count(*) FROM post;";  
		int count=0;
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
					
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}			
			System.out.println("글 총 갯수는 : "+count);
			return count;
		
		}catch (Exception e) {
		// TODO: handle exception
			System.out.println("글총갯수 error : " + e.getMessage());
		} 			
		return 0;
	}
}
