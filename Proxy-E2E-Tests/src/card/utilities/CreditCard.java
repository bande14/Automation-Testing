package card.utilities;

public class CreditCard {
	
	private String pan;
	private String name;
	private String surname;
	private String cvv;
	private String expiry;
	
	public CreditCard (){
		
	}
	
	public String getPan (){
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

}
