package org.practo.insta.einvoicing.model;

/**
 * 
 * @author harsha
 *
 * InvoideDateTag class matches to the {TimeStamp} tag in ZATCA TLV QR Code tags
 */
public class InvoiceDateTag extends ZatcaBaseTag {
	/**
	 * using constructor to set the default tag id to 3 as per ZATCA recommendation
	 */
	public InvoiceDateTag() {
		super.setTagId(3);
	}
}
