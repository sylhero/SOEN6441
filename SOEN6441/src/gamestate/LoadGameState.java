package gamestate;

import gamedata.GameData;
import gamepanel.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import currency.Coin;
import log.GlobalLog;
import tilemap.Tile;
import tilemap.TileMap;
import usefulfunctions.ValidateMap;

public class LoadGameState extends GameState{
	private TileMap tileMap;
	private GameData gameData;
	public LoadGameState(GameStateManager gsm){
		this.gsm = gsm;
		tileMap = tileMap.getTileMap();
		gameData = new GameData();
		
	}
	private void loadPressed(MouseEvent e){
		
		int x = e.getX();

		int y = e.getY();

		System.out.println(x);

		System.out.println(y);
		if(x >= 300 && x <= 400 && y >=300 && y<=350 ){
			fileChooser();

		}
		
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
			
			Tile[][] tempMap = gameData.ReadGameData(file.getName()).getMapData();
			Coin.getCoinObject().setCurrency(gameData.ReadGameData(file.getName()).getMoney());
			tileMap.loadGameDataMap(file.getName(), tempMap);
			//System.out.println(tileMap.getCellHeight());
			tileMap.setCorrectPath(ValidateMap.getCorrectRoute(tempMap));
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
		loadPressed(e);
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
