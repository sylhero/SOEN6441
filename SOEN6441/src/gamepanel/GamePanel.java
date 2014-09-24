package gamepanel;

import gamestate.GameStateManager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable,MouseMotionListener,MouseListener{
	public static final int WIDTH  = 800;
	public static final int HEIGHT = 600;
	//public static final int SCALE  = 2;
	public static int DELAY  = 20;
	public static Thread gameThread;
	private Graphics2D graphicsObject;
	private Image drawingImage;
	private volatile boolean running = false;
	private volatile boolean gameOver = false;
	private volatile boolean isPaused = false;
	private GameStateManager gsm;
	//private JButton button;
	
	public GamePanel(){
		this.setPreferredSize(new Dimension(WIDTH ,HEIGHT));	
		setFocusable(true);
		requestFocus();
	}
	public void init(){
		running = true;
		drawingImage = new BufferedImage(WIDTH ,HEIGHT ,BufferedImage.TYPE_INT_RGB);
		graphicsObject = (Graphics2D) drawingImage.getGraphics();
		gsm = new GameStateManager();
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}
	public void addNotify(){
		super.addNotify();
		init();
		if(gameThread==null||!running){
			gameThread = new Thread(this);
			gameThread.start();
		}
	}
	
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long beforeTime, timeDiff, sleep;
		

        beforeTime = System.currentTimeMillis();
        while (true) {
        
        		checkPause();
            gameUpdate();
            gameRender();
            drawToScreen();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("Interrupted: " + e.getMessage());
            }

            beforeTime = System.currentTimeMillis();
        }
		
	}
	
	//pause game true or false
	public synchronized boolean pauseGame(){
		notify();
		return gsm.pauseGame();
		
		
	}
	public void resume(){
		gsm.resumeGame();
	}
	
	//check pause
	
	public void checkPause(){
		isPaused = this.pauseGame();
		if (isPaused) {
	             {
	               while (isPaused && running)
	            	   isPaused = this.pauseGame();
	               }
	           }
	        
		//System.out.println(isPaused);
	}
		
	//check gameover
	
	public void checkGameOver(){
		
	}
	
	public void gameUpdate(){
		// this is the first function being executed
		if(!gameOver && running){
			
			gsm.update();
			
		}
		
	}
	
	public void gameRender(){
		
		gsm.draw(graphicsObject);
		
	}
	public void drawToScreen(){
		Graphics g2 = getGraphics();
		g2.drawImage(drawingImage, 0, 0,
				null);
		g2.dispose();
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		gsm.mouseDragged(e);
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		gsm.mouseMoved(e);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		gsm.mouseClicked(e);
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		gsm.mousePressed(e);
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		gsm.mouseReleased(e);
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		gsm.mouseEntered(e);
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		gsm.mouseExited(e);
		
	}
	

}
