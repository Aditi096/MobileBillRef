package com.cg.mobilebilling.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.mobilebilling.beans.Address;
import com.cg.mobilebilling.beans.Bill;
import com.cg.mobilebilling.beans.Customer;
import com.cg.mobilebilling.beans.Plan;
import com.cg.mobilebilling.beans.PostpaidAccount;
import com.cg.mobilebilling.daoservices.BillingDAOServices;
import com.cg.mobilebilling.daoservices.CustomerDAOServices;
import com.cg.mobilebilling.daoservices.PlanDAOServices;
import com.cg.mobilebilling.daoservices.PostpaidDAOServices;
import com.cg.mobilebilling.exceptions.BillDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.BillingServicesDownException;
import com.cg.mobilebilling.exceptions.CustomerDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.InvalidBillMonthException;
import com.cg.mobilebilling.exceptions.PlanDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.PostpaidAccountNotFoundException;
@Component("billingServices")
public class BillingServicesImpl implements BillingServices {
	@Autowired
	BillingDAOServices billingDAOServices;
	@Autowired
	CustomerDAOServices customerDAOServices;
	@Autowired
	PlanDAOServices planDAOServices;
	@Autowired
	PostpaidDAOServices postpaidDAOServices;
	private static int flag=0;
	@Override
	public List<Plan> getPlanAllDetails() throws BillingServicesDownException {
		List<Plan>plans=planDAOServices.findAll();
		if(plans==null) throw new BillingServicesDownException("No plans Found!!!!");
		return plans;
	}
	@Override
	public Customer acceptCustomerDetails(Customer customer) throws BillingServicesDownException {
		customerDAOServices.save(customer);
		if(flag==0) {
			addPlans();
			flag++;
		}
		return customer;
	}

	@Override
	public long openPostpaidMobileAccount(int customerID, int planID)
			throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException, BillingServicesDownException {

		Customer customer=customerDAOServices.findById(customerID).orElseThrow (()->new CustomerDetailsNotFoundException("Sorry Customer details not found!!!"));
		Plan plan=planDAOServices.findById(planID).orElseThrow(()->new PlanDetailsNotFoundException("Sorry requested plan is unavailable!!!"));
		PostpaidAccount postpaid=new PostpaidAccount(plan, customer);
		postpaidDAOServices.save(postpaid);
		return postpaid.getMobileNo();
	}

	@Override
	public Bill generateMonthlyMobileBill(int customerID, long mobileNo, String billMonth, int noOfLocalSMS,
			int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls, int internetDataUsageUnits)
					throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
					BillingServicesDownException, PlanDetailsNotFoundException {
		Customer customer=customerDAOServices.findById(customerID).orElseThrow(()->new CustomerDetailsNotFoundException("Sorry Customer details not found!!!"));
		PostpaidAccount postpaidAccount=postpaidDAOServices.getPostPaidAccountDetails(customer, mobileNo);
		if(postpaidAccount==null) throw new PostpaidAccountNotFoundException("Requested PostPaid Account does not exist!!!");
		Bill bill=billingDAOServices.getMobileBillDetails(postpaidAccount, billMonth);
		if(bill!=null) throw new BillingServicesDownException("Bill already exist for this month!!!");
		bill=new Bill(noOfLocalSMS, noOfStdSMS, noOfLocalCalls, noOfStdCalls, internetDataUsageUnits, postpaidAccount, billMonth);
		if(noOfLocalSMS>postpaidAccount.getPlan().getFreeLocalSMS())
			bill.setLocalSMSAmount((noOfLocalSMS-postpaidAccount.getPlan().getFreeLocalSMS())*postpaidAccount.getPlan().getLocalSMSRate());
		else
			bill.setLocalSMSAmount(0);
		if(noOfStdSMS>postpaidAccount.getPlan().getFreeStdSMS())
			bill.setStdSMSAmount((noOfStdSMS-postpaidAccount.getPlan().getFreeStdSMS())*postpaidAccount.getPlan().getStdSMSRate());
		else
			bill.setStdSMSAmount(0);
		if(noOfLocalCalls>postpaidAccount.getPlan().getFreeLocalCalls())
			bill.setLocalCallAmount((noOfLocalCalls-postpaidAccount.getPlan().getFreeLocalCalls())*postpaidAccount.getPlan().getLocalCallRate());
		else
			bill.setLocalCallAmount(0);
		if(noOfStdCalls>postpaidAccount.getPlan().getFreeStdCalls())
			bill.setStdCallAmount((noOfStdCalls-postpaidAccount.getPlan().getFreeStdCalls())*postpaidAccount.getPlan().getStdCallRate());
		else
			bill.setStdCallAmount(0);
		if(internetDataUsageUnits>postpaidAccount.getPlan().getFreeInternetDataUsageUnits())
			bill.setInternetDataUsageAmount((internetDataUsageUnits-postpaidAccount.getPlan().getFreeInternetDataUsageUnits())*postpaidAccount.getPlan().getInternetDataUsageRate());
		else
			bill.setInternetDataUsageAmount(0);
		float intialamount=bill.getLocalSMSAmount()+bill.getLocalCallAmount()+bill.getStdSMSAmount()+bill.getStdCallAmount()+bill.getInternetDataUsageAmount()+postpaidAccount.getPlan().getMonthlyRental();
		bill.setCgst((intialamount*5)/100);
		bill.setSgst((intialamount*5)/100);
		bill.setTotalBillAmount(intialamount+bill.getCgst()+bill.getSgst());
		billingDAOServices.save(bill);
		return bill;

	}

