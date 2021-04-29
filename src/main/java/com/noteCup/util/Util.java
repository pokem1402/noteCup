package com.noteCup.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Util {

	public static String getPropertiesValue(String source, String property) {
		
		Properties prop = new Properties();
		InputStream in = Util.class.getClassLoader().getResourceAsStream("messages\\messages_ko_KR.properties");
		try {
			prop.load(new InputStreamReader(in, "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(property);
	}
}
