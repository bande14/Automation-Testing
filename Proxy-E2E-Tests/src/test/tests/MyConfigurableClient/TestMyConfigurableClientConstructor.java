package test.tests.MyConfigurableClient;

import java.nio.file.InvalidPathException;
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

import com.experitest.client.InternalException;

import utilities.MyConfigurableClient;

@RunWith(Parameterized.class)
public class TestMyConfigurableClientConstructor extends TestCase {

	// client to check
	protected MyConfigurableClient client;
	private int port;

	// input
	@Parameter(0)
	public String host;
	@Parameter(1)
	public String portString;
	@Parameter(2)
	public String projectFolder;

	// expected outcome
	@Parameter(3)
	public Class<? extends Exception> expectedException;
	@Parameter(4)
	public String expectedMessage;

	// rule for exception
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// parameters to set to the input and expected outcome
	@Parameters(name = "MyConfigurableClient with {0}, {1}, {2} = {3} and {4}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] {

				// wrong IP and port tests (in case connection is not valid)
				/*
				 * { "127.0.0.2", "8889", System.getProperty("user.dir"),
				 * InternalException.class, "Connectivity Exception" }, --->
				 * when IP is wrong then hard to get which exception
				 */
				{ "127.0.0.1", "8884", System.getProperty("user.dir"),
						InternalException.class, "Connectivity Exception" },
				{ "127.0.0.2", "8889", null, NullPointerException.class,
						"Project directory is null." },
				{ "127.0.0.1", "8884", null, NullPointerException.class,
						"Project directory is null." },
				{ "127.0.0.1", "8889", "folder", InvalidPathException.class,
						"Project directory is not a valid path." },
				// expected IP and port tests
				{ "127.0.0.1", "8889", null, NullPointerException.class,
						"Project directory is null." },
				{ "127.0.0.1", "8889", "folder", InvalidPathException.class,
						"Project directory is not a valid path." },
				// all fine - please note device must be connected!
				{ "127.0.0.1", "8889", System.getProperty("user.dir"), null,
						null }, });
	}

	@Before
	public void setUp() {
		if (this.portString != null)
			this.port = Integer.parseInt(this.portString);

		// check for exception only if expected
		if (this.expectedException != null) {
			thrown.expect(this.expectedException);
			thrown.expectMessage(this.expectedMessage);
		}
	}

	@Test
	public void testMyConfigurableClientBehaviour() {

		client = new MyConfigurableClient(this.host, this.port,
				this.projectFolder);
	}

	@After
	public void tearDown() {
		if (this.client != null)
			this.client.releaseClient();
	}

}
