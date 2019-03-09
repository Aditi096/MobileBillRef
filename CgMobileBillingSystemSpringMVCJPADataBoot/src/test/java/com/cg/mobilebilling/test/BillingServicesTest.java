package com.cg.mobilebilling.test;

import static org.junit.Assert.*;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.cg.mobilebilling.daoservices.BillingServiceDAO;
import com.cg.mobilebilling.daoservices.CustomerDAO;
import com.cg.mobilebilling.daoservices.PlanDetailsDAO;
import com.cg.mobilebilling.daoservices.PostPaidAccountDAO;
import com.cg.mobilebilling.exceptions.BillingServicesDownException;
import com.cg.mobilebilling.exceptions.CustomerDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.PlanDetailsNotFoundException;
import com.cg.mobilebilling.services.BillingServices;
import com.cg.mobilebilling.services.BillingServicesImpl;
@RunWith(EasyMockRunner.class)
public class BillingServicesTest {
	@Mock 
	BillingServiceDAO billingServiceDAO;
	@Mock
	CustomerDAO customerDAO;
	@Mock
	PlanDetailsDAO planDetailsDAO;
	@Mock
	PostPaidAccountDAO postPaidAccountDAO;
	@TestSubject
	BillingServices billingServices = new BillingServicesImpl();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}
	@Test
	public void openPostPaidMobileAccount() throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException, BillingServicesDownException {
		{
			billingServices.acceptCustomerDetails("Vivek", "Singla", "viveksingla4809@gmail.com", "18-09-1996", "Mandi Gobindgarh", "Punjab", 147301);
			assertEquals(1000000001, billingServices.openPostpaidMobileAccount(20003, 1001));
		}
	}
	
	@Test(expected=CustomerDetailsNotFoundException.class)
	public void getCustomerDetails() throws CustomerDetailsNotFoundException, BillingServicesDownException {
		{
			billingServices.getCustomerDetails(20014);
		}
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}
