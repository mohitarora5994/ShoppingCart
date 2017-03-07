package com.neosofttech.utils;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.neosofttech.model.Products;

public class PdfView extends AbstractPdfView{

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override		
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Map m = (Map) session.getAttribute("INVOICE");
		List<Products> pro=(List<Products>) m.get("pro");
		List<Long> count = (List<Long>) m.get("Quan");
		PdfPTable t = new PdfPTable(pro.size()+1);
		String username = (String) m.get("USERNAME");
		String address = (String) m.get("Address");
		String mobileNo = (String) m.get("MobileNo");
		Date date = (Date) m.get("Date");
		String paymentType = (String) m.get("PaymentType");
		String total = String.valueOf(m.get("total"));
		Paragraph p1 =new Paragraph(new Chunk("Name: "+username));
		Paragraph p2 =new Paragraph(new Chunk("Address: "+address));
		Paragraph p3 =new Paragraph(new Chunk("MobileNo:"+mobileNo));
		Paragraph p4 =new Paragraph(new Chunk("Date: "+date.toString()));
		Paragraph p5 =new Paragraph(new Chunk("PaymentType: "+paymentType));
		Paragraph p6 =new Paragraph(new Chunk("TotalPrice: "+total));
		Paragraph p7 = new Paragraph(new Chunk("Copyright © 2013 E-SHOPPER Inc. All rights reserved."));
		t.addCell("ModelName");
		for (Products products : pro) {
			t.addCell(products.getModelName());
		}
		
		t.addCell("Price");
		for (Products products : pro) {
			t.addCell(products.getPrice());
		}
		t.addCell("Quantity");
		for(Long c : count){
			String cc = String.valueOf(c);
			t.addCell(cc);
		}
		Image image = Image.getInstance("logo.png");
		document.add(image);
		document.add(p1);
		document.add(p2);
		document.add(p3);
		document.add(p4);
		document.add(Chunk.NEWLINE);
		document.add(t);
		document.add(Chunk.NEWLINE);
		document.add(p6);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.add(p5);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.add(p7);
	}

	
}
