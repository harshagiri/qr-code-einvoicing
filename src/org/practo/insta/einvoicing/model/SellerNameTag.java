package org.practo.insta.einvoicing.model;

/**
 * 
 * @author harsha
 *
 * SellerNameTag class matches to the {Seller's Name} tag in ZATCA TLV QR Code tags
 */
public class SellerNameTag extends ZatcaBaseTag {
	/**
	 * using constructor to set the default tag id to 1 as per ZATCA recommendation
	 */
	public SellerNameTag() {
		super.setTagId(1);
	}
}
