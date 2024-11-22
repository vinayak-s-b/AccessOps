package com.SpringMicro.UserService.Model;

import javax.swing.text.Document;

public class WrapperClass {

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	private User user;
	private Department department;
}
