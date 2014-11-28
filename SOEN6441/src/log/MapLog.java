package log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import currency.Coin;

/**
 * this class is responsible for reading and writing map log file.
 * this class use singleton pattern.
 * @author yulongsong
 *
 */
public class MapLog {
	private String mapName;
	private static MapLog mapLogObject = new MapLog();
	public static final String PATH = System.getProperty("user.dir")+"/resources/maplog/";
	/**
	 * The constructor.
	 */
	private MapLog(){		
	}
	
	/**
	 * The singleton pattern method.
	 * @return the mapLog object.
	 */
	public static MapLog getMapLogObject()
	{
		return mapLogObject;
	}
	
	/**
	 * Write the map log in a text file.
	 * @param mapName The name of the text file.
	 * @param log The information which should be saved in log file
	 */
	private void writeToFile(String mapName, String log){
		try {
			 
 
			File file = new File(PATH+mapName+".txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(log);
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Read the map log from the text file.
	 * @param mapName The name of the text file.
	 * @param position The position of the saved log file.
	 * @return
	 */
	private String readFromFile(String mapName,String position){
		BufferedReader br = null;
		String sCurrentLine="";
		String temp = "";
		 
		try {
 
			br = new BufferedReader(new FileReader(PATH+mapName+".txt"));
			if(position.equalsIgnoreCase("first")){
				sCurrentLine = br.readLine();
			}else if(position.equalsIgnoreCase("last")){
				while ((temp = br.readLine()) != null) 
			    {
					sCurrentLine = temp;
			    }
			}else if(position.equalsIgnoreCase("all")){
				while ((temp = br.readLine()) != null) 
			    {
					sCurrentLine += temp+"\n";
			    }
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return sCurrentLine;
 
	}
	
	/**
	 * Read the score from the log file 
	 * @param mapName The name of the text file.
	 * @return the scores saved in the text file.
	 */
	private ArrayList<Integer> readScore(String mapName){
		ArrayList<Integer> tempFive = new ArrayList<Integer>(5);
		tempFive.add(0);
		tempFive.add(0);
		tempFive.add(0);
		tempFive.add(0);
		tempFive.add(0);
		
		BufferedReader br = null;
		String current="";
		 
		try {
			br = new BufferedReader(new FileReader(PATH+mapName+".txt"));
			while ((current = br.readLine()) != null){
				String[] temp = current.split(",");
				tempFive.add(Integer.parseInt(temp[temp.length-1]));	
				
			}
 
			
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return tempFive;
		
	}
	
	/**
	 * get the map name.
	 * @return the map name.
	 */
	public String getMapName(){
		return this.mapName;
	}
	
	/**
	 * Save the creation time of the map in the text file.
	 * @param mapName The name of the text file.
	 */
	public void createMapLog(String mapName)
	{
		File file = new File(PATH+mapName+".txt");
		 
		// if file doesnt exists, then create it
		if (file.exists()) {
			return;
		}else{
			Date date  = new Date();
			String log =date.toString()+","+"null,"+"null,"+0+"\n";
			writeToFile(mapName,log);
		}
		
	}
	
	/**
	 * Save the edit time of the map in the text file.
	 * @param mapName The name of the text file.
	 */
	public void editMapLog(String mapName){
		Date date  = new Date();
		String tempLine = readFromFile(mapName,"first");
		String creationTime = tempLine.split(",")[0];
		String log =creationTime+","+date.toString()+","+"null,"+0+"\n";
		writeToFile(mapName,log);		
	}
	
	/**
	 * Save the play time and score in the text file.
	 * @param score The score of the play.
	 */
	public void playMapLog(int score){
		String tempLine = readFromFile(mapName,"last");
		String[] split = tempLine.split(",");
		String creationTime = split[0];
		String editTime = split[1];
		Date date = new Date();
		String log = creationTime+","+editTime+","+date.toString()+","+score+"\n";
		writeToFile(mapName,log);		
	}
	/**
	 * get the top five score.
	 * @param mapName The name of the text file.
	 * @return An array list which contains the top five score.
	 */
	public ArrayList<Integer> getTopFive(String mapName){
		this.mapName = mapName;
		ArrayList<Integer> tempTopFive = readScore(mapName);
		Collections.sort(tempTopFive);
		Collections.reverse(tempTopFive);
		return tempTopFive;
	
	}
	/**
	 * Get the information saved in the text file.
	 * @param mapName The name of the text file.
	 * @return All the string saved in the text file.
	 */
	public String getAllMapLog(String mapName){
		return readFromFile(mapName,"all");
		
	}
}
