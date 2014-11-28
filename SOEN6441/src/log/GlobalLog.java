package log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * global log class
 * @author yulongsong
 *
 */
public class GlobalLog implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4748986207589833325L;
	
	public static ArrayList<String> globalLog = new ArrayList<String>();
	/**
	 * add to global log
	 * @param log
	 */
	private static GlobalLog globalLogObject = new GlobalLog();
	private GlobalLog(){};
	
	public static GlobalLog getObject()
	{
		return globalLogObject;
	}
	
	
	public void addToGlobalLog(String log, Date date){
		//Date date = new Date();
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
