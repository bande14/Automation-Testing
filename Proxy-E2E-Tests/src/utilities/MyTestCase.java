package utilities;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class MyTestCase {
	
	protected static MyConfigurableClient client;
	protected static String projectBaseDirectory;

	private static ProjectProperties projectProperties;
	protected static final int ERROR = -1;

	@BeforeClass
	public static void beforeClass() {

		// take the info from the configuration file of the project
		try {
			projectBaseDirectory = System.getProperty("user.dir");
			projectProperties = new ProjectProperties(projectBaseDirectory + "\\setup.properties");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(ERROR);
		}
		
		String host = projectProperties.getProjectProperties().getProperty("host");
		if (host == null){
			System.err.println("Host cannot be null.");
			System.exit(ERROR);
		}
		
		String portString = projectProperties.getProjectProperties().getProperty("port");
		int port = 0;
		if (portString != null){
			try {
				port = Integer.parseInt(portString);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Port cannot be converted to a number.");
			}
		}
		else {
			System.err.println("Port cannot be null.");
			System.exit(ERROR);
		}
		
		System.out.println("Project Properties:" + "\nHost: " + host + "\nPort: " + port);
		
		// create a client with such configuration
		try {
			client = new MyConfigurableClient(host, port, projectBaseDirectory);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(ERROR);
		}
		
		// retrieve deviceName and set it
		String deviceName = projectProperties.getProjectProperties().getProperty("deviceName");
		try {
			client.setDevice(deviceName);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(ERROR);
		}
		
	}

	@AfterClass
	public static void afterClass() {
		client.generateReport(true);
	}


}
