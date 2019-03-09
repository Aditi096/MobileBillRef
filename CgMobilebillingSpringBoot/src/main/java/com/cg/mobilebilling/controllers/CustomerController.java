package com.cg.mobilebilling.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.mobilebilling.beans.Bill;
import com.cg.mobilebilling.beans.Customer;
import com.cg.mobilebilling.beans.Plan;
import com.cg.mobilebilling.beans.PostpaidAccount;
import com.cg.mobilebilling.exceptions.BillDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.BillingServicesDownException;
import com.cg.mobilebilling.exceptions.CustomerDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.InvalidBillMonthException;
import com.cg.mobilebilling.exceptions.PlanDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.PostpaidAccountNotFoundException;
import com.cg.mobilebilling.services.BillingServices;

@Controller
public class CustomerController {
	@Autowired
	BillingServices billingServices;
	@RequestMapping("/customerRegistration")
	public ModelAndView acceptCustomerDetails(@Valid@ ModelAttribute Customer customer,BindingResult result ) throws BillingServicesDownException {
		if(result.hasErrors())
			return new ModelAndView("registerCustomerPage");

		customer=billingServices.acceptCustomerDetails(customer);
		return new ModelAndView("customerRegistrationSuccessPage","customer",customer);
	}
	@RequestMapping("/postpaidAccount")
	public ModelAndView openPostpaidMobileAccount(@RequestParam int customerID,@RequestParam int planID) throws BillingServicesDownException, PlanDetailsNotFoundException, CustomerDetailsNotFoundException {
		long mobileNumber=billingServices.openPostpaidMobileAccount(customerID, planID);
		return new ModelAndView("PostpaidAccountSuccessPage","mobileNumber",mobileNumber);
	}
	@RequestMapping("/getAllPlanDetails")
	public ModelAndView getPlanAllDetails() throws BillingServicesDownException, PlanDetailsNotFoundException, CustomerDetailsNotFoundException {
		List<Plan>plans=billingServices.getPlanAllDetails();
		return new ModelAndView("getAllPlanDetailsPage","plans",plans);
	}
	@RequestMapping("/mobileBillGeneration")
	public ModelAndView generateMonthlyMobileBill(@RequestParam int customerID,@RequestParam long mobileNo,@RequestParam String billMonth,@RequestParam int noOfLocalSMS,
			@RequestParam int noOfStdSMS,@RequestParam int noOfLocalCalls,@RequestParam int noOfStdCalls,@RequestParam int internetDataUsageUnits) throws BillingServicesDownException, PlanDetailsNotFoundException, CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException {
		Bill bill=billingServices.generateMonthlyMobileBill(customerID, mobileNo, billMonth, noOfLocalSMS, noOfStdSMS, noOfLocalCalls, noOfStdCalls, internetDataUsageUnits);
		return new ModelAndView("mobileBillPage","bill",bill);
	}
	@RequestMapping("/getCustomer")
	public ModelAndView getCustomerDetails(@RequestParam int customerID) throws BillingServicesDownException, CustomerDetailsNotFoundException {
		Customer customer=billingServices.getCustomerDetails(customerID);
		return new ModelAndView("customerDetailsPage","customer",customer);
	}
	@RequestMapping("/postpaidAccountDetails")
	public ModelAndView getpostpaidAccountDetails(@RequestParam int customerID,@RequestParam long mobileNo) throws BillingServicesDownException, CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		PostpaidAccount postpaid=billingServices.getPostPaidAccountDetails(customerID, mobileNo);
		return new ModelAndView("postpaidAccountDetailsPage","postpaid",postpaid);
	}
	@RequestMapping("/getAllCustomerDetails")
	public ModelAndView getAllCustomerDetails() throws BillingServicesDownException, PlanDetailsNotFoundException, CustomerDetailsNotFoundException {
		List<Customer>customers=billingServices.getAllCustomerDetails();
		return new ModelAndView("getAllCustomerDetailsPage","customers",customers);
	}
	@RequestMapping("/customerAllPostpaidAccountDetails")
	public ModelAndView getCustomerAllPostpaidAccountDetails(@RequestParam int customerID) throws BillingServicesDownException, PlanDetailsNotFoundException, CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		List<PostpaidAccount>postpaids=billingServices.getCustomerAllPostpaidAccountsDetails(customerID);
		return new ModelAndView("customerAllPostpaidAccountDetailsPage","postpaids",postpaids);
	}
	@RequestMapping("/mobileBillDetails")
	public ModelAndView getmobileBillDetails(@RequestParam int customerID,@RequestParam long mobileNo,@RequestParam String billMonth) throws BillingServicesDownException, PlanDetailsNotFoundException, CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException, BillDetailsNotFoundException {
		Bill bill=billingServices.getMobileBillDetails(customerID, mobileNo, billMonth);
		return new ModelAndView("mobileBillDetailsPage","bill",bill);
	}
	@RequestMapping("/getAllBillDetails")
	public ModelAndView getmobileAllBillDetails(@RequestParam int customerID,@RequestParam long mobileNo) throws BillingServicesDownException, PlanDetailsNotFoundException, CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException, BillDetailsNotFoundException {
		List <Bill>bills=billingServices.getCustomerPostPaidAccountAllBillDetails(customerID, mobileNo);
		return new ModelAndView("mobileAllBillDetailsPage","bills",bills);
	}
	@RequestMapping("/changePlanDetails")
	public ModelAndView changePlanDetails(@RequestParam int customerID,@RequestParam long mobileNo, @RequestParam int planID) throws BillingServicesDownException, PlanDetailsNotFoundException, CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException, BillDetailsNotFoundException {
		boolean status=billingServices.changePlan(customerID, mobileNo, planID);
		if(status)
			return new ModelAndView("planChangeSuccessPage");
		else
			return new ModelAndView("changePlanPage");
	}
	@RequestMapping("/postpaidAccountPlanDetails")
	public ModelAndView getpostpaidAccountPlanDetails(@RequestParam int customerID,@RequestParam long mobileNo) throws BillingServicesDownException, PlanDetailsNotFoundException, CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		Plan plan=billingServices.getCustomerPostPaidAccountPlanDetails(customerID, mobileNo);
		return new ModelAndView("postpaidAccountPlanDetailsPage","plan",plan);
	}
	@RequestMapping("/closeCustomerPostPaidAccountSuccessful")
	public ModelAndView closeCustomerPostPaidAccount(@RequestParam int customerID,@RequestParam long mobileNo) throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {

		boolean status=billingServices.closeCustomerPostPaidAccount(customerID, mobileNo);
		if(status==true)
			return new ModelAndView("closeCustomerPostPaidAccountSuccessfulPage","mobileNo",mobileNo);
		else
			return new ModelAndView("closeCustomerPostPaidAccountPage");
	}

	@RequestMapping("/deleteCustomerSuccessful")
	public ModelAndView deleteCustomer(@RequestParam int customerID) throws PostpaidAccountNotFoundException, BillingServicesDownException, CustomerDetailsNotFoundException {
		boolean status=billingServices.deleteCustomer(customerID);
		if(status==true)
			return new ModelAndView("deleteCustomerSuccessfulPage","customerID",customerID);
		else 
			return new ModelAndView("deleteCustomerPage");
	}



}
