package screen;

import utilities.MyConfigurableClient;

public interface AppScreen {
	
	// list of operations to be performed
	public static final String CLICK = "click";
	public static final String LAUNCH = "launch";
	
	public void performOperation (String operation, String object, MyConfigurableClient client) throws NullPointerException;
	
	public void checkscreen ();

}
