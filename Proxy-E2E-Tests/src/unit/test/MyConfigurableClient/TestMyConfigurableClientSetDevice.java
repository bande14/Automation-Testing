package unit.test.MyConfigurableClient;

import java.util.Arrays;

import junit.framework.TestCase;

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

import com.experitest.client.InternalException;

@RunWith(Parameterized.class)
public class TestMyConfigurableClientSetDevice extends TestCase {

	// client to check
	protected MyConfigurableClient client;

	// input
	@Parameter(0)
	public String deviceName;

	// expected outcome
	@Parameter(1)
	public Class<? extends Exception> expectedException;
	@Parameter(2)
	public String expectedMessage;

	// rule for exception
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// parameters to set to the input and expected outcome
	@Parameters(name = "MyConfigurableClient setDevice with {0} = {1} and {2}")
	public static Iterable<Object[]> data() {
		return Arrays
				.asList(new Object[][] {
						// negative test cases
						{ null, NullPointerException.class,
								"Device name is null." },
						{ "", InternalException.class, "Failed to set device" },
						{ "E6655", InternalException.class,
								"Failed to set device" },
						{ "adb:E6655", InternalException.class,
								"Failed to set device" },
						{ "ciao", InternalException.class,
								"Failed to set device" },
						// positive test cases
						{ "E6653", null, null }, { "adb:E6653", null, null }, });
	}

	@Before
	public void setUp() {
		// we assume constructor tested and connectivity in place
		client = new MyConfigurableClient("127.0.0.1", 8889,
				System.getProperty("user.dir"));

		// check for exception only if expected
		if (this.expectedException != null) {
			thrown.expect(this.expectedException);
			thrown.expectMessage(this.expectedMessage);
		}
	}

	@Test
	public void testMyConfigurableClientsetDevice() {

		client.setDevice(deviceName);
	}

	@After
	public void tearDown() {
		if (this.client != null)
			this.client.releaseClient();
	}
}
