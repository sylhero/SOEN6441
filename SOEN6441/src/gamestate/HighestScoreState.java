package gamestate;

import gamepanel.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class HighestScoreState extends GameState {
	
	public HighestScoreState(GameStateManager gsm){
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
	
	private void pressMapLog(MouseEvent e){
		
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
		g.setColor(Color.black);
		g.fillRect(0,0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(Color.RED);
		String mapName = "liyichen.xml";
		g.setFont(new Font("Arial",Font.BOLD,30));
		g.setColor(Color.RED);
		g.drawString(mapName, 300, 200);
		g.setColor(Color.WHITE);
		String score1 = "NO.1 "+"3000";
		String score2 = "NO.2 "+"2988";
		String score3 = "NO.3 "+"2480";
		String score4 = "NO.4 "+"2599";
		String score5 = "NO.5 "+"2698";
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
