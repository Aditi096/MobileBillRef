package com.cg.mobilebilling.services;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.mobilebilling.beans.Address;
import com.cg.mobilebilling.beans.Bill;
import com.cg.mobilebilling.beans.Customer;
import com.cg.mobilebilling.beans.Plan;
import com.cg.mobilebilling.beans.PostpaidAccount;
import com.cg.mobilebilling.daoservices.BillingServiceDAO;
import com.cg.mobilebilling.daoservices.CustomerDAO;
import com.cg.mobilebilling.daoservices.PlanDetailsDAO;
import com.cg.mobilebilling.daoservices.PostPaidAccountDAO;
import com.cg.mobilebilling.exceptions.BillDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.BillingServicesDownException;
import com.cg.mobilebilling.exceptions.CustomerDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.InvalidBillMonthException;
import com.cg.mobilebilling.exceptions.PlanDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.PostpaidAccountNotFoundException;
@Component("billingServices")
public class BillingServicesImpl implements BillingServices {

	@Autowired
	private BillingServiceDAO billingServiceDAO;
	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private PlanDetailsDAO planDetailsDAO;
	@Autowired
	private PostPaidAccountDAO postPaidAccountDAO;

	@Override
	public List<Plan> getPlanAllDetails() throws BillingServicesDownException {
		return planDetailsDAO.findAll();
	}

	@Override
	public Customer acceptCustomerDetails(String firstName, String lastName, String emailID, String dateOfBirth,
			String billingAddressCity, String billingAddressState, int billingAddressPinCode)
					throws BillingServicesDownException {
		Customer customer = new Customer(firstName, lastName, emailID, dateOfBirth, new Address(billingAddressPinCode, billingAddressCity, billingAddressState), null);
		customer = customerDAO.save(customer);
		return customer;
	}

	@Override
	public long openPostpaidMobileAccount(int customerID, int planID)
			throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException, BillingServicesDownException {
		Customer customer = customerDAO.findById(customerID).orElseThrow(()->
			new CustomerDetailsNotFoundException("No Customer Details Found!"));
		Plan plan = planDetailsDAO.findById(planID).orElseThrow(()->
			new PlanDetailsNotFoundException("No Plan Details Found!"));
		PostpaidAccount postpaidAccount = new PostpaidAccount(plan,null,customer);
		postpaidAccount = postPaidAccountDAO.save(postpaidAccount);
		long mobileNo = postpaidAccount.getMobileNo();
		Map<Long, PostpaidAccount> accounts = new HashMap<>();
		accounts.put(mobileNo,postpaidAccount);
		customer.setPostpaidAccounts(accounts);
		return mobileNo;
	}

	@Override
	public int generateMonthlyMobileBill(int customerID, long mobileNo, String billMonth, int noOfLocalSMS,
			int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls, int internetDataUsageUnits)
					throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
					BillingServicesDownException, PlanDetailsNotFoundException {
		Customer customer = customerDAO.findById(customerID).orElseThrow(()->
			new CustomerDetailsNotFoundException("No Customer Details Found!"));
		PostpaidAccount postpaidAccount = postPaidAccountDAO.findById(mobileNo).orElseThrow(() ->
			new PostpaidAccountNotFoundException("No PostPaid Account Found!"));
		Plan plan = postpaidAccount.getPlan();
		if (plan == null)
			throw new PlanDetailsNotFoundException("No Plan Details Found!");
		int flag=0;
		for (Enum month : Month.values()) {
			if (month.toString().equalsIgnoreCase(billMonth)) {
				flag=1;
				break;
			}
		}
		if (flag==0)	
			throw new InvalidBillMonthException("Enter Valid Month");
		int a;
		float localSMSAmount = (a = (noOfLocalSMS - plan.getFreeLocalSMS())) <= 0 ? 0 : a * plan.getLocalSMSRate();
		float localCallAmount = (a = (noOfLocalCalls - plan.getFreeLocalCalls())) <= 0 ? 0 : a * plan.getLocalCallRate();
		float stdSMSAmount = (a = (noOfStdSMS - plan.getFreeStdSMS())) <= 0 ? 0 : a * plan.getLocalCallRate();
		float stdCallAmount = (a = (noOfStdCalls - plan.getFreeStdCalls())) <= 0 ? 0 : a * plan.getStdCallRate();
		float internetDataUsageAmount = (a = (internetDataUsageUnits - plan.getFreeInternetDataUsageUnits())) <= 0 ? 0 : a * plan.getInternetDataUsageRate();
		float totalAmountWithoutTaxes = localCallAmount + localSMSAmount + stdCallAmount + stdSMSAmount + internetDataUsageAmount + plan.getMonthlyRental();
		float cgst = 0.09f * totalAmountWithoutTaxes;
		float sgst = 0.09f * totalAmountWithoutTaxes;
		float servicesTax = cgst + sgst;
		float vat = 0.05f * totalAmountWithoutTaxes;
		float totalBillAmount = totalAmountWithoutTaxes + servicesTax + vat;
		Bill bill = new Bill(noOfLocalSMS, noOfStdSMS, noOfLocalCalls, noOfStdCalls, internetDataUsageUnits, billMonth, totalBillAmount, localSMSAmount, stdSMSAmount, localCallAmount, stdCallAmount, internetDataUsageAmount, servicesTax, vat, postpaidAccount);
		bill = billingServiceDAO.save(bill);
		return bill.getBillID();
	}

