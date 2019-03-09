package com.cg.mobilebilling.daoservices;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.mobilebilling.beans.Bill;
import com.cg.mobilebilling.beans.Customer;
import com.cg.mobilebilling.beans.PostpaidAccount;
public interface BillingDAOServices extends JpaRepository<Bill, Integer>{
	@Query("select a from Bill a where a.postpaidAccount=:postpaidAccount and a.billMonth=:billMonth")
	Bill getMobileBillDetails(@Param("postpaidAccount") PostpaidAccount postpaidAccount,@Param("billMonth") String billMonth);	
	@Transactional
	@Modifying
	@Query("delete from Bill a where a.postpaidAccount=:postpaidAccount")
	void deleteAllBills(@Param("postpaidAccount") PostpaidAccount postpaidAccount);
}