package proxy.e2etest;

import java.lang.invoke.MethodHandles;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.org.apache.xml.internal.security.Init;

import utilities.MyTestCase;
import utilities.configuration.TestConfiguration;
import screen.AppScreen;
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
		InitScreen init = new InitScreen();
		init.performOperation(AppScreen.LAUNCH, InitScreen.WALLET, client);
		// jumps in home screen
		HomeScreen home = new HomeScreen();
		home.checkscreen();
		home.performOperation(AppScreen.CLICK, HomeScreen.ADD_PROXY_CARD, client);
	}

}
