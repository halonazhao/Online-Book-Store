package com.mvc.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.UserBiz;
import com.mvc.entity.TUser;

/**
 * Servlet implementation class paymentSvl
 */
@WebServlet("/user/paymentSvl")
public class paymentSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public paymentSvl() {
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
		String money = request.getParameter("totalPrice");
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://" + request.getServerName()+":"+request.getServerPort()+path;
		if(money != null) {
			double tmoney = Double.valueOf(money);
			UserBiz biz = new UserBiz();
			TUser auser = (TUser) request.getSession().getAttribute("user");

			try {
				if(auser.getBalance() >= tmoney) {
					biz.buyBooks(auser.getUname(), tmoney, (Map<String, Integer>)request.getSession().getAttribute("shoppingCart"));
					auser.setBalance(auser.getBalance()-tmoney); //update balance in session;
					request.setAttribute("totalPrice", money);
					request.getRequestDispatcher("/WEB-INF/main/payment.jsp").forward(request, response);
				}else {
					request.setAttribute("Error", "Balance is insufficient.");
					request.getRequestDispatcher("/Error.jsp").forward(request, response);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("Error", "Went wrong");
				request.getRequestDispatcher("/Error.jsp").forward(request, response);
				e.printStackTrace();
			}
		}else {
			request.setAttribute("Error", "Went wrong");
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}

	}

}
