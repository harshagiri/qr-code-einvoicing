package org.practo.insta.einvoicing.model;

/**
 * 
 * @author harsha
 *
 * SellerNameTag class matches to the {VAT Registration Number} tag in ZATCA TLV QR Code tags
 */
public class TaxNumberTag extends ZatcaBaseTag {
	/**
	 * using constructor to set the default tag id to 2 as per ZATCA recommendation
	 */
	public TaxNumberTag() {
		super.setTagId(2);
	}
}
