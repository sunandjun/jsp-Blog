package com.cos.blog.config.action.post;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.config.action.Action;
import com.cos.blog.dao.PostDao;
import com.cos.blog.model.Post;
import com.cos.blog.model.User;

public class PostUpdateProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1세션확인
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("principal");
		
		String userIds = request.getParameter("userId");
		if(userIds == null) {
			System.out.println("getParameter(\"userId\") = null");
			return;
		}
		
		int userId = Integer.parseInt(userIds);
		if(user == null) {
			System.out.println("test:user=null");
			return;
		} if(user.getId() != userId) {
			System.out.println("test:user!=userId");
			return;
		}
		System.out.println("test:1");
		//2공백 null 확인
		//3값 검증          
		String postId = request.getParameter("id");
		System.out.println("test:2" + postId);
		int id =Integer.parseInt(postId);
		System.out.println("test:3" + id);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		
		System.out.println("title = " + title);
		System.out.println("content = " + content);
		System.out.println("userId = " + userId);
		
		title = title.replaceAll("<", "&lt;");
		title = title.replaceAll(">", "&gt;");
		
		//Post post = new Post(id,title,content);
		Post post = Post.builder()
				.id(id)
				.title(title)
				.content(content)
				.build();
		
		
		PostDao dao =  PostDao.getInstance();
		int n = dao.수정하기(post);
		
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = response.getWriter();
		if(n == 1) {
			//response.sendRedirect("/post?cmd=list");
			pw.print("ok");
		}else {
			pw.print("fail");
		}
		
	}

}
