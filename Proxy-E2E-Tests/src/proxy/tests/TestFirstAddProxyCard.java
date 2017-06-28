package proxy.tests;

import java.lang.invoke.MethodHandles;

import org.junit.BeforeClass;
import org.junit.Test;

import utilities.MyTestCase;
import utilities.TestConfiguration;

public class TestFirstAddProxyCard extends MyTestCase {
	
	private static String testCaseName;
	private static TestConfiguration configuration;

	@BeforeClass
	public static void setUpClient() {
		
		// set the test case name (taken as reflection) and get the configuration
		testCaseName = MethodHandles.lookup().lookupClass().getSimpleName();
		try {
			configuration = new TestConfiguration(projectBaseDirectory + TestConfiguration.CONF_FILE_NAME, testCaseName);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(MyTestCase.ERROR);
		}
		
		//set the report type, location and test name
		client.setReporter("pdf", projectBaseDirectory + "\\reports", testCaseName);
		
		// launch the client
		client.launch("com.vodafone.mwallet/.LandingActivity", true, true);
	}

	@Test
	public void test() {
		if (client.waitForElement("default", "ADD MORE", 0, 10000)) {
			// If statement
		}
	}

}
