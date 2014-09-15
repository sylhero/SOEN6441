package gameState;

import entity.MonsterTest;
import gamepanel.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import tilemap.Tile;
import tilemap.TileMap;
import usefulfunctions.LoadImage;

public class SelectMapState extends GameState{
	
	private TileMap tileMap;
	
	//monster
	private MonsterTest monster;
	//pause
	private boolean isPaused;
	
	
	public SelectMapState(GameStateManager gsm){
		this.gsm = gsm;
		isPaused = false;
		tileMap = new TileMap("/Users/yulongsong/Documents/workspace/SOEN6441/resources/gamemaps/testMap1.map");
		monster = new MonsterTest(tileMap);
	}
	
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("this is X: "+e.getX());
		//System.out.println("this is Y: "+e.getY());
		//map[column][row] column comes first
		
        
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		System.out.println(x);
		System.out.println(y);
		if(x >= 0 && x <= 30 && y >=0 && y<=30 ){
			this.pause(true);
		}
		if(x > 30 && x <= 60 && y >=0 && y<=30 ){
			this.pause(false);
		}
		
		if(isPaused == false){
		tileMap.mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		monster.update();
		
	}

	
	@Override
	public void draw(Graphics2D g) {
		//clear screen
		tileMap.clearScreen(g);
		tileMap.draw(g);
		monster.draw(g);
		
		
		
	}
	




	@Override
	public void pause(boolean result) {
		this.isPaused = result;
		
	}
	@Override 
	public boolean pause(){
		return this.isPaused;
		
	}



	@Override
	public void resume() {
		 synchronized(this) {
		        this.notify();
		    }
		
	}

	


	
	
}
