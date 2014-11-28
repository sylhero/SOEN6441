package gamestate;

import gamedata.GameDataParser;
import gamepanel.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import currency.Coin;
import log.CollectiveLog;
import log.GlobalLog;
import log.MapLog;
import log.WaveLog;
import tilemap.Tile;
import tilemap.TileMap;
import usefulfunctions.ValidateMap;
/**
 * this class is responsible for loading gamedata
 * @author yulongsong
 *
 */
public class LoadGameState extends GameState{
	private TileMap tileMap;
	private GameDataParser gameData;
	public LoadGameState(GameStateManager gsm){
		this.gsm = gsm;
		tileMap = tileMap.getTileMap();
		gameData = new GameDataParser();
		
	}
	//private GlobalLog globalLog;
	/**
	 * press load
	 * @param e
	 */
	private void loadPressed(MouseEvent e){
		
		int x = e.getX();

		int y = e.getY();

		System.out.println(x);

		System.out.println(y);
		if(x >= 300 && x <= 400 && y >=300 && y<=350 ){
			fileChooser();

		}
		
	}
	/**
	 * choose file
	 */
	private void fileChooser(){
	      
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("SER", "ser");
		chooser.setFileFilter(filter);
		String userPath = System.getProperty("user.dir")+"/resources/gamedata/";
		chooser.setCurrentDirectory(new File(userPath));
		int returnValue = chooser.showOpenDialog(null);
		if(returnValue == JFileChooser.APPROVE_OPTION){
			java.io.File file = chooser.getSelectedFile();
			Date date = new Date();
			GlobalLog.getObject().addToGlobalLog("user selects the game data: "+file.getName()+"\n",date);
			System.out.println(file.getAbsolutePath());
			//set the tilemap path
			String path = file.getAbsolutePath();
			//load the map first
			
			gameData.ReadGameData(file.getName());
			
			Tile[][] tempMap = gameData.getMapData();
			
			//TODO Fix lalalalalla !!!!!!!!
			
			PlayState.towerList = gameData.getTowerList();
			System.out.printf("tower list length %d\n",gameData.getTowerList().size());
			Coin.getCoinObject().setCurrency(gameData.getMoney());
			tileMap.loadGameDataMap(file.getName(), tempMap);
			//System.out.println(tileMap.getCellHeight());
			LinkedList<Point> tempPath = ValidateMap.getCorrectRoute(tempMap);
			tileMap.setCorrectPath(tempPath);
			CollectiveLog.collectivelLog = gameData.getCollectiveLog();
			GlobalLog.globalLog = gameData.getGlobalLog();
			WaveLog.batchCounter = gameData.getWaveBatchCounter();
			WaveLog.waveLog = gameData.getWaveLog();
			WaveLog.tpane = gameData.getWaveTpane();
			System.out.println(WaveLog.tpane == null);
			MapLog.getMapLogObject().getTopFive(file.getName().trim().split("\\.")[0]);
			gsm.switchState(GameStateManager.GAMESTART);
		} else if(returnValue == JFileChooser.CANCEL_OPTION){
			//cancel set none
			
		}
	       
	   }
	
	/**
	 * mouse drag
	 */

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * mouse move
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * mouse click
	 */

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * mouse press
	 */

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		loadPressed(e);
	}
	/**
	 * mouse release
	 */

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * mouse enter
	 */

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * mouse exit
	 */

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * pause
	 */

	@Override
	public boolean pause() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * pause
	 */

	@Override
	public void pause(boolean result) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * resume
	 */

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * init
	 */

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * update
	 */

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * draw
	 */

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
