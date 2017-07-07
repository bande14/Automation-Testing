package screen.wallet;

import screen.AppScreen;
import utilities.MyConfigurableClient;

public class HomeScreen implements AppScreen {

	// list of objects in the screen - probably to be added manually...
	public static final String ADD_PROXY_CARD = "";

	@Override
	public void performOperation(String operation, String object,
			MyConfigurableClient client) throws NullPointerException {
		if (operation == null)
			throw new NullPointerException("Operation is null");
		if (object == null)
			throw new NullPointerException("Object is null");
		if (client == null)
			throw new NullPointerException("Client is null");

	}

	@Override
	public void checkscreen() {
		// TODO Auto-generated method stub

	}

}
