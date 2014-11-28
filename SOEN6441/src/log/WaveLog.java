package log;

import java.awt.TextArea;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
/**
 * wave log
 * @author yulongsong
 *
 */
public class WaveLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3658473829471754341L;
	
	public static ArrayList<String> waveLog = new ArrayList<String>();
	public static int batchCounter = 0;
	public static  JTabbedPane tpane = new JTabbedPane();
	/**
	 * add to wave log
	 * @param log
	 */
	
	private static WaveLog waveLogObject = new WaveLog();
	private WaveLog(){};
	public static WaveLog getObject()
	{
		return waveLogObject;
	}
	public void addToWaveLog(String log,Date date){
		//Date date = new Date();
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
		tpane.addTab("Wave"+batchCounter++, null, new JScrollPane(new TextArea(result)));
		
	}

}
