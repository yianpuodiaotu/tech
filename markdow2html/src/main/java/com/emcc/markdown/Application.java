package com.emcc.markdown;

import java.io.IOException;
import java.util.Properties;

public class Application {

	public static void main(String[] args) throws IOException {
		Properties properties =  new Properties();
		properties.load(Application.class.getClassLoader().getResourceAsStream("config.properties"));
		String from = properties.getProperty("from");
		String to = properties.getProperty("to");
		
		FileCopy.copyFolder(from, to);	
	}
}
