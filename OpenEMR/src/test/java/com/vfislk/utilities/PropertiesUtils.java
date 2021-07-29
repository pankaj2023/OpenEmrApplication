package com.vfislk.utilities;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.Assert;
public class PropertiesUtils {

	public static String getValue(String fileDetail,String key) {
		
		Properties prop = null;
		String value = null;
		try {
			
			prop = new Properties();
			FileInputStream file = new FileInputStream(fileDetail);
			prop.load(file);
			value = prop.getProperty(key);
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		} finally {
			prop.clear();
		}

		return value;
	}
	

}
