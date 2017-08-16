package com.reza.importdata;

import com.reza.importdata.ui.AppUI;

public class Application {
	public static void main(String[] args) {
		runApp();
	} 
	
	public static void runApp() {
		AppUI app = new AppUI();
		app.doJob();
	}
	
}
