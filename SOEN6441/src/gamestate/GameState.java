package gamestate;

import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
/**
 * 
 * @author yulongsong
 * abstract class for every game state to extend
 */
public abstract class GameState implements MouseMotionListener,MouseListener{
	protected GameStateManager gsm;
	public abstract boolean pause();
	public abstract void pause(boolean result);
	public abstract void resume();
	public abstract void init(String path);
	public abstract void update();
	public abstract void draw(Graphics2D g);

}
