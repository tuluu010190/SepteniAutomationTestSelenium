package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertiesHandles {
	public static final String DEFAULT_PROPERTIES = "system.properties";
	private static Properties prod;

	/**
	 * 
	 * @return
	 */
	public static Properties getProperties() {
		if (prod == null) {
			prod=new Properties();
			try {
				prod.load(new FileInputStream(System.getProperty("user.dir") + File.separator + "config"  + File.separator + DEFAULT_PROPERTIES));
			} catch (IOException e) {
			}
		}
		return prod;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static String getKey(String key) {
		String value = "";
		try {
			Object obj = getProperties().get(key);
			if (obj != null) {
				value = obj.toString();
			}
		} catch (Exception e) {

		}
		return value;
	}
}