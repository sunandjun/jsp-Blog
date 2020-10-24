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
import com.cos.blog.utils.Script;

public class PostSearchAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("keyword") == null || request.getParameter("keyword").equals("")) {
			Script.back(response, "검색 키워드가 없습니다.");
			return;
		}
		//최초 요청시 0 그뒤로부터는 +1 -1 값이 들어옴
		//널값 체크해야함 원래는
		int page = Integer.parseInt(request.getParameter("page"));
		String keyword = request.getParameter("keyword");
		
		PostDao postDao = PostDao.getInstance();
		List<Post> posts = postDao.글목록(page,keyword);
		int count = postDao.글총갯수(keyword);
		int lastPage = (count % 3) == 0 ? (count /3) : (count/3)+1;
		lastPage = lastPage -1;
		
		
		request.setAttribute("posts", posts);
		request.setAttribute("totalCount",lastPage);
		
		RequestDispatcher rd = request.getRequestDispatcher("/post/list.jsp");
		rd.forward(request, response);
	}

}
