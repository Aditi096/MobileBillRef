package com.cg.mobilebilling.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mobilebilling.beans.Bill;
import com.cg.mobilebilling.beans.Customer;
import com.cg.mobilebilling.exceptions.BillDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.BillingServicesDownException;
import com.cg.mobilebilling.exceptions.CustomerDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.InvalidBillMonthException;
import com.cg.mobilebilling.exceptions.PlanDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.PostpaidAccountNotFoundException;
import com.cg.mobilebilling.services.BillingServices;
import com.cg.mobilebilling.util.GeneratePdfReport;


@RestController
public class PDFController {
	@Autowired
	BillingServices billingServices;

	@RequestMapping(value = "/billpdfreport", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> billPDFReport(@RequestParam int customerID,@RequestParam long mobileNo,@RequestParam String billMonth) throws BillingServicesDownException, PlanDetailsNotFoundException, CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException, BillDetailsNotFoundException {

		Bill bill=billingServices.getMobileBillDetails(customerID, mobileNo, billMonth);
		ByteArrayInputStream bis = GeneratePdfReport.billPDFReport(bill);


		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=customersreport.pdf");

		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

}
