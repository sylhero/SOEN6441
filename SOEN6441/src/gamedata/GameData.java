package gamedata;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import tilemap.TileMap;
import towers.TowerBase;

public class GameData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2444615011686386224L;
	
	protected TileMap mapData;
	protected ArrayList<TowerBase> towerList;
	

	public GameData(TileMap map_data, ArrayList<TowerBase> tower_list){
		
		this.mapData = map_data;
		this.towerList = tower_list;
		
	}
	
	public GameData(){
		
	}
	
	private TileMap getMapData(){
		
		return this.mapData;
		
	}
		
	private ArrayList<TowerBase> getTowerList(){
		
		return this.towerList;
		
	}
	
	public boolean WriteGameData(){
		
		boolean isTrue = false;
		
		try{
			
			FileOutputStream fos = new FileOutputStream("game.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();

			isTrue = true;
		} catch(Exception e){
			
			e.printStackTrace();
		}
		
		return isTrue;
		
	}
	
	public GameData ReadGameData(){
		
		GameData gd = new GameData();
		
		try{
			
			FileInputStream fis = new FileInputStream("game.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			gd = (GameData) ois.readObject();
			ois.close();
			
		} catch(Exception e){
			
			e.printStackTrace();
		}
		
		return gd;
	}
	
	
}
