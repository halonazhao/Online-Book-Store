package com.mvc.biz;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mvc.dao.BookDao;
import com.mvc.dao.UserDao;
import com.mvc.entity.TBook;
import com.mvc.entity.TOrderDetail;
import com.mvc.entity.TUser;

public class UserBiz {
	public TUser login(String uname, String pwd) throws Exception{
		TUser aUser = null;

		if(uname != null && pwd != null && !uname.equals("") && !pwd.equals("")) {
			UserDao dao = new UserDao();
			aUser = dao.login(uname,pwd);
		}
		return aUser;
	}
	
	public void buyBooks(String uname, double totalPrice, Map<String, Integer> shoppingCart) throws Exception{
		int newBalance = 0;
		//make payment: 1. check balance sufficiency 2. make payment
		//create ord

		if(uname != null && shoppingCart != null) {
			UserDao userdao = new UserDao();
			try {
				userdao.beginTransaction();
				BookDao bookdao = new BookDao();
				bookdao.setConnection(userdao.getConnection()); // two dao have to use the same connection so that they can be in
				//the same transaction
				String orderNo = bookdao.createOrder(uname, totalPrice);
				Set<String> isbns = shoppingCart.keySet();
				//create the orderdetails  -- update inventory
				List<TBook> books = bookdao.getCart(isbns);
				for(TBook book:books) {
					TOrderDetail detail = new TOrderDetail();
					detail.setOrderNo(orderNo);
					detail.setAmount(shoppingCart.get(book.getIsbn()));
					detail.setIsbn(book.getIsbn());
					detail.setDealprice(book.getPrice());
					bookdao.addOrderDetail(detail);
				}
					userdao.commit();
			} catch (Exception e) {
				userdao.rollback();
				e.printStackTrace();
			} finally {
				userdao.closeConnection();	
			}

		}else {
			throw new Exception("invalid para");
		}
	}
	
}
