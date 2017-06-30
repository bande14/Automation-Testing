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

import utilities.TestConfiguration;

@RunWith(Parameterized.class)
public class TestTestConfiguration {

	protected TestConfiguration configuration;

	// input to validate
	@Parameter(0)
	public String pathConfiguration;
	@Parameter(1)
	public String testCaseName;

	// expected output
	@Parameter(2)
	public Class<? extends Exception> expectedException;
	@Parameter(3)
	public String expectedMessage;

	// rule for exception
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// parameters to set to the input and expected outcome
	@Parameters(name = "TestConfiguration with {0}, {1} = {2} and {3}")
	public static Iterable<Object[]> data() {
		return Arrays
				.asList(new Object[][] {
						// negative test cases
						{ null, null, NullPointerException.class,
								"Path is null." },
						{ null, "testCase", NullPointerException.class,
								"Path is null." },
						{ "C:", null, NullPointerException.class,
								"Test case name is null." },
						{
								"C:\\Users\\BandettiniA2\\Desktop\\pippo",
								"testCase",
								IllegalArgumentException.class,
								"Test Configuration file must end with "
										+ TestConfiguration.CONF_FILE_NAME },
						{
								System.getProperty("user.dir") + "\\"
										+ TestConfiguration.CONF_FILE_NAME, "",
								IllegalArgumentException.class,
								"Test case name is empty." },
						{
								"C:\\Users\\BandettiniA2\\Desktop\\"
										+ TestConfiguration.CONF_FILE_NAME,
								"tescase",
								FileNotFoundException.class,
								"File " + "C:\\Users\\BandettiniA2\\Desktop\\"
										+ TestConfiguration.CONF_FILE_NAME
										+ " not found." },
						/*{
								"C:\\Users\\BandettiniA2\\Desktop\\Test\\"
										+ TestConfiguration.CONF_FILE_NAME,
								"testCase",
								NullPointerException.class,
								"Sheet " + TestConfiguration.CONF_SHEET_NAME
										+ " cannot be found within "
										+ TestConfiguration.CONF_FILE_NAME
										+ "." },*/
						{
								System.getProperty("user.dir") + "\\"
										+ TestConfiguration.CONF_FILE_NAME,
								"testcase", IllegalArgumentException.class,
								"Not possible to find any cell with test case name testcase" },
						// check about values
						{
								System.getProperty("user.dir") + "\\"
										+ TestConfiguration.CONF_FILE_NAME,
								"test2", null, null },
						{
								System.getProperty("user.dir") + "\\"
										+ TestConfiguration.CONF_FILE_NAME,
								"test3", null, null },
						{
								System.getProperty("user.dir") + "\\"
										+ TestConfiguration.CONF_FILE_NAME,
								"test4", null, null },
						// positive test cases
						{
								System.getProperty("user.dir") + "\\"
										+ TestConfiguration.CONF_FILE_NAME,
								"TestFirstAddProxyCard", null, null }, });
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
			this.configuration = new TestConfiguration(this.pathConfiguration,
					this.testCaseName);
		} catch (IOException e) {
			// IOExpcetion must be handled so we check the message only
			assertEquals(e.getMessage(), this.expectedMessage);
			this.configuration = null;
		}
		// if all is fine I check if the values are correctly taken
		if (this.configuration != null) {
			// checks according to the test cases
			switch (this.testCaseName) {
			case "test2":
				assertEquals(this.configuration.getValue(TestConfiguration.CARD_TYPE), null);
				assertEquals(this.configuration.getValue(TestConfiguration.CARD_POSITION),"1");
				assertEquals(this.configuration.getValue(TestConfiguration.USER_AUTHENTICATION),"MD");
				assertEquals(this.configuration.getValue(TestConfiguration.FRAUD_STATUS),"OFF");
				break;
			case "test4":
				assertEquals(this.configuration.getValue(TestConfiguration.CARD_TYPE),"PP");
				assertEquals(this.configuration.getValue(TestConfiguration.CARD_POSITION),"1");
				assertEquals(this.configuration.getValue(TestConfiguration.USER_AUTHENTICATION),"PP");
				assertEquals(this.configuration.getValue(TestConfiguration.FRAUD_STATUS),"ON");
				break;
			case "test3":
				assertEquals(this.configuration.getValue(TestConfiguration.CARD_TYPE),"MC");
				assertEquals(this.configuration.getValue(TestConfiguration.CARD_POSITION), null);
				assertEquals(this.configuration.getValue(TestConfiguration.USER_AUTHENTICATION),"MD");
				assertEquals(this.configuration.getValue(TestConfiguration.FRAUD_STATUS),"OFF");
				break;
			case "TestFirstAddProxyCard":
				assertEquals(this.configuration.getValue(TestConfiguration.CARD_TYPE),"VISA");
				assertEquals(this.configuration.getValue(TestConfiguration.CARD_POSITION),"1");
				assertEquals(this.configuration.getValue(TestConfiguration.USER_AUTHENTICATION),"3DS");
				assertEquals(this.configuration.getValue(TestConfiguration.FRAUD_STATUS),"ON");
				break;
			default:
				throw new AssertionError("No test case found while checking values.");
			}
		}
	}

}
