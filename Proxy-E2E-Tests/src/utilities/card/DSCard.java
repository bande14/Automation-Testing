package utilities.card;

import utilities.configuration.CardConfiguration;

public class DSCard extends CreditCard{
	
	private String username;
	private static final String USERNAME_KEY = "USERNAME";

	private String password;
	private static final String PASSWORD_KEY = "PASSWORD";

	public DSCard(CardConfiguration cardConf) throws IllegalArgumentException {
		super(cardConf);
		if ((this.username = cardConf.GetValue(USERNAME_KEY)) == null) {
			throw new IllegalArgumentException("3DS card username not found.");
		}
		if ((this.password = cardConf.GetValue(PASSWORD_KEY)) == null) {
			throw new IllegalArgumentException("3DS card password not found.");
		}
	}
	
	public String get3DSUsername () {
		return this.username;
	}
	
	public String getP3DSPassword () {
		return this.password;
	}

}
