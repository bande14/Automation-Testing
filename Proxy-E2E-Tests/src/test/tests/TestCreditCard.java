package test.tests;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import utilities.CreditCard;

@RunWith(Parameterized.class)
public class TestCreditCard {

	protected CreditCard card;

	// input to validate
	@Parameter(0)
	public String pathConfigurationFile;
	@Parameter(1)
	public String cardType;
	@Parameter(2)
	public String userAuth;
	@Parameter(3)
	public String last4Digits;

	// expected output
	@Parameter(4)
	public Class<? extends Exception> expectedException;
	@Parameter(5)
	public String expectedMessage;

	// rule for exception
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// parameters to set to the input and expected outcome
	@Parameters(name = "TestCreditCard with {0}, {1}, {2} and {3} = {4}, {5}")
	public static Iterable<Object[]> data() {
		return Arrays
				.asList(new Object[][] {
						// negative test cases
						{ null, null, null, null, NullPointerException.class,
								"Path is null." },
						{ "C:", null, null, null, NullPointerException.class,
								"Card type is null. Expected VISA or MC." },
						{ "C:", "cardType", null, null,
								NullPointerException.class,
								"User authentication is null. Expected 3DS or MD." },
						{
								"C:",
								"cardType",
								"3DS",
								null,
								IllegalArgumentException.class,
								"Test Configuration file must end with "
										+ CreditCard.CONF_FILE_NAME },
						{
								System.getProperty("user.dir") + "\\"
										+ CreditCard.CONF_FILE_NAME, "", "3DS",
								null, IllegalArgumentException.class,
								"Card type is empty." },
						{
								System.getProperty("user.dir") + "\\"
										+ CreditCard.CONF_FILE_NAME, "VISA",
								"", null, IllegalArgumentException.class,
								"User authentication is empty." },
						{
								System.getProperty("user.dir") + "\\"
										+ CreditCard.CONF_FILE_NAME, "VISA",
								"3DS", "", IllegalArgumentException.class,
								"Last four digits cannot be empty." },
						{
								System.getProperty("user.dir") + "\\"
										+ CreditCard.CONF_FILE_NAME, "VISA",
								"3DS", "23", IllegalArgumentException.class,
								"Last four digits length is different than four." },
						{
								System.getProperty("user.dir") + "\\"
										+ CreditCard.CONF_FILE_NAME, "VISA",
								"3DS", "12be", IllegalArgumentException.class,
								"Last four digits cannot be converted to a number." },
						{
								System.getProperty("user.dir") + "\\"
										+ CreditCard.CONF_FILE_NAME, "PP",
								"3DS", null, IllegalArgumentException.class,
								"PP or 3DS not correct." },
						{
								System.getProperty("user.dir") + "\\"
										+ CreditCard.CONF_FILE_NAME, "PP",
								"PP", null, IllegalArgumentException.class,
								"PP or PP not correct." },
						{
								System.getProperty("user.dir") + "\\"
										+ CreditCard.CONF_FILE_NAME, "VISA",
								"PP", null, IllegalArgumentException.class,
								"VISA or PP not correct." },
						{
								System.getProperty("user.dir") + "\\"
										+ CreditCard.CONF_FILE_NAME, "MC",
								"VISA", null, IllegalArgumentException.class,
								"MC or VISA not correct." },

						{
								"C:\\Users\\BandettiniA2\\Desktop\\"
										+ CreditCard.CONF_FILE_NAME,
								"VISA",
								"3DS",
								null,
								FileNotFoundException.class,
								"File " + "C:\\Users\\BandettiniA2\\Desktop\\"
										+ CreditCard.CONF_FILE_NAME
										+ " not found." },
						{
								System.getProperty("user.dir") + "\\"
										+ CreditCard.CONF_FILE_NAME, "VISA",
								"3DS", null, null, null },

				});
	}

	@Before
	public void setUp() {
		// check for exception only if expected
		if (this.expectedException != null) {
			if (this.expectedException == NullPointerException.class
					| this.expectedException == IllegalArgumentException.class) {
				thrown.expect(this.expectedException);
				thrown.expectMessage(this.expectedMessage);
			}
		}
	}

	@Test
	public void testProjectProperties() {
		try {
			this.card = new CreditCard(this.pathConfigurationFile,
					this.cardType, this.userAuth, this.last4Digits);
		} catch (IOException e) {
			// IOExpcetion must be handled so we check the message only
			assertEquals(e.getMessage(), this.expectedMessage);
			this.card = null;
		}
		// if all is fine I check if the values are correctly taken
		if (this.card != null) {
			System.out.println(card.getPan());
			System.out.println(card.getExpiryDate());
			System.out.println(card.getCvv());
			System.out.println(card.getName());
			System.out.println(card.getSurname());
		}
	}
}
