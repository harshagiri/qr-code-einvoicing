package org.practo.insta.einvoicing.generator;

import org.practo.insta.einvoicing.model.InvoiceDateTag;
import org.practo.insta.einvoicing.model.InvoiceTaxAmount;
import org.practo.insta.einvoicing.model.InvoiceTotalAmount;
import org.practo.insta.einvoicing.model.SellerNameTag;
import org.practo.insta.einvoicing.model.TaxNumberTag;

/**
 * 
 * @author harsha
 *
 * Core class that should be consumed to get Zatca compliant Base64 encode string
 * TLV - stands for Tag Length Value
 */
public class ZatcaTLVProcessor {
	private SellerNameTag sellerName = null;
	private TaxNumberTag vatNumber = null;
	private InvoiceDateTag timeStamp = null;
	private InvoiceTotalAmount invoiceAmt = null;
	private InvoiceTaxAmount vatAmount = null;

	/**
	 * Constructor to prepare the necessary objects to be initialized
	 * 
	 * @param sellerValue - Seller's name as per Zatca TLV standards
	 * @param vatNumValue - VAT Registration number as per Zatca TLV standards
	 * @param timeStampValue - Time stamp as per Zatca TLV standards
	 * @param invoiceAmtValue - Invoice Total (with VAT) as per Zatca TLV standards
	 * @param vatAmtValue - VAT Total as per Zatca TLV standards
	 */
	public ZatcaTLVProcessor(String sellerValue,
			String vatNumValue,
			String timeStampValue,
			double invoiceAmtValue,
			double vatAmtValue) {

		if(sellerValue == null || sellerValue.isEmpty() || 
				vatNumValue == null || vatNumValue.isEmpty() || 
				timeStampValue == null || timeStampValue.isEmpty()) {
			throw new IllegalArgumentException("Seller's Name, VAT registration Number and TimeStamp cannot be null ");
		}
		sellerName = new SellerNameTag();
		sellerName.setValue(sellerValue);

		vatNumber = new TaxNumberTag();
		vatNumber.setValue(vatNumValue);

		timeStamp = new InvoiceDateTag();
		timeStamp.setValue(timeStampValue);

		invoiceAmt = new InvoiceTotalAmount();
		//Formatting explicitly to 2 digits after decimal place in amount
		invoiceAmt.setValue(String.format("%.2f", invoiceAmtValue));

		vatAmount = new InvoiceTaxAmount();
		//Formatting explicitly to 2 digits after decimal place in VAT
		vatAmount.setValue(String.format("%.2f",vatAmtValue));
	}

	/**
	 * Method that returns the Base64 string of passed values for Zatca QR code processing
	 * 
	 * @return Base64 string
	 */
	public String generateQRString() {
		ZatcaTLVQRCodeGenerator codeGenerator = new ZatcaTLVQRCodeGenerator(sellerName, 
				vatNumber, timeStamp, invoiceAmt, vatAmount);
		String base64TLVString = codeGenerator.generateZatcalTLVQRCode();
		//TODO: Replace this with logger
		System.out.println(base64TLVString);
		return base64TLVString;
	}
}
