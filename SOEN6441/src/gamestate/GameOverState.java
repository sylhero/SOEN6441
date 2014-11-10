package gamestate;

import gamepanel.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
/**
 * this is the game over state
 * @author yulongsong
 *
 */
public class GameOverState extends GameState{
	/**
	 * constructor
	 * @param gsm
	 */
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
	 * @param e
	 */
	private void pressQuit(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(x >= GamePanel.WIDTH/2-70 && x<= GamePanel.WIDTH/2+50
				&& y >=GamePanel.HEIGHT/2+40 && y<= GamePanel.HEIGHT/2+90){
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
		String GameOver = "Game Over";
		g.setFont(new Font("Arial",Font.BOLD,30));
		g.drawString(GameOver, GamePanel.WIDTH/2-80, GamePanel.HEIGHT/2);
		g.setColor(Color.WHITE);
		g.fillRect(GamePanel.WIDTH/2-70, GamePanel.HEIGHT/2+40, 120, 50);
		g.setColor(Color.RED);
		g.setFont(new Font("Arial",Font.BOLD,20));
		g.drawString("QUIT", GamePanel.WIDTH/2-40, GamePanel.HEIGHT/2+70);
//		g.setColor(Color.WHITE);
//		g.fillRect(GamePanel.WIDTH/2-70, GamePanel.HEIGHT/2+110, 120, 50);
//		g.setColor(Color.GREEN);
//		g.drawString("RESTART", GamePanel.WIDTH/2-60, GamePanel.HEIGHT/2+140);
	}
	

}
