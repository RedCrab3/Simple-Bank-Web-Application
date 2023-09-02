package com.luv2code.springdemo.service;

import java.util.List;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.entity.Transaction;

public interface CustomerService {
	
	public List<Customer> getCustomers();

// public void saveCustomer(Customer c);

	public Customer getCustomer(int id);

//	public void deleteCustomer(int id);

//	public List<Customer> searchCustomers(String searchName);
	
	public String SHA512(String pin);

	public List<Customer> getCustomers(int id);

	public String sendOTP(Customer c, int amount);

	public void setOtp(Customer sender, String otp);

	public void performTransaction(Customer sender, Customer recipient, int amount);

	public List<Transaction> getTransactions(long accountNumber);

}
