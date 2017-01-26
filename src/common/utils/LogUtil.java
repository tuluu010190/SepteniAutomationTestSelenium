package utils;

//import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
//import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Level;

public class LogUtil {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	private static SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
	private static Date date = new Date();

	private static File logFolder = null;
	private static File logFile = null;

	private static StringBuilder logString;

	/**
	 * 
	 * @param suiteName
	 */
	public static void init(String suiteName){
		String logFolderPath = CommonOperation.getDirFolder("output") + suiteName + File.separator + dateFormat.format(date);
		String logFilePath =  logFolderPath + File.separator + suiteName + "-" + timeFormat.format(date) + ".txt";
		System.out.println("Log folder path : " + logFolderPath);
		System.out.println("Log file path : " + logFilePath);

		logFolder = new File(logFolderPath);
		logFile = new File(logFilePath);

		logString = new StringBuilder();
	}

	public static boolean isFirstWriteLog = true;
	/**
	 * 
	 * @param log
	 */
	public static void logTrace(String log){
		if(isFirstWriteLog){
			logString.append(log);
			isFirstWriteLog = false;
		}
		else{
			logString.append("\n"+log);
		}
	}

	/**
	 * 
	 */
	public static void writeLog(){
		try{
			if(!logFolder.isDirectory() || !logFolder.exists()){
				logFolder.mkdirs();
			}
			if(!logFile.exists()){
				logFile.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFile), StandardCharsets.UTF_8));
			PrintWriter writer = new PrintWriter(bw);

			writer.println(logString);
			writer.flush();
			writer.close();
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * @param message
	 * @param level
	 */
	public static void log(String message, Level level) {
		Throwable t = new Throwable(); 
		String logMessage = message;
		StackTraceElement[] elements = t.getStackTrace();
		Logger logger = Logger.getLogger(CommonOperation.class);
		String Filename = elements[2].getFileName();
		String sClassName = Filename.substring(0, Filename.length() - 5);//remove .java
		String sMethodName = elements[2].getMethodName();
		logMessage = String.format("[%-10s][%s] %s", sClassName, sMethodName, message);
		logger.log(level, logMessage);
	}

	public static void  trace(String message) {
		log(message, Level.TRACE);
	}

	public static void debug(String message) {
		log(message, Level.DEBUG);
	}

	public static void info(String message) {
		log(message, Level.INFO);
	}

	public static void warn(String message) {
		log(message, Level.WARN);
	}

	public static void error(String message) {
		log(message, Level.ERROR);
	}

	//private static Logger LogUtil = Logger.getLogger(LogUtil.class.getName());//
	// This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
	public static void startTestCase(String sTestCaseName){

		info("****************************************************************************************");

		info("****************************************************************************************");

		info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");

		info("****************************************************************************************");

		info("****************************************************************************************");
	}

	//This is to print log for the ending of the test case
	public static void endTestCase(String sTestCaseName){
		info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");

		info("X");

		info("X");

		info("X");

		info("X");
	}

	// Need to create these methods, so that they can be called  
	/*public static void info(String message) {
		LogUtil.info(message);
	}

	public static void warn(String message) {
		LogUtil.warn(message);
	}

	public static void error(String message) {
		LogUtil.error(message);
	}

	public static void fatal(String message) {
		LogUtil.fatal(message);
	}

	public static void debug(String message) {
		LogUtil.debug(message);
	}*/
}