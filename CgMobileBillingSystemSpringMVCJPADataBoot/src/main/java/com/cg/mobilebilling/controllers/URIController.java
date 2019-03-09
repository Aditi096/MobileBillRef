package com.cg.mobilebilling.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.mobilebilling.beans.Bill;
import com.cg.mobilebilling.beans.Customer;
import com.cg.mobilebilling.beans.Plan;
import com.cg.mobilebilling.beans.PostpaidAccount;

@Controller
public class URIController {
	Customer customer;
	Plan plan;
	PostpaidAccount postpaidAccount;
	Bill bill;

	@RequestMapping("/")
	public String getIndexPage() {
		return "indexPage";
	}

	@RequestMapping("/registration")
	public String getRegistrationPage() {
		return "registrationPage";
	}
	@RequestMapping("/index")
	public String getHomePage() {
		return "indexPage";
	}
	
	@RequestMapping("/getPlanAllDetails")
	public String getPlanAllDetailsPage() {
		return "getPlanAllDetailsPage";
	}
	
	@RequestMapping("/openPostpaidMobileAccount")
	public String openPostpaidMobileAccountPage() {
		return "openPostpaidMobileAccountPage"; 
	}

	@RequestMapping("/getCustomerDetails")
	public String getCustomerDetailsPage() {
		return "getCustomerDetailsPage";
	}
	
	@RequestMapping("/getAllCustomerDetails")
	public String getAllCustomerDetailsPage() {
		return "getAllCustomerDetailsPage";
	}
	
	@RequestMapping("/getPostPaidAccountDetails")
	public String getPostPaidAccountDetailsPage() {
		return "getPostPaidAccountDetailsPage";
	}
	
	@RequestMapping("/deleteCustomer")
	public String deleteCustomerPage() {
		return "deleteCustomerPage";
	}
	
	@RequestMapping("/closeCustomerPostPaidAccount")
	public String closeCustomerPostPaidAccountPage() {
		return "closeParticularCustomerPostpaidAccountPage";
	}
	
	@RequestMapping("/getCustomerPostPaidAccountPlanDetails")
	public String getCustomerPostPaidAccountPlanDetailsPage() {
		return "getCustomerPostPaidAccountPlanDetailsPage";
	}
	
	@RequestMapping("/changePlan")
	public String changePlanPage() {
		return "changeSpecificPlanPage";
	}
	
	@RequestMapping("/getMobileBillDetails")
	public String getMobileBillDetailsPage() {
		return "getMobileBillDetailsPage";
	}
	
	@RequestMapping("/getCustomerPostPaidAccountAllBillDetails")
	public String getCustomerPostPaidAccountAllBillDetailsPage() {
		return "getCustomerPostPaidAccountAllBillDetailsPage";
	}
	
	@RequestMapping("/getCustomerAllPostpaidAccountsDetails")
	public String getCustomerAllPostpaidAccountsDetailsPage() {
		return "getCustomerAllPostpaidAccountsDetailsPage";
	}
	
	@RequestMapping("/generateMonthlyMobileBill")
	public String generateMonthlyMobileBillPage() {
		return "generateMonthlyMobileBillPage";
	}
	@RequestMapping("/addPlans")
	public String addPlansPage() {
		return "addNewPlansPage";
	}
	@ModelAttribute
	public Customer getCustomer() {
		customer=new Customer();
		return customer;
	}
	
	@ModelAttribute
	public Plan getPlan() {
		plan=new Plan();
		return plan;
	}
	
	@ModelAttribute
	public PostpaidAccount getPostpaidAccount() {
		postpaidAccount=new PostpaidAccount();
		return postpaidAccount;
	}
	
	@ModelAttribute
	public Bill getBill() {
		bill=new Bill();
		return bill;
	}
}
