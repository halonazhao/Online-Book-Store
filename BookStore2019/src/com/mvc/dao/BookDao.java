package com.mvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.mvc.entity.TBook;
import com.mvc.entity.TOrderDetail;

public class BookDao extends BaseDao{
	public List<TBook> getCart(Set<String> isbns) throws Exception{
		List<TBook> books = null;
		String isbnsString = null;
		int i = 0;
		for(String isbn: isbns) {
			if(i==0) {
				isbnsString = "'"+isbn+"'";
			}else {
				isbnsString = isbnsString + ",'" + isbn + "'";
			}
			i = i+1;
		}
		String sql = "select isbn,name,author,publication,publicationdate,price,description from book where isbn in ("
				+ isbnsString + ")";
		System.out.println(sql);
		this.openConnection();
		PreparedStatement ps = this.connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		books = new ArrayList<TBook>();
		while(rs.next()) {
			TBook abook = new TBook();
			abook.setIsbn(rs.getString("isbn"));
			abook.setName(rs.getString("name"));
			abook.setAuthor(rs.getString("author"));
			abook.setPublication(rs.getString("publication"));
			abook.setPubDate(rs.getDate("publicationdate"));
			abook.setPrice(rs.getDouble("price"));
			abook.setDescription(rs.getString("description"));		
			books.add(abook);
		}
		ps.close();
		rs.close();
		return books;
	}
	
	public List<TBook> getAllBooks() throws Exception{
		List<TBook> books = null;
		String sql = "select isbn,name,author,publication,publicationdate,price,description from book";
		this.openConnection();
		PreparedStatement ps = this.connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		books = new ArrayList<TBook>();
		while(rs.next()) {
			TBook abook = new TBook();
			abook.setIsbn(rs.getString("isbn"));
			abook.setName(rs.getString("name"));
			abook.setAuthor(rs.getString("author"));
			abook.setPublication(rs.getString("publication"));
			abook.setPubDate(rs.getDate("publicationdate"));
			abook.setPrice(rs.getDouble("price"));
			abook.setDescription(rs.getString("description"));		
			books.add(abook);
		}
		ps.close();
		rs.close();
		return books;
	}
	
	public byte[] getPic(String isbn) throws Exception{
		byte[] pic = null;
		String sql = "select picture from book where isbn = ?";
		this.openConnection();
		PreparedStatement ps = this.connection.prepareStatement(sql);
		ps.setString(1, isbn);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			pic = rs.getBytes("picture");
		}
		ps.close();
		rs.close();
		return pic;
	}
	
	public TBook getBookDetail(String isbn) throws Exception{
		TBook abook = null;
		String sql = "select isbn,name,author,publication,publicationdate,price,description from book where isbn = ?";
		this.openConnection();
		PreparedStatement ps = this.connection.prepareStatement(sql);
		ps.setString(1, isbn);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			abook = new TBook();
			abook.setIsbn(rs.getString("isbn"));
			abook.setName(rs.getString("name"));
			abook.setAuthor(rs.getString("author"));
			abook.setPublication(rs.getString("publication"));
			abook.setPubDate(rs.getDate("publicationdate"));
			abook.setPrice(rs.getDouble("price"));
			abook.setDescription(rs.getString("description"));
			break;
		}
		ps.close();
		rs.close();
		return abook;
	}
	
	public String createOrder(String uname, double totalPrice) throws Exception{
		String ordNo="";
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		ordNo = "Ord" + sd.format(new Date());
		String sql = "insert into bookOrder values (?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.connection.prepareStatement(sql);
		ps.setString(1, ordNo);
		ps.setString(2, uname);
		ps.setDouble(3, totalPrice);
		ps.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
		ps.executeUpdate();
		return ordNo;
	}
	
	public void addOrderDetail(TOrderDetail detail) throws Exception{
		String sql = "insert into orderdetail values( (select nvl(max(id),0)+1 from orderdetail),?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.connection.prepareStatement(sql);
		ps.setString(1, detail.getOrderNo());
		ps.setString(2, detail.getIsbn());
		ps.setInt(3, detail.getAmount());
		ps.setDouble(4, detail.getDealprice());
		ps.executeUpdate();
		ps.close();
		updateStock(detail.getIsbn(), detail.getAmount());
	}
	
	private void updateStock(String isbn, int amountBought) throws Exception{
		String sql = "update book set stock=stock-? where isbn = ?";
		this.openConnection();
		PreparedStatement ps = this.connection.prepareStatement(sql);
		ps.setInt(1, amountBought);
		ps.setString(2, isbn);
		ps.executeUpdate();
		ps.close();
	}
}
