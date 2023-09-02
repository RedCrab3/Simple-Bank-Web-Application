package com.luv2code.springdemo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.entity.Transaction;
import com.luv2code.springdemo.service.CustomerService;
import com.luv2code.springdemo.utils.SortUtils;

@Controller
@RequestMapping("/bank")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	public static String makeUrl(HttpServletRequest request)
	{
	    return request.getRequestURL().toString() + "?" + request.getQueryString();
	}
	
	@GetMapping("")
	public String MainMenu()
	{
		return "main-menu";
	}
	
	@GetMapping("/list")
	public String listCustomers(Model m) {
		List<Customer> customers=customerService.getCustomers();
		m.addAttribute("customers",customers);
		m.addAttribute("flag",0);
		return "list-customers";	
	}
	
	@PostMapping("/getPin")//only get requests accepted and responded to
	public String getPin(@RequestParam("customerId") int id, Model m) {
		m.addAttribute("customerId",id);
		return "pin-form";
	}
	
	@PostMapping("/detail")
	public String getDetails(@RequestParam("customerId") int id, @RequestParam("pin") String pin, Model m) {
		Customer c=customerService.getCustomer(id);
		if (customerService.SHA512(pin).equals(c.getPin()))
		{
			m.addAttribute("customer",c);
			m.addAttribute("flag","nien_Problem");
			return "customer-detail";
		}
		else
		{   
			List<Customer> customers=customerService.getCustomers();
			m.addAttribute("customers",customers);
			m.addAttribute("flag",1);
			return "list-customers";	
		}
	}
	
	@PostMapping("/transaction")
	public String transaction(@RequestParam("Sender") int id, Model m) {
		List<Customer> customers=customerService.getCustomers(id);
		m.addAttribute("customerId",id);
		m.addAttribute("recipientCustomers",customers);
		return "transaction-form";
	}
	
	@PostMapping("/OTP")
	public String getOTP(@RequestParam("Sender") int senderId, @RequestParam("Recipient") int recieverId, @RequestParam("Amount") int amount, Model m) {
		Customer sender=customerService.getCustomer(senderId);
		m.addAttribute("Sender",senderId);
		m.addAttribute("Recipient",recieverId);
		m.addAttribute("Amount",amount);		
		String otp = customerService.sendOTP(sender, amount);
		customerService.setOtp(sender, otp);
		System.out.println(sender.getOtp());
		return "otp-form";
	}
	
	@PostMapping("/verify")
	public String verify(@RequestParam("Sender") int senderId, @RequestParam("Recipient") int recieverId, @RequestParam("Amount") int amount, @RequestParam("otp") int otp, Model m) {
		Customer sender=customerService.getCustomer(senderId);
		if(sender.getOtp().equals(String.valueOf(otp)))
		{
			if(sender.getBalance()<amount)
			{
				m.addAttribute("customer",sender);
				m.addAttribute("flag","insufficient_Balance");
				return "customer-detail";
			}
			else 
			{
				Customer recipient=customerService.getCustomer(recieverId);
				customerService.performTransaction(sender,recipient,amount);
				m.addAttribute("customerId",senderId);
				return "transaction-success";
			}
		}
		else
			m.addAttribute("customer",sender);
			m.addAttribute("flag","invalid_OTP");
			return "customer-detail";	
	}	
	
	@GetMapping("/securedCustDet")
	public String backToCustomerDetailsSecuredChannel(@RequestParam("customerId") int customerId, Model m) {
		Customer customer = customerService.getCustomer(customerId);
		m.addAttribute("customer",customer);
		m.addAttribute("flag","nien_Problem");
		return "customer-detail";
	}
	
	@PostMapping("/transactionList")
	public String transactionList(@RequestParam("customerId") int customerId, Model m)
	{
		Customer customer = customerService.getCustomer(customerId);
		List<Transaction> transactions = customerService.getTransactions(customer.getAccountNumber());
		m.addAttribute("transactions", transactions);
		m.addAttribute("customer", customer);
		return "transactions-list";
	}
	
/*
	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer c, BindingResult br) {
		if(br.hasErrors())
			return "customer-form";
		else {
			customerService.saveCustomer(c);
	//		If we only used "/customer/list"
	//		Then if we added a new customer ... then hit the reload button on our browser, then the old customer will be added    again (eeek). If the user continues to hit the reload button, then even more submissions. This happens because the browser can cache the previous request. This is bad since they are duplicate submissions.
	//		We avoid this issue by using "redirect:/customer/list". In this case, the browser doesn't cache the request.
	//		This is an implementation of an industry pattern: Post/Redirect/Get. You can read more about it here:
			return "redirect:/customer/list";
		}
	}
	
	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model m) {
		// get customer from DB using id and add it as attribute to m for pre poplulating the form and send it to form
		Customer c=customerService.getCustomer(id);
		m.addAttribute("customer",c);
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int id) {
		customerService.deleteCustomer(id);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam(value="searchName", required=false) String searchName, Model m) {
		if((searchName==null)||(searchName==""))
			return "redirect:/customer/list";
		List<Customer> customers=customerService.searchCustomers(searchName);
		m.addAttribute("customers",customers);
		return "list-customers";
	}*/
}
