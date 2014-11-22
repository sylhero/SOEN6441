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

public class MapLog {
	private File mapLog;
	//String gap = "-----------------------------------------------";
	private static MapLog mapLogObject = new MapLog();
	public String PATH = System.getProperty("user.dir")+"/resources/maplog/";
	
	public static MapLog getMapLogObject()
	{
		return mapLogObject;
	}
	
	public void createLog(String mapName)
	{
		mapLog = new File(PATH + mapName+".txt");
		System.out.println(mapLog.getName());
		if(!mapLog.exists())
		{

			try {
				mapLog.createNewFile();
				FileWriter writeMapLog = new FileWriter(PATH + mapLog.getName(),true);
				BufferedWriter bwMapLog = new BufferedWriter(writeMapLog);
				Date date = new Date();
				System.out.println("!!");
				//bwMapLog.write("Creation Time"+gap.substring(0,(gap.length()-13))+",Edit Time"+gap.substring(0,(gap.length()-9))+",Play Time"+gap.substring(0,(gap.length()-9))+",Score");
				System.out.println("!!!");
				bwMapLog.newLine();
				System.out.println("!!!");
				bwMapLog.flush();
				System.out.println("!!!");
				bwMapLog.write(date.toString()+",null,null,0");
				//bwMapLog.write(date.toString()+gap.substring(0,(gap.length()-date.toString().length()))+","+gap+","+gap+","+"0");
				bwMapLog.newLine();
				bwMapLog.flush();
				bwMapLog.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
								
		}
		
	}
	
	public void saveMapLog(String type, int score) 	
	{
		System.out.println("save map log"+mapLog.getName());
		try{
			FileWriter writeMapLog = new FileWriter(PATH + mapLog.getName(),true);
			BufferedWriter bwMapLog = new BufferedWriter(writeMapLog);
			if(type.equalsIgnoreCase("edit"))
			{
				Date date = new Date();
				//bwMapLog.write("null,"+date.toString()+",null,0");
				//bwMapLog.write(gap+","+date.toString()+gap.substring(0,(gap.length()-date.toString().length()))+","+gap+",0");		
				bwMapLog.newLine();
			}
			if(type.equalsIgnoreCase("play"))
			{
				Date date = new Date();
				bwMapLog.write("null,null,"+date.toString()+","+Integer.toString(score));
				//bwMapLog.write(gap+","+gap+","+date.toString()+gap.substring(0,(gap.length()-date.toString().length()))+","+Integer.toString(score));
				bwMapLog.newLine();
			}
			bwMapLog.flush();
			bwMapLog.close();		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getLog(String mapName)
	{
		
		String log ="";
		FileReader readLog;
		try {
			readLog = new FileReader(PATH + mapName+".txt");
			BufferedReader brLog = new BufferedReader(readLog);
			String line;
			while((line = brLog.readLine())!=null)
			{
				log+=line+"\n";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return log;		
	}
	
	public int[] topScore(String mapName)
	{
		int[] topScore = new int[5];
		FileReader readLog = null;
		try {
			readLog = new FileReader(PATH + mapName+".txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader brLog = new BufferedReader(readLog);
		ArrayList scoreList = new ArrayList();
		String line;
		String[] temp;
		//String line2;
		try {
			line = brLog.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while((line = brLog.readLine())!=null)
			{
				//line = brLog.readLine();
				System.out.println(line);
				temp = line.split(",");
				scoreList.add(Integer.parseInt(temp[3]));
				System.out.println(temp[3]);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(scoreList);
		Collections.reverse(scoreList);
		for(int i = 0;i<topScore.length;i++)
		{
			topScore[i]=(int) scoreList.get(i);
		}
		try {
			brLog.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return topScore;		
	}

	public static void main(String[] Args) throws IOException{
		MapLog test = getMapLogObject();
		test.createLog("haha");
		test.saveMapLog("edit",0);
		test.saveMapLog("play",89);
		test.saveMapLog("play",78);
		test.saveMapLog("play",85);
		test.saveMapLog("play",12);
		test.saveMapLog("play",895);
		test.saveMapLog("play",563);
		
		test.createLog("hehe");
		test.saveMapLog("edit",0);
		test.saveMapLog("play",89);
		test.saveMapLog("play",78);
		test.saveMapLog("play",85);
		
		//String lint = " , ,Fri Nov 21 17:43:34 EST 2014 ,90";
		//String[] tem = lint.split(",");
		//int[] testscore = test.topScore("hehe");
		//for(int i=0;i<testscore.length;i++)
		//{
			//System.out.println(i+":"+testscore[i]);
		//}
		
	}
	
}
