package log;

import java.util.ArrayList;
import java.util.Date;

/**
 * global log class
 * @author yulongsong
 *
 */
public class GlobalLog {
	
	public static ArrayList<String> globalLog = new ArrayList<String>();
	/**
	 * add to global log
	 * @param log
	 */
	public static void addToGlobalLog(String log){
		Date date = new Date();
		globalLog.add(date.toString()+"   "+log);
	}
	/**
	 * get global log
	 * @return
	 */
	public static String getAllGobalLog(){
		String result="";
		for(String s : globalLog){
			result+=s;
		}
		return result;
	}

}
