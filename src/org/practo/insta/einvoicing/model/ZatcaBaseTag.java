package org.practo.insta.einvoicing.model;

import org.apache.commons.codec.binary.Hex;

/**
 * Base abstract class that dictates the core of tags and its processing
 * 
 * @author harsha
 *
 */
public abstract class ZatcaBaseTag {
	protected int tagId;
	protected int length;
	protected String value;
	
	/**
	 * Return tagID in String
	 * 
	 * @param returnHex set to true if the returned string should be in Hexadecimal equivalent of integer
	 * @return String
	 */
	public String getTagId(boolean returnHex) {
		if(returnHex) {		
			return String.format("%02x", tagId);
		} 
		return tagId+"";
	}
	
	/**
	 * Return tag value length in string
	 * 
	 * @param returnHex set to true if the returned string should be in Hexadecimal equivalent of integer
	 * @return String
	 */
	public String getLength(boolean returnHex) {
		if(returnHex) {			
			return String.format("%02x", length);
		}
		return length+"";
	}
	
	/**
	 * Return tag value
	 * 
	 * @param returnHex set to true if the returned string should be in Hexadecimal
	 * @return String
	 */
	public String getValue(boolean returnHex) {
		if(returnHex && value!= null && !value.isEmpty()) {			
			return Hex.encodeHexString(value.getBytes());
		}else {
			return value;
		}
	}
	
	/**
	 * Setter for tag ID should not be null
	 * 
	 * @param tagId
	 */
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	
	/**
	 * Setter for tag length should not be null
	 * 
	 * @param length
	 */
	public void setLength(int length) {
		this.length = length;
	}
	
	/**
	 * Setter for tag value, should not be null
	 * 
	 * @param value
	 */
	public void setValue(String value) {
		//Setting the tag length as soon as we get the tag value
		setLength(value.trim().length());
		this.value = value;
	}
	
	public String returnHexString() {
		String hexString =  getTagId(true)+getLength(true)+getValue(true);
		//TODO: Replace this by logger
		System.out.println(getValue(false)+": "+hexString);
		return hexString;
	}
}
