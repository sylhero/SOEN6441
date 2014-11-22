package gamestate;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
/**
 * 
 * @author yulongsong
 * This class is the controller of switching 
 * between states
 */

public class GameStateManager implements MouseMotionListener,MouseListener{

	private ArrayList<GameState> gameStates;
	private int currentState;
	public static final int MENUSTATE = 0;
	public static final int SELECTMAP = 1;
	public static final int GAMESTART = 2;
	public static final int CREATEMAP = 3;
	public static final int GAMEOVER  = 4;
	public static final int HIGHEST   = 5;
	public static final int LOADGAME  = 6;
	public static final int EXIT	  = 7;
	
	
	/**
	 * constructor add states
	 */
	public GameStateManager(){
		gameStates = new ArrayList<>();
		currentState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new SelectMapState(this));
		gameStates.add(new PlayState(this));
		gameStates.add(new CreateMapState(this));
		gameStates.add(new GameOverState(this));
		gameStates.add(new HighestScoreState(this));
		gameStates.add(new LoadGameState(this));
	}
	/**
	 * switch among different game states
	 * @param state The game state.
	 */
	public void switchState(int state){
		currentState = state;
		gameStates.get(currentState).init();
		
	}
	/**
	 * pause the game
	 * @return the corresponding game state's pause().
	 */
	public boolean pauseGame(){
		return gameStates.get(currentState).pause();
		
	}
	/**
	 * resume the game
	 */
	public void resumeGame(){
		gameStates.get(currentState).resume();
	}
	/**
	 * update animation
	 */
	public void update(){
		gameStates.get(currentState).update();
	}
	/**
	 * draw
	 * @param g The graphic object.
	 */
	public void draw(Graphics2D g){
		gameStates.get(currentState).draw(g);
	}
	/**
	 * mouse drag event
	 */

	@Override
	public void mouseDragged(MouseEvent e) {
		gameStates.get(currentState).mouseDragged(e);
		
	}
	/**
	 * mouse move event
	 */

	@Override
	public void mouseMoved(MouseEvent e) {
		gameStates.get(currentState).mouseMoved(e);
		
	}
	/**
	 * mouse click event
	 */

	@Override
	public void mouseClicked(MouseEvent e) {
		gameStates.get(currentState).mouseClicked(e);
		
	}
	/**
	 * mouse press event
	 */

	@Override
	public void mousePressed(MouseEvent e) {
		gameStates.get(currentState).mousePressed(e);
		
	}
	/**
	 * mouse release event
	 */

	@Override
	public void mouseReleased(MouseEvent e) {
		gameStates.get(currentState).mouseReleased(e);
		
	}
	/**
	 * mouse entered event
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		gameStates.get(currentState).mouseEntered(e);
		
	}
	/**
	 * mouse exit event
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		gameStates.get(currentState).mouseExited(e);
		
	}

	
	

}
