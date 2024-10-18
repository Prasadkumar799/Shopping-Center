package com.entity;

public class Customers {
	private long user_id;
	private String user_firstname;
	private String user_lastname;
	private String user_email;
	private String user_password;
	private String user_contact;
	private String user_address;
	
	public Customers(long user_id, String user_firstname, String user_lastname, String user_email,
			String user_password, String user_contact, String user_address) {
		super();
		this.user_id = user_id;
		this.user_firstname = user_firstname;
		this.user_lastname = user_lastname;
		this.user_email = user_email;
		this.user_password = user_password;
		this.user_contact = user_contact;
		this.user_address = user_address;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getUser_firstname() {
		return user_firstname;
	}
	public void setUser_firstname(String user_firstname) {
		this.user_firstname = user_firstname;
	}
	public String getUser_lastname() {
		return user_lastname;
	}
	public void setUser_lastname(String user_lastname) {
		this.user_lastname = user_lastname;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_contact() {
		return user_contact;
	}
	public void setUser_contact(String user_contact) {
		this.user_contact = user_contact;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	
	@Override
	public String toString() {
		return "Customers [user_id=" + user_id + ", user_firstname=" + user_firstname + ", user_lastname="
				+ user_lastname + ", user_email=" + user_email + ", user_password=" + user_password + ", user_contact="
				+ user_contact + ", user_address=" + user_address + "]";
	}
	
	
	
}
