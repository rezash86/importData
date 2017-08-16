package com.reza.importdata.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
	
	public ExecutorService getThread(){
		return Executors.newSingleThreadExecutor();
	}
}