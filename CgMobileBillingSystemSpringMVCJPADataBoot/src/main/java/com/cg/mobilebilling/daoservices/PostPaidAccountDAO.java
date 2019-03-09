package com.cg.mobilebilling.daoservices;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cg.mobilebilling.beans.Bill;
import com.cg.mobilebilling.beans.PostpaidAccount;
import com.cg.mobilebilling.exceptions.BillingServicesDownException;
import com.cg.mobilebilling.exceptions.CustomerDetailsNotFoundException;

public interface PostPaidAccountDAO extends JpaRepository<PostpaidAccount, Long> {
	@Query("SELECT p FROM PostpaidAccount p WHERE p.customer.customerID = :customerID")
	public List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(@Param("customerID") int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException;
	
	@Transactional
	@Modifying
	@Query("DELETE from PostpaidAccount b WHERE b.mobileNo = ?1")
	public int deleteCustomerPostpaidAccount(@Param("mobileNo") long mobileNo);
}
