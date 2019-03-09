package com.cg.mobilebilling.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.cg.mobilebilling.beans.Bill;
import com.cg.mobilebilling.beans.Customer;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePdfReport {
	public static ByteArrayInputStream billPDFReport(Bill bill) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfPTable table = new PdfPTable(10);
			table.setWidthPercentage(60);
			table.setWidths(new int[]{3,3,3,3,3,3,3,3,3,3});

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Bill Id", headFont));
			hcell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Bill month", headFont));
			hcell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("localSMSAmount", headFont));
			hcell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("stdSMSAmount", headFont));
			hcell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("localCallAmount", headFont));
			hcell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("stdCallAmount", headFont));
			hcell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("internetDataUsageAmount", headFont));
			hcell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("cgst", headFont));
			hcell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("sgst", headFont));
			hcell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("totalBillAmount", headFont));
			hcell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			if( bill!=null) {


				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(bill.getBillID())));
				cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(bill.getBillMonth())));
				cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(bill.getLocalSMSAmount())));
				cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(bill.getStdSMSAmount())));
				cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(bill.getLocalCallAmount())));
				cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(bill.getStdCallAmount())));
				cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(bill.getInternetDataUsageAmount())));
				cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(bill.getCgst())));
				cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(bill.getSgst())));
				cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(bill.getTotalBillAmount())));
				cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);



			}

			PdfWriter.getInstance(document, out);
			document.open();
			document.add(table);

			document.close();

		} catch(DocumentException ex) {

			Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
		}

		return new ByteArrayInputStream(out.toByteArray());  
	}


}


