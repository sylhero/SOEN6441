package log;

import java.util.ArrayList;
import java.util.Date;

public class WaveLog {
	public static ArrayList<String> waveLog = new ArrayList<String>();
	public static void addToWaveLog(String log){
		Date date = new Date();
		waveLog.add(date.toString()+"   "+log);
	}
	public static String getAllWaveLog(){
		String result="";
		for(String s : waveLog){
			result+=s;
		}
		return result;
	}
	public static  void init(){
		waveLog = new ArrayList<String>();
	}

}
