package Main;

import gamepanel.GamePanel;

import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JFrame;

import log.GlobalLog;
/**
 * 
 * @author yulongsong
 * this is the game entrance
 */
public class StartTD {
	private JFrame window;
	public StartTD(){
		window = new JFrame("TD Game");
		window.requestFocus();
		//add game thread
		window.setResizable(false);
		window.add(new GamePanel());
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] Args){
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {  
            	Date date = new Date();
            	GlobalLog.getObject().addToGlobalLog("user starts the game\n",date);
                new StartTD();              
            }
        });
	}
}
