package com.cos.blog.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.config.DBConn;
import com.cos.blog.config.action.Action;
import com.cos.blog.config.action.user.UserJoinFormAction;
import com.cos.blog.config.action.user.UserJoinProcAction;
import com.cos.blog.config.action.user.UserLoginFormAction;
import com.cos.blog.config.action.user.UserLoginProcAction;
import com.cos.blog.config.action.user.UserLogoutAction;
import com.cos.blog.config.action.user.UserUpdateFormAction;
import com.cos.blog.config.action.user.UserUpdateProcAction;
import com.cos.blog.dao.UserDao;
import com.cos.blog.model.Post;
import com.cos.blog.model.User;

//http://localhost:8080/blog/ffidfdf.do
//모든 .do 요청은 FrontController를 탄다.
@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserController() {
        super();
       
    }
    //http://localhost:8080/post/list.do
     void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 System.out.println("/user 요청됨");
 		String cmd = request.getParameter("cmd");
 		System.out.println("cmd = " + cmd);
 		 		
 		Action action = route(cmd);
 		if(action != null)action.execute(request, response);
    }
     
     private Action route(String cmd) {
    	 if(cmd.equals("joinForm")) {
  			//회원가입 페이지 Redirect
  			return new UserJoinFormAction();
  		}else if(cmd.equals("loginForm")) {
  			//로그인 페이지 Redirect
  			return new UserLoginFormAction();
  		}else if(cmd.equals("updateForm")) {
  			return new UserUpdateFormAction();
  		}else if(cmd.equals("joinProc")) {
  			return new UserJoinProcAction();
  		}else if(cmd.equals("loginProc")) {
  			return new UserLoginProcAction();
  		}else if(cmd.equals("updateProc")) {
  			return new UserUpdateProcAction();
  		}else if(cmd.equals("logout")) {
  			return new UserLogoutAction();
  		}
    	 return null;
     }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
