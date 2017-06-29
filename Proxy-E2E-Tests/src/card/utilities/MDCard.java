package card.utilities;

import configuration.utilities.CardConfiguration;

public class MDCard extends CreditCard {
	
	private String code;
	private static final String CODE_KEY = "CODE";


	public MDCard(CardConfiguration cardConf) throws IllegalArgumentException {
		super(cardConf);
		if ((this.code = cardConf.GetValue(CODE_KEY)) == null) {
			throw new IllegalArgumentException("MD code not found.");
		}
	}
	
	public String getMDCode () {
		return this.code;
	}

}
