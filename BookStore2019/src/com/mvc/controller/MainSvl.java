package com.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.BookBiz;
import com.mvc.biz.UserBiz;
import com.mvc.entity.TBook;
import com.mvc.entity.TUser;

import java.util.List;

/**
 * Servlet implementation class MainSvl
 */
@WebServlet("/MainSvl")
public class MainSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainSvl() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void service(HttpServletRequest request, HttpServletResponse response) 
    	throws ServletException, IOException{
    	//auto login verification
    	Object obj = request.getSession().getAttribute("user");
    	if(obj == null) {
    		Cookie[] cookies = request.getCookies();
    		if(cookies != null) {
    			for(int i=0; i<cookies.length;i++) {
        			Cookie cookie = cookies[i];
        			if(cookie.getName().equals("user")) {
        				String value = cookie.getValue();
        				String[] unameAndpwd = value.split("#");
        				UserBiz biz = new UserBiz();
        				try {
    						TUser auser = biz.login(unameAndpwd[0], unameAndpwd[1]);
    						if(auser != null) {
    							request.getSession().setAttribute("user", auser);
    						}
    					} catch (Exception e) {
    						// TODO Auto-generated catch block
    						request.setAttribute("error", "Went wrong");
    						request.getRequestDispatcher("/Error.jsp").forward(request, response);
    						e.printStackTrace();
    					}
        			}
        			break;
        		}
    		}	
    	}
    	BookBiz biz = new BookBiz();
    	try {
			List<TBook> books = biz.getAllBooks();
			request.setAttribute("books", books);
	    	request.getRequestDispatcher("/WEB-INF/main/main.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "Went wrong");
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
