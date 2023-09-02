package com.luv2code.springdemo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.DAO.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.entity.Transaction;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDAO customerDAO;

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

//	@Override
//	@Transactional
//	public void saveCustomer(Customer c) {
//		customerDAO.saveCustomer(c);
//	}

	@Override
	@Transactional
	public Customer getCustomer(int id) {
		return customerDAO.getCustomer(id);
	}

//	@Override
//	@Transactional
//	public void deleteCustomer(int id) {
//		customerDAO.deleteCustomer(id);
//	}

//	@Override
//	@Transactional
//	public List<Customer> searchCustomers(String searchName) {
//		return customerDAO.searchCustomers(searchName);
//	}

	@Override
	public String SHA512(String pin) {
		return customerDAO.SHA512(pin);
	}

	@Override
	@Transactional
	public List<Customer> getCustomers(int id) {
		return customerDAO.getCustomers(id);
	}

	@Override
	public String sendOTP(Customer c, int amount) {
		return customerDAO.sendOTP(c, amount);
		
	}

	@Override
	@Transactional
	public void setOtp(Customer sender, String otp) {
		customerDAO.setOtp(sender, otp);
		
	}

	@Override
	@Transactional( rollbackFor = SQLException.class)
	public void performTransaction(Customer sender, Customer recipient, int amount) {
		customerDAO.performTransaction(sender, recipient, amount);
	}

	@Override
	@Transactional
	public List<Transaction> getTransactions(long accountNumber) {
		return customerDAO.getTransactions(accountNumber);
	}

}
