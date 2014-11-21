package gamedata;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import tilemap.Tile;
import towers.TowerBase;

public class GameData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2444615011686386224L;
	
	protected Tile[][] mapData;
	protected ArrayList<TowerBase> towerList;
	
	protected static final String PATH = "/resources/gamedata/";
	

	public GameData(Tile[][] map_data, ArrayList<TowerBase> tower_list){
		
		this.mapData = map_data;
		this.towerList = tower_list;
		
	}
	
	public GameData(){
		
	}
	
	public void setMapData(Tile[][] map_data){
		
		this.mapData = map_data;
		
	}
	
	public Tile[][] getMapData(){
		
		return this.mapData;
		
	}
	
	public void setTowerList(ArrayList<TowerBase> tower_list){
		
		this.towerList = tower_list;
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
