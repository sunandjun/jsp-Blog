package com.cos.blog.config.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.config.action.Action;
import com.cos.blog.dao.UserDao;
import com.cos.blog.model.User;
import com.cos.blog.utils.Script;

public class UserJoinProcAction implements Action{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 회원가입 진행 (insert) Model로 이동
			System.out.println("Usercontroller : joinProc : ");
			/*User user = new User(
					request.getParameter("username"),
					request.getParameter("password"),
					request.getParameter("email"),
					request.getParameter("address")
					);*/
			User user = User.builder()
					.username(request.getParameter("username"))
					.password(request.getParameter("password"))
					.email(request.getParameter("email"))
					.address(request.getParameter("address"))
					.build();
			UserDao userDao = UserDao.getInstance();
			int n = userDao.회원가입(user);
			//2. 로그인 페이지 이동 Redirect
			if(n == 1) {
				//response.sendRedirect("/user/loginForm.jsp");
				Script.href(response, "/user/loginForm.jsp", "회원가입에 성공하셨습니다.");
			}else {
				Script.back(response, "회원가입에 실패 하셨습니다.");
			}
			
	}
}
