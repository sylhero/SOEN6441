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
/*
 * this class is for testing purpose
 */
public class SelectMapState extends GameState{
	
	private TileMap tileMap;
	
	//monster
	private MonsterTest[] monster;
	//pause
	private boolean isPaused;
	//control the speed
	private int a;
	
	public SelectMapState(GameStateManager gsm){
		this.gsm = gsm;
		isPaused = false;
		tileMap = new TileMap("/Users/yulongsong/Documents/workspace/SOEN6441/resources/gamemaps/testMap1.map");
		monster = new MonsterTest[10];
		initializeMonsters();
		
	}
	//initialize the monsters
	
	private void initializeMonsters(){
		for(int i = 0; i<10; i++){
			monster[i] = new MonsterTest(tileMap);
		}
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
		if(a > 50){
			monster[1].update();
		}
		monster[0].update();
		a++;
	}

	
	@Override
	public void draw(Graphics2D g) {
		//clear screen
		tileMap.clearScreen(g);
		tileMap.draw(g);
		monster[0].draw(g);
		monster[1].draw(g);
		
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
