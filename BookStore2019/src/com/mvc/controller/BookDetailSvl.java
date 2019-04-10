package com.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.BookBiz;
import com.mvc.entity.TBook;

/**
 * Servlet implementation class BookDetailSvl
 */
@WebServlet("/BookDetailSvl")
public class BookDetailSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookDetailSvl() {
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
		if(isbn != null) {
			BookBiz biz = new BookBiz();
			TBook abook = null;
			try {
				abook = biz.getBookDetail(isbn);
				request.setAttribute("abook", abook);
				request.getRequestDispatcher("/WEB-INF/main/BookDetail.jsp").forward(request, response);;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			request.setAttribute("error", "Went wrong");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		
	}

}
