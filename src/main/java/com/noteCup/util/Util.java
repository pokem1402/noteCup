package com.noteCup.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.util
 * @File		: Util.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 29.
 * @type		: Util
 * @version		: 
 * @formatter:on
 */
public class Util {

	public static String getPropertiesValue(final String source, final String property) {
		
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