	@Override
	public Customer getCustomerDetails(int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
		return customerDAO.findById(customerID).orElseThrow(()->
		new CustomerDetailsNotFoundException("No Customer Details Found!"));
	}

	@Override
	public List<Customer> getAllCustomerDetails() throws BillingServicesDownException {		
		return customerDAO.findAll();
	}

	@Override
	public PostpaidAccount getPostPaidAccountDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {
		Customer customer = customerDAO.findById(customerID).orElseThrow(()->
		new CustomerDetailsNotFoundException("No Customer Details Found!"));
		PostpaidAccount postpaidAccount = postPaidAccountDAO.findById(mobileNo).orElseThrow(() ->
		new PostpaidAccountNotFoundException("No PostPaid Account Found!"));
		return postpaidAccount;
	}

	@Override
	public List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
		Customer customer = customerDAO.findById(customerID).orElseThrow(()->
			new CustomerDetailsNotFoundException("No Customer Details Found!"));
		return postPaidAccountDAO.getCustomerAllPostpaidAccountsDetails(customerID);
	}

	@Override
	public Bill getMobileBillDetails(int customerID, long mobileNo, String billMonth)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillDetailsNotFoundException, BillingServicesDownException {
		Customer customer = customerDAO.findById(customerID).orElseThrow(()->
		new CustomerDetailsNotFoundException("No Customer Details Found!"));
		PostpaidAccount postpaidAccount = postPaidAccountDAO.findById(mobileNo).orElseThrow(() ->
		new PostpaidAccountNotFoundException("No PostPaid Account Found!"));
		Bill bill = billingServiceDAO.retrieveBill(mobileNo, billMonth);
		return bill;
	}

	@Override
	public List<Bill> getCustomerPostPaidAccountAllBillDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException,
			BillDetailsNotFoundException {
		Customer customer = customerDAO.findById(customerID).orElseThrow(()->
		new CustomerDetailsNotFoundException("No Customer Details Found!"));
		PostpaidAccount postpaidAccount = postPaidAccountDAO.findById(mobileNo).orElseThrow(() ->
		new PostpaidAccountNotFoundException("No PostPaid Account Found!"));
		List<Bill> bills=billingServiceDAO.retrieveAllBills(mobileNo);
		return bills;
	}

	@Override
	public boolean changePlan(int customerID, long mobileNo, int planID) throws CustomerDetailsNotFoundException,
	PostpaidAccountNotFoundException, PlanDetailsNotFoundException, BillingServicesDownException {
		Customer customer = customerDAO.findById(customerID).orElseThrow(()->
		new CustomerDetailsNotFoundException("No Customer Details Found!"));
		PostpaidAccount postpaidAccount = postPaidAccountDAO.findById(mobileNo).orElseThrow(() ->
		new PostpaidAccountNotFoundException("No PostPaid Account Found!"));
		Plan plan = planDetailsDAO.findById(planID).orElseThrow(()->
		new PlanDetailsNotFoundException("No Plan registered to this PostPaid Account!"));
		postpaidAccount.setPlan(plan);
		postPaidAccountDAO.save(postpaidAccount);
		return true;
	}

	@Override
	public boolean closeCustomerPostPaidAccount(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {
		Customer customer = customerDAO.findById(customerID).orElseThrow(()->
		new CustomerDetailsNotFoundException("No Customer Details Found!"));	
		PostpaidAccount postpaidAccount = postPaidAccountDAO.findById(mobileNo).orElseThrow(() ->
		new PostpaidAccountNotFoundException("No PostPaid Account Found!"));
		postPaidAccountDAO.deleteCustomerPostpaidAccount(mobileNo);
		return true;
	}

	@Override
	public boolean deleteCustomer(int customerID)
			throws BillingServicesDownException, CustomerDetailsNotFoundException {
		customerDAO.deleteById(customerID);
		return true;
	}

	@Override
	public Plan getCustomerPostPaidAccountPlanDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException,
			PlanDetailsNotFoundException {
		Customer customer = customerDAO.findById(customerID).orElseThrow(()->
		new CustomerDetailsNotFoundException("No Customer Details Found!"));
		PostpaidAccount postpaidAccount = postPaidAccountDAO.findById(mobileNo).orElseThrow(() ->
		new PostpaidAccountNotFoundException("No PostPaid Account Found!"));
		return postpaidAccount.getPlan();
	}

	@Override
	public Plan acceptPlanDetails(Plan plan) {
		Plan plan1=planDetailsDAO.save(plan);
		return plan1;
	}
}