package com.kleenxcoder.pdf.pdfbox.accounting;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import be.quodlibet.boxable.page.PageProvider;

/**
 * @author kleenxcoder
 */

public class AcountSheetPageProvider implements PageProvider<PDPage> {

    PDDocument     document;
    PDPage         page;
    PDImageXObject pdfImageLogo;
    PDFont         fontArial;
    PDFont         fontArialBold;

    boolean firstPage = true;

    private static final String IMAGE_LOGO = "images/logo.jpg";

    private static final String FONT_ARIAL_TTF = "fonts/arial.ttf";
    private static final String FONT_ARIAL_TTF_BOLD = "fonts/arialbd.ttf";

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    private static List<String> headerContact;
    
    private List<String> letterDetails;
    private List<String> customerAddress;

    public static final int LEFT_MARGIN = 70;
    public static final int RIGHT_MARGIN = 550;
    private static final String PAGE_NUMBERING = "Page %s / %s";

    private boolean initializeImages = true;
    private boolean initializeFonts = true;

    private static final float FONT_SIZE_8 = 8;
    private static final float FONT_SIZE_10 = 10;
    private static final float FONT_SIZE_11 = 11;
    private static final float FONT_SPACE_BETWEEN_LINES_10 = 10;
    private static final float FONT_SPACE_BETWEEN_LINES_12 = 12;
    private static final float FONT_SPACE_BETWEEN_LINES_13 = 13;

    public AcountSheetPageProvider(CustomerSegment segment, Date creationDate, String customerNumber, List<String> customerAddress) throws IOException {
        document = new PDDocument();
        initialize(segment, creationDate, customerNumber, customerAddress);
    }

    public void addNewCustomer(CustomerSegment segment, Date creationDate, String customerNumber, List<String> customerAddress) throws IOException {
        firstPage = true;
        initialize(segment, creationDate, customerNumber, customerAddress);
    }

    private void initialize(CustomerSegment segment, Date creationDate, String customerNumber, List<String> customerAddress) throws IOException {
        letterDetails = Arrays.asList(SIMPLE_DATE_FORMAT.format(creationDate), customerNumber);
        this.customerAddress = customerAddress;

        if (initializeFonts) {
            synchronized (AcountSheetPageProvider.class) {
                fontArial = PDType0Font.load(document, new File(AcountSheetPageProvider.class.getClassLoader().getResource(FONT_ARIAL_TTF).getFile()));
                fontArialBold = PDType0Font.load(document, new File(AcountSheetPageProvider.class.getClassLoader().getResource(FONT_ARIAL_TTF_BOLD).getFile()));
                initializeFonts = false;
            }
        }

        if (initializeImages) {
            synchronized (AcountSheetPageProvider.class) {
                pdfImageLogo = PDImageXObject.createFromFile(AcountSheetPageProvider.class.getClassLoader().getResource(IMAGE_LOGO).getFile(), document);
                initializeImages = false;
            }
        }

        switch (segment) {
            case BUSINESS:
                headerContact = AccountConstants.HEADER_BUSINESS;
                break;
            case RESIDENTIAL:
                headerContact = AccountConstants.HEADER_RESIDENTIAL;
                break;
        }
    }

