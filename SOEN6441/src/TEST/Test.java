package TEST;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Test extends JPanel
        implements Runnable {

    private final int B_WIDTH = 800;
    private final int B_HEIGHT = 600;
    private final int INITIAL_X = 0;
    private final int INITIAL_Y = 0;
    private final int DELAY = 25;
    private int[][] obstacles;

    private Image star;
    private Image star2;
    private Thread animator;
    private int x, y;

    public Test() {

        initBoard();
    }
    public void drawCenteredCircle(Graphics2D g, int x, int y, int r) {
    	  x = x-(r/2);
    	  y = y-(r/2);
    	  g.fillOval(x,y,r,r);
    	}

    private void loadImage() {
    	 ImageIcon ii = new ImageIcon(this.getClass().getResource(
                 "/images/animation.gif"));
        star = ii.getImage();
        ImageIcon iii = new ImageIcon(this.getClass().getResource(
                "/images/Fight.gif"));
        star2 = iii.getImage();
    }

    private void initBoard() {
    		obstacles = new int[10][10];
    		obstacles[1][2] = 1;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setDoubleBuffered(true);

        loadImage();

        x = INITIAL_X;
        y = INITIAL_Y;
    }

    @Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawStar(g);
    }

    private void drawStar(Graphics g) {

        g.drawImage(star, x, y, this);
        
        g.drawImage(star2,x+50,y+50,this);
        Graphics2D g2d = (Graphics2D)g;
        drawCenteredCircle(g2d,x,y+80,50);
        
        Toolkit.getDefaultToolkit().sync();
    }
//    private void drawMap(Graphics g){
//    	Graphics2D g2d = (Graphics2D)g;
//    	for(int i =0;i<obstacles.length;i++){
//    		for(int j = 0;j<obstacles.length;j++){
//    			if(obstacles[i][j]==1){
//    				g2d.drawRect (10, 10, 200, 200);  
//    			}
//    		}
//    	}
//    	g2d.drawRect (10, 10, 200, 200);  
//    	
//    }

    private void cycle() {

        x += 1;
       

        if (y > B_HEIGHT || x > B_WIDTH) {

            y = INITIAL_Y;
            x = INITIAL_X;
        }
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            cycle();
            repaint();

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
}