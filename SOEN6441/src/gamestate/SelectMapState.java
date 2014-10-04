package gamestate;

import gamepanel.GamePanel;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import usefulfunctions.LoadImage;
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
	/**
	 * constructor
	 * @param gsm
	 */
	public SelectMapState(GameStateManager gsm){
		this.gsm = gsm;
		isPaused = false;

		myTitleFont = new Font("Arial",Font.BOLD,26);
		titleImage = LoadImage.loadImage("/images/selectmaptitle.png");
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
		g.drawImage(titleImage, GamePanel.WIDTH / 2 - 150, 40, 300, 60, null);
		g.drawImage(mapIcon, 360,400,80,80, null);
		
	
	}
	




	


	@Override
	public void resume() {
		 
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

	


	
	
}
