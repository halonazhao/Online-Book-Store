package com.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.BookBiz;

/**
 * Servlet implementation class Picload
 */
@WebServlet("/PicloadSvl")
public class PicloadSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PicloadSvl() {
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
		// TODO Auto-generated method stub
		String isbn = request.getParameter("isbn");
		System.out.println(isbn);
		if(isbn == null) {
			request.setAttribute("error", "isbn wrong");
			request.getRequestDispatcher("Error.jsp");
		}else {
			BookBiz biz = new BookBiz();
			try {
				byte[] pic = biz.getPic(isbn);
				if(pic != null) {
					ServletOutputStream stm = response.getOutputStream();
					stm.write(pic);
					stm.flush();
					stm.close();
				}

			} catch (Exception e) {
				request.setAttribute("error", "isbn wrong");
				request.getRequestDispatcher("Error.jsp");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
