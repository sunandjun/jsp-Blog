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

public class PostDetailAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1> id 값 받기(null 인지 체크)
		String idString = request.getParameter("id");
		
		// id 파람이 없을때   || 파람은 있는데 값이 없을때
		if(idString == null || idString.equals("")) {
			idString = "0";
		}
		int id = Integer.parseInt(idString);
		
		//2> DAO 연결해서 Post postEntity = 상세보기(id) 함수 호출
		PostDao dao =  PostDao.getInstance();
		//추가 : 조회수 증가
		int result = dao.조회수증가(id);
		if(result == 1) {
			Post postEntity = dao.상세보기(id);
			
			//3> request postEntity 담기
			request.setAttribute("post", postEntity);
			
			//4> deltail.jsp 이동 => RequestDispatcher 사용하기
			RequestDispatcher rd = request.getRequestDispatcher("/post/detail.jsp");
			rd.forward(request, response);
		}else {
			// response.sendRedirect("/");
			Script.href(response, "/", "잘못된 접근 입니다.");
		}
		
		
		
	}

}
