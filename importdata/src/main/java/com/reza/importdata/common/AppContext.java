package com.reza.importdata.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * This class creates an instance for the applicationContext
 * @author reza.sh
 *
 */
public class AppContext {
	private AppContext() {
	}

	private static class LazyHolder {
		static final AppContext INSTANCE = new AppContext();
	}

	public static AppContext getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public ApplicationContext getContext(){
		return new ClassPathXmlApplicationContext("classpath:Beans.xml");
	}
	
}