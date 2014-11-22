package gamestate;

import gamepanel.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import log.GlobalLog;
import usefulfunctions.LoadImage;
/**
 * 
 * @author yulongsong
 * This is the first state of the game
 * This class extends the GameState 
 * 
 */
public class MenuState extends GameState{
	//background image
	private Image background;
	//title image
	private Image title;
	private Font myButtonFont;
	private boolean startHover;
	private boolean loadHover;
	private boolean createHover;
	private boolean exitHover;
	private boolean isPaused;
	public static final Color BEFORE = new Color(255, 198, 93);
	public static final Color AFTER  = new Color(123, 200, 164);
	
	//menu button
	int firstButtonX = (GamePanel.WIDTH-100)/2;
	int firstButtonY = (GamePanel.HEIGHT-25)/2+30;
	Rectangle startGameButton = new Rectangle(firstButtonX,
											firstButtonY,100,25);
	Rectangle loadGameButton  = new Rectangle(firstButtonX,
											firstButtonY+28,100,25);
	Rectangle createMapButton = new Rectangle(firstButtonX,
											firstButtonY+55,100,25);
	Rectangle exitButton      = new Rectangle(firstButtonX,
											firstButtonY+83,100,25);
	
	
	/**
	 * constructor
	 * @param gsm The game state manager.
	 */
	public MenuState(GameStateManager gsm){
		background   = LoadImage.loadImage("/images/menubackground.jpg");
		title        = LoadImage.loadImage("/images/title.png");
		myButtonFont = new Font("Arial",Font.BOLD,12);
		this.gsm = gsm;
	}
	

	/**
	 * not used yet
	 * 
	 */

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * draw buttons
	 */

	
	@Override
	public void draw(Graphics2D g) {
		//draw background
		
		g.drawImage(background,0,0,GamePanel.WIDTH,GamePanel.HEIGHT, null);
		
		//draw the title
		
		g.drawImage(title, GamePanel.WIDTH / 2 - 150, 40, 300, 60, null);
		//draw the start button
		//draw button background
		if(!startHover){
			g.setColor(BEFORE);	
		}else{
			g.setColor(AFTER);
		}
		//fill the background
		g.fillRect(startGameButton.x,
				startGameButton.y, 
				startGameButton.width,
				startGameButton.height);
		//set font 
		g.setFont(myButtonFont);
		//set fore color
		g.setColor(Color.BLACK);
		String startGameString = "Start Game";
		FontMetrics sgfm = g.getFontMetrics(myButtonFont);
		g.drawString(startGameString, 
				(startGameButton.width-sgfm.stringWidth(startGameString))/2+startGameButton.x, 
				startGameButton.height-sgfm.getHeight()/2+startGameButton.y);
		//draw the load button
		if(!loadHover){
			g.setColor(BEFORE);	
		}else{
			g.setColor(AFTER);
		}
		g.fillRect(loadGameButton.x,
				loadGameButton.y, 
				loadGameButton.width,
				loadGameButton.height);
		g.setColor(Color.BLACK);
		String loadGameString = "Load Game";
		FontMetrics lgfm = g.getFontMetrics(myButtonFont);
		g.drawString(loadGameString, 
				(loadGameButton.width-lgfm.stringWidth(loadGameString))/2+loadGameButton.x, 
				loadGameButton.height-lgfm.getHeight()/2+loadGameButton.y);
		//draw create map button
		if(!createHover){
			g.setColor(BEFORE);	
		}else{
			g.setColor(AFTER);
		}
		g.fillRect(createMapButton.x,
				createMapButton.y, 
				createMapButton.width,
				createMapButton.height);
		g.setFont(myButtonFont);
		g.setColor(Color.BLACK);
		String createMapString = "Create Map";
		FontMetrics cmfm = g.getFontMetrics(myButtonFont);
		g.drawString(createMapString, 
				(createMapButton.width-cmfm.stringWidth(createMapString))/2+createMapButton.x, 
				createMapButton.height-cmfm.getHeight()/2+createMapButton.y);
		//draw exit button
		if(!exitHover){
			g.setColor(BEFORE);	
		}else{
			g.setColor(AFTER);
		}
		g.fillRect(exitButton.x,
				exitButton.y, 
				exitButton.width,
				exitButton.height);
		g.setFont(myButtonFont);
		g.setColor(Color.BLACK);
		String exitString = "Exit Game";
		FontMetrics egfm = g.getFontMetrics(myButtonFont);
		g.drawString(exitString, 
				(exitButton.width-egfm.stringWidth(exitString))/2+exitButton.x, 
				exitButton.height-egfm.getHeight()/2+exitButton.y);
		
		
	}
	/**
	 * not used yet
	 */

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * mouse move event
	 */

