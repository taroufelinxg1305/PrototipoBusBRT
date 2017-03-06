package uis.brt.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * This class is responsible for providing configuration to different components
 * in the middleware
 */
public class ConfigAdmin extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public ConfigAdmin() {
		load();
	}

	/**
	 * Loads properties files (resources) found in all jars contained in the
	 * classpath
	 */
	public void load() {
		try {
			List<InputStream> streams = loadResources("config.properties", null);
			for (InputStream inputStream : streams) {
				load(inputStream);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create a list with streams of files required in jars found in classpath  
	 * @param name name of resource to load
	 * @param classLoader classLoader to be used
	 * @return List of stream loaded
	 * @throws IOException if any stream cannot be load
	 */
	public static List<InputStream> loadResources(String name,
			ClassLoader classLoader) throws IOException {
		final List<InputStream> list = new ArrayList<InputStream>();
		final Enumeration<URL> systemResources = (classLoader == null ? ClassLoader
				.getSystemClassLoader() : classLoader).getResources(name);
		while (systemResources.hasMoreElements()) {
			list.add(systemResources.nextElement().openStream());
		}
		return list;
	}

}
