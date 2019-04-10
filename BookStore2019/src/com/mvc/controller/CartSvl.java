package com.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CartSvl
 */
@WebServlet("/CartSvl")
public class CartSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartSvl() {
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
		String isbn = request.getParameter("isbn");
	}
	
	private String getSaleCookieString(HttpServletRequest request, HttpServletResponse response, String cookieName) throws ServletException, IOException {
		String strRet = null;
		Cookie[] cookies = request.getCookies();
		for(int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			String cm = cookie.getName();
			if(cm.equals(cookieName)) {
				strRet = cookie.getName();
				System.out.println(cm + "-----age=" + cookie.getMaxAge());
				System.out.println(cm + "-----path=" + cookie.getPath());
				System.out.println(cm + "-----value="+strRet);
				break;
			}
		}
		return strRet;
	}

}
