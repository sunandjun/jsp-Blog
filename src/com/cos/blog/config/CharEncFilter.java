package com.cos.blog.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharEncFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		//doFilter하고나서 진행하는함수
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		System.out.println("한글 UTF-8 셋팅 완료");
		chain.doFilter(request, response); //필터 등록
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		//doFilter 하기전 함수
	}

}
