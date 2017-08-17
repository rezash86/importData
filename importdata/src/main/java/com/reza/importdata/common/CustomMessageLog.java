package com.reza.importdata.common;

public class CustomMessageLog extends Exception{
	
	private static final long serialVersionUID = 1L;

	public static void showLog(String message){
		System.out.println(message);
	}
}