	@Override
	public void mouseMoved(MouseEvent e) {
		int mouseX = e.getX() ;
		int mouseY = e.getY() ;
		if(mouseX >= startGameButton.x &&
				mouseX <= startGameButton.x + startGameButton.width &&
				mouseY >= startGameButton.y && 
				mouseY <= startGameButton.y +startGameButton.height){
			startHover = true;
			loadHover = false;
			createHover = false;
			exitHover = false;
			
		}
		else if(mouseX >= loadGameButton.x &&
				mouseX <= loadGameButton.x + loadGameButton.width &&
				mouseY >= loadGameButton.y && 
				mouseY <= loadGameButton.y +loadGameButton.height){
			startHover = false;
			loadHover = true;
			createHover = false;
			exitHover = false;
		}
		else if(mouseX >= createMapButton.x &&
				mouseX <= createMapButton.x + createMapButton.width &&
				mouseY >= createMapButton.y && 
				mouseY <= createMapButton.y +createMapButton.height){
			createHover = true;
			loadHover = false;
			startHover = false;
			exitHover = false;
		}
		else if(mouseX >= exitButton.x &&
				mouseX <= exitButton.x + exitButton.width &&
				mouseY >= exitButton.y && 
				mouseY <= exitButton.y +exitButton.height){
			exitHover = true;
			loadHover = false;
			createHover = false;
			startHover = false;
		}
		
	}
	/**
	 * mouse click event
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	/**
	 * mouse press event
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX() ;
		int mouseY = e.getY() ;
		if(mouseX >= startGameButton.x &&
				mouseX <= startGameButton.x + startGameButton.width &&
				mouseY >= startGameButton.y && 
				mouseY <= startGameButton.y +startGameButton.height){
			GlobalLog.addToGlobalLog("user clicks start game button\n");
			gsm.switchState(GameStateManager.SELECTMAP);
		}else if (mouseX >= createMapButton.x &&
				mouseX <= createMapButton.x + createMapButton.width &&
				mouseY >= createMapButton.y && 
				mouseY <= createMapButton.y +createMapButton.height){
			GlobalLog.addToGlobalLog("user clicks create map button\n");
			gsm.switchState(GameStateManager.CREATEMAP);
		}else if ( mouseX >= exitButton.x &&
				mouseX <= exitButton.x + exitButton.width &&
				mouseY >= exitButton.y && 
				mouseY <= exitButton.y +exitButton.height){
			GlobalLog.addToGlobalLog("user clicks exit the game\n");
			System.exit(0);
		}else if(mouseX >= loadGameButton.x &&
				mouseX <= loadGameButton.x + loadGameButton.width &&
				mouseY >= loadGameButton.y && 
				mouseY <= loadGameButton.y +loadGameButton.height){
			gsm.switchState(GameStateManager.LOADGAME);
			
		}
		
	}
	/**
	 * mouse release event
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * mouse enter event
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * mouse exit event
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * pause event
	 * @param result A boolean variable to indicate if it paused.
	 */

	@Override
	public void pause(boolean result) {
		this.isPaused = result;
		
		
	}
	/**
	 * pause
	 * 
	 */
	@Override
	public boolean pause(){
		return this.isPaused;
		
	}

	/**
	 * resume not implement yet
	 */

	@Override
	public void resume() {
		
		
	}

	
	
	
	

}