    private void insertHeader(PDPage blankPage) throws IOException {
    	// logo position
    	float posY = 753;
    	float headerScale = 0.15f;
    	float logoScale = 0.160f;
    	float logoPosY = posY - ((pdfImageLogo.getHeight() * logoScale) - (302 * headerScale));
    	
        try (PDPageContentStream contents = new PDPageContentStream(document, blankPage, AppendMode.APPEND, true)) {
            //draw company logo
            contents.drawImage(pdfImageLogo, RIGHT_MARGIN - 67, logoPosY, pdfImageLogo.getWidth() * logoScale, pdfImageLogo.getHeight() * logoScale);
            
            // heading
            writeMultiLineTextAligned(contents, fontArial, ObjectAlignment.LEFT, 36, 0, LEFT_MARGIN, 745, AccountConstants.HEADER_KONTOBLATT);
            
            // heading address
            writeMultiLineTextAligned(contents, fontArial, ObjectAlignment.LEFT, FONT_SIZE_11, FONT_SPACE_BETWEEN_LINES_13, LEFT_MARGIN, 684, customerAddress);
            
            // heading details
            writeMultiLineTextAligned(contents, fontArial, ObjectAlignment.LEFT, FONT_SIZE_11, FONT_SPACE_BETWEEN_LINES_13, LEFT_MARGIN, 615, AccountConstants.HEADER_DETAILS);
            writeMultiLineTextAligned(contents, fontArial, ObjectAlignment.LEFT, FONT_SIZE_11, FONT_SPACE_BETWEEN_LINES_13, LEFT_MARGIN + 120, 615, letterDetails);
            
            // heading information
            writeMultiLineTextAligned(contents, fontArial, ObjectAlignment.LEFT, FONT_SIZE_11, FONT_SPACE_BETWEEN_LINES_13, LEFT_MARGIN, 575, AccountConstants.HEADER_INFORMATION);
            
            //heading content
            writeMultiLineTextAligned(contents, fontArial, ObjectAlignment.RIGHT, FONT_SIZE_10, FONT_SPACE_BETWEEN_LINES_13, RIGHT_MARGIN - 5, 684, headerContact);
        }
    }

    private void writeMultiLineTextAligned(PDPageContentStream contents, PDFont font, ObjectAlignment alignment, float fontSize, float fontSpaceBetweenLines,
            float fontPosX, float fontPosY, List<String> text) throws IOException {
        for (int i = 0; i < text.size(); i++) {
            float text_width = (font.getStringWidth(text.get(i)) / 1000.0f) * fontSize;

            contents.beginText();
            contents.setFont(font, fontSize);
            switch (alignment) {
                case LEFT:
                    contents.newLineAtOffset(fontPosX, fontPosY - i * fontSpaceBetweenLines);
                    contents.showText(StringUtils.defaultString(text.get(i), ""));
                    break;
                case RIGHT:
                    contents.newLineAtOffset(fontPosX - text_width, fontPosY - i * fontSpaceBetweenLines);
                    contents.showText((text.get(i)));
                    break;
            }
            contents.endText();
        }
    }

    private void insertFooter(PDPage blankPage) throws IOException {
        //footer Payment Data
        try (PDPageContentStream contents = new PDPageContentStream(document, blankPage, AppendMode.APPEND, true)) {
            //footer Payment Data
            writeMultiLineTextAligned(contents, fontArialBold, ObjectAlignment.LEFT, FONT_SIZE_8, FONT_SPACE_BETWEEN_LINES_10, LEFT_MARGIN, 75, AccountConstants.FOOTER_PAYMENT);
        }
    }

    @Override
    public PDPage createPage() {
        PDPage newPage = new PDPage();
        newPage.setMediaBox(new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight()));
        document.addPage(newPage);
        this.page = newPage;

        try {
            if (firstPage) {
                insertHeader(newPage);
                firstPage = false;
            }
            insertFooter(newPage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newPage;
    }

    @Override
    public PDPage nextPage() {
        return createPage();
    }

    @Override
    public PDPage previousPage() {
    	throw new RuntimeException("not jey implemented");
    }

    @Override
    public PDDocument getDocument() {
        return document;
    }

    public PDFont getFontArial() {
        return fontArial;
    }

    public PDFont getFontArialBold() {
        return fontArialBold;
    }

    public void insertPageNumeration() throws IOException {
        PDPageContentStream contents = null;
        for (int pageIndex = 0; pageIndex < document.getNumberOfPages(); pageIndex++) {
            try {
                String pageText = getFormatedPageNumber(pageIndex, document.getNumberOfPages());
                contents = new PDPageContentStream(document, document.getPage(pageIndex), AppendMode.APPEND, true);
                writeMultiLineTextAligned(contents, fontArial, ObjectAlignment.RIGHT, FONT_SIZE_10, FONT_SPACE_BETWEEN_LINES_12, RIGHT_MARGIN -1, 45, Arrays.asList(pageText));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (contents != null)
                  contents.close();
            }
        }
    }

    private String getFormatedPageNumber(int pageIndex, int numberOfPages) {
        return String.format(PAGE_NUMBERING, ++pageIndex, numberOfPages);
    }

}
