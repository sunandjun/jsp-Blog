package com.cos.blog.config.action.post;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.config.action.Action;
import com.cos.blog.dao.PostDao;
import com.cos.blog.model.Post;

public class PostUpdateFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//수정 화면으로 데이터를 넘준다.
		String s = request.getParameter("id");
		
		// id 파람이 없을때   || 파람은 있는데 값이 없을때
		if(s == null || s.equals("")) {
			s = "0";
		}
		
		int id = Integer.parseInt(s);
		System.out.println("post id = " + s);
		
		PostDao dao =  PostDao.getInstance();
		Post post = dao.상세보기(id);

		request.setAttribute("post", post);
		
		RequestDispatcher rd = request.getRequestDispatcher("/post/updateForm.jsp");
		rd.forward(request, response);	
		
	}

}
