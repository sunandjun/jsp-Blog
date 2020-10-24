package com.cos.blog.config.action.post;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.config.action.Action;
import com.cos.blog.dao.PostDao;
import com.cos.blog.model.Post;

public class PostDeleteProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String s = request.getParameter("id");
		System.out.println(s);
		int id = Integer.parseInt(s);
		
		System.out.println(s);
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = response.getWriter();
		//postdao 연결
		PostDao dao = PostDao.getInstance();
		int result = dao.삭제하기(id);
				
		if(result == 1) {
			pw.print("ok");
		}else {
			pw.print("fail");
		}
		pw.flush();
	}

}
