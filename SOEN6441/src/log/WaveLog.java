package log;

import java.awt.TextArea;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
/**
 * wave log
 * this class use singleton pattern.
 * @author yulongsong
 *
 */
public class WaveLog implements Serializable{
	
	private static final long serialVersionUID = 3658473829471754341L;
	
	public static ArrayList<String> waveLog = new ArrayList<String>();
	public static ArrayList<String> totalWaveLog = new ArrayList<String>();
	public static int batchCounter = 0;
	public static  JTabbedPane tpane = new JTabbedPane();
	private static WaveLog waveLogObject = new WaveLog();
	
	/**
	 * The constructor.
	 */
	private WaveLog(){};
	
	/**
	 * The singleton pattern method.
	 * @return the waveLog object.
	 */
	public static WaveLog getObject()
	{
		return waveLogObject;
	}
	
	/**
	 * add to wave log.
	 * @param log The information save in the log.
	 * @param date The date of the information saved in the log.
	 */
	public void addToWaveLog(String log,Date date){
		waveLog.add(date.toString()+"   "+log);
	}
	
	/**
	 * init
	 */
	public static  void init(){
		waveLog = new ArrayList<String>();
	}
	
	 
	
	/**
	 * add wave tab
	 */
	public static void addWaveTab(){
		String result="";
		for(String s : waveLog){
			result+=s;
		}
		totalWaveLog.add(result);
		tpane.addTab("Wave"+batchCounter++, null, new JScrollPane(new TextArea(result)));
		waveLog = new ArrayList<String>();
	}

}
