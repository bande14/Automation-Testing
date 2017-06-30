package unit.test.MyConfigurableClient;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import utilities.MyConfigurableClient;

@RunWith(Parameterized.class)
public class TestMyConfigurableClientSetReporter {

	// client to check
	protected MyConfigurableClient client;

	// input
	@Parameter(0)
	public String format;
	@Parameter(1)
	public String reportPath;
	@Parameter(2)
	public String testName;
	// expected outcome
	@Parameter(3)
	public Class<? extends Exception> expectedException;
	@Parameter(4)
	public String expectedMessage;

	// rule for exception
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// parameters to set to the input and expected outcome
	@Parameters(name = "MyConfigurableClient setReporter with {0}, {1}, and {2} = {4} and {5}")
	public static Iterable<Object[]> data() {
		return Arrays
				.asList(new Object[][] {
						// negative test cases
						{ null, "", "", NullPointerException.class,
								"Format is null." },
						{ "", null, "", NullPointerException.class,
								"Report path is null." },
						{ "", "", null, NullPointerException.class,
								"Test name is null" },
						{ null, null, null, NullPointerException.class,
								"Format is null." },
						{ "", System.getProperty("user.dir"), "test",
								IllegalArgumentException.class,
								"Format of the report can only be pdf or xml." },
						{ "txt", System.getProperty("user.dir"), "test",
								IllegalArgumentException.class,
								"Format of the report can only be pdf or xml." },
						{ "pdf", "", "test", IllegalArgumentException.class,
								"Report path is empty." },
						{ "pdf", System.getProperty("user.dir"), "",
								IllegalArgumentException.class,
								"Test name is empty." },
						// positive test cases
						{ "pdf", "C:", "test", null, null },
						{ "pdf", System.getProperty("user.dir"), "test", null,
								null },
						{ "xml", System.getProperty("user.dir"), "test", null,
								null }, });
	}

	@Before
	public void setUp() {
		// we assume constructor tested and connectivity in place
		client = new MyConfigurableClient("127.0.0.1", 8889,
				System.getProperty("user.dir"));
	}

	@Test
	public void testMyConfigurableClientsetReporter() {

		// check for exception only if expected
		if (this.expectedException != null) {
			thrown.expect(this.expectedException);
			thrown.expectMessage(this.expectedMessage);
		}

		System.out.println(client.setReporter(format, reportPath, testName));
	}

	@After
	public void tearDown() {
		if (this.client != null)
			this.client.releaseClient();
	}

}
