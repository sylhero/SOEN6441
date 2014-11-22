package log;

import java.util.ArrayList;
import java.util.Date;


public class GlobalLog {
	
	public static ArrayList<String> globalLog = new ArrayList<String>();
	
	public static void addToGlobalLog(String log){
		Date date = new Date();
		globalLog.add(date.toString()+"   "+log);
	}
	
	public static String getAllGobalLog(){
		String result="";
		for(String s : globalLog){
			result+=s;
		}
		return result;
	}

}
