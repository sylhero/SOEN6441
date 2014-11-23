package gamestate;

import gamepanel.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import log.GlobalLog;
import log.MapLog;
/**
 * this is the game over state
 * @author yulongsong
 *
 */
public class GameOverState extends GameState{
	/**
	 * constructor
	 * @param gsm The game state manager.
	 */
	
	private String mapName;
	private MapLog mapLog = MapLog.getMapLogObject();
	private ArrayList<Integer> topFive;
	public GameOverState(GameStateManager gsm){
		this.gsm = gsm;
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
	 * press quit button event
	 * @param e320, 430, 100, 50
	 */
	private void pressQuit(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(x >= 320 && x<= 420
				&& y >=430 && y<= 480){
			GlobalLog.addToGlobalLog("user press quit button."+"\n");
			System.exit(0);
		}
		
	}
	
//	private void pressRestart(MouseEvent e){
//		int x = e.getX();
//		int y = e.getY();
//		
//		if(x >= GamePanel.WIDTH/2-70 && x<= GamePanel.WIDTH/2+50
//				&& y >=GamePanel.HEIGHT/2+110 && y<= GamePanel.HEIGHT/2+160){
//			gsm.switchState(0);
//		}
//	}
	/**
	 * mouse press
	 */

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		pressQuit(e);
		//pressRestart(e);
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
	 * pause game
	 */

	@Override
	public boolean pause() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * pause game
	 */

	@Override
	public void pause(boolean result) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * resume game
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
		mapName = mapLog.getMapName();
		topFive = mapLog.getTopFive(mapName);
		
	}
	/**
	 * update
	 */

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * draw buttons
	 */
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(Color.RED);
		g.setFont(new Font("Arial",Font.BOLD,35));
		g.setColor(Color.RED);
		g.drawString("Game Over", 300, 150);
		g.setColor(Color.WHITE);
		
		String score1 = "NO.1 "+topFive.get(0);
		String score2 = "NO.2 "+topFive.get(1);
		String score3 = "NO.3 "+topFive.get(2);
		String score4 = "NO.4 "+topFive.get(3);
		String score5 = "NO.5 "+topFive.get(4);
		g.drawString(score1, 320, 240);
		g.drawString(score2, 320, 280);
		g.drawString(score3, 320, 320);
		g.drawString(score4, 320, 360);
		g.drawString(score5, 320, 400);
		g.setColor(Color.GREEN);
		g.fillRect(320, 430, 100, 50);
		g.setColor(Color.BLACK);
		String start = "QUIT";
		g.drawString(start, 330, 460);
		
		
	}
	

}
