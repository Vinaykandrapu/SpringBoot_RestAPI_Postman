package com.vinay.studentmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class StudentRequestDTO {

	@NotBlank(message = "Name can't be empty")
	private String name;
	
	@NotBlank(message = "Email can't be empty")
	@Email(message = "Enter a valid emails")
	private String email;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public StudentRequestDTO(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	public StudentRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
