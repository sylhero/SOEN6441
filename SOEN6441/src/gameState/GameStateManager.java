package gameState;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


public class GameStateManager implements MouseMotionListener,MouseListener{

	private ArrayList<GameState> gameStates;
	private int currentState;
	public static final int MENUSTATE = 0;
	public static final int SELECTMAP = 1;
	public static final int GAMESTART = 2;
	public static final int LOADGAME  = 3;
	public static final int CREATEMAP = 4;
	public static final int EXIT		  = 5;
	
	public GameStateManager(){
		gameStates = new ArrayList<>();
		currentState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new SelectMapState(this));
		gameStates.add(new PlayState(this));
	}
	//switch to other stages
	public void switchState(int state, String mapPath){
		currentState = state;
		gameStates.get(currentState).init(mapPath);
		
	}
	public boolean pauseGame(){
		return gameStates.get(currentState).pause();
		
	}
	public void resumeGame(){
		gameStates.get(currentState).resume();
	}
	
	public void update(){
		gameStates.get(currentState).update();
	}
	public void draw(Graphics2D g){
		gameStates.get(currentState).draw(g);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		gameStates.get(currentState).mouseDragged(e);
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		gameStates.get(currentState).mouseMoved(e);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		gameStates.get(currentState).mouseClicked(e);
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		gameStates.get(currentState).mousePressed(e);
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		gameStates.get(currentState).mouseReleased(e);
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		gameStates.get(currentState).mouseEntered(e);
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		gameStates.get(currentState).mouseExited(e);
		
	}

	
	

}
