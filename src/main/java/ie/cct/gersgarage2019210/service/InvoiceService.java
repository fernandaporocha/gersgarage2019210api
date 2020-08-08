package ie.cct.gersgarage2019210.service;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ie.cct.gersgarage2019210.model.Booking;
import ie.cct.gersgarage2019210.model.BookingItem;
import ie.cct.gersgarage2019210.model.ServiceType;

@Service
public class InvoiceService {
	private static String FILE = "c:/temp/FirstPdf.pdf";
	Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	

	Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	BigDecimal totalService = new BigDecimal(0);
	BigDecimal totalItem = new BigDecimal(0);

	public void generateInvoice(Booking booking, List<BookingItem> items) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			addInfoData(document, booking);
			document.add(new Paragraph(" "));
			addServiceTable(document, booking);
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			addItemsTable(document, booking, items);
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			addTotalInvoice(document);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addInfoData(Document document, Booking booking) throws DocumentException {
		Paragraph logo = new Paragraph("Ger's Garage");
		//https://howtodoinjava.com/java/date-time/localdate-format-example/
		String formattedDate = booking.getBookingDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
		Paragraph date = new Paragraph("Booking Date: "+ formattedDate);
		Paragraph userName = new Paragraph("Customer: "+
				booking.getCustomer().getFirstName() + " " + booking.getCustomer().getLastName());
		Paragraph mobile = new Paragraph("Mob No:   " + booking.getCustomer().getContactDetails().getMobilePhone());
		document.add(logo);
		document.add(date);
		document.add(userName);
		document.add(mobile);
		document.add(new Paragraph(" "));
		Paragraph vehicle = new Paragraph("Vehicle: " + booking.getVehicle().getModel().getMake().getName() + " " +booking.getVehicle().getModel().getName());
		Paragraph licence = new Paragraph("Licence: " + booking.getVehicle().getLicence());
		document.add(vehicle);
		document.add(licence);

	}

	private void addServiceTable(Document document, Booking booking) throws DocumentException {
		PdfPTable table = new PdfPTable(2);

		PdfPCell c1 = new PdfPCell(new Phrase("Services", boldFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Price", boldFont));
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(c1);
		table.setHeaderRows(1);

		table.addCell(booking.getRequiredBooking().getName());
		c1 = new PdfPCell(new Phrase("€ " + booking.getRequiredBooking().getPrice()));
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(c1);
		totalService = totalService.add(booking.getRequiredBooking().getPrice());
		for (ServiceType service : booking.getRequiredServices()) {
			table.addCell(service.getName());
			c1 = new PdfPCell(new Phrase("€ " + service.getPrice()));
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(c1);
			totalService = totalService.add(service.getPrice());
		}
		c1 = new PdfPCell(new Phrase("Total", boldFont));
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("€ " + totalService, boldFont));
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(c1);

		document.add(table);
	}

	private void addItemsTable(Document document, Booking booking, List<BookingItem> items) throws DocumentException {
		PdfPTable table = new PdfPTable(4);

		Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
		PdfPCell c1 = new PdfPCell(new Phrase("Items", boldFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Quantity", boldFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		c1 = new PdfPCell(new Phrase("Price", boldFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		c1 = new PdfPCell(new Phrase("Total", boldFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		for (BookingItem item : items) {
			table.addCell(item.getItem().getName());
			
			c1 = new PdfPCell(new Phrase(item.getItem().getQuantity().toString()));
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("€ " + item.getItem().getPrice()));
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(c1);

			BigDecimal total = item.getItem().getPrice().multiply(new BigDecimal(item.getItem().getQuantity()));
			c1 = new PdfPCell(new Phrase("€ " + total));
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(c1);
			totalItem = totalItem.add(total);

		}
		c1 = new PdfPCell(new Phrase("Total", boldFont));
		//https://kb.itextpdf.com/home/it5kb/examples/colspan-and-rowspan
		c1.setColspan(2);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("€ " + totalItem, boldFont));
		c1.setColspan(2);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(c1);

		document.add(table);
	}
	
	private void addTotalInvoice(Document document) throws DocumentException {
		PdfPTable table = new PdfPTable(2);
		BigDecimal totalInvoice = totalItem.add(totalService);

		PdfPCell c1 = new PdfPCell(new Phrase("Total Due", boldFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("€ " + totalInvoice, boldFont));
		c1.setColspan(2);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(c1);

		document.add(table);
	}
	
}
