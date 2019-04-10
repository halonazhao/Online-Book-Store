package com.mvc.biz;

import java.util.List;
import java.util.Set;

import com.mvc.dao.BookDao;
import com.mvc.entity.TBook;

public class BookBiz {
	public List<TBook> getCart(Set<String> isbns) throws Exception{
		List<TBook> books = null;
		if(isbns != null) {
			BookDao dao = new BookDao();
			try {
				books = dao.getCart(isbns);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dao.closeConnection();
				return books;
			}

		}else {
			throw new Exception("isbn is empty");
		}
	}
	public List<TBook> getAllBooks() throws Exception{
		List<TBook> books = null;
		BookDao dao = new BookDao();
		books = dao.getAllBooks();
		return books;
	}
	public byte[] getPic(String isbn) throws Exception{
		if(isbn != null) {
			byte[] pic = null;
			BookDao dao = new BookDao();
			try {
				pic = dao.getPic(isbn);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dao.closeConnection();
				return pic;
			}

		}else {
			throw new Exception("isbn is not found");
		}

	}
	public TBook getBookDetail(String isbn) throws Exception{
		BookDao dao = new BookDao();
		TBook abook = null;
		try {
			abook = dao.getBookDetail(isbn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dao.closeConnection();
			return abook;
		}
	}
}
