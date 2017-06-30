package unit.test;

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

import utilities.ProjectProperties;
import junit.framework.TestCase;

@RunWith(Parameterized.class)
public class TestProjectProperties extends TestCase {

	protected ProjectProperties properties;

	// input to validate
	@Parameter(0)
	public String path;

	// expected output
	@Parameter(1)
	public Class<? extends Exception> expectedException;
	@Parameter(2)
	public String expectedMessage;

	// rule for exception
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// parameters to set to the input and expected outcome
	@Parameters(name = "ProjectProperties with {0} = {1} and {2}")
	public static Iterable<Object[]> data() {
		return Arrays
				.asList(new Object[][] {
						{ null, NullPointerException.class, "Path is null." },
						{ "C:\\Users\\BandettiniA2\\Desktop\\pippo",
								IllegalArgumentException.class,
								"Project file must end with setup.properties" },
						{ "pippo", IllegalArgumentException.class,
								"Project file must end with setup.properties" },
						{ "C:\\Users\\BandettiniA2\\Desktop\\file.txt",
								IllegalArgumentException.class,
								"Project file must end with setup.properties" },
						{ "C:\\Users\\BandettiniA2\\Desktop\\setup.properties",
								FileNotFoundException.class,
								"File C:\\Users\\BandettiniA2\\Desktop\\setup.properties not found." },
						//positive test case
						{System.getProperty("user.dir")+ "\\setup.properties", null, null } });
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
			this.properties = new ProjectProperties(this.path);
		} catch (IOException e) {
			// IOExpcetion must be handled so we check the message only
			assertEquals(e.getMessage(), this.expectedMessage);
			this.properties = null;
		}
		// if all is fine I check if the values are correctly taken
		if(this.properties!=null){
			assertEquals(this.properties.getProjectProperties().getProperty("host"), "127.0.0.1");
			assertEquals(this.properties.getProjectProperties().getProperty("port"), "8889");
			assertEquals(this.properties.getProjectProperties().getProperty("deviceName"), "E6653");
		}
	}

}
