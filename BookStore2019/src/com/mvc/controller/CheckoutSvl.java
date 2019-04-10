package com.mvc.controller;

import java.io.IOException;
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
 * Servlet implementation class CheckoutSvl
 */
@WebServlet("/user/CheckoutSvl")
public class CheckoutSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutSvl() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//here needs a validation for user login
		Map<String, Integer> shoppingCart = (Map<String, Integer>)request.getSession().getAttribute("shoppingCart");
		BookBiz biz= new BookBiz();
		try {
			List<TBook> books = biz.getCart(shoppingCart.keySet());
			double totalPrice = 0;
			for(TBook abook : books) {
				String amount = request.getParameter(abook.getIsbn());
				int amountN = Integer.valueOf(amount);
				abook.setAmount(amountN);
				shoppingCart.put(abook.getIsbn(), amountN);
				totalPrice += abook.getPrice()*amountN;
			}
			request.setAttribute("totalPrice", totalPrice);
			request.setAttribute("books", books);
			request.getRequestDispatcher("/WEB-INF/main/checkout.jsp").forward(request, response);;
		} catch (Exception e) {
			request.setAttribute("Error", "Went wrong");
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}

	}

}
