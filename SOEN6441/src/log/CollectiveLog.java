package log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Collective log class, this class use singleton pattern.
 * @author yulongsong
 *
 */
public class CollectiveLog implements Serializable {

	private static final long serialVersionUID = -2918986659756346995L;
	
	public static ArrayList<String> collectivelLog = new ArrayList<String>();
		
	private static CollectiveLog collectiveLogObject = new CollectiveLog();
	
	private CollectiveLog(){};
	
	/**
	 * The singleton pattern method.
	 * @return the collectivelog object.
	 */
	public static CollectiveLog getObject()
	{
		return collectiveLogObject;
	}
	
	/**
	 * add to tower log.
	 * @param log The information save in the log.
	 * @param date The date of the information saved in the log.
	 */
	public void addToAllTowerLog(String log, Date date)
	{
		collectivelLog.add(date.toString()+"   "+log);
	}
	
	/**
	 * get all collective logs
	 * @return result all the information saved in the collective log.  
	 */	
	public static String getAllTowerLog(){
		String result="";
		for(String s : collectivelLog){
			result+=s;
		}
		return result;
	}
}
