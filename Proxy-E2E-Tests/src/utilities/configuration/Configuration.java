package utilities.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Configuration {
	
	private HashMap<String, String> configuration;

	public static final String CONF_FILE_NAME = "TestDataProxy.xls";
	private static final int KEY_ROW = 0;
	
	public Configuration (String pathConfigurationFile, String sheetName, String key, String value)
			throws NullPointerException, IllegalArgumentException, IOException {

		if (pathConfigurationFile == null)
			throw new NullPointerException("Configuration path is null.");
		if (sheetName == null)
			throw new NullPointerException("Configuration sheet is null.");
		if (key == null)
			throw new NullPointerException("Configuration key is null.");
		if (value == null)
			throw new NullPointerException("Configuration value is null.");
		
		if (!pathConfigurationFile.endsWith(CONF_FILE_NAME))
			throw new IllegalArgumentException(
					"Configuration file must end with " + CONF_FILE_NAME);
		
		// open the file to the path, search for the right sheet and extract the
		// configuration
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
				// sheet found, going to read the row with the value for the key
				// matching the one in input
				System.out.println("Rows: " + sheet.getRows());
				System.out.println("Columns: " + sheet.getColumns());
				
				Cell keyCell = null, valueCell = null;
				int keyColumn = 0, valueRow = 0;
				
				// look for the key in the first row till it finds it or null
				if ((keyCell = sheet.findCell(key,
						0, KEY_ROW,
						sheet.getColumns()-1, KEY_ROW, false)) != null) {
					// key found; looking now for the value
					keyColumn = keyCell.getColumn();
					if ((valueCell = sheet.findCell(value,
							keyColumn, 0,
							keyColumn, sheet.getRows()-1, false)) != null) {
						// value found
						valueRow = valueCell.getRow();
						this.configuration = new HashMap<String, String>();
						for (int i = 0; i < sheet.getColumns(); i++) {
							// the storing happens as <key, value> pair
							this.configuration.put(sheet.getCell(i, KEY_ROW)
									.getContents(), sheet.getCell(i, valueRow)
									.getContents());
						}
					} else {
						workbook.close();
						throw new IllegalArgumentException(
								"Not possible to find any cell with the value "
										+ value);
					}	
				} else {
					workbook.close();
					throw new IllegalArgumentException(
							"Not possible to find any cell with the key "
									+ key);
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
	
	public String GetValue (String key) throws NullPointerException{
		if (key == null)
			throw new NullPointerException("The key is null.");
		return this.configuration.get(key);
	}


}
