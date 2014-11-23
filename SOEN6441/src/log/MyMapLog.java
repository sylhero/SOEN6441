package log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

/*
 * 1. Create only one map file log
 * 2. before creation, you'd check if the mapLog object is created, if not create one
 * 3. when edit a map, load the mapLog object, and store the edit information in a editTime arrayList
 * 4. when a map is played, load the mapLog object and store the playedTime in a treeSet as key and store the score as
 * value
 * 5. when checking the highest score, load all the mapLog object and get the treeSet to fetch five highest scores
 * 
 * 
 * 
 * 
 */

public class MyMapLog {

	private static MyMapLog mml = new MyMapLog();
	private File fileName;
	public static final String PATH = System.getProperty("user.dir") + "/resources/maplog/";
	private ArrayList<String> scores = new ArrayList<String>();
	
	private MyMapLog(){};
	
	
	public static MyMapLog getMapLogInstance()
	{
		return mml;
	}

	
	public void createMapLog(String mapName)
	{
		fileName = new File(PATH + mapName + ".txt");
		BufferedWriter bw = null;
		System.out.println(fileName.getName() + " is been created");
		
		if(!fileName.exists())
		{
			try {
				
				bw = new BufferedWriter(new FileWriter(fileName));
				bw.newLine();
				
				Date date = new Date();
				DateFormat df = DateFormat.getInstance();
				df = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss");
				String mapInfor = df.format(date);
				
				bw.write("CREATED TIME: " + mapInfor + ",          EDITED TIME: NULL,          PLAYED TIME: NULL,          SCORE: 0");
				bw.flush();			
				
				
			} catch (IOException e) 
			{			
				e.printStackTrace();
			}finally{
				try {
					bw.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}	
	}
	
	
	public void editPlayMap(String operationType, int score)
	{
		BufferedWriter bw = null;	
		
		try {
			
			bw = new BufferedWriter(new FileWriter(fileName, true));
			if(operationType.equalsIgnoreCase("edit"))
			{
				Date date = new Date();
				DateFormat df = DateFormat.getInstance();
				df = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss");
				String editedInfor = df.format(date);
				
				bw.newLine();
				bw.write("CREATED TIME: NULL" + ",          EDITED TIME: " + editedInfor + "," + "          PLAYED TIME: NULL,          SCORE: 0");			
			}else if(operationType.equalsIgnoreCase("play"))
			{
				Date date = new Date();
				DateFormat df = DateFormat.getInstance();
				df = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss");
				String playedInfor = df.format(date);
				
				bw.newLine();
				bw.write("CREATED TIME: NULL" + ",          EDITED TIME: NULL" + ",          PLAYED TIME: " + playedInfor +
						",          SCORE: " + String.valueOf(score));
			}
			
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
	
	public ArrayList<String> getTopScores(String fileName) throws FileNotFoundException
	{
		File socre = new File(PATH + fileName + ".txt");
		Scanner scanner = new Scanner(socre);
		 
/**
 * 
 * 
 * 
 * bug is here !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 * 
 * 		
 */
		while(scanner.hasNext())
		{
			String sc2 = scanner.findInLine("SCORE: ");
			String sc = scanner.nextLine();
			int num = Integer.parseInt(sc);
			//scores.add(sc);
			System.out.println(num);
			
		}
	
//		Collections.sort(scores);
//		Collections.reverse(scores);
//		
		scanner.close();
		
		return scores;
		
	}
	
	
	
	public static void main(String[] Args) throws FileNotFoundException
	{
		MyMapLog mml = MyMapLog.getMapLogInstance();
		mml.createMapLog("test_1");
		mml.editPlayMap("edit", 0);
		mml.editPlayMap("play", 10);
		mml.editPlayMap("play", 8);
		mml.editPlayMap("play", 15);
		
		ArrayList al = mml.getTopScores("test_1");
		
//		for(Object i : al)
//		{
//			System.out.println((Integer)i);
//		}
	}
	
}
