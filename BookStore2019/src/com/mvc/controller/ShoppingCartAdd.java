package com.mvc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.BookBiz;
import com.mvc.entity.TBook;

/**
 * Servlet implementation class ShoppingCartAdd
 */
@WebServlet("/user/ShoppingCartAdd")
public class ShoppingCartAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCartAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object obj = request.getSession().getAttribute("user");
		if(obj == null) {
			//If not loggin, should loggin first; 
			request.getRequestDispatcher("/WEB-INF/main/Login.jsp").forward(request, response);;
		}
		String isbn = request.getParameter("isbn");
		if(isbn == null) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "Went wrong");
			request.getRequestDispatcher("/Error.jsp").forward(request, response);;
		}else {
			Object objc = request.getSession().getAttribute("shoppingCart");
			Map<String, Integer> shopMap = null;
			if(objc == null) {
				shopMap = new HashMap<String, Integer>();
				request.getSession().setAttribute("shoppingCart", shopMap);
			}else {
				shopMap = (Map<String, Integer>)objc;
				shopMap.put(isbn, 1);
				request.getRequestDispatcher("/user/ShoppingCartSvl").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
