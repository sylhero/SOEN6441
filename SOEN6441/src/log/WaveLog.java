package log;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class WaveLog {
	public static ArrayList<String> waveLog = new ArrayList<String>();
	public static int batchCounter = 0;
	public static  JTabbedPane tpane = new JTabbedPane();
	public static void addToWaveLog(String log){
		Date date = new Date();
		waveLog.add(date.toString()+"   "+log);
	}
//	public static String getAllWaveLog(){
//		String result="";
//		for(String s : waveLog){
//			result+=s;
//		}
//		return result;
//	}
	public static  void init(){
		waveLog = new ArrayList<String>();
	}
	
	
	
	public static void addWaveTab(){
		String result="";
		for(String s : waveLog){
			result+=s;
		}
		tpane.addTab("Wave"+batchCounter++, null, new JScrollPane(new TextArea(result)));
		
	}

}
