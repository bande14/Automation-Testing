package utilities.card;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import utilities.configuration.CardConfiguration;

public class CreditCard {
	
	private String pan;
	private static final String PAN_KEY = "PAN";
	
	private String name;
	private static final String NAME_KEY = "NAME";
	
	private String surname;
	private static final String SURNAME_KEY = "SURNAME";
	
	private String cvv;
	private static final String CVV_KEY = "CVV";
	
	private String expiry;
	private static final String EXPIRY_KEY = "EXPIRY";
	
	private static final int PAN_LENGTH = 16;
	private static final int CVV_LENGTH = 3;

	
	public CreditCard (CardConfiguration cardConf) throws NullPointerException, IllegalArgumentException {
		if (cardConf == null)
			throw new NullPointerException("Card configuration is null.");
		if ((this.pan = cardConf.GetValue(PAN_KEY)) == null) {
			throw new IllegalArgumentException("Card PAN not found.");
		}
		if ((this.name = cardConf.GetValue(NAME_KEY)) == null) {
			throw new IllegalArgumentException("Card name not found.");
		}
		if ((this.surname = cardConf.GetValue(SURNAME_KEY)) == null) {
			throw new IllegalArgumentException("Card surname not found.");
		}
		if ((this.cvv = cardConf.GetValue(CVV_KEY)) == null) {
			throw new IllegalArgumentException("Card CVV not found.");
		}
		if ((this.expiry = cardConf.GetValue(EXPIRY_KEY)) == null) {
			throw new IllegalArgumentException("Card expiry not found.");
		}
		// check about the info
		if (!this.checkCardInfo())
			throw new IllegalArgumentException("Some card information are not correct.");
	}
	
	public String getPan () {
		return this.pan;
	}
	
	public String getExpiryDate(){
		return this.expiry;
	}

	public String getCvv(){
		return this.cvv;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getSurname(){
		return this.surname;
	}
	
	private boolean checkCardInfo() {
		if (this.pan == null | this.expiry == null | this.cvv == null
				| this.name == null | this.surname == null) {
			System.err.println("Some card fields are null.");
			return false;
		}
		if (this.pan.length() != PAN_LENGTH | !isNumber(this.pan)) {
			System.err.println("The PAN is incorrect.");
			return false;
		}
		if(!isValidExpiryDate(this.expiry)){
			System.err.println("The expiry date is incorrect.");
			return false;
		}
		if(this.cvv.length() != CVV_LENGTH | !isNumber(this.cvv)){
			System.err.println("The CVV is incorrect.");
			return false;
		}
		if(isNumber(this.name) | isNumber(this.surname)){
			System.err.println("The name or the surname is a number.");
			return false;
		}
		return true;
	}

	private boolean isNumber(String string) {
		for (int i = 0; i < string.length(); i++) {
			if (!Character.isDigit(string.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private boolean isValidExpiryDate(String expiryDate) {
		DateFormat formatter = new SimpleDateFormat("MM/yyyy");
		formatter.setLenient(false);
		try {
			formatter.parse(expiryDate);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

}
