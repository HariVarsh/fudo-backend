package com.mindtree.fudo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="customerId")
	private int customerId;
	
	@Column(name="firstName",nullable=false)
	private String firstName;
	
	@Column(name="lastName",nullable=false)
	private String lastName;
	
	@Column(name="customerPhone",nullable=false,unique=true)
	private long customerPhone;
	
	@Column(name="customerEmail",nullable=false,unique=true)
	private String customerEmail;
	
	@Column(name="password",nullable=false)
	private String password;
	
	@Column(name="referralCode",nullable=false,unique=true)
	private String referralCode;
	
	@Column(name="points")
	private int points;
	
	@OneToMany(mappedBy="customer")
	List<MyOrder> orderList;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int customerId, String firstName, String lastName, long customerPhone, String customerEmail,
			String password, String referralCode, int points) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
		this.password = password;
		this.referralCode = referralCode;
		this.points = points;
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


	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", customerPhone=" + customerPhone + ", customerEmail=" + customerEmail + ", password=" + password
				+ ", referralCode=" + referralCode + ", points=" + points + "]";
	}
	
}
