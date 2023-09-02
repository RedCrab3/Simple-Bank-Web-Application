package com.luv2code.springdemo.DAO;


import java.util.Date;
import java.util.List;
import java.util.Random;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.entity.Transaction;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Repository
public class CustomerDAOImple implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		Session session=sessionFactory.getCurrentSession();
		return session.createQuery("from Customer ",Customer.class).getResultList();//return list of 
		//all customers 
		//ordered by first name
	}

//	@Override
//	public void saveCustomer(Customer c) {
//		Session session=sessionFactory.getCurrentSession();
//		//session.save(c); for only saving not updating
//		session.saveOrUpdate(c);
//		//save or update depending on c.getId() empty/null or has int value
//	}

	@Override
	public Customer getCustomer(int id) {
		Session session=sessionFactory.getCurrentSession();
		return session.get(Customer.class,id);
	}

//	@Override
//	public void deleteCustomer(int id) {
//		Session session=sessionFactory.getCurrentSession();
//		session.delete(session.get(Customer.class,id));
//	}

//	@Override
//	public List<Customer> searchCustomers(String searchName) {
//		Session session=sessionFactory.getCurrentSession();
//		return session.createQuery("from Customer where lower(firstName) like '%"+searchName.toLowerCase()+
//				"%' or lower(lastName) like '%"+searchName.toLowerCase()+"%'",Customer.class).getResultList();
//	}

	@Override
	public String SHA512(String pin) {
		try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(pin.getBytes());
            BigInteger n = new BigInteger(1, messageDigest);
            String hashtext = n.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
  
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public List<Customer> getCustomers(int id) {
		Session session=sessionFactory.getCurrentSession();
		return session.createQuery("from Customer c where c.id not like :id",Customer.class).setParameter("id", id).getResultList();
	}

	@Override
	public String sendOTP(Customer c, int amount) {
		String numbers = "0123456789";
		Random rndm_method = new Random();  
        String otp="";  
        for (int i = 0; i < 5; i++)
        otp+=numbers.charAt(rndm_method.nextInt(numbers.length()));
		final String ACCOUNT_SID = "ACce3b86406d4182f24d29bb7ddc9368cd";
		final String AUTH_TOKEN = "312e677c95c6b920632280c1d62d4e2c";
	    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	    Message message = Message.creator(new PhoneNumber("+91"+c.getNumber()),
	        new PhoneNumber("+15342482894"), 
	        "Your One Time Password for transaction of amount "+(char)8377+" "+amount+" is "+otp).create();
	    System.out.println(message.getSid());
	    return otp;
		
	}

	@Override
	public void setOtp(Customer sender, String otp) {
		Session session=sessionFactory.getCurrentSession();
		sender.setOtp(otp);
		session.saveOrUpdate(sender);
	}

	@Override
	public int deductSender(Customer sender, int amount) {
		try {
		Session session=sessionFactory.getCurrentSession();
		sender.setBalance(sender.getBalance()-amount);
		session.saveOrUpdate(sender);
		return 1;
		}
		catch(Exception e) {
			return 0;
		}
	}

	@Override
	public void sendDebitMsg(Customer sender, int amount) {
		final String ACCOUNT_SID = "ACce3b86406d4182f24d29bb7ddc9368cd";
		final String AUTH_TOKEN = "312e677c95c6b920632280c1d62d4e2c";
	    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	    Message message = Message.creator(new PhoneNumber("+91"+sender.getNumber()),
	        new PhoneNumber("+15342482894"), 
	        ""+(char)8377+" "+amount+" has been debited from your account. Your current balance is "+(char)8377+" "+sender.getBalance()).create();
	    System.out.println(message.getSid());
	}

	@Override
	public int creditRecipient(Customer recipient, int amount) {
		try {
		Session session=sessionFactory.getCurrentSession();
		recipient.setBalance(recipient.getBalance()+amount);
		session.saveOrUpdate(recipient);
		return 1;
		}
		catch(Exception e) {
			return 0;
		}
	}

	@Override
	public void sendCreditMsg(Customer recipient, int amount) {
		final String ACCOUNT_SID = "ACce3b86406d4182f24d29bb7ddc9368cd";
		final String AUTH_TOKEN = "312e677c95c6b920632280c1d62d4e2c";
	    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	    Message message = Message.creator(new PhoneNumber("+91"+recipient.getNumber()),
	        new PhoneNumber("+15342482894"), 
	        ""+(char)8377+" "+amount+" has been credited to your account. Your current balance is "+(char)8377+" "+recipient.getBalance()).create();
	    System.out.println(message.getSid());
	}

	@Override
	public void performTransaction(Customer sender, Customer recipient, int amount) {
		Session session=sessionFactory.getCurrentSession();
		Date date = new Date();
		System.out.println("-------------------------------"+date+"-----------------------");
		Transaction transaction = new Transaction(sender.getAccountNumber(), recipient.getAccountNumber(), sender.getFirstName()+" "+sender.getLastName(), recipient.getFirstName()+" "+recipient.getLastName(), date, amount);
		session.saveOrUpdate(transaction);
		int flag=0;
		flag = deductSender(sender, amount);
		if(flag==1) {
			sendDebitMsg(sender,amount);
			flag=0;
			flag = creditRecipient(recipient, amount);
			if(flag==1) {
				sendCreditMsg(recipient,amount);
				flag=0;
			}
		}
	}

	@Override
	public List<Transaction> getTransactions(long accountNumber) {
		Session session=sessionFactory.getCurrentSession();
		return session.createQuery("from Transaction t where t.senderAccount like :accountNumber or t.recipientAccount like :accountNumber",Transaction.class).setParameter("accountNumber", accountNumber).getResultList();
	}
}
