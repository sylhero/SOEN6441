package gamedata;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import currency.Coin;

import tilemap.Tile;
import towers.TowerBase;

public class GameData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2444615011686386224L;
	
	protected Tile[][] mapData;
	protected ArrayList<TowerBase> towerList;
	protected int currency;
	
	protected static final String PATH = System.getProperty("user.dir")+"/resources/gamedata/";
	

	public GameData(Tile[][] map_data, ArrayList<TowerBase> tower_list, Integer money){
		
		this.mapData = map_data;
		this.towerList = tower_list;
		this.currency = money;
		
	}
	
	
	public GameData(){
		
	}
	
	public void setMapData(Tile[][] map_data){
		
		this.mapData = map_data;
		
	}
	
	public Tile[][] getMapData(){
		
		System.out.println("I can get map data from .ser!!!!!!!!!!!!!!");
		
		for(int i=0; i<this.mapData.length; i++){
			for(int j=0; j<this.mapData[0].length; j++){
				System.out.println(mapData[i][j].getTileType());
			}
		}
		
		return this.mapData;
		
	}
	
	public void setTowerList(ArrayList<TowerBase> tower_list){
		
		this.towerList = tower_list;
	}
		
	public ArrayList<TowerBase> getTowerList(){
		
		
		return this.towerList;
		
	}
	
	public void setCoin(Integer cur){
		
		this.currency = cur;
	}
	
	public Integer getMoney(){
		System.out.println(this.currency);
		
		return this.currency;
	}
	
	
	
	public boolean WriteGameData(String mapName){
		
		boolean isTrue = false;
		
		String file_name = mapName+".ser";
				
		try{
			
			FileOutputStream fos = new FileOutputStream(PATH+file_name);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
			System.out.println("Money:"+this.currency);
			
			System.out.println("The size of tower list is"+this.towerList.size());

			isTrue = true;
		} catch(Exception e){
			
			e.printStackTrace();
		}
		
		return isTrue;
		
	}
	
	public void ReadGameData(String file_name){
		
		GameData gd = new GameData();
		
		try{
			
			FileInputStream fis = new FileInputStream(PATH+file_name);
			ObjectInputStream ois = new ObjectInputStream(fis);
			gd = (GameData) ois.readObject();
			this.mapData = gd.mapData;
			this.towerList = gd.towerList;
			this.currency = gd.currency;
			
			ois.close();
			
		} catch(Exception e){
			
			e.printStackTrace();
		}
		
	}
	
}
