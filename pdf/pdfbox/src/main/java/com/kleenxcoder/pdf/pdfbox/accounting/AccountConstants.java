package com.kleenxcoder.pdf.pdfbox.accounting;

import java.util.Arrays;
import java.util.List;

/**
 * @author kleenxcoder
 */

public abstract class AccountConstants {
	
	public static final List<String> HEADER_KONTOBLATT = Arrays.asList("ACCOUNT SHEET");
	
	public static final List<String> HEADER_RESIDENTIAL = Arrays.asList(
            "Call:   +1 123 456 789",
            "Shop:   +1 123 456 789",
            "E-Mail: contact@disneynow.com",
            "Web:    www.disneynow.com");
	
	public static final List<String> HEADER_BUSINESS = Arrays.asList(
    		"Call:   +1 123 456 789",
            "Shop:   +1 123 456 789",
            "E-Mail: contact@disneynow.com",
            "Web:    www.disneynow.com");
    
	public static final List<String> FOOTER_PAYMENT = Arrays.asList(
            "Disney Press Inc",
            "Orlando",
            "USA",
            "www.disneynow.com");
    
	public static final List<String> HEADER_DETAILS = Arrays.asList(
            "Creation Date   :",
            "Customer Number :");
	
	public static final List<String> HEADER_INFORMATION = Arrays.asList(
            "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore");
	
	public static final String FOOTER_LINE1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
	
	public static final String FOOTER_LINE2 = "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
	
	
	public static final String HEADER_DATE = "Date";
	public static final String HEADER_INVOICE = "Invoice";
	public static final String HEADER_POSTING_TEXT = "Text";
	public static final String HEADER_DEBIT = "Debit";
	public static final String HEADER_CREDIT = "Credit";
	public static final String HEADER_BALANCE = "Balance";
	
	public static final List<String> HEADER_COLUMNS = Arrays.asList(
			HEADER_DATE,
			HEADER_INVOICE,
			HEADER_POSTING_TEXT,
			HEADER_DEBIT,
			HEADER_CREDIT,
			HEADER_BALANCE);

}
