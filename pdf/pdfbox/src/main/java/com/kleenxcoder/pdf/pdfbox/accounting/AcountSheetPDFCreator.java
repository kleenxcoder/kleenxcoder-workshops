package com.kleenxcoder.pdf.pdfbox.accounting;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.HorizontalAlignment;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.page.PageProvider;

/**
 * @author kleenxcoder
 */

public class AcountSheetPDFCreator implements Serializable{
	
	private static final long serialVersionUID = 8546481084193901751L;
	PageProvider<PDPage> pageProvider;
	float lastPostion;
	PDPage currentPage;
	boolean pageNumerationExecuted = false;

	/* Initialize DataTable */
	float margin = 70;
	float tableWidth = 480;
	float yStartNewPage = 885; //850
	float yStart = 540;
	float bottomMargin = 100;
	float pageTopMargin = 100;
	
	public AcountSheetPDFCreator(CustomerSegment segment, Date creationDate, String customerNumber, List<String> customerAddress) throws IOException {
		pageProvider = new AcountSheetPageProvider(segment, creationDate, customerNumber, customerAddress);
	}
	
	public void addAdditionalCustomer(CustomerSegment segment, Date creationDate, String customerNumber, List<String> customerAddress) throws IOException {
		((AcountSheetPageProvider)pageProvider).addNewCustomer(segment, creationDate, customerNumber, customerAddress);
	}
	
	public void populateData(List<String[]> tableData, AccountSheetAdditinalData additionalData, List<String> textBoldRows) throws IOException {
		insertMainTable(tableData, textBoldRows);
		insertMainTableFooter(additionalData);
		insertMainTableBalance(additionalData);
		insertMainTableInformation();
	}

	private void insertMainTable(final List<String[]> data, List<String> textBoldRows) throws IOException {
		PDPage page = pageProvider.createPage();
		BaseTable table = new BaseTable(yStart, yStartNewPage, pageTopMargin, bottomMargin, tableWidth, margin, pageProvider.getDocument(), page, true, true, pageProvider);

		// Create Header row
		Row<PDPage> headerRow = table.createRow(10f);
		
		Cell<PDPage> cell;
		for (String headerColumn : AccountConstants.HEADER_COLUMNS) {
			cell = headerRow.createCell((100 / 6.0f), headerColumn);
			cell.setFont((((AcountSheetPageProvider) pageProvider).getFontArialBold()));
			cell.setFontSize(8);
			cell.setFillColor(new Color(232, 232, 232));
			
			switch (headerColumn) {
			case AccountConstants.HEADER_INVOICE:
				cell.setAlign(HorizontalAlignment.LEFT);
				break;
			case AccountConstants.HEADER_POSTING_TEXT:
				cell.setAlign(HorizontalAlignment.LEFT);
				break;
			default:
				cell.setAlign(HorizontalAlignment.CENTER);
				break;
			}
		}
		
		table.addHeaderRow(headerRow);
		
		// Insert data rows
		for (String[] dataRow : data) {
			Row<PDPage> row = table.createRow(10f);

			for (int i = 0; i < dataRow.length; i++) {
				if (dataRow.length == 1) {
					cell = row.createCell((100), dataRow[i]);
				} else {
					cell = row.createCell((100 / 6.0f), dataRow[i]);
				}
				
				
				if( textBoldRows.contains(dataRow[2].trim()) ) {
					cell.setFont(((AcountSheetPageProvider) pageProvider).getFontArialBold());
				} else {
					cell.setFont(((AcountSheetPageProvider) pageProvider).getFontArial());
				}
				
				cell.setFontSize(8);
				
				switch (i) {
				case 0:
					cell.setAlign(HorizontalAlignment.CENTER);
					break;
				case 1:
					cell.setAlign(HorizontalAlignment.LEFT);
					break;
				case 2:
					cell.setAlign(HorizontalAlignment.LEFT);
					break;
				default:
					cell.setAlign(HorizontalAlignment.RIGHT);
					break;
				}
				
				cell.setTopPadding(0f);
				cell.setBottomPadding(0f);
			}
		}
		lastPostion = table.draw();
		currentPage = table.getCurrentPage();
	}

