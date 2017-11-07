package com.emcc.markdown;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Application {
	static Properties properties =  new Properties();
	
	static{
		try {
			File file = new File("./config/config.properties");
			System.out.println(file.getAbsolutePath());
			FileInputStream input = new FileInputStream(new File("./config/config.properties"));
			properties.load(input);
		} catch (IOException e) {
			System.err.println("未能找到配置文件");
		}
	}
	
	public static String getProperty(String key){
		return properties.getProperty(key);
	}

	public static void main(String[] args) throws IOException {
		
		String from = Application.getProperty("from");
		String to = Application.getProperty("to");
		
		System.out.println("========转换中==============");
		
		FileCopy.copyFolder(from, to);	
		
		System.out.println("========转换结束==============");
	}
}
