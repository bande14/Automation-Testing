package unit.test.CreditCard;

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

import utilities.configuration.CardConfiguration;
import utilities.configuration.Configuration;
import utilities.card.DSCard;
import junit.framework.TestCase;

@RunWith(Parameterized.class)
public class TestDSCard extends TestCase {

	protected DSCard card;

	// input to validate
	@Parameter(0)
	public String pathFile;
	@Parameter(1)
	public String sheet;
	@Parameter(2)
	public String key;
	@Parameter(3)
	public String value;

	// expected output
	@Parameter(4)
	public Class<? extends Exception> expectedException;
	@Parameter(5)
	public String expectedMessage;

	// rule for exception
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// parameters to set to the input and expected outcome
	@Parameters(name = "ProjectProperties with {0}, {1}, {2}, {3} = {4} and {5}")
	public static Iterable<Object[]> data() {
		return Arrays
				.asList(new Object[][] {
						{ null, null, null, null, NullPointerException.class,
								"Configuration path is null." },
						{ "", null, null, null, NullPointerException.class,
								"Configuration sheet is null." },
						{ "", "", null, null, NullPointerException.class,
								"Configuration key is null." },
						{ "", "", "", null, NullPointerException.class,
								"Configuration value is null." },
						{
								"C:\\Users\\BandettiniA2\\Desktop\\pippo",
								"",
								"",
								"",
								IllegalArgumentException.class,
								"Configuration file must end with "
										+ Configuration.CONF_FILE_NAME },
						{
								"C:\\Users\\BandettiniA2\\Desktop\\"
										+ Configuration.CONF_FILE_NAME,
								"",
								"",
								"",
								FileNotFoundException.class,
								"File C:\\Users\\BandettiniA2\\Desktop\\"
										+ Configuration.CONF_FILE_NAME
										+ " not found." },
						{
								System.getProperty("user.dir") + "\\"
										+ Configuration.CONF_FILE_NAME,
								"prova",
								"",
								"",
								NullPointerException.class,
								"Sheet prova cannot be found within "
										+ Configuration.CONF_FILE_NAME + "." },
						{
								System.getProperty("user.dir") + "\\"
										+ Configuration.CONF_FILE_NAME,
								"VISA3DS", "prova", "",
								IllegalArgumentException.class,
								"Not possible to find any cell with the key prova" },
						{
								System.getProperty("user.dir") + "\\"
										+ Configuration.CONF_FILE_NAME,
								"VISA3DS", "CVV", "prova",
								IllegalArgumentException.class,
								"Not possible to find any cell with the value prova" },
						// positive test case
						{
								System.getProperty("user.dir") + "\\"
										+ Configuration.CONF_FILE_NAME,
								"VISA3DS", "CVV", "737", null, null },
						// other test cases
						{
								System.getProperty("user.dir") + "\\"
										+ Configuration.CONF_FILE_NAME,
								"FORTESTINGONLY", "NAME", "Wrong",
								IllegalArgumentException.class,
								"Some card information are not correct." },
						{
								System.getProperty("user.dir") + "\\"
										+ Configuration.CONF_FILE_NAME,
								"FORTESTINGONLY", "SURNAME", "Expiry",
								IllegalArgumentException.class,
								"Some card information are not correct." },
						{
								System.getProperty("user.dir") + "\\"
										+ Configuration.CONF_FILE_NAME,
								"FORTESTINGONLY", "SURNAME", "CVV",
								IllegalArgumentException.class,
								"Some card information are not correct." }, });
	}

	@Before
	public void setUp() {
		this.card = null;
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
		System.out.println(System.getProperty("user.dir"));
		try {
			this.card = new DSCard(new CardConfiguration(pathFile, sheet, key,
					value));
		} catch (IOException e) {
			// IOExpcetion must be handled so we check the message only,
			assertEquals(e.getMessage(), this.expectedMessage);
			this.card = null;
		}
		// if all is fine I check if the values are correctly taken
		if (this.card != null) {
			assertEquals(this.card.getPan(), "4212345678901237");
			assertEquals(this.card.getExpiryDate(), "08/2018");
			assertEquals(this.card.getCvv(), "737");
			assertEquals(this.card.getName(), "Alberto");
			assertEquals(this.card.getSurname(), "Bandettini");
			assertEquals(this.card.get3DSUsername(), "user");
			assertEquals(this.card.get3DSPassword(), "password");
		}
	}

}
