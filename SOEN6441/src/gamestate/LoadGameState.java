package gamestate;

import gamepanel.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import log.GlobalLog;
import tilemap.Tile;
import usefulfunctions.ValidateMap;

public class LoadGameState extends GameState{
	public LoadGameState(GameStateManager gsm){
		this.gsm = gsm;
		
	}
	private void loadPressed(MouseEvent e){
//		if(){
//			
//		}
	}
	private void fileChooser(){
	      
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("SER", "ser");
		chooser.setFileFilter(filter);
		String userPath = System.getProperty("user.dir")+"/resources/gamedata/";
		chooser.setCurrentDirectory(new File(userPath));
		int returnValue = chooser.showOpenDialog(null);
		if(returnValue == JFileChooser.APPROVE_OPTION){
			java.io.File file = chooser.getSelectedFile();
			GlobalLog.addToGlobalLog("user selects the game data: "+file.getName()+"\n");
			System.out.println(file.getAbsolutePath());
			//set the tilemap path
			String path = file.getAbsolutePath();
			//load the map first
			//Tile[][] tempMap = tileMap.loadMap(path);
			//System.out.println(tileMap.getCellHeight());
			//tileMap.setCorrectPath(ValidateMap.getCorrectRoute(tempMap));
			gsm.switchState(GameStateManager.GAMESTART);
		} else if(returnValue == JFileChooser.CANCEL_OPTION){
			//cancel set none
			
		}
	       
	   }

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	public boolean pause() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pause(boolean result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(300, 300, 100, 50);
		String load = "Load";
		g.setColor(Color.WHITE);
		g.drawString(load, 310, 320);
		
		

		
	}

}
