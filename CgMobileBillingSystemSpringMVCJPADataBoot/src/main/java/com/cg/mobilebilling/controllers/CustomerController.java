package com.cg.mobilebilling.controllers;


import java.util.ArrayList;
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
	private BillingServices billingServices;

	@RequestMapping("/registerCustomer")
	public ModelAndView registerCustomerAction(@Valid @ModelAttribute Customer customer,
			BindingResult result){
		try {
			if(result.hasErrors())
				return new ModelAndView("registrationPage");
			ModelAndView model = new ModelAndView("registrationPage");
			customer = billingServices.acceptCustomerDetails(customer.getFirstName(), customer.getLastName(), customer.getEmailID(), customer.getDateOfBirth(), customer.getBillingAddress().getCity(),customer.getBillingAddress().getState(), customer.getBillingAddress().getPinCode());
			model.addObject("message", "Customer details added successfully. Your customer Id is: "+customer.getCustomerID());
			return model;
		} catch (BillingServicesDownException e) {
			return new ModelAndView("registrationPage", "message", e.getMessage());
		}

	}

	@RequestMapping("planAllDetails")
	public ModelAndView planAllDetailsAction(@Valid @ModelAttribute Plan plan,
			BindingResult result) {
		try {
			ModelAndView model=new ModelAndView("getPlanAllDetailsPage");
			List<Plan> plans=billingServices.getPlanAllDetails();
			model.addObject("plans", plans);
			return model;
		} catch (BillingServicesDownException e) {
			return new ModelAndView("getPlanAllDetailsPage", "message", e.getMessage());
		}
	}

	@RequestMapping("savePlan")
	public ModelAndView planDetailsAction(@Valid @ModelAttribute Plan plan,
			BindingResult result) throws BillingServicesDownException {
		ModelAndView model = new ModelAndView("addNewPlansPage");
		plan=billingServices.acceptPlanDetails(plan);
		model.addObject("message", "Plan details added successfully.  Plan Id is: "+plan.getPlanID());
		return model;
	}

	@RequestMapping("openPostpaidAccount")
	public ModelAndView openPostpaidAccountAction( @Valid @RequestParam("planID") int planID,@ModelAttribute Customer customer,
			BindingResult result)  {
		try {

			long mobileNo=billingServices.openPostpaidMobileAccount(customer.getCustomerID(), planID);
			ModelAndView model = new ModelAndView("openPostpaidMobileAccountPage","mobileNo",mobileNo);
			model.addObject("message", "Your postpaid account for mobileNo "+mobileNo+" is successfully opened");
			return model;
		} catch (PlanDetailsNotFoundException | CustomerDetailsNotFoundException | BillingServicesDownException e) {
			return new ModelAndView("openPostpaidMobileAccountPage", "message", e.getMessage());
		}
	}

	@RequestMapping("customerDetails")
	public ModelAndView customerDetailsAction(@Valid@ModelAttribute Customer customer,
			BindingResult result) {
		try {
			ModelAndView model = new ModelAndView("getCustomerDetailsPage");
			customer=billingServices.getCustomerDetails(customer.getCustomerID());
			model.addObject("flag",1);
			model.addObject("customer", customer);
			return model;
		} catch (CustomerDetailsNotFoundException | BillingServicesDownException e) {
			return new ModelAndView("getCustomerDetailsPage", "message", e.getMessage());
		} 
	}

	@RequestMapping("/allCustomerDetails")
	public ModelAndView allCustomerDetailsAction(@Valid @ModelAttribute Customer customer,
			BindingResult result) {
		try {
			ModelAndView model = new ModelAndView("getAllCustomerDetailsPage");
			List<Customer> customers;
			customers = billingServices.getAllCustomerDetails();
			model.addObject("flag",1);
			model.addObject("customers", customers);
			return model;
		} catch (BillingServicesDownException e) {
			return new ModelAndView("getAllCustomerDetailsPage", "message", e.getMessage());
		}
	}

	@RequestMapping("postpaidAccountDetails")
	public ModelAndView postpaidAccountDetailsAction(@Valid @RequestParam("mobileNo") long mobileNo, @ModelAttribute Customer customer,
			BindingResult result) {
		try {
			ModelAndView model = new ModelAndView("getPostPaidAccountDetailsPage");
			PostpaidAccount postpaidAccount=billingServices.getPostPaidAccountDetails(customer.getCustomerID(), mobileNo);
			model.addObject("flag",1);
			model.addObject("postpaidAccount",postpaidAccount);
			return model;
		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | BillingServicesDownException e) {
			return new ModelAndView("getPostPaidAccountDetailsPage", "message", e.getMessage());
		}
	}


	@RequestMapping("deleteCustomerAccount")
	public ModelAndView deleteCustomerAccountAction(@Valid  @ModelAttribute Customer customer,
			BindingResult result)  {
		try {
			ModelAndView model = new ModelAndView("deleteCustomerPage");
			billingServices.deleteCustomer(customer.getCustomerID());
			model.addObject("message", "Customer Account deleted");
			return model;
		} catch (BillingServicesDownException | CustomerDetailsNotFoundException e) {
			return new ModelAndView("deleteCustomerPage", "message", e.getMessage());
		}
	}

	@RequestMapping("deletePostpaidAccount")
	public ModelAndView deletePostpaidAccountAction(@Valid @RequestParam("mobileNo") long mobileNo, @ModelAttribute Customer customer,
			BindingResult result)  {
		try {
			ModelAndView model = new ModelAndView("closeParticularCustomerPostpaidAccountPage");
			billingServices.closeCustomerPostPaidAccount(customer.getCustomerID(), mobileNo);
			model.addObject("message", "Customer's PostPaid Account deleted");
			return model;
		} catch (BillingServicesDownException | CustomerDetailsNotFoundException | PostpaidAccountNotFoundException e) {
			return new ModelAndView("closeParticularCustomerPostpaidAccountPage", "message", e.getMessage());
		}		
	}

	@RequestMapping("customerPostPaidAccountAllBillDetails")
	public ModelAndView customerPostPaidAccountPlanDetailsAction(@Valid @RequestParam("customerID") int customerID,@RequestParam("mobileNo") long mobileNo, @ModelAttribute Bill bill,
			BindingResult result) {
		try {

			ModelAndView model = new ModelAndView("pdfView");
			Customer customer = billingServices.getCustomerDetails(customerID);
			PostpaidAccount account = billingServices.getPostPaidAccountDetails(customerID, mobileNo);
			Plan plan = billingServices.getCustomerPostPaidAccountPlanDetails(customerID, mobileNo);
			List<Bill>bills=billingServices.getCustomerPostPaidAccountAllBillDetails(customerID, mobileNo);
			bills.add(bill);
			model.addObject("customer", customer);
			model.addObject("account", account);
			model.addObject("plan", plan);
			model.addObject("bills",bills);
			model.addObject("flag","1");
			return model;
		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | BillDetailsNotFoundException | BillingServicesDownException | PlanDetailsNotFoundException e) {
			return new ModelAndView("getCustomerPostPaidAccountAllBillDetailsPage", "message", e.getMessage());
		}
	}
	@RequestMapping("planChange")
	public ModelAndView planChangeAction(@Valid @RequestParam("mobileNo") long mobileNo, @RequestParam("planID") int planID,@ModelAttribute Customer customer,
			BindingResult result)  {
		try {
			ModelAndView model = new ModelAndView("changeSpecificPlanPage");
			billingServices.changePlan(customer.getCustomerID(), mobileNo, planID);
			model.addObject("message","Your plan is successfully changed");
			return model;
		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | PlanDetailsNotFoundException | BillingServicesDownException e) {
			return new ModelAndView("changeSpecificPlanPage", "message", e.getMessage());
		}
	}

	@RequestMapping("mobileBillDetails")
	public ModelAndView mobileBillDetailsAction(@Valid @RequestParam("customerID") int customerID, @RequestParam("mobileNo") long mobileNo, @ModelAttribute Bill bill,
			BindingResult result)  {
		try {
			
			ModelAndView model = new ModelAndView("pdfView");
			Customer customer = billingServices.getCustomerDetails(customerID);
			PostpaidAccount account = billingServices.getPostPaidAccountDetails(customerID, mobileNo);
			Plan plan = billingServices.getCustomerPostPaidAccountPlanDetails(customerID, mobileNo);
			bill=billingServices.getMobileBillDetails(customerID, mobileNo, bill.getBillMonth());
			List<Bill> bills = new ArrayList<>();
			bills.add(bill);
			model.addObject("customer", customer);
			model.addObject("account", account);
			model.addObject("plan", plan);
			model.addObject("bills",bills);
			model.addObject("flag","1");
			return model;
		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | InvalidBillMonthException
				| BillDetailsNotFoundException | BillingServicesDownException | PlanDetailsNotFoundException e) {
			return new ModelAndView("getMobileBillDetailsPage", "message", e.getMessage());
		}
	}

	@RequestMapping("customerAllPostPaidAccountDetails")
	public ModelAndView customerAllPostPaidAccountDetailsAction(@Valid @ModelAttribute Customer customer,
			BindingResult result)  {
		try {
			ModelAndView model = new ModelAndView("getCustomerAllPostpaidAccountsDetailsPage");
			List<PostpaidAccount> postpaidAccounts= billingServices.getCustomerAllPostpaidAccountsDetails(customer.getCustomerID());
			model.addObject("flag",1);
			model.addObject("postpaidAccounts", postpaidAccounts);
			return model;
		} catch (CustomerDetailsNotFoundException | BillingServicesDownException e) {
			return new ModelAndView("getCustomerAllPostpaidAccountsDetailsPage", "message", e.getMessage());
		}
	}

	@RequestMapping("generateMonthlyBill")
	public ModelAndView generateMonthlyMobileAction(@ModelAttribute Bill bill, @RequestParam("customerID") int customerID, @RequestParam("mobileNo") long mobileNo,
			BindingResult result)  {
		try {
			ModelAndView model = new ModelAndView("generateMonthlyMobileBillPage");
			System.out.println("Bill month"+bill.getBillMonth());
			System.out.println("local sms"+bill.getNoOfLocalSMS());
			int billID=billingServices.generateMonthlyMobileBill(customerID, mobileNo, bill.getBillMonth(), bill.getNoOfLocalSMS(),bill.getNoOfStdSMS(), bill.getNoOfLocalCalls(), bill.getNoOfStdCalls(), bill.getInternetDataUsageUnits());
			model.addObject("billID",billID);
			model.addObject("message","Your monthly bill for billID "+billID+"has been successfully generated");
			return model;
		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | InvalidBillMonthException
				| PlanDetailsNotFoundException | BillingServicesDownException e) {
			return new ModelAndView("generateMonthlyMobileBillPage", "message", e.getMessage());
		}
	}
	
	@RequestMapping("postPaidAccountPlanDetails")
	public ModelAndView postPaidAccountPlanDetailsAction(@ModelAttribute Plan plan, @RequestParam("customerID") int customerID, @RequestParam("mobileNo") long mobileNo,
			BindingResult result)  {
		try {
			ModelAndView model = new ModelAndView("getCustomerPostPaidAccountPlanDetailsPage");
				plan=billingServices.getCustomerPostPaidAccountPlanDetails(customerID, mobileNo);
				model.addObject("plan",plan);
				model.addObject("flag",1);
				return model;
			} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | BillingServicesDownException
					| PlanDetailsNotFoundException e) {
				return new ModelAndView("getCustomerPostPaidAccountPlanDetailsPage", "message", e.getMessage());
			}
		
		
		
	}
}
