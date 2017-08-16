package com.reza.importdata;

import java.util.List;

import com.reza.importdata.model.MarketPrice;
import com.reza.importdata.ui.AppUI;

public class Application {
	public static void main(String[] args) {
		runApp();
	} 
	
	public static void runApp() {
		AppUI app = new AppUI();
		List<MarketPrice> fetchData = app.fetchData();
		app.importDate(fetchData);
	}
	
	
}
