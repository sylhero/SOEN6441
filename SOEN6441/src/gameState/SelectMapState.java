package gameState;

import entity.MonsterTest;
import gamepanel.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import tilemap.TileMap;
import usefulfunctions.LoadImage;
/*
 * this class is for testing purpose
 */
public class SelectMapState extends GameState{
	
	private boolean isPaused;
	private Font myTitleFont;
	private Image backGroundImage;
	private Image mapIcon;
	//private Image effect;
	
	public SelectMapState(GameStateManager gsm){
		this.gsm = gsm;
		isPaused = false;
		//tileMap = new TileMap();
		
		//monster = new MonsterTest[10];
		//initializeMonsters();

		myTitleFont = new Font("Arial",Font.BOLD,26);
		this.backGroundImage = LoadImage.loadImage("/images/selectmapbackground.jpg");
		this.mapIcon = LoadImage.loadImage("/images/mapicon.png");
		//this.effect  = LoadImage.loadImageIcon("/images/effect1.gif").getImage();
		
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
		int x = e.getX();
		int y = e.getY();
		System.out.println(x);
		System.out.println(y);
		if(x >= 360 && x <= 440 && y >=400 && y<=480 ){
			
			showFileChooserDemo();
			
		}
	}
	private void showFileChooserDemo(){
	      
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("MAP FILE", "map");
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(new File("/Users/yulongsong/Documents/workspace/SOEN6441/resources/gamemaps/"));
		int returnValue = chooser.showOpenDialog(null);
		if(returnValue == JFileChooser.APPROVE_OPTION){
			java.io.File file = chooser.getSelectedFile();
			System.out.println(file.getAbsolutePath());
			//set the tilemap path
			String path = file.getAbsolutePath();
			gsm.switchState(GameStateManager.GAMESTART,path);
		} else if(returnValue == JFileChooser.CANCEL_OPTION){
			//cancel set none
			
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
	public void init(String path) {
		
		
	}

	@Override
	public void update() {
		
	}

	
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(backGroundImage, 0,0,GamePanel.WIDTH,GamePanel.HEIGHT, null);
		g.setFont(myTitleFont);
		g.setColor(Color.RED);
		String title = "Select your Map";
		FontMetrics tfm = g.getFontMetrics();
		g.drawString(title, (GamePanel.WIDTH-tfm.stringWidth(title))/2, 60);
		g.drawImage(mapIcon, 360,400,80,80, null);
		//g.drawImage(effect,400,300,60,60, null);
	
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
