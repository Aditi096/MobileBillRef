package com.cg.mobilebilling.boot;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
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
import com.cg.mobilebilling.services.BillingServices;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CgMobileBillingSpringMvcjpaDataBootApplicationTests {
	@MockBean
	private  CustomerDAOServices customerDAO;
	@MockBean
	private PlanDAOServices planDAO;
	@MockBean
	private BillingDAOServices billingDAO;
	@MockBean
	private PostpaidDAOServices postpaidDAO;
	@Autowired
	private BillingServices billingServices;
	private Bill bill1;
	private Customer customer1,customer2;
	private PostpaidAccount postpaidAccount1,postpaidAccount2,postpaidAccount3;
	private ArrayList<Customer> customerList = new ArrayList<>();
	private ArrayList<PostpaidAccount> postPaidAccountList = new ArrayList<>();
	private Plan plan1 ;
	@Before
	public void setUpData() {
		customer1 = new Customer(111,"Renuka", "Indukuri", "renuka@gmail.com", "19/10/1996", new Address(405750, "pune", "Maharastra"));
		customer2= new Customer(222,"Chandana", "Maale", "chandana@gmail.com", "14/05/1997", new Address(405750, "pune", "Maharastra"));
		bill1= new Bill(11,100, 100, 100, 100, 200,postpaidAccount3, "January", 555.5f, 2.5f, 3.5f, 0.0f, 0.0f, 0.0f, 25.25f, 25.25f);
		postpaidAccount1=new PostpaidAccount(900000001, plan1, customer1);
		postpaidAccount2 = new PostpaidAccount(900000002, plan1, customer1);
		postpaidAccount3 = new PostpaidAccount(900000003, plan1, customer2);
		plan1 = new Plan(1,499,100,100,50,50,1000,0.10f,0.20f,0.05f,0.07f,0.03f,"pune","plan101");
		postPaidAccountList.add(postpaidAccount1);
		postPaidAccountList.add(postpaidAccount2);
		customerList.add(customer1);
		customerList.add(customer2);

	}

	@Test
	public void acceptCustomerDetailsTest() throws CustomerDetailsNotFoundException, BillingServicesDownException {
		Mockito.when(customerDAO.save(Mockito.any(Customer.class))).thenReturn(customer1);
		customer1=billingServices.acceptCustomerDetails(customer1);
		assertThat(111).isEqualTo(customer1.getCustomerID());	
	}

	@Test
	public void getAllCustomerDetailsTest() throws BillingServicesDownException {
		Mockito.when(customerDAO.findAll()).thenReturn(customerList);
		assertThat(billingServices.getAllCustomerDetails()).isEqualTo(customerList);	
	}

	@Test(expected =CustomerDetailsNotFoundException.class)
	public void invalidCustomerOpenPostpaidMobileAccountTest() throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException, BillingServicesDownException {
		billingServices.openPostpaidMobileAccount(110,111);
		Mockito.verify(customerDAO.findById(Mockito.anyInt()));
	}

	@Test
	public void getCustomerDetailsTest() throws CustomerDetailsNotFoundException, BillingServicesDownException {
		Mockito.when(customerDAO.findById(Mockito.anyInt())).thenReturn(Optional.of(customer1));
		assertThat(billingServices.getCustomerDetails(111)).isEqualTo(customer1);

	}

	@Test(expected =PlanDetailsNotFoundException.class)
	public void invalidPlanOpenPostpaidMobileAccountTest() throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException, BillingServicesDownException {
		Mockito.when(customerDAO.findById(Mockito.anyInt())).thenReturn(Optional.of(customer1));
		billingServices.openPostpaidMobileAccount(111,11);
	}

	@Test
	public void getPostPaidAccountDetailsTest() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {
		Mockito.when(customerDAO.findById(Mockito.anyInt())).thenReturn(Optional.of(customer2));
		Mockito.when(postpaidDAO.getPostPaidAccountDetails(Mockito.any(Customer.class), Mockito.anyLong())).thenReturn(postpaidAccount3);
		assertThat(billingServices.getPostPaidAccountDetails(222, 900000003)).isEqualTo(postpaidAccount3);
	}

	@Test
	public void getCustomerAllPostpaidAccountsDetailsTest() throws CustomerDetailsNotFoundException, BillingServicesDownException, PostpaidAccountNotFoundException {
		Mockito.when(customerDAO.findById(Mockito.anyInt())).thenReturn(Optional.of(customer1));
		Mockito.when(postpaidDAO.getCustomerAllPostpaidAccountsDetails(Mockito.any(Customer.class))).thenReturn(postPaidAccountList);
		assertThat(billingServices.getCustomerAllPostpaidAccountsDetails(111)).isEqualTo(postPaidAccountList);
	}

	@Test
	public void generateMonthlyMobileBillTest() throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException, BillingServicesDownException, PostpaidAccountNotFoundException, InvalidBillMonthException, BillDetailsNotFoundException {
		Mockito.when(customerDAO.findById(Mockito.anyInt())).thenReturn(Optional.of(customer1));
		Mockito.when(planDAO.findById(Mockito.anyInt())).thenReturn(Optional.of(plan1));
		Mockito.when(postpaidDAO.getPostPaidAccountDetails(Mockito.any(Customer.class), Mockito.anyLong())).thenReturn(postpaidAccount1);
		Mockito.when(billingDAO.getMobileBillDetails(Mockito.any(PostpaidAccount.class),Mockito.any(String.class))).thenReturn(null);
		Mockito.when(billingDAO.save(Mockito.any(Bill.class))).thenReturn(bill1);
		Bill bill=billingServices.generateMonthlyMobileBill(111, 900000001, "January", 100, 100, 100, 100, 200);
		assertThat(bill.getTotalBillAmount()).isEqualTo(555.5f);
	}

	@Test
	public void getMobileBillDetailsTest() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException, BillDetailsNotFoundException, BillingServicesDownException {
		Mockito.when(customerDAO.findById(Mockito.anyInt())).thenReturn(Optional.of(customer1));
		Mockito.when(postpaidDAO.getPostPaidAccountDetails(Mockito.any(Customer.class), Mockito.anyLong())).thenReturn(postpaidAccount1);
		Mockito.when(billingDAO.getMobileBillDetails(Mockito.any(PostpaidAccount.class),Mockito.any(String.class))).thenReturn(bill1);
		Bill bill=billingServices.getMobileBillDetails(111,900000001, "January");
		assertThat(bill.getTotalBillAmount()).isEqualTo(555.5f);
	}
}