	@Override
	public Customer getCustomerDetails(int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {

		return customerDAOServices.findById(customerID).orElseThrow (()->new CustomerDetailsNotFoundException("Sorry Customer details not found!!!"));
	}

	@Override
	public List<Customer> getAllCustomerDetails() throws BillingServicesDownException {
		List<Customer>list=customerDAOServices.findAll();
		if(list==null) throw new BillingServicesDownException("No customer Found or Services are down Please try again later!!!");
		return list;
	}

	@Override
	public PostpaidAccount getPostPaidAccountDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {
		Customer customer=customerDAOServices.findById(customerID).orElseThrow (()->new CustomerDetailsNotFoundException("Sorry Customer details not found for"+customerID));
		PostpaidAccount postpaid=postpaidDAOServices.getPostPaidAccountDetails(customer, mobileNo);
		if(postpaid==null) throw new PostpaidAccountNotFoundException("Postpaid Account not found for "+mobileNo);
		return postpaid;
	}

	@Override
	public List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException, PostpaidAccountNotFoundException {
		Customer customer=customerDAOServices.findById(customerID).orElseThrow (()->new CustomerDetailsNotFoundException("Sorry Customer details not found!!!"));
		List<PostpaidAccount> postpaid=new ArrayList<>();
		postpaid=postpaidDAOServices.getCustomerAllPostpaidAccountsDetails(customer);
		if(postpaid==null) throw new PostpaidAccountNotFoundException("No postpaid Accounts found!!!!");
		return postpaid;
	}

	@Override
	public Bill getMobileBillDetails(int customerID, long mobileNo, String billMonth)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillDetailsNotFoundException, BillingServicesDownException {
		Customer customer=customerDAOServices.findById(customerID).orElseThrow (()->new CustomerDetailsNotFoundException("Sorry Customer details not found!!!"));
		PostpaidAccount postpaidAccount=postpaidDAOServices.getPostPaidAccountDetails(customer, mobileNo);
		if(postpaidAccount==null) throw new PostpaidAccountNotFoundException("Requested PostPaid Account does not exist!!!");
		Bill bill=billingDAOServices.getMobileBillDetails(postpaidAccount ,billMonth);
		if(bill==null) throw new InvalidBillMonthException("No Bill Details found for this month!!! ");
		return bill;
	}

	@Override
	public List<Bill> getCustomerPostPaidAccountAllBillDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException,
			BillDetailsNotFoundException {
		Customer customer=customerDAOServices.findById(customerID).orElseThrow (()->new CustomerDetailsNotFoundException("Sorry Customer details not found!!!"));
		PostpaidAccount postpaidAccount=postpaidDAOServices.getPostPaidAccountDetails(customer, mobileNo);
		if(postpaidAccount==null) throw new PostpaidAccountNotFoundException("Requested PostPaid Account does not exist!!!");
		List<Bill> bill=new ArrayList<>();
		bill=postpaidDAOServices.getCustomerPostPaidAccountAllBillDetails(customer, mobileNo);
		if(bill==null) throw new BillDetailsNotFoundException("Sorry Bill Details not found!!!");
		return bill;
	}

	@Override
	public boolean changePlan(int customerID, long mobileNo, int planID) throws CustomerDetailsNotFoundException,
	PostpaidAccountNotFoundException, PlanDetailsNotFoundException, BillingServicesDownException {
		Customer customer=customerDAOServices.findById(customerID).orElseThrow (()->new CustomerDetailsNotFoundException("Sorry Customer details not found!!!"));
		PostpaidAccount postpaidAccount=postpaidDAOServices.getPostPaidAccountDetails(customer, mobileNo);
		if(postpaidAccount==null) throw new PostpaidAccountNotFoundException("Requested PostPaid Account does not exist!!!");
		Plan plan=planDAOServices.findById(planID).orElseThrow(()->new PlanDetailsNotFoundException("Sorry requested plan is unavailable!!!"));
		postpaidAccount=new PostpaidAccount(mobileNo, plan, customer);
		postpaidDAOServices.save(postpaidAccount);
		return true;
	}
	@Override
	public boolean closeCustomerPostPaidAccount(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {
		Customer customer=customerDAOServices.findById(customerID).orElseThrow(()->
		new CustomerDetailsNotFoundException("Customer Details Not Found With CustomerID="+customerID));
		PostpaidAccount postpaidAccount=postpaidDAOServices.getPostPaidAccountDetails(customer, mobileNo);
		if(postpaidAccount==null) throw new PostpaidAccountNotFoundException("Postpaid Account not found for "+mobileNo);
		billingDAOServices.deleteAllBills(postpaidAccount);
		postpaidDAOServices.closeCustomerPostPaidAccount(customer, mobileNo);
		return true;
	}

	@Override
	public boolean deleteCustomer(int customerID)
			throws BillingServicesDownException, CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		ArrayList<PostpaidAccount>postpaidAccounts=(ArrayList<PostpaidAccount>) getCustomerAllPostpaidAccountsDetails(customerID);
		for(PostpaidAccount postpaidAccount:postpaidAccounts) {
			closeCustomerPostPaidAccount(customerID, postpaidAccount.getMobileNo());			
		}
		customerDAOServices.deleteById(customerID);
		return true;
	}

	@Override
	public Plan getCustomerPostPaidAccountPlanDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException,
			PlanDetailsNotFoundException {
		Customer customer=customerDAOServices.findById(customerID).orElseThrow (()->new CustomerDetailsNotFoundException("Sorry Customer details not found!!!"));
		PostpaidAccount postpaidAccount=postpaidDAOServices.getPostPaidAccountDetails(customer, mobileNo);
		if(postpaidAccount==null) throw new PostpaidAccountNotFoundException("Requested PostPaid Account does not exist!!!");
		Plan plan=postpaidAccount.getPlan();
		return plan;
	}
	@Override
	public void addPlans() throws BillingServicesDownException {
		Plan plan1=new Plan(1,50,1000,500,1000,100,500,0.5f,1.0f,0.5f,1.0f,0.7f,"State","Golden");
		planDAOServices.save(plan1);
		Plan plan2=new Plan(2,100,500,500,500,100,1000,.02f,0.5f,1.0f,0.5f,0.5f,"State","Premium");
		planDAOServices.save(plan2);
		Plan plan3=new Plan(3,500,500,200,500,500,1000,1.0f,0.5f,1.0f,0.5f,0.5f,"India","UltraPremium");
		planDAOServices.save(plan3);

	}
}