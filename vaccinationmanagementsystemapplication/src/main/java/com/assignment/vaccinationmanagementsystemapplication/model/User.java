package com.assignment.vaccinationmanagementsystemapplication.model;


public class User<T> {
	private String userId;
	private String userPassword;
	
	public User(String userId, String userPassword){
		this.userId = userId;
		this.userPassword = userPassword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	

}
