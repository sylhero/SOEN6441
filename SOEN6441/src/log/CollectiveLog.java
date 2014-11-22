package log;

import java.util.ArrayList;
import java.util.Date;

public class CollectiveLog {
public static ArrayList<String> collectivelLog = new ArrayList<String>();
	
	public static void addToAllTowerLog(String log){
		Date date = new Date();
		collectivelLog.add(date.toString()+"   "+log);
	}
	
	public static String getAllTowerLog(){
		String result="";
		for(String s : collectivelLog){
			result+=s;
		}
		return result;
	}

}
