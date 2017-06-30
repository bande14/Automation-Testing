package utilities.configuration;

import java.io.IOException;

public class TestConfiguration extends Configuration {
	
	public static final String CONF_SHEET = "TESTCONF";
	public static final String TEST_CASE_NAME_KEY = "TESTCASENAME";

	public TestConfiguration(String pathConfigurationFile, String sheetName,
			String key, String value) throws NullPointerException,
			IllegalArgumentException, IOException {
		super(pathConfigurationFile, sheetName, key, value);
		// TODO Auto-generated constructor stub
	}

}
