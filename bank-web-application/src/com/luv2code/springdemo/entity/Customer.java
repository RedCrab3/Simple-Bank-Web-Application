package com.luv2code.springdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="account_number")
	@NotNull(message="is required")
	@Min(1)
	private long accountNumber;
	
	@NotNull(message="is required")
	@Size(min=1,message="is required")
	@Column(name="first_name")
	private String firstName;
	
	@NotNull(message="is required")
	@Size(min=1,message="is required")
	@Column(name="last_name")
	private String lastName;
	
	@NotNull(message="is required")
	@Size(min=1,message="is required")
	@Pattern(regexp="^[A-Za-z0-9]+@[A-Za-z0-9]+.com$", message="Enter Valid Email")
	@Column(name="email")
	private String email;
	
	@NotNull(message="is required")
	@Min(1)
	@Column(name="balance")
	private double balance;
	
	@NotNull(message="is required")
	@Size(min=1,message="is required")
	@Column(name="pin")
	private String pin;
	
	@NotNull(message="is required")
	@Size(min=1,message="is required")
	@Column(name="number")
	private String number;
	
	@Column(name="otp")
	private String otp;

	public Customer() {
	}
	
	public Customer(long accountNumber, String firstName, String lastName, String email, double balance, String pin, String number) {
		this.accountNumber=accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.balance = balance;
		this.pin = pin;
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", accountNumber=" + accountNumber + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", balance=" + balance + ", pin=" + pin + ", number=" + number
				+ ", otp=" + otp + "]";
	}
}
