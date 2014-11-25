package gamestate;

import gamepanel.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import log.MapLog;
/**
 * this class is responsible for displaying highest score
 * @author yulongsong
 *
 */
public class HighestScoreState extends GameState {
	private MapLog mapLog = MapLog.getMapLogObject();
	private String mapName;
	private ArrayList<Integer> topFive;
	
	public HighestScoreState(GameStateManager gsm){
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
	 * mouse press
	 * @param e
	 */
	
	private void pressStart(MouseEvent e){
		
		int tempX = e.getX();
		int tempY = e.getY();
		if(tempX >= 260 && tempX <= 360 &&
				tempY >= 430 && tempY <= 480 ){
			gsm.switchState(GameStateManager.GAMESTART);
		}
		
	}
	/**
	 * back press
	 * @param e
	 */
	private void pressBack(MouseEvent e){
		int tempX = e.getX();
		int tempY = e.getY();
		if(tempX >= 400 && tempX <= 500 &&
				tempY >= 430 && tempY <= 480 ){
			gsm.switchState(GameStateManager.SELECTMAP);
		}
		
	}
	/**
	 * mouse press
	 */

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		pressStart(e);
		pressBack(e);
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
		
		this.mapName = mapLog.getMapName();
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
	 * draw
	 */

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.black);
		g.fillRect(0,0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(Color.RED);
		g.setFont(new Font("Arial",Font.BOLD,35));
		g.setColor(Color.RED);
		g.drawString(mapName, 300, 150);
		g.setFont(new Font("Arial",Font.BOLD,30));
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
		g.fillRect(260, 430, 100, 50);
		g.setColor(Color.BLACK);
		String start = "START";
		g.drawString(start, 260, 460);
		g.setColor(Color.RED);
		g.fillRect(400, 430, 100, 50);
		g.setColor(Color.WHITE);
		String back = "BACK";
		g.drawString(back, 410, 460);
		
		
		
		
		
	}

}
