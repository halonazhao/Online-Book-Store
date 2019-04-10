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
 * Servlet implementation class ShoppingCartSvl
 */
@WebServlet("/user/ShoppingCartSvl")
public class ShoppingCartSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCartSvl() {
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
		Object obj = request.getSession().getAttribute("shoppingCart");
		Map<String, Integer> shopMap = null;
		if(obj == null) {
			shopMap = new HashMap<String, Integer>();
			request.getSession().setAttribute("shoppingCart", shopMap);
		}else {
			shopMap = (Map<String, Integer>)obj;
		}
		BookBiz biz = new BookBiz();
		try {
			List<TBook> books = biz.getCart(shopMap.keySet());
			request.setAttribute("shoppingCart", books);
			request.getRequestDispatcher("/WEB-INF/main/shoppingCart.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("Error", "Went wrong");
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

}
