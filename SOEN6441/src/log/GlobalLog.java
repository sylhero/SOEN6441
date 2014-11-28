package log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * global log class.
 * this class use singleton pattern.
 * @author yulongsong
 *
 */
public class GlobalLog implements Serializable{

	private static final long serialVersionUID = -4748986207589833325L;
	
	public static ArrayList<String> globalLog = new ArrayList<String>();
	
	
	private static GlobalLog globalLogObject = new GlobalLog();
	private GlobalLog(){};
	
	/**
	 * The singleton pattern method.
	 * @return the globalLog object.
	 */
	public static GlobalLog getObject()
	{
		return globalLogObject;
	}
	
	/**
	 * add to global log
	 * @param log The information save in the log.
	 * @param date The date of the information saved in the log.
	 */
	public void addToGlobalLog(String log, Date date){
		//Date date = new Date();
		globalLog.add(date.toString()+"   "+log);
	}
	
	/**
	 * get global log
	 * @return All the information saved in the collective log.
	 */
	public static String getAllGobalLog(){
		String result="";
		for(String s : globalLog){
			result+=s;
		}
		return result;
	}

}
