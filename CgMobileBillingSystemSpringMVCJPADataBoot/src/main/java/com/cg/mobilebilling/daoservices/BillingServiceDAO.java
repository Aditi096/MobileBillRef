package com.cg.mobilebilling.daoservices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cg.mobilebilling.beans.Bill;

public interface BillingServiceDAO extends JpaRepository<Bill, Integer>{
	@Query("SELECT b from Bill b WHERE b.postpaidAccount.mobileNo = ?1 AND b.billMonth = ?2")
	public Bill retrieveBill(@Param("mobileNo") long mobileNo, @Param("billMonth") String billMonth);
	
	@Query("SELECT b from Bill b WHERE b.postpaidAccount.mobileNo = ?1")
	public List<Bill> retrieveAllBills(@Param("mobileNo") long mobileNo);
}
