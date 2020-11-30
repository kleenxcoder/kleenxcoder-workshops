package com.kleenxcoder.pdf.pdfbox.accounting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kleenxcoder
 */

@Slf4j
public class AcountSheetPDFCreatorTest {

	private static final String BUSINESS_FILENAME = "accountSheet_business.pdf";
	private static final String BUSINESS_MULTI_FILENAME = "accountSheet_business_multiple_accounts.pdf";
	private static final String BUSINESS_MULTI_MULTI_FILENAME = "accountSheet_business_multiple_accounts_multiple_sheet.pdf";
	private static final String RESIDENTIAL_FILENAME = "accountSheet_residential.pdf";
	private static final List<String> BOLD_ROW_TXT = Arrays.asList("Deposit", "Debit", "Derecognition");

	@Test
	public void createExampleResidentialPDF() throws IOException {
		CustomerSegment segment = CustomerSegment.RESIDENTIAL;
		AcountSheetPDFCreator pdfCreator = new AcountSheetPDFCreator(segment, new Date(), "12345678901",
				getCustomerAddress(segment));
		pdfCreator.populateData(getTestData(), getAdditionalData(), BOLD_ROW_TXT);
		pdfCreator.save(RESIDENTIAL_FILENAME);
	}

	@Test
	public void createExampleBusinessPDF() throws IOException {
		log.debug("createExampleBusinessPDF ...");
		CustomerSegment segment = CustomerSegment.BUSINESS;
		AcountSheetPDFCreator pdfCreator = new AcountSheetPDFCreator(segment, new Date(), "12345678901",
				getCustomerAddress(segment));
		pdfCreator.populateData(getTestData(), getAdditionalData(), BOLD_ROW_TXT);
		pdfCreator.save(BUSINESS_FILENAME);
	}

	@Test
	public void createExampleBusinessMultiSitePDF() throws IOException {
		log.debug("createExampleBusinessMultiSitePDF ...");
		CustomerSegment segment = CustomerSegment.BUSINESS;
		AcountSheetPDFCreator pdfCreator = new AcountSheetPDFCreator(segment, new Date(), "12345678901",
				getCustomerAddress(segment));
		pdfCreator.populateData(getTestData(), getAdditionalData(), BOLD_ROW_TXT);
		segment = CustomerSegment.BUSINESS;
		pdfCreator.addAdditionalCustomer(segment, new Date(), "12345678901", getCustomerAddress(segment));
		pdfCreator.populateData(getTestData(), getAdditionalData(), BOLD_ROW_TXT);
		segment = CustomerSegment.BUSINESS;
		pdfCreator.addAdditionalCustomer(segment, new Date(), "12345678901", getCustomerAddress(segment));
		pdfCreator.populateData(getTestData(), getAdditionalData(), BOLD_ROW_TXT);
		pdfCreator.save(BUSINESS_MULTI_FILENAME);
	}

	@Test
	public void createExampleBusinessMultiSitePDFMultipleTimes() throws IOException {
		log.debug("createExampleBusinessMultiSitePDFMultipleTimes ...");
		AcountSheetPDFCreator pdfCreator = null;
		for (int i = 0; i < 2; i++) {
			CustomerSegment segment = CustomerSegment.BUSINESS;

			try {
				pdfCreator = new AcountSheetPDFCreator(segment, new Date(), "12345678901", getCustomerAddress(segment));
				pdfCreator.populateData(getTestData(), getAdditionalData(), BOLD_ROW_TXT);
				for (int x = 0; x < 10; x++) {
					pdfCreator.addAdditionalCustomer(segment, new Date(), "12345678901", getCustomerAddress(segment));
					pdfCreator.populateData(getTestData(), getAdditionalData(), BOLD_ROW_TXT);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			pdfCreator.save(BUSINESS_MULTI_MULTI_FILENAME);
		}
	}

	private static List<String[]> getTestData() {
		List<String[]> data = new ArrayList<>();

		// Make the table a bit bigger
		for (int i = 0; i < 50; i++) {
			data.addAll(getOneDataSet());
		}

		return data;
	}

	private static List<String[]> getOneDataSet() {
		List<String[]> dataSet = new ArrayList<>();
		dataSet.add(new String[] { "01.07.2000", "12345678901", "Invoice", "22,44", "", "-22,44" });
		dataSet.add(new String[] { "10.08.2000", "", "Payment Batch", "", "22,44", "0,00" });

		return dataSet;
	}

	private static AccountSheetAdditinalData getAdditionalData() {
		AccountSheetAdditinalData additinalData = new AccountSheetAdditinalData();
		additinalData.setCredit("111.00");
		additinalData.setDebit("222.00");
		additinalData.setBalance("-111.00");
		return additinalData;
	}

	private List<String> getCustomerAddress(CustomerSegment segment) {
		List<String> address = new ArrayList<>();

		switch (segment) {
		case RESIDENTIAL:
			address.add("Mrs.");
			address.add("Daisy Duck");
			address.add("Duck Street 1");
			address.add("Duckburg");
			break;

		case BUSINESS:
			address.add("Duck Inc.");
			address.add("Donald Duck");
			address.add("Duck Street 1");
			address.add("Duckburg");
			break;

		}

		return address;
	}

}
