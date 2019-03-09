package com.cg.mobilebilling.beans;
import java.util.Date;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Customer {
	@Id
	@SequenceGenerator(name="customerId_seq" ,initialValue=111,allocationSize=1,sequenceName="customerId_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="customerId_seq")
	private int customerID;
	@Pattern(regexp = "^[a-zA-Z\\s]+$",message="{Valid.firstName}")
	private String firstName;
	@Pattern(regexp = "^[a-zA-Z\\s]+$",message="{Valid.lastName}")
	private String lastName; 
	@NotEmpty(message="{email.NotEmpty}")
	@Email(message="{validEmail.email}")
	private String emailID;
	@Pattern(regexp = "(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[012]).(19[0-9][0-9]|200[0-9]|201[0-5])",message="{Valid.date}")
	private String dateOfBirth;
	@Embedded
	private Address billingAddress;
	@OneToMany(mappedBy="customer")
	private Map<Long, PostpaidAccount> postpaidAccounts;
	public Customer() {}
	public Customer(String firstName, String lastName, String emailID, String dateOfBirth, Address billingAddress) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.dateOfBirth = dateOfBirth;
		this.billingAddress = billingAddress;
	}
	public Customer(int customerID,String firstName, String lastName, String emailID, String dateOfBirth, Address billingAddress) {
		super();
		this.customerID=customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.dateOfBirth = dateOfBirth;
		this.billingAddress = billingAddress;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
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
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public  String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Address getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	public Map<Long, PostpaidAccount> getPostpaidAccounts() {
		return postpaidAccounts;
	}
	public void setPostpaidAccounts(Map<Long, PostpaidAccount> postpaidAccounts) {
		this.postpaidAccounts = postpaidAccounts;
	}
	@Override
	public String toString() {
		return "customerID=" + customerID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailID=" + emailID + ", dateOfBirth=" + dateOfBirth + ", billingAddress=" + billingAddress;
	}

}