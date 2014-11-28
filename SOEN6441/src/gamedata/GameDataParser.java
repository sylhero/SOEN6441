package gamedata;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JTabbedPane;

import log.CollectiveLog;
import log.GlobalLog;
import log.WaveLog;

import currency.Coin;

import tilemap.Tile;
import towers.TowerBase;

/**
 * Parse game data class
 * @author hongruiguan
 *
 */
public class GameDataParser implements Serializable{
	
	/**
	 * automatically generate serial version ID for version control. 
	 */
	private static final long serialVersionUID = 2444615011686386224L;
	
	protected Tile[][] mapData;
	protected ArrayList<TowerBase> towerList;
	protected int currency;
	protected ArrayList<String> collectiveLog;
	protected ArrayList<String> globalLog;
	protected ArrayList<String> totalWaveLog;	
	protected static final String PATH = System.getProperty("user.dir")+"/resources/gamedata/";
	

	/**
	 * GameDataParser Constructor aims to get game information which will be saved as objects.
	 * @param map_data
	 * @param tower_list
	 * @param money
	 */
	public GameDataParser(Tile[][] map_data, ArrayList<TowerBase> tower_list, Integer money){
		
		this.mapData = map_data;
		this.towerList = tower_list;
		this.currency = money;
		this.collectiveLog = CollectiveLog.getObject().collectivelLog;
		this.globalLog = GlobalLog.getObject().globalLog;
		this.totalWaveLog = WaveLog.getObject().totalWaveLog;
				
		
	}
	public GameDataParser(){
		
	}


	/**
	 * Get map information from saved file.
	 * @return a two dimensional array for map data.
	 */
	public Tile[][] getMapData(){
		
		//System.out.println("I can get map data from .ser!!!!!!!!!!!!!!");
		
		//for(int i=0; i<this.mapData.length; i++){
			//for(int j=0; j<this.mapData[0].length; j++){
				//System.out.println(mapData[i][j].getTileType());
			//}
		//}
		
		return this.mapData;		
	}
	

	/**
	 * Get a tower list from saved file.	
	 * @return an array list for tower list.
	 */
	public ArrayList<TowerBase> getTowerList(){
		
		
		return this.towerList;
		
	}
	

	/**
	 * Get currency from saved file.
	 * @return currency
	 */
	public Integer getMoney(){
		//System.out.println(this.currency);
		
		return this.currency;
	}
	
	/**
	 * Get wave log from saved file.
	 * @return wave log
	 */
	public ArrayList<String> getTotalWaveLog(){
		
		return this.totalWaveLog;
	}
	
	/**
	 * Get global log from saved file.
	 * @return global log
	 */
	public ArrayList<String> getGlobalLog(){
		
		return this.globalLog;
		
	}
	
	/**
	 * Get collective log from saved file.
	 * @return collective log
	 */
	public ArrayList<String> getCollectiveLog(){
		
		//System.out.println("Loaded size of collective log is "+this.collectiveLog.collectivelLog.size());
		return this.collectiveLog;
		
		
	}
	
	
	/**
	 * Write game information as objects to a file
	 * @param mapName make it as a file name
	 * @return return true if write successfully.
	 */
	public boolean WriteGameData(String mapName){
		
		boolean isTrue = false;
		
		String file_name = mapName+".ser";
				
		try{
			
			FileOutputStream fos = new FileOutputStream(PATH+file_name);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
			//System.out.println("Money:"+this.currency);
			
			//System.out.println("The size of tower list is "+this.towerList.size());
			
			//System.out.println("The size of collective log is "+this.collectiveLog.collectivelLog.size());

			isTrue = true;
			
		} catch(Exception e){
			
			e.printStackTrace();
		}
		
		return isTrue;
		
	}
	
	/**
	 * Read game information
	 * @param file_name offer a file name to read a specific file
	 * @return return true if read file successfully.
	 */
	public boolean ReadGameData(String file_name){
		
		boolean isTrue = false;
		
		//GameDataParser gd = new GameDataParser();
		
		try{
			
			FileInputStream fis = new FileInputStream(PATH+file_name);
			ObjectInputStream ois = new ObjectInputStream(fis);
			GameDataParser gd = (GameDataParser) ois.readObject();
			this.mapData = gd.mapData;
			this.towerList = gd.towerList;
			this.currency = gd.currency;
			this.collectiveLog = gd.collectiveLog;
			//System.out.println(gd.collectiveLog.collectivelLog.size());
			this.globalLog = gd.globalLog;
			this.totalWaveLog = gd.totalWaveLog;
			
			ois.close();
			
			isTrue = true;
			
		} catch(Exception e){
			
			e.printStackTrace();
		}
		
		return isTrue;
		
	}
	
}
