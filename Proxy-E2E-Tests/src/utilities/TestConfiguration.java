package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class TestConfiguration {

	private HashMap<String, String> testConfiguration;
	private String testCasename;

	public static final String CONF_SHEET_NAME = "TestConf";
	public static final String CONF_FILE_NAME = "TestDataProxy.xls";

	public static final String CARD_TYPE = "CardType";
	public static final String CARD_POSITION = "CardPosition";
	public static final String USER_AUTHENTICATION = "UserAuthentication";
	public static final String FRAUD_STATUS = "FraudStatus";

	private static final int FIRST_COLUMN_WITH_DATA = 1;
	private static final int COLUMN_WITH_TESTCASE_NAME = 0;
	private static final int FIRST_ROW = 0;

	public TestConfiguration(String pathConfigurationFile, String testCaseName)
			throws NullPointerException, IllegalArgumentException, IOException {

		if (pathConfigurationFile == null)
			throw new NullPointerException("Path is null.");
		if (testCaseName == null)
			throw new NullPointerException("Test case name is null.");

		if (!pathConfigurationFile.endsWith(CONF_FILE_NAME))
			throw new IllegalArgumentException(
					"Test Configuration file must end with " + CONF_FILE_NAME);
		if (testCaseName.isEmpty())
			throw new IllegalArgumentException("Test case name is empty.");
		this.testCasename = testCaseName;

		// open the file to the path, search for the right sheet and extract the
		// test configuration
		File confFile = new File(pathConfigurationFile);
		if (confFile.exists()) {
			this.testConfiguration = new HashMap<String, String>();
			Workbook workbook = null;
			Sheet sheet = null;
			try {
				workbook = Workbook.getWorkbook(confFile);
				if ((sheet = workbook.getSheet(CONF_SHEET_NAME)) == null) {
					workbook.close();
					throw new NullPointerException("Sheet " + CONF_SHEET_NAME
							+ " cannot be found within " + CONF_FILE_NAME + ".");
				}
				// sheet found, going to read the row with the test case name
				// matching the one in input
				System.out.println("Rows: " + sheet.getRows());
				System.out.println("Columns: " + sheet.getColumns());
				Cell testCaseNameCell = null;
				int testCaseRow = 0;
				// look for the test case name in all rows and only the first
				// column till it finds it or null
				if ((testCaseNameCell = sheet.findCell(this.testCasename,
						COLUMN_WITH_TESTCASE_NAME, FIRST_ROW,
						COLUMN_WITH_TESTCASE_NAME, sheet.getRows() - 1, false)) != null) {
					// test case found; storing all properties in the
					// configuration
					testCaseRow = testCaseNameCell.getRow();
					for (int i = FIRST_COLUMN_WITH_DATA; i < sheet.getColumns(); i++) {
						// the storing happens as <key, value> pair
						this.testConfiguration.put(sheet.getCell(i, FIRST_ROW)
								.getContents(), sheet.getCell(i, testCaseRow)
								.getContents());
					}
				} else {
					workbook.close();
					throw new IllegalArgumentException(
							"Not possible to find any cell with test case name "
									+ this.testCasename);
				}
				// close the workbook
				workbook.close();
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

	public String getValue(String key) throws NullPointerException {
		
		if (key == null)
			throw new NullPointerException("The key is null.");
		
		String configuration = this.testConfiguration.get(key);
		
		switch (key) {
		case CARD_TYPE:
			if (!configuration.equals("VISA") & !configuration.equals("MC")
					& !configuration.equals("PP"))
				configuration = null;
			break;
		case CARD_POSITION:
			if (!	   (configuration.equals("01")|configuration.equals("1")) 
					& !(configuration.equals("02")|configuration.equals("2"))
					& !(configuration.equals("03")|configuration.equals("3"))
					& !(configuration.equals("04")|configuration.equals("4"))
					& !(configuration.equals("05")|configuration.equals("5")))
				configuration = null;
			break;
		case USER_AUTHENTICATION:
			if (!configuration.equals("3DS") & !configuration.equals("MD")
					& !configuration.equals("PP"))
				configuration = null;
			break;
		case FRAUD_STATUS:
			if (!configuration.equals("ON") & !configuration.equals("OFF"))
				configuration = null;
			break;
		default:
			break;
		}
		
		return configuration;
	}

}
