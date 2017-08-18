package com.reza.importdata.common;

/**
 * This class contains constants and messages
 * @author reza.sh
 *
 */
public class ApplicationConstants {
	
	public static Long DURATION = 20L;
	public static Long REFRESH_DATA_INTERVAL = 300000L;
	public static final String FEED_URL="https://www.misoenergy.org/ria/LMP.aspx?query=udstable&format=xml";
	public static final String LMP="LMP";
	public static final String CONGESTION="congestion";
	public static final String LOSS="loss";
	public static final String HUB_NAME="name";
	public static final String REF_ID="RefId";
	public static final String DATE_TIME_PATTERN="dd-MMM-yyyy HH:mm";
	
	public static final String ERROR_CONNECTION_NOT_EXISTS = "connection to server is not available";
	public static final String DATABASE_ERROR = "Database problem";
	public static final String ERROR_DATA_EXISTS = "Data is imported before";
	public static final String MESSAGE_SUCCESSFUL_IMPORT ="Successfull import";
	public static final String MESSAGE_UNSUCCESSFUL_IMPORT ="UnSuccessfull import";
	
}
