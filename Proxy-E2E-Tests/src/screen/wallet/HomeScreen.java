package screen.wallet;

import utilities.MyConfigurableClient;
import utilities.configuration.TestConfiguration;

public class HomeScreen {

	// list of objects in the screen - probably to be added manually...
	private static final String WALLET = "com.vodafone.mwallet/.LandingActivity";
	private static final String nativeZone = "NATIVE";
	private static final String xpathTabLoyalty = "xpath=//*[@text='LOYALTY']";
	private static final String xpathTabTickets = "xpath=//*[@text='TICKETS' and @width>0]";


	public static void launch (MyConfigurableClient client) {
		client.launch(WALLET, true, true);
	}
	
	public static boolean homeScreenLaunched (MyConfigurableClient client) {
		if (client.waitForElement(nativeZone, xpathTabLoyalty, 0, 1000)
				|| client.waitForElement(nativeZone, xpathTabTickets, 0, 1000)){
		}
		return true;
	}
	
	public static void clickAddProxy(MyConfigurableClient client){
		
	}
	
	public static boolean checkforhomescreen(MyConfigurableClient client, TestConfiguration configuration) {
		//conf should be the one for wallet so we should extend utilities to have the conf of the wallet read and used
		return true;
	}

}
