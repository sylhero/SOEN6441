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

public class GameDataParser implements Serializable{
	
	/**
	 * automatically generate serial version ID for version control. For version control
	 */
	private static final long serialVersionUID = 2444615011686386224L;
	
	protected Tile[][] mapData;
	protected ArrayList<TowerBase> towerList;
	protected int currency;
	protected ArrayList<String> collectiveLog;
	protected ArrayList<String> globalLog;
	protected ArrayList<String> totalWaveLog;	
	protected static final String PATH = System.getProperty("user.dir")+"/resources/gamedata/";
	

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

	
	public void setMapData(Tile[][] map_data){
		
		this.mapData = map_data;
		
	}
	
	public Tile[][] getMapData(){
		
		//System.out.println("I can get map data from .ser!!!!!!!!!!!!!!");
		
		//for(int i=0; i<this.mapData.length; i++){
			//for(int j=0; j<this.mapData[0].length; j++){
				//System.out.println(mapData[i][j].getTileType());
			//}
		//}
		
		return this.mapData;		
	}
	
	
	
	public void setTowerList(ArrayList<TowerBase> tower_list){
		
		this.towerList = tower_list;
	}
		
	public ArrayList<TowerBase> getTowerList(){
		
		
		return this.towerList;
		
	}
	
	public void setMoney(Integer cur){
		
		this.currency = cur;
	}
	
	public Integer getMoney(){
		//System.out.println(this.currency);
		
		return this.currency;
	}
	

	public ArrayList<String> getTotalWaveLog(){
		
		return this.totalWaveLog;
	}
	
	public ArrayList<String> getGlobalLog(){
		
		return this.globalLog;
		
	}
	
	public ArrayList<String> getCollectiveLog(){
		
		//System.out.println("Loaded size of collective log is "+this.collectiveLog.collectivelLog.size());
		return this.collectiveLog;
		
		
	}
	
	
	
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