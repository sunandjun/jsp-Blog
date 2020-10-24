package com.cos.blog.config.action.user;

import java.io.IOException;
import java.net.HttpCookie;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.config.action.Action;
import com.cos.blog.dao.UserDao;
import com.cos.blog.model.User;
import com.cos.blog.utils.Script;
import com.sun.security.ntlm.Server;

public class UserLoginProcAction implements Action{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * User user = new User( request.getParameter("username"),
		 * request.getParameter("password"));
		 */
		
		//체크 박스가 값이 체크 되어 있면 on 이 넘어온다.
		System.out.println(request.getParameter("remember"));
		
		User user = User.builder()
				.username(request.getParameter("username"))
				.password(request.getParameter("password"))
				.build();
		System.out.println(user);
		//1. 로그인 진행 (select) Model로 이동
		UserDao userDao = UserDao.getInstance();
		User userEntity = userDao.로그인(user);
		//2. 세션에 저장
		if(userEntity != null) {
			
			if(request.getParameter("remember") != null) {
				//쿠키에 값을 넣는다.
			
				
				Cookie cookie = new Cookie("remember",
						URLEncoder.encode(userEntity.getUsername(),"utf-8"));
				
				response.addCookie(cookie);
				//위에 코드와 같은 의미 임
				//response.setHeader("Set-Cookie", "remember="+userEntity.getUsername());
			}else {
				Cookie cookie = new Cookie("remember","");
				cookie.setMaxAge(0);//타이머를 0초로 만든다. 0초뒤에 사라진다.
				response.addCookie(cookie);
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("principal", userEntity);
			System.out.println(userEntity);
			//3. 메인 페이지 이동 Redirect
			//response.sendRedirect("/index.jsp");
			Script.href(response, "/", "로그인 성공");
		}else {
			//로그인 실패!!
			Script.back(response, "아이디 혹은 비밀번호가 틀렸습니다.");			
		} 			
			
	}//execute()
}//UserLoginProcAction.class
