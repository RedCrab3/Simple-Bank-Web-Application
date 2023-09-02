package com.luv2code.springdemo.DAO;

import java.util.List;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.entity.Transaction;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();

//	public void saveCustomer(Customer c);

	public Customer getCustomer(int id);

//	public void deleteCustomer(int id);

//	public List<Customer> searchCustomers(String searchName);

	public String SHA512(String pin);

	public List<Customer> getCustomers(int id);

	public String sendOTP(Customer c, int amount);

	public void setOtp(Customer sender, String otp);

	public int deductSender(Customer sender, int amount);

	public void sendDebitMsg(Customer sender, int amount);

	public int creditRecipient(Customer recipient, int amount);

	public void sendCreditMsg(Customer recipient, int amount);

	public void performTransaction(Customer sender, Customer recipient, int amount);

	public List<Transaction> getTransactions(long accountNumber);

}
