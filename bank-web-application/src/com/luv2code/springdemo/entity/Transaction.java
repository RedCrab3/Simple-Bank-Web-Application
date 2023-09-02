package com.luv2code.springdemo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="transactions")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="transaction_id")
	private long transaction_id;
	
	@Column(name="Sender_account")
	@NotNull(message="is required")
	@Min(1)
	private long senderAccount;
	
	@Column(name="Sender_name")
	@NotNull(message="is required")
	private String senderName;
	
	@Column(name="Recipient_account")
	@NotNull(message="is required")
	@Min(1)
	private long recipientAccount;
	
	@Column(name="Recipient_name")
	@NotNull(message="is required")
	private String recipientName;
	
	@Column(name="timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	@NotNull(message="is required")
	@Min(1)
	@Column(name="Amount")
	private double amount;
	
	public Transaction() {
		
	}

	public Transaction(long senderAccount, long recipientAccount, String senderName, String recipientName, Date timestamp, double amount) {
		this.senderAccount = senderAccount;
		this.recipientAccount = recipientAccount;
		this.senderName = senderName;
		this.recipientName = recipientName;
		this.timestamp = timestamp;
		this.amount = amount;
	}

	public long getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(long transaction_id) {
		this.transaction_id = transaction_id;
	}

	public long getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(long senderAccount) {
		this.senderAccount = senderAccount;
	}

	public long getRecipientAccount() {
		return recipientAccount;
	}

	public void setRecipientAccount(long recipientAccount) {
		this.recipientAccount = recipientAccount;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [transaction_id=" + transaction_id + ", senderAccount=" + senderAccount + ", senderName="
				+ senderName + ", recipientAccount=" + recipientAccount + ", recipientName=" + recipientName
				+ ", timestamp=" + timestamp + ", amount=" + amount + "]";
	}
	
}
