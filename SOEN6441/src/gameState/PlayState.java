package gameState;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
/*
 * not implement yet just ignore
 */
public class PlayState extends GameState{
	private boolean isPaused;
	public PlayState(GameStateManager gsm){
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
	public void pause(boolean result) {
		
		this.isPaused = result;
	}
	
	@Override
	
	public boolean pause(){
		return this.isPaused;
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
		
	}

	@Override
	public void resume() {
		 synchronized(this) {
		        this.notify();
		    }
		
	}

}
