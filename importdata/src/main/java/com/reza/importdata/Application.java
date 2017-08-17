package com.reza.importdata;

import com.reza.importdata.common.ApplicationConstants;
import com.reza.importdata.ui.JobImport;

public class Application {
	public static void main(String[] args) {
		runApp();
	} 
	
	/**
	 * Application starts here
	 */
	public static void runApp() {
		JobImport app = new JobImport();
		app.doJob(ApplicationConstants.DURATION);
	}
	
}
