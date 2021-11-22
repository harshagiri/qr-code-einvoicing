package org.practo.insta.einvoicing.generator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.practo.insta.einvoicing.model.InvoiceDateTag;
import org.practo.insta.einvoicing.model.InvoiceTaxAmount;
import org.practo.insta.einvoicing.model.InvoiceTotalAmount;
import org.practo.insta.einvoicing.model.SellerNameTag;
import org.practo.insta.einvoicing.model.TaxNumberTag;
import org.practo.insta.einvoicing.model.ZatcaBaseTag;

/**
 * 
 * @author harsha
 *
 * This class is the core class that accepts the tag objects and generates the Base64 encoded string
 */
public class ZatcaTLVQRCodeGenerator {
	private List<ZatcaBaseTag> tags = new ArrayList<ZatcaBaseTag>();
	private String finalBase64EncodedString = "";
	private Pattern HEXADECIMAL_PATTERN = Pattern.compile("\\p{XDigit}+");

	/**
	 * Default constructor to create the necessary list of tags that the class can process
	 * 
	 * @param sellerName
	 * @param vatNumber
	 * @param timeStamp
	 * @param invoiceAmt
	 * @param vatAmount
	 */
	public ZatcaTLVQRCodeGenerator( SellerNameTag sellerName ,
			TaxNumberTag vatNumber,
			InvoiceDateTag timeStamp,
			InvoiceTotalAmount invoiceAmt,
			InvoiceTaxAmount vatAmount) {
		//Order is important here and it should not be changed
		tags.add(sellerName);
		tags.add(vatNumber);
		tags.add(timeStamp);
		tags.add(invoiceAmt);
		tags.add(vatAmount);
	}

	/**
	 * This method loops through all tags, concatenates and then convert them to Base64
	 * 
	 * @return Base64 string
	 */
	public String generateZatcalTLVQRCode() {

		for (Iterator<ZatcaBaseTag> iterator = tags.iterator(); iterator.hasNext();) {
			ZatcaBaseTag zatcaBaseTag = (ZatcaBaseTag) iterator.next();
			finalBase64EncodedString += zatcaBaseTag.returnHexString();
		}
		//TODO: Replace this by logger
		System.out.println(finalBase64EncodedString);
		return generateBase64(finalBase64EncodedString);
	}


	/**
	 * Method to check if the input string is a hexadecimal string or not
	 * 
	 * @param input
	 * @return boolean true of hexadecimal else false
	 */
	private boolean isHexadecimal(String input) {
		final Matcher matcher = HEXADECIMAL_PATTERN.matcher(input);
		return matcher.matches();
	}

	private byte[] convertHexStringToHexByte(String hexString) {
		//creating a byte array to hold the converted byte string
		byte[] byteEquiHexString = new byte[hexString.length() / 2];
		
		for (int i = 0; i < byteEquiHexString.length; i++) {
			//Consider every 2 digits in the hexString as hex equivalent Number
			int index = i * 2;
			//Parsing a hex number as 16 bit (1 number)
			int j = Integer.parseInt(hexString.substring(index, index + 2), 16);
			//Store the converted byte equivalent of hexString in new array
			byteEquiHexString[i] = (byte) j;
		}
		return byteEquiHexString;
	}
	/**
	 * Converts each method to Base64 using java.util.Base64 accepting hexString
	 * @param hexString
	 * @return Base64 string of hexString
	 */
	private String generateBase64(String hexString) {
		String returnString = null;
		if(isHexadecimal(hexString)) {
			returnString = Base64.getEncoder().encodeToString(convertHexStringToHexByte(hexString));
		}
		return returnString;
	}
}
