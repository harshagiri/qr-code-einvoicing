package org.practo.insta.einvoicing.model;

/**
 * 
 * @author harsha
 *
 * InvoiceTotalAmount class matches to the {Invoice Total(with VAT)} tag in ZATCA TLV QR Code tags
 */
public class InvoiceTotalAmount extends ZatcaBaseTag {
	/**
	 * using constructor to set the default tag id to 4 as per ZATCA recommendation
	 */
	public InvoiceTotalAmount() {
		super.setTagId(4);
	}
}
