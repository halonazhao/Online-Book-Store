package com.mvc.entity;

public class TOrderDetail {
	private int aid;
	private String orderNo;
	private String isbn;
	private int amount;
	private double dealprice;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getDealprice() {
		return dealprice;
	}
	public void setDealprice(double dealprice) {
		this.dealprice = dealprice;
	}
}
