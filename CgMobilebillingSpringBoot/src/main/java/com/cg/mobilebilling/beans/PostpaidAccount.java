package com.cg.mobilebilling.beans;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
@Entity
public class PostpaidAccount {
	@Id
	@SequenceGenerator(name="mobileNo_seq" ,initialValue=791119999,allocationSize=1,sequenceName="mobileNo_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="mobileNo_seq")
	private long mobileNo;
	@ManyToOne
	private Plan plan;
	@OneToMany(mappedBy="postpaidAccount")
	private  Map<Integer, Bill> bills;
	@ManyToOne
	private Customer customer;
	public PostpaidAccount() {}
	public PostpaidAccount(long mobileNo, Plan plan, Map<Integer, Bill> bills) {
		super();
		this.mobileNo = mobileNo;
		this.plan = plan;
		this.bills = bills;
	}

	public PostpaidAccount(long mobileNo, Plan plan, Customer customer) {
		super();
		this.mobileNo = mobileNo;
		this.plan = plan;
		this.customer = customer;
	}
	public PostpaidAccount(Plan plan, Customer customer) {
		super();
		this.plan = plan;
		this.customer = customer;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public Map<Integer, Bill> getBills() {
		return bills;
	}
	public void setBills(Map<Integer, Bill> bills) {
		this.bills = bills;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "PostpaidAccount [mobileNo=" + mobileNo + ", plan=" + plan + "]";
	}

}