package gamedata;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import tilemap.TileMap;
import towers.TowerBase;

public class GameData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2444615011686386224L;
	
	protected TileMap mapData;
	protected ArrayList<TowerBase> towerList;
	
	protected static final String PATH = "/resources/gamedata/";
	

	public GameData(TileMap map_data, ArrayList<TowerBase> tower_list){
		
		this.mapData = map_data;
		this.towerList = tower_list;
		
	}
	
	public GameData(){
		
	}
	
	public TileMap getMapData(){
		
		return this.mapData;
		
	}
		
	public ArrayList<TowerBase> getTowerList(){
		
		return this.towerList;
		
	}
	
	public boolean WriteGameData(){
		
		boolean isTrue = false;
		
		String file_name = "asdfjkladf.ser";
				
		try{
			
			FileOutputStream fos = new FileOutputStream(PATH+file_name);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();

			isTrue = true;
		} catch(Exception e){
			
			e.printStackTrace();
		}
		
		return isTrue;
		
	}
	
	public GameData ReadGameData(String file_name){
		
		GameData gd = new GameData();
		
		try{
			
			FileInputStream fis = new FileInputStream(PATH+file_name);
			ObjectInputStream ois = new ObjectInputStream(fis);
			gd = (GameData) ois.readObject();
			ois.close();
			
		} catch(Exception e){
			
			e.printStackTrace();
		}
		
		return gd;
	}
	
	
}
