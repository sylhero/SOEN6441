package log;

import java.util.ArrayList;
import java.util.Date;
/**
 * collective log class
 * @author yulongsong
 *
 */
public class CollectiveLog {
	
public static ArrayList<String> collectivelLog = new ArrayList<String>();
	/**
	 * add to collective log
	 * @param log
	 */
	private static CollectiveLog collectiveLogObject = new CollectiveLog();
	private CollectiveLog(){};
	public static CollectiveLog getObject()
	{
		return collectiveLogObject;
	}
	
	public void addToAllTowerLog(String log, Date date){
		//Date date = new Date();
		collectivelLog.add(date.toString()+"   "+log);
	}
	/**
	 * get all collective logs
	 * @return result
	 */
	
	public static String getAllTowerLog(){
		String result="";
		for(String s : collectivelLog){
			result+=s;
		}
		return result;
	}

}
