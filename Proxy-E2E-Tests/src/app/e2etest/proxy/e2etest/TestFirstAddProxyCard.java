package app.e2etest.proxy.e2etest;

import java.lang.invoke.MethodHandles;

import org.junit.BeforeClass;
import org.junit.Test;

import utilities.MyTestCase;
import utilities.configuration.TestConfiguration;
import screen.proxy.KYC2Form;
import screen.proxy.ProxyRegistration;
import screen.wallet.*;

public class TestFirstAddProxyCard extends MyTestCase {
	
	private static String testCaseName;
	private static TestConfiguration configuration;

	@BeforeClass
	public static void setUpClient() {
		
		// set the test case name (taken as reflection) and get the configuration
		testCaseName = MethodHandles.lookup().lookupClass().getSimpleName();
		try {
			configuration = new TestConfiguration(projectBaseDirectory + TestConfiguration.CONF_FILE_NAME, TestConfiguration.CONF_SHEET, TestConfiguration.TEST_CASE_NAME_KEY, testCaseName);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(MyTestCase.ERROR);
		}
		
		//set the report type, location and test name
		client.setReporter("pdf", projectBaseDirectory + "\\reports", testCaseName);
		
	}

	@Test
	public void test() {
		//launch the wallet
		HomeScreen.launch(client);
		if(!HomeScreen.homeScreenLaunched(client)){
			//either report error or we register the wallet
		}
		//check the homescreen - please note this is only an example. 
		// Checks about the homescreen should be done in wallet test cases and not so relevant for proxy.
		// Btw it does not hurt
		HomeScreen.checkforhomescreen(client, configuration);
		//click addCard
		HomeScreen.clickAddProxy(client);
		//MLD4 form to fill
		if(!KYC2Form.checkKYC2Form(client)){
			//report error
		}
		KYC2Form.fillForm(client, configuration);
		if(!KYC2Form.checkFilledForm(client)){
			//report error
		}
		//continue the registration
		ProxyRegistration.registerProxy(client, configuration);
		//etc
	}

}
