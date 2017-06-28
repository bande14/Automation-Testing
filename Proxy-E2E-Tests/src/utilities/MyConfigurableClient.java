package utilities;

import java.io.File;
import java.nio.file.InvalidPathException;

import com.experitest.client.Client;
import com.experitest.client.InternalException;

/**
 * 
 * Class defining the client for the communication with the SeeTest agent in a
 * device.
 * 
 * @author BandettiniA2
 *
 */
public class MyConfigurableClient extends Client {

	private String projectBaseDirectory;

	public MyConfigurableClient(String host, int port,
			String projectBaseDirectory) throws InternalException,
			NullPointerException, InvalidPathException {

		// Client default constructor
		super(host, port, true);

		// check the input
		if (projectBaseDirectory == null)
			throw new NullPointerException("Project directory is null.");

		File file = new File(projectBaseDirectory);
		if (!file.exists())
			throw new InvalidPathException(projectBaseDirectory,
					"Project directory is not a valid path.");

		// set the project base directory
		this.setProjectBaseDirectory(projectBaseDirectory);
		this.projectBaseDirectory = projectBaseDirectory;

	}

	public void setDevice(String deviceName) throws InternalException,
			NullPointerException {
		if (deviceName == null)
			throw new NullPointerException("Device name is null.");
		// check the device name and try to fix
		if (!deviceName.matches("adb:(.*)")) {
			deviceName = "adb:" + deviceName;
			System.err
					.println("Device Name was not properly set. Tried to fix it in "
							+ deviceName);
		}
		// this may generate an InternalException when device name is not
		// valid
		super.setDevice(deviceName);
	}

	public String setReporter(String format, String reportPath, String testName)
			throws IllegalArgumentException, NullPointerException {

		if (format == null)
			throw new NullPointerException("Format is null.");
		if (reportPath == null)
			throw new NullPointerException("Report path is null.");
		if (testName == null)
			throw new NullPointerException("Test name is null.");

		if (format.equals("pdf") | format.equals("xml")) {
			if (reportPath.isEmpty())
				throw new IllegalArgumentException("Report path is empty.");
			if (!reportPath.startsWith(this.projectBaseDirectory)) {
				System.err
						.println("Please note the report path is not under the project directory.");
				System.err.println("Report path: " + reportPath);
				System.err.println("Project base directory: "
						+ projectBaseDirectory);
			}
			if (testName.isEmpty())
				throw new IllegalArgumentException("Test name is empty.");

			return super.setReporter(format, reportPath
					+ "\\reports", testName);
		}
		throw new IllegalArgumentException(
				"Format of the report can only be pdf or xml.");
	}
}
