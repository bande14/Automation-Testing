package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class CreditCard {

	private static final String VISA_3DS_SHEET_NAME = "VISA3DS";
	private static final String VISA_MD_SHEET_NAME = "VISAMD";
	private static final String MC_3DS_SHEET_NAME = "MCMD";
	private static final String MC_MD_SHEET_NAME = "MCMD";
	private static final int FIRST_CARD_ROW = 1;
	private static final int PAN_COLUMN = 0;
	private static final int EXPIRY_COLUMN = 1;
	private static final int CVV_COLUMN = 2;
	private static final int NAME_COLUMN = 3;
	private static final int SURNAME_COLUMN = 4;
	private static final int PAN_LENGTH = 16;
	private static final int CVV_LENGTH = 3;

	public static final String CONF_FILE_NAME = "TestDataProxy.xls";

	private String pan;
	private String name;
	private String surname;
	private String cvv;
	private String expiry;

	public CreditCard(String pathConfigurationFile, String cardType,
			String userAuthentication, String last4digits)
			throws NullPointerException, IllegalArgumentException, IOException {

		if (pathConfigurationFile == null)
			throw new NullPointerException("Path is null.");
		if (cardType == null)
			throw new NullPointerException(
					"Card type is null. Expected VISA or MC.");
		if (userAuthentication == null)
			throw new NullPointerException(
					"User authentication is null. Expected 3DS or MD.");

		if (!pathConfigurationFile.endsWith(CONF_FILE_NAME))
			throw new IllegalArgumentException(
					"Test Configuration file must end with " + CONF_FILE_NAME);

		if (cardType.isEmpty())
			throw new IllegalArgumentException("Card type is empty.");
		if (userAuthentication.isEmpty())
			throw new IllegalArgumentException("User authentication is empty.");

		if (last4digits != null) {
			if (last4digits.isEmpty())
				throw new IllegalArgumentException(
						"Last four digits cannot be empty.");
			if (last4digits.length() != 4)
				throw new IllegalArgumentException(
						"Last four digits length is different than four.");
			try {
				Integer.parseInt(last4digits);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException(
						"Last four digits cannot be converted to a number.");
			}
		}

		String sheetName = null;
		if (cardType.equals("VISA") & userAuthentication.equals("3DS"))
			sheetName = VISA_3DS_SHEET_NAME;
		if (cardType.equals("VISA") & userAuthentication.equals("MD"))
			sheetName = VISA_MD_SHEET_NAME;
		if (cardType.equals("MC") & userAuthentication.equals("3DS"))
			sheetName = MC_3DS_SHEET_NAME;
		if (cardType.equals("MC") & userAuthentication.equals("MD"))
			sheetName = MC_MD_SHEET_NAME;

		if (sheetName == null)
			throw new IllegalArgumentException(cardType + " or "
					+ userAuthentication + " not correct.");

		// open the file to the path, search for the right sheet and extract the
		// card info
		File confFile = new File(pathConfigurationFile);
		if (confFile.exists()) {
			Workbook workbook = null;
			Sheet sheet = null;
			try {
				workbook = Workbook.getWorkbook(confFile);
				if ((sheet = workbook.getSheet(sheetName)) == null) {
					workbook.close();
					throw new NullPointerException("Sheet " + sheetName
							+ " cannot be found within " + CONF_FILE_NAME + ".");
				}
				// sheet found
				System.out.println("Rows: " + sheet.getRows());
				System.out.println("Columns: " + sheet.getColumns());

				// last 4 digits specified - we look for the PAN with it
				if (last4digits != null) {
					Cell panCell = null;
					int cardRow = 0;
					for (int i = 0; i < sheet.getRows(); i++) {
						panCell = sheet.getCell(PAN_COLUMN, i);
						if (panCell.getContents().endsWith(last4digits)) {
							cardRow = panCell.getRow();
							break;
						}
					}
					if (cardRow == 0) {
						workbook.close();
						throw new IllegalArgumentException("Last four digits "
								+ last4digits + " did not match with any card.");
					}

					// card found so we read the info
					this.pan = sheet.getCell(PAN_COLUMN, cardRow).getContents();
					this.expiry = sheet.getCell(EXPIRY_COLUMN, cardRow)
							.getContents();
					this.cvv = sheet.getCell(CVV_COLUMN, cardRow).getContents();
					this.name = sheet.getCell(NAME_COLUMN, cardRow)
							.getContents();
					this.surname = sheet.getCell(SURNAME_COLUMN, cardRow)
							.getContents();

				} else {

					// last 4 digits not specified - we read the first card
					this.pan = sheet.getCell(PAN_COLUMN, FIRST_CARD_ROW)
							.getContents();
					this.expiry = sheet.getCell(EXPIRY_COLUMN, FIRST_CARD_ROW)
							.getContents();
					this.cvv = sheet.getCell(CVV_COLUMN, FIRST_CARD_ROW)
							.getContents();
					this.name = sheet.getCell(NAME_COLUMN, FIRST_CARD_ROW)
							.getContents();
					this.surname = sheet
							.getCell(SURNAME_COLUMN, FIRST_CARD_ROW)
							.getContents();

				}
				// close the workbook
				workbook.close();

				// check about the info just read
				if (!this.checkCardInfo())
					throw new IllegalArgumentException(
							"Card information in the sheet " + sheetName
									+ " is not correct.");

			} catch (IOException e) {
				throw new IOException("File " + pathConfigurationFile
						+ " cannot be read.");
			} catch (BiffException e) {
				throw new IOException("File " + pathConfigurationFile
						+ " cannot be read due to a BiffException.");
			}
		} else
			throw new FileNotFoundException("File " + pathConfigurationFile
					+ " not found.");
	}

	private boolean checkCardInfo() {
		if (this.pan == null | this.expiry == null | this.cvv == null
				| this.name == null | this.surname == null) {
			System.err.println("Some card fields are null.");
			return false;
		}
		if (this.pan.length() != PAN_LENGTH | !isNumber(this.pan)) {
			System.err.println("The PAN is incorrect.");
			return false;
		}
		if(!isValidExpiryDate(this.expiry)){
			System.err.println("The expiry date is incorrect.");
			return false;
		}
		if(this.cvv.length() != CVV_LENGTH | !isNumber(this.cvv)){
			System.err.println("The CVV is incorrect.");
			return false;
		}
		if(isNumber(this.name) | isNumber(this.surname)){
			System.err.println("The name or the surname is a number.");
			return false;
		}
		return true;
	}

	private boolean isNumber(String string) {
		for (int i = 0; i < string.length(); i++) {
			if (!Character.isDigit(string.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private boolean isValidExpiryDate(String expiryDate) {
		DateFormat formatter = new SimpleDateFormat("MM/yyyy");
		formatter.setLenient(false);
		try {
			formatter.parse(expiryDate);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

}
