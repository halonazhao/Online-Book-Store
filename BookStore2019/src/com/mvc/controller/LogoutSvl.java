package com.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.UserBiz;
import com.mvc.entity.TUser;

/**
 * Servlet implementation class LogoutSvl
 */
@WebServlet("/LogoutSvl")
public class LogoutSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutSvl() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate(); //remove all the information
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://" + request.getServerName()+":"+request.getServerPort()+path;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
	        Cookie cookie = new Cookie("user", "");
	        cookie.setMaxAge(0);
	        response.addCookie(cookie);
		}
		response.sendRedirect(basePath + "/MainSvl");
	}

}
