package com.cg.mobilebilling.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Bill {
	@Id
	@SequenceGenerator(name="billId_seq" ,initialValue=11,allocationSize=1,sequenceName="billId_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="billId_seq")
	private int billID;
	private int noOfLocalSMS, noOfStdSMS, noOfLocalCalls, noOfStdCalls, internetDataUsageUnits;
	@ManyToOne
	private PostpaidAccount postpaidAccount;
	private String billMonth;
	private float totalBillAmount, localSMSAmount, stdSMSAmount, localCallAmount, stdCallAmount, internetDataUsageAmount, cgst,sgst;
	public Bill() {}
	public Bill(int noOfLocalSMS, int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls, int internetDataUsageUnits,
			String billMonth, float totalBillAmount, float localSMSAmount, float stdSMSAmount, float localCallAmount,
			float stdCallAmount, float internetDataUsageAmount, float cgst, float sgst) {
		super();
		this.noOfLocalSMS = noOfLocalSMS;
		this.noOfStdSMS = noOfStdSMS;
		this.noOfLocalCalls = noOfLocalCalls;
		this.noOfStdCalls = noOfStdCalls;
		this.internetDataUsageUnits = internetDataUsageUnits;
		this.billMonth = billMonth;
		this.totalBillAmount = totalBillAmount;
		this.localSMSAmount = localSMSAmount;
		this.stdSMSAmount = stdSMSAmount;
		this.localCallAmount = localCallAmount;
		this.stdCallAmount = stdCallAmount;
		this.internetDataUsageAmount = internetDataUsageAmount;
		this.cgst = cgst;
		this.sgst = sgst;
	}


	public Bill(int billID, int noOfLocalSMS, int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls,
			int internetDataUsageUnits, PostpaidAccount postpaidAccount, String billMonth, float totalBillAmount,
			float localSMSAmount, float stdSMSAmount, float localCallAmount, float stdCallAmount,
			float internetDataUsageAmount, float cgst, float sgst) {
		super();
		this.billID = billID;
		this.noOfLocalSMS = noOfLocalSMS;
		this.noOfStdSMS = noOfStdSMS;
		this.noOfLocalCalls = noOfLocalCalls;
		this.noOfStdCalls = noOfStdCalls;
		this.internetDataUsageUnits = internetDataUsageUnits;
		this.postpaidAccount = postpaidAccount;
		this.billMonth = billMonth;
		this.totalBillAmount = totalBillAmount;
		this.localSMSAmount = localSMSAmount;
		this.stdSMSAmount = stdSMSAmount;
		this.localCallAmount = localCallAmount;
		this.stdCallAmount = stdCallAmount;
		this.internetDataUsageAmount = internetDataUsageAmount;
		this.cgst = cgst;
		this.sgst = sgst;
	}
	public Bill(int noOfLocalSMS, int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls, int internetDataUsageUnits,
			PostpaidAccount postpaidAccount, String billMonth) {
		super();
		this.noOfLocalSMS = noOfLocalSMS;
		this.noOfStdSMS = noOfStdSMS;
		this.noOfLocalCalls = noOfLocalCalls;
		this.noOfStdCalls = noOfStdCalls;
		this.internetDataUsageUnits = internetDataUsageUnits;
		this.postpaidAccount = postpaidAccount;
		this.billMonth = billMonth;
	}
	public int getBillID() {
		return billID;
	}
	public void setBillID(int billID) {
		this.billID = billID;
	}
	public int getNoOfLocalSMS() {
		return noOfLocalSMS;
	}
	public void setNoOfLocalSMS(int noOfLocalSMS) {
		this.noOfLocalSMS = noOfLocalSMS;
	}
	public int getNoOfStdSMS() {
		return noOfStdSMS;
	}
	public void setNoOfStdSMS(int noOfStdSMS) {
		this.noOfStdSMS = noOfStdSMS;
	}
	public int getNoOfLocalCalls() {
		return noOfLocalCalls;
	}
	public void setNoOfLocalCalls(int noOfLocalCalls) {
		this.noOfLocalCalls = noOfLocalCalls;
	}
	public int getNoOfStdCalls() {
		return noOfStdCalls;
	}
	public void setNoOfStdCalls(int noOfStdCalls) {
		this.noOfStdCalls = noOfStdCalls;
	}
	public int getInternetDataUsageUnits() {
		return internetDataUsageUnits;
	}
	public void setInternetDataUsageUnits(int internetDataUsageUnits) {
		this.internetDataUsageUnits = internetDataUsageUnits;
	}
	public String getBillMonth() {
		return billMonth;
	}
	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}
	public float getTotalBillAmount() {
		return totalBillAmount;
	}
	public void setTotalBillAmount(float totalBillAmount) {
		this.totalBillAmount = totalBillAmount;
	}
	public float getLocalSMSAmount() {
		return localSMSAmount;
	}
	public void setLocalSMSAmount(float localSMSAmount) {
		this.localSMSAmount = localSMSAmount;
	}
	public float getStdSMSAmount() {
		return stdSMSAmount;
	}
	public void setStdSMSAmount(float stdSMSAmount) {
		this.stdSMSAmount = stdSMSAmount;
	}
	public float getLocalCallAmount() {
		return localCallAmount;
	}
	public void setLocalCallAmount(float localCallAmount) {
		this.localCallAmount = localCallAmount;
	}
	public float getStdCallAmount() {
		return stdCallAmount;
	}
	public void setStdCallAmount(float stdCallAmount) {
		this.stdCallAmount = stdCallAmount;
	}
	public float getInternetDataUsageAmount() {
		return internetDataUsageAmount;
	}
	public void setInternetDataUsageAmount(float internetDataUsageAmount) {
		this.internetDataUsageAmount = internetDataUsageAmount;
	}
	public float getCgst() {
		return cgst;
	}
	public void setCgst(float cgst) {
		this.cgst = cgst;
	}
	public float getSgst() {
		return sgst;
	}
	public void setSgst(float sgst) {
		this.sgst = sgst;
	}

	public PostpaidAccount getPostpaidAccount() {
		return postpaidAccount;
	}
	public void setPostpaidAccount(PostpaidAccount postpaidAccount) {
		this.postpaidAccount = postpaidAccount;
	}
	@Override
	public String toString() {
		return "Bill [billID=" + billID + ", noOfLocalSMS=" + noOfLocalSMS + ", noOfStdSMS=" + noOfStdSMS
				+ ", noOfLocalCalls=" + noOfLocalCalls + ", noOfStdCalls=" + noOfStdCalls + ", internetDataUsageUnits="
				+ internetDataUsageUnits + ", billMonth=" + billMonth + ", totalBillAmount=" + totalBillAmount
				+ ", localSMSAmount=" + localSMSAmount + ", stdSMSAmount=" + stdSMSAmount + ", localCallAmount="
				+ localCallAmount + ", stdCallAmount=" + stdCallAmount + ", internetDataUsageAmount="
				+ internetDataUsageAmount + ", cgst=" + cgst + ", sgst=" + sgst + "]";
	}



}