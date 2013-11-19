package ba.etf.tim11.eCinema.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public final class DaoConfiguration
{
	// Constants ----------------------------------------------------------------------
	
	public static final String CONFIGURATION_FILE = "dao.configuration";

	private static final Properties configuration = new Properties();
	private static DaoConfiguration instance = new DaoConfiguration();
	
	
	static {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream configurationFile = classLoader.getResourceAsStream(CONFIGURATION_FILE);

        if (configurationFile == null) {
        	// TODO(kklisura): Create new exception type
            //throw new Exception("Configuration file '" + CONFIGURATION_FILE + "' is missing in classpath.");
        }

        try {
        	configuration.load(configurationFile);
        } catch (IOException e) {
        	// TODO(kklisura): 
            //throw new Exception("Cannot load properties file '" + CONFIGURATION_FILE + "'.", e);
        }
	}
	
	
	private DaoConfiguration() {
		
	}
	
	public DaoConfiguration getInstance() {
		// NOTE(kklisura): Not thread safe!
		return instance;
	}

    public String getEntry(String key, boolean mandatory) throws Exception 
    {
        String entry = configuration.getProperty(key);

        if (entry == null || entry.trim().length() == 0) 
        {
            if (mandatory) {
                throw new Exception("Required property '" + key + "'" + " is missing in configuration file '" + CONFIGURATION_FILE + "'.");
            } else {
                entry = null;
            }
        }

        return entry;
    }
	
}