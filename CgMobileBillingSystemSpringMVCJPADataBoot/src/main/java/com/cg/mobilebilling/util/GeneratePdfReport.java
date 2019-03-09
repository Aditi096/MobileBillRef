package com.cg.mobilebilling.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.mobilebilling.beans.Bill;
import com.cg.mobilebilling.beans.Customer;
import com.cg.mobilebilling.beans.Plan;
import com.cg.mobilebilling.beans.PostpaidAccount;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePdfReport extends AbstractITextPdfView {

		@SuppressWarnings("unchecked")
		@Override
		protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			// get data model which is passed by the Spring container
			Customer customer = (Customer) model.get("customer");
			PostpaidAccount account = (PostpaidAccount) model.get("account");
			Plan plan = (Plan) model.get("plan");
			List<Bill> bills = (List<Bill>) model.get("bills");
			
			
			for (Bill bill : bills) {
			document.add(new Paragraph("Bills"));
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);

			document.add(new Paragraph("Customer ID: " + customer.getCustomerID()));
			document.add(new Paragraph("Customer Name: " + customer.getFirstName() + " " + customer.getLastName()));
			document.add(new Paragraph("Customer Email Address: " + customer.getEmailID()));
			document.add(new Paragraph("Customer Billing Address: " + customer.getBillingAddress().getCity() + ", " + customer.getBillingAddress().getState() + " - " + customer.getBillingAddress().getPinCode()));
			document.add(new Paragraph("Customer Mobile Number: " + account.getMobileNo() ));
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(9);
			table.setWidthPercentage(100.0f);
			table.setWidths(new float[] {1.0f, 1.5f, 1.0f, 1.5f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f});
			table.setSpacingBefore(0);
			
			// define font for table header row
			Font font = FontFactory.getFont(FontFactory.HELVETICA);
			font.setColor(BaseColor.WHITE);
			
			// define table header cell
			PdfPCell cell = new PdfPCell();
			cell.setBackgroundColor(BaseColor.DARK_GRAY);
			cell.setPadding(5);
			
			// write table header 
			cell.setPhrase(new Phrase("Plan ID", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Plan Name", font));
			table.addCell(cell);

			cell.setPhrase(new Phrase("Bill ID", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Billing Month", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Number of Local Calls", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Number of Local SMS", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Number of STD Calls", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Number of STD SMS", font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("Internet Usage Units", font));
			table.addCell(cell);
			
			document.add(table);
			
			table.flushContent();
			// write table row data
			
				table.addCell(String.valueOf(plan.getPlanID()));
				table.addCell(plan.getPlanName());
				table.addCell(String.valueOf(bill.getBillID()));
				table.addCell(bill.getBillMonth());
				table.addCell(String.valueOf(bill.getNoOfLocalCalls()));
				table.addCell(String.valueOf(bill.getNoOfLocalSMS()));
				table.addCell(String.valueOf(bill.getNoOfStdCalls()));
				table.addCell(String.valueOf(bill.getNoOfStdSMS()));
				table.addCell(String.valueOf(bill.getInternetDataUsageUnits()));
				
				document.add(table);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(new Paragraph("Monthly Rental: Rs. " + plan.getMonthlyRental()));
				document.add(new Paragraph("Amount for Local Calls: Rs. " + bill.getLocalCallAmount()));
				document.add(new Paragraph("Amount for Local SMS: Rs. " + bill.getLocalSMSAmount()));
				document.add(new Paragraph("Amount for STD Calls: Rs. " + bill.getStdCallAmount()));
				document.add(new Paragraph("Amount for STD SMS: Rs. " + bill.getStdSMSAmount()));
				document.add(new Paragraph("Amount for Internet Usage: Rs. " + bill.getInternetDataUsageAmount()));
				document.add(new Paragraph("Service tax on the Bill: Rs. " + bill.getServicesTax()));
				document.add(new Paragraph("VAT on the Bill: Rs. " + bill.getVat()));
				document.add(Chunk.NEWLINE);
				document.add(new Paragraph("Total Bill Amount For " + bill.getBillMonth() + " month: Rs." + bill.getTotalBillAmount()));
				document.add(Chunk.NEWLINE);
				
				document.add(Chunk.NEXTPAGE);
			}
			
						
			
		}
}	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		 public static void generateBillSummaryReport(Customer customer, Plan plan, PostpaidAccount account) { 
		    try {
			Document document = new Document();
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			PdfPTable table = new PdfPTable(3);

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell = new PdfPCell();
			hcell = new PdfPCell(new Phrase("CustomerID", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("PlanID", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Mobile Number", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			PdfPCell cell;

			cell = new PdfPCell(new Phrase(String.valueOf(customer.getCustomerID())));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(plan.getPlanID())));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(account.getMobileNo())));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			PdfWriter.getInstance(document, out);
			document.open();
			document.add(table);

			document.close();
		} catch (DocumentException e) {
			System.err.println("Error generating PDF huh   "+ e.getMessage());
		}

	}*/

	
