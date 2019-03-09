package com.cg.mobilebilling.daoservices;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.mobilebilling.beans.Bill;
import com.cg.mobilebilling.beans.Customer;
import com.cg.mobilebilling.beans.Plan;
import com.cg.mobilebilling.beans.PostpaidAccount;

public interface PostpaidDAOServices extends JpaRepository<PostpaidAccount, Long> {
	@Query("select a from PostpaidAccount a where a.customer=:customer")
	List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(@Param("customer") Customer customer);
	@Query("select a from PostpaidAccount a where a.customer=:customer and a.mobileNo=:mobileNo")
	PostpaidAccount getPostPaidAccountDetails(@Param("customer") Customer customer,@Param("mobileNo") long mobileNo);
	@Transactional
	@Modifying
	@Query("delete from PostpaidAccount a where a.customer=:customer and a.mobileNo=:mobileNo")
	void closeCustomerPostPaidAccount(@Param("customer") Customer customer,@Param("mobileNo") long mobileNo);
	@Query("select a.bills from PostpaidAccount a where a.customer=:customer and a.mobileNo=:mobileNo")
	List<Bill> getCustomerPostPaidAccountAllBillDetails(@Param("customer") Customer customer,@Param("mobileNo") long mobileNo);
}
