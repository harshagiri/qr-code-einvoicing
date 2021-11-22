package org.practo.insta.einvoicing.model;

/**
 * 
 * @author harsha
 * 
 * InvoiceTaxAmount class matches to the {VAT Total} tag in ZATCA TLV QR Code tags
 */
public class InvoiceTaxAmount extends ZatcaBaseTag {
	/**
	 * using constructor to set the default tag id to 5 as per ZATCA recommendation
	 */
	public InvoiceTaxAmount() {
		super.setTagId(5);
	}
}
