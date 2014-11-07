package gamestate;

import gamepanel.GamePanel;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import tilemap.Tile;
import tilemap.TileMap;
import usefulfunctions.LoadImage;
import usefulfunctions.ValidateMap;
/**
 * 
 * @author yulongsong
 * this class is for selecting map
 */
public class SelectMapState extends GameState{
	private boolean isPaused;
	private Font myTitleFont;
	private Image backGroundImage;
	private Image titleImage;
	private Image mapIcon;
	private TileMap tileMap;
	/**
	 * constructor
	 * @param gsm
	 */
	public SelectMapState(GameStateManager gsm){
		this.gsm = gsm;
		isPaused = false;
		tileMap = TileMap.getTileMap();
		myTitleFont = new Font("Arial",Font.BOLD,26);
		titleImage = LoadImage.loadImage("/images/selectmaptitle.png");
		this.backGroundImage = LoadImage.loadImage("/images/selectmapbackground.jpg");
		this.mapIcon = LoadImage.loadImage("/images/mapicon.png");
		//this.effect  = LoadImage.loadImageIcon("/images/effect1.gif").getImage();
		
	}
	/**
	 * mouse drag event
	 * @param e
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * mouse move event
	 * @param e
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		
		
        
	}
	/**
	 * mouse click event
	 * @param e
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * mouse press event
	 * @param e
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println(x);
		System.out.println(y);
		if(x >= 360 && x <= 440 && y >=400 && y<=480 ){
			
			fileChooser();
			
		}
	}
	/**
	 * file chooser
	 */
	private void fileChooser(){
	      
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
		chooser.setFileFilter(filter);
		String userPath = System.getProperty("user.dir")+"/resources/gamemaps/";
		chooser.setCurrentDirectory(new File(userPath));
		int returnValue = chooser.showOpenDialog(null);
		if(returnValue == JFileChooser.APPROVE_OPTION){
			java.io.File file = chooser.getSelectedFile();
			System.out.println(file.getAbsolutePath());
			//set the tilemap path
			String path = file.getAbsolutePath();
			//load the map first
			Tile[][] tempMap = tileMap.loadMap(path);
			//System.out.println(tileMap.getCellHeight());
			tileMap.setCorrectPath(ValidateMap.getCorrectRoute(tempMap));
			gsm.switchState(GameStateManager.GAMESTART);
		} else if(returnValue == JFileChooser.CANCEL_OPTION){
			//cancel set none
			
		}
	       
	   }
	/**
	 * mouse release event
	 * @param e
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * mouse enter event
	 * @param e
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	/**
	 * mouse exit event
	 * @param e
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * initialize parameter
	 * 
	 */
	@Override
	public void init() {
		
		
	}
	/**
	 * update
	 * 
	 */
	@Override
	public void update() {
		
	}

	/**
	 * draw event
	 * @param g
	 */
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(backGroundImage, 0,0,GamePanel.WIDTH,GamePanel.HEIGHT, null);
		g.drawImage(titleImage, GamePanel.WIDTH / 2 - 150, 40, 300, 60, null);
		g.drawImage(mapIcon, 360,400,80,80, null);
		
	
	}
	




	

	/**
	 * resume
	 * 
	 */
	@Override
	public void resume() {
		 
	}
	/**
	 * pause
	 * 
	 */
	@Override
	public boolean pause() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * pause
	 * @param result
	 */
	@Override
	public void pause(boolean result) {
		// TODO Auto-generated method stub
		
	}

	


	
	
}
