package com.mindtree.fudo.dto;

public class UpdatePasswordDto {
	
	private String email;
	private String password;
	
	public UpdatePasswordDto() {
		super();
	}
	
	public UpdatePasswordDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
