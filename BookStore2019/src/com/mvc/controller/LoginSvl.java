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
 * Servlet implementation class LoginSvl
 */
@WebServlet("/LoginSvl")
public class LoginSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/main/Login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		UserBiz biz = new UserBiz();
		TUser auser;
		try {
			auser = biz.login(uname, pwd);
			if(auser != null) {
				request.getSession().setAttribute("user", auser);
				//write cookie
				Cookie cookie = new Cookie("user", uname+"#"+pwd);
				cookie.setMaxAge(60*60);
				response.addCookie(cookie);
				request.getRequestDispatcher("/MainSvl").forward(request, response);
			}else {
				request.setAttribute("message", "username/password invalid");
				request.getRequestDispatcher("/WEB-INF/main/Login.jsp").forward(request, response);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", "Internet connection broke.");
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		}
	}

}
