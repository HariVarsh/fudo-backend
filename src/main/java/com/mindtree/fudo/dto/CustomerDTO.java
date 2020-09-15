package com.mindtree.fudo.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.mindtree.fudo.entity.MyOrder;


public class CustomerDTO {

	private int customerId;
	
	@NotBlank(message="FirstName is required")
	@NotNull(message="FirstName cannot be null")
	@Pattern(message="No invalid charecters Allowed in first name",regexp="^[a-zA-Z]+$")
	@Size(max=25,message="The FirstName should have only 25 charecters")
	private String firstName;

	@NotBlank(message="LastName is required")
	@NotNull(message="LastName cannot be null")
	@Pattern(message="No invalid charecters Allowed in Last name",regexp="^[a-zA-Z]+$")
	@Size(max=25,message="The LastName should have only 25 charecters")
	private String lastName;
	
//	@NotBlank(message="PHONE is required")
//	@NotNull(message="Phone cannot be null")
//	@Pattern(message="No invalid charecters Allowed in Phone",regexp="^[6-9][0-9]+$")
//	@Size(max=10,message="The length of the Phone number is invalid")
	private long customerPhone;
	
	@NotBlank(message="mail is required")
	@NotNull(message="mail cannot be null")
	@Email(message="The Formate is Not Valid")
	private String customerEmail;
	
	@NotBlank(message="Password is required")
	@NotNull(message="Password cannot be null")
	@Pattern(message="Password must contain one capital one small and on number",regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$")
	@Size(max=10,min=6,message="The Password Should have length between 6 and 10")
	private String password;
	
	private String referralCode;
	
	private int points;
	List<MyOrder> orderList;

	public CustomerDTO() {
		super();
	}

	public CustomerDTO(int customerId, String firstName, String lastName, long customerPhone, String customerEmail,
			String password, String referralCode, int points, List<MyOrder> orderList) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
		this.password = password;
		this.referralCode = referralCode;
		this.points = points;
		this.orderList = orderList;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(long customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public List<MyOrder> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<MyOrder> orderList) {
		this.orderList = orderList;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", customerPhone=" + customerPhone + ", customerEmail=" + customerEmail + ", password=" + password
				+ ", referralCode=" + referralCode + ", points=" + points + ", orderList=" + orderList + "]";
	}
	
}
