package org.practo.insta.einvoicing;

import org.practo.insta.einvoicing.generator.ZatcaTLVProcessor;

/**
 * 
 * @author harsha
 *
 * Test app to kick start the conversion and testing
 */
public class LaunchApp {

	public static void main(String[] args) {
		ZatcaTLVProcessor zatcaMain = new ZatcaTLVProcessor(
				"Bobs Records", 
				"210122393500003", 
				"2022-04-25T15:30:00Z", 
				1000.00, 
				150.00);
		zatcaMain.generateQRString();
	}

}
