package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ProjectProperties {

	private Properties projectProperties;

	public ProjectProperties(String pathProperties) throws NullPointerException,
			FileNotFoundException, IOException, IllegalArgumentException {
		if (pathProperties == null)
			throw new NullPointerException("Path is null.");
		if (!pathProperties.endsWith("setup.properties"))
			throw new IllegalArgumentException("Project file must end with setup.properties");
		// open the file to the path and extract the properties
		File setupPropFile = new File(pathProperties);
		if (setupPropFile.exists()) {
			this.projectProperties = new Properties();
			FileReader reader;
			try {
				reader = new FileReader(setupPropFile);
				this.projectProperties.load(reader);
				reader.close();
			} catch (IOException e) {
				throw new IOException("File cannot be read.");
			}
		} else
			throw new FileNotFoundException("File " + pathProperties
					+ " not found.");
	}
	
	public Properties getProjectProperties () throws NullPointerException {
		if (this.projectProperties == null)
			throw new NullPointerException("Properties of the project are null.");
		return this.projectProperties;
	}

}
