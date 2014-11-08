package gamestate;

import gamepanel.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class GameOverState extends GameState{
	public GameOverState(GameStateManager gsm){
		this.gsm = gsm;
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
	private void pressQuit(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(x >= GamePanel.WIDTH/2-70 && x<= GamePanel.WIDTH/2+50
				&& y >=GamePanel.HEIGHT/2+40 && y<= GamePanel.HEIGHT/2+90){
			System.exit(0);
		}
		
	}
	private void pressRestart(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		
		if(x >= GamePanel.WIDTH/2-70 && x<= GamePanel.WIDTH/2+50
				&& y >=GamePanel.HEIGHT/2+110 && y<= GamePanel.HEIGHT/2+160){
			gsm.switchState(0);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		pressQuit(e);
		//pressRestart(e);
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
