package screen.wallet;

import screen.AppScreen;
import utilities.MyConfigurableClient;

public class InitScreen implements AppScreen{
	
	//list of objects in the screen - probably to be added manually...
	public static final String WALLET = "com.vodafone.mwallet/.LandingActivity";

	@Override
	public void performOperation(String operation, String object,
			MyConfigurableClient client) throws NullPointerException {
		// TODO Auto-generated method stub
		if(operation == null)
			throw new NullPointerException("Operation is null");
		if(object == null)
			throw new NullPointerException("Object is null");
		if(client == null)
			throw new NullPointerException("Client is null");
		
		if(operation.equals(LAUNCH) & object.equals(WALLET)){
			client.launch(WALLET, true, true);
		}
	}

	@Override
	public void checkscreen() {
		// TODO Auto-generated method stub
	}

}