	private void insertMainTableFooter(AccountSheetAdditinalData additionalData) throws IOException {
		createNewPageIfNeeded(50f);
		BaseTable table = new BaseTable((lastPostion -10 ), yStartNewPage, pageTopMargin, bottomMargin, tableWidth, AcountSheetPageProvider.LEFT_MARGIN + (tableWidth / 6 * 3), pageProvider.getDocument(), currentPage, true, true, pageProvider);
		Row<PDPage> row = table.createRow(10f);
		createCell(row, HorizontalAlignment.RIGHT, (100 / 6.0f), additionalData.getDebit());
		createCell(row, HorizontalAlignment.RIGHT, (100 / 6.0f), additionalData.getCredit());
		createCell(row, HorizontalAlignment.RIGHT, (100 / 6.0f), additionalData.getBalance());

		lastPostion = table.draw();
		currentPage = table.getCurrentPage();
	}

	private void createCell(Row<PDPage> row, HorizontalAlignment alignment, float width, String value) {
		Cell<PDPage> cell;
		cell = row.createCell((100 / 6.0f), value);
		cell.setFont(((AcountSheetPageProvider) pageProvider).getFontArial());
		cell.setFontSize(10);
		cell.setAlign(alignment);
	}

	private void createNewPageIfNeeded(float spaceNeeded) {
		if (lastPostion - spaceNeeded < 100) {
			currentPage = pageProvider.createPage();
			lastPostion = 745;
		}
	}
	
	private void insertMainTableBalance(AccountSheetAdditinalData additionalData) throws IOException {
		float spaceNeeded = 50f;
		createNewPageIfNeeded(spaceNeeded);
            // balance
            try (PDPageContentStream contents = new PDPageContentStream(pageProvider.getDocument(), currentPage, AppendMode.APPEND, true)) {
                // text
                contents.beginText();
                contents.setFont(((AcountSheetPageProvider) pageProvider).getFontArialBold(), 12);
                contents.newLineAtOffset(AcountSheetPageProvider.LEFT_MARGIN, lastPostion -20);
                contents.showText("Balance:");
                contents.endText();
                // amount
                contents.beginText();
                contents.setFont(((AcountSheetPageProvider) pageProvider).getFontArial(), 12);
                contents.newLineAtOffset(AcountSheetPageProvider.LEFT_MARGIN + 90f, lastPostion - 20);
                contents.showText(additionalData.getBalance() + " Dollar*");
                contents.endText();
            }
		lastPostion -= spaceNeeded;
	}
	
	private void insertMainTableInformation() throws IOException {
		float spaceNeeded = 50f;
		createNewPageIfNeeded(spaceNeeded);
		
            try (PDPageContentStream contents = new PDPageContentStream(pageProvider.getDocument(), currentPage, AppendMode.APPEND, true)) {
                contents.beginText();
                contents.setFont(((AcountSheetPageProvider) pageProvider).getFontArial(), 7);
                contents.newLineAtOffset(AcountSheetPageProvider.LEFT_MARGIN, lastPostion);
                contents.showText(AccountConstants.FOOTER_LINE1);
                contents.endText();
                
                contents.beginText();
                contents.setFont(((AcountSheetPageProvider) pageProvider).getFontArial(), 7);
                contents.newLineAtOffset(AcountSheetPageProvider.LEFT_MARGIN, lastPostion- 10);
                contents.showText(AccountConstants.FOOTER_LINE2);
                contents.endText();
            }
		lastPostion -= spaceNeeded;
	}

	public void save(String fileName) throws IOException {
		insertPageNumeration();
		pageProvider.getDocument().save(fileName);
		pageProvider.getDocument().close();
	}
	
	private void insertPageNumeration() throws IOException {
		if (!pageNumerationExecuted) {
			((AcountSheetPageProvider) pageProvider).insertPageNumeration();
			pageNumerationExecuted = true;
		}
	}

	public ByteArrayOutputStream getByteArrayOutputStream() throws IOException {
		insertPageNumeration();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			pageProvider.getDocument().save(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pageProvider.getDocument().close();
		}
		return out;
	}

}