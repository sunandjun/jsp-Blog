package com.cos.blog.config.action.user;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.config.DBConn;
import com.cos.blog.config.action.Action;
import com.cos.blog.model.User;

public class UserUpdateFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//회원수정 페이지로 이동 Model로 이동 후 RequestDispatcherS
		HttpSession session = request.getSession();
		User user =	(User)session.getAttribute("principal");
		
		//Connection conn = DBConn.getInstance();
		
		request.setAttribute("userData", user);
		
		RequestDispatcher rd = request.getRequestDispatcher("/user/updateForm.jsp");
		rd.forward(request, response);
				
	}

}